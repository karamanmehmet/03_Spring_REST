package com.cybertek.controller;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.cybertek.config.AppConfig;
import com.cybertek.exception.MyCustomException;
import com.cybertek.model.Product;

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

	private final String BASE_API = "/products/products_timeout";

	private static final Logger logger = LoggerFactory.getLogger(RESTClientController.class);

	@Autowired
	public RESTClientController(@Qualifier("webClient-Builder") WebClient webclient) {
		this.webclient = webclient;
	}

	@GetMapping("onErrorReturn")
	public Product onErrorReturn() {

		return webclient.get().uri(BASE_API).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new MyCustomException("API not found")))
				.bodyToMono(Product.class)
				.timeout(Duration.ofMillis(100))
				.onErrorReturn(new Product("Apple", 1.5f))
				.block();

	}

}
