package com.acoustic.reg;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AcousticRegistryApplication {
	
	Logger log = LoggerFactory.getLogger(AcousticRegistryApplication.class);
	
	//@Value("${HOSTNAME}")
	String hostName;

	public static void main(String[] args) {
		SpringApplication.run(AcousticRegistryApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		log.info("build verison:{}", "V1.666666");
		log.info("hostname:{}", hostName);
	}

}
