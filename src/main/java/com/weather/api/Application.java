package com.weather.api;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	@Bean
	public OkHttpClient getOkHttpClient() {
		return new OkHttpClient();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
