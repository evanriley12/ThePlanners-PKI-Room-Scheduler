package com.theplanners.pkiclassroomrescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.theplanners.pkiclassroomrescheduler")
public class PkiclassroomreschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PkiclassroomreschedulerApplication.class, args);
	}

}
