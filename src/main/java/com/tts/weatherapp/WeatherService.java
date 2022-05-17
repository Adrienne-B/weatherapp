package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/*What this class is going to do is it is going to be utility class*/
/*that we use to make the API call....*/

/*It is very common when making Spring Boot applications to create*/
/*"Service" classes that essentially are just utility classes.*/

@Service
public class WeatherService {
	@Value("${api_key}")
	private String apiKey;

	/* This code asks Spring Boot to inject (automatically provide) */
	// UrlComponentsBuilder when the Service is build -- that is what the Autowired
	// means.

	// @Autowired
	// UrlComponentBuilder builder;

	public Response getForecast(String zipCode) {
		// The right way to build a url is to use a builtin java library.
		// String url = "https://api.openweathermap.org/data/2.5/weather";
		// url += "?zip" + zipCode + "&units=imperial&appid=" + apiKey;

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("https").host("api.openweathermap.org")
				.path("/data/2.5/weather").queryParam("zip", zipCode).queryParam("units", "imperial")
				.queryParam("appid", apiKey).build();

		String url = uriComponents.toUriString();
		System.out.println("-------------------" + url);

		// return null;

		// Any time you want to make a REST API call or just generally
		// any request over HTTP via Spring Boot you create a RestTemplate.
		RestTemplate restTemplate = new RestTemplate();

		// WE want to do an HTTP GET to get the page
		// LOOKUP JAVA EXCEPTION HANDLING
		Response response;
		try {
			response = restTemplate.getForObject(url, Response.class);
		} catch (HttpClientErrorException e) {
			response = new Response();
			response.setName("error");
		}
		return response;
	}
}
