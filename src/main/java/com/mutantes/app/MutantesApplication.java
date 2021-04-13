package com.mutantes.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantesApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(MutantesApplication.class, args);
		}catch(Exception e) {
		}
	}

}
