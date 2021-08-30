package com.acoustic.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author nikhil.wankhade
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.acoustic.search.dao"})
@EntityScan(basePackages = {"com.acoustic.search.entities"})
@ComponentScan(basePackages = { "com.acoustic.search", "com.acoustic.search.controller"})
public class AcousticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcousticSearchApplication.class, args);
	}

}
