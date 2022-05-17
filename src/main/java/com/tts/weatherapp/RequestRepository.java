package com.tts.weatherapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {
	/* We aren't going to define any additional query methods yet */

}
