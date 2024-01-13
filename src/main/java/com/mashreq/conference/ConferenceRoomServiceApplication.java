package com.mashreq.conference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.mashreq.conference") // Adjust the package path
@ComponentScan(basePackages = {"com.mashreq.conference","com.mashreq.conference.persistence.entity"}) // Adjust the package path
public class ConferenceRoomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceRoomServiceApplication.class, args);
	}
}
