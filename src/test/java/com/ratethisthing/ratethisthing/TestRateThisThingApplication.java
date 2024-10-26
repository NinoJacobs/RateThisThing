package com.ratethisthing.ratethisthing;

import org.springframework.boot.SpringApplication;

public class TestRateThisThingApplication {

	public static void main(String[] args) {
		SpringApplication.from(RateThisThingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
