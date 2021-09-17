package com.docker.test.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.docker.test.controller.dto.AspRes;

@RestController
public class TestController {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(TestController.class);
	
	@Value("${test.flag}")
	String test;
	
	@PostConstruct
	void init() {
		log.debug("test-flag:{}", test);
	}
	
	@GetMapping("/")
	public String defaultTest() {
		log.debug("@@inside defaultTest");
		return "version 4.0 ";
	}
	
	@GetMapping("/test/{name}")
	public String test(@PathVariable String name) {
		log.debug("@@inside test with name:{}", name);
		return "hello " + name + " !";
	}
	
	@GetMapping("/asptest")
	public AspRes asptest() {
		log.debug("@@inside asptest");
		return new AspRes("accepted!");
	}

}
