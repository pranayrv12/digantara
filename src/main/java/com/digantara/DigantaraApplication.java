package com.digantara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DigantaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigantaraApplication.class, args);
	}
}
