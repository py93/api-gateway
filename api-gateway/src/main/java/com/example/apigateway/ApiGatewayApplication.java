package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(ApiGatewayApplication.URIConfiguration.class)
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, URIConfiguration uriConfiguration) {
		String httpUri = uriConfiguration.getHttpbin();
		return builder.routes()
				.route(p -> p
					.path("/get")
					.filters(f -> f.addRequestHeader("Hello","World"))
					.uri(httpUri))
				.build();
	}

	@ConfigurationProperties
	class URIConfiguration {
		private String httpbin = "http://httpbin.org.80";

		public String getHttpbin(){
			return httpbin;

		}

		public void setHttpbin(String httpbin)
		{
			this.httpbin=httpbin;
		}
	}
}

