package com.antoniofarias.investmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestmentappApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestmentappApplication.class, args);
	}

}
