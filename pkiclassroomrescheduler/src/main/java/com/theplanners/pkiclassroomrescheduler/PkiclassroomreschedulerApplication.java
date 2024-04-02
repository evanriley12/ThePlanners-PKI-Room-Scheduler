package com.theplanners.pkiclassroomrescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.theplanners.pkiclassroomrescheduler.system.ReadClassroom;

@SpringBootApplication
public class PkiclassroomreschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PkiclassroomreschedulerApplication.class, args);
	}

}
