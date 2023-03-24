package com.lijiawei.practice.mymall.learning.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.DefaultSecurityFilterChain;

@SpringBootApplication
public class MymallLearningInitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MymallLearningInitApplication.class, args);
	}

}
