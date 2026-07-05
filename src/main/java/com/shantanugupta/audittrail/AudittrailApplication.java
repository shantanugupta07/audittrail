package com.shantanugupta.audittrail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AudittrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudittrailApplication.class, args);
	}

}
