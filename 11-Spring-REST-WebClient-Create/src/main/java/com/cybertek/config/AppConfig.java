package com.cybertek.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	@Qualifier("webClient-Create")
	public WebClient webClient() {
		return WebClient.create();
	}

	@Bean
	@Qualifier("webClient-Create-Url")
	public WebClient webClient_Create_Url(@Value("${baseUrl}") String baseUrl) {
		return WebClient.create(baseUrl);
	}

	@Bean
	@Qualifier("webClient-Builder")
	public WebClient.Builder webClientBuilder(@Value("${baseUrl}") String baseUrl) {
		return WebClient.builder().baseUrl(baseUrl);

	}

	@Bean
	@Qualifier("webClient-Builder-Detail")
	public WebClient.Builder webClientBuilder_Detail(@Value("${baseUrl}") String baseUrl) {
		return WebClient.builder()
				.baseUrl(baseUrl)
				.defaultCookie("SessionId", "CyberTek14ABCSession")
				.defaultHeader("Version", "v1.0");

	}

}