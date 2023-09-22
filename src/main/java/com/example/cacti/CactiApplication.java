package com.example.cacti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CactiApplication {

	public static void main(String[] args) {
		Arrays.stream(SpringApplication.run(CactiApplication.class, args)
				.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
