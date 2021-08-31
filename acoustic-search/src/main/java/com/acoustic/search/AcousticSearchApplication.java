package com.acoustic.search;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger log = LoggerFactory.getLogger(AcousticSearchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AcousticSearchApplication.class, args);
	}
	
	@PostConstruct
	void init() {
		log.info("****************************************");
		log.debug("version:{}", "V10.0");
		log.info("****************************************");
	}

}
