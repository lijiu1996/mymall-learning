package com.lijiawei.practice.mymall.learning.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.web.DefaultSecurityFilterChain;

@SpringBootApplication
public class MymallLearningInitApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MymallLearningInitApplication.class, args);
	}

}
