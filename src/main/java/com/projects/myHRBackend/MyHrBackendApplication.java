package com.projects.myHRBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MyHrBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHrBackendApplication.class, args);
	}

}
