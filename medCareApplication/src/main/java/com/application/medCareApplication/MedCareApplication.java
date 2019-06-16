package com.application.medCareApplication;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MedCareApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MedCareApplication.class).headless(false).web(WebApplicationType.NONE).run(args);
	}
}
