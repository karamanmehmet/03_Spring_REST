package com.cybertek.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.cybertek.exception.MyCustomException;
import com.cybertek.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@RestController
@RequestMapping("/client")
public class RESTClientController {

	private WebClient webclient;

	private final String BASE_API = "/products/products_retry";

	private static final Logger logger = LoggerFactory.getLogger(RESTClientController.class);

	@Autowired
	public RESTClientController(@Qualifier("webClient-Builder") WebClient webclient) {
		this.webclient = webclient;
	}

	@GetMapping("retry")
	public Flux<Product> retry() {


	
	   
		return  webclient.get().uri(BASE_API).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new MyCustomException("API not found")))
				.bodyToFlux(Product.class)
				.doOnError(error -> logger.error("Unexpected error has been encountered : ", error))
				.retry(1000);

	
	}
	
	@GetMapping("retrywhen")
	public Flux<Product> retrywhen() {
	   
		return webclient.get().uri(BASE_API).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new MyCustomException("API not found")))
				.bodyToFlux(Product.class)
				.doOnError(error -> logger.error("Unexpected error has been encountered : ", error))
				.retryWhen(Retry.backoff(5, Duration.ofMillis(250)));


	}
	

}
