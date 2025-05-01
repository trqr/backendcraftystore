package com.beboustore.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class BeboustoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeboustoreApplication.class, args);
	}

}
	