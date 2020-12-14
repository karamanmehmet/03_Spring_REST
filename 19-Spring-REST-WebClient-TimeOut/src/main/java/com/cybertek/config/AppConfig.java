package com.cybertek.config;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpClient;


@Configuration
public class AppConfig {

	@Bean
	@Qualifier("webClient-Create")
	public WebClient webClient() {
		return WebClient.create();
	}


	@Bean
	@Qualifier("webClient-Builder-Detail")
	public WebClient.Builder webClientBuilder_Detail(@Value("${baseUrl}") String baseUrl) {
		return WebClient.builder()
				.baseUrl(baseUrl)
				.defaultCookie("SessionId", "CyberTek14ABCSession")
				.defaultHeader("Version", "v1.0");

	}
	

	@Bean
	@Qualifier("webClient-Create-GlobalTimeOut")
	public WebClient getWebClient()
	{

		final ConnectionProvider theTcpClientPool = ConnectionProvider.create("tcp-client-pool");
		final LoopResources theTcpClientLoopResources = LoopResources.create("tcp-client-loop");

		final TcpClient theTcpClient = TcpClient
			    .create(theTcpClientPool)
			    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 150)
			    .runOn(theTcpClientLoopResources)
			    .doOnConnected(theConnection -> {
			        theConnection.addHandlerLast(new ReadTimeoutHandler(150, TimeUnit.MILLISECONDS));
			        theConnection.addHandlerLast(new WriteTimeoutHandler(150, TimeUnit.MILLISECONDS));
			    });
		
		
		return WebClient.builder()
		        .clientConnector(new ReactorClientHttpConnector(HttpClient.from(theTcpClient)))
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .build();
	
	}
}