package com.hospitalSystem.hospital_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HospitalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalSystemApplication.class, args);
	}

}
