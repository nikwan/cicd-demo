package com.acoustic.users.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.acoustic.users.controllers.service.impl.UserControllerServiceImpl;
import com.acoustic.users.dao.TestDao;
import com.acoustic.users.dto.UsersRes;
import com.acoustic.users.entities.TestEntity;

@RestController
@RequestMapping({"/users"})
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Value("${test.flag}")
	String test;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	TestDao testDao;
	@Autowired
	UserControllerServiceImpl usersService;

	@PostConstruct
	void init() {
		this.log.info("****************************************");
		this.log.debug("test-flag:{} version:{}", this.test, "V9.0");
		this.log.debug("db_test:{}", testDao.findAll());
		this.log.info("****************************************");
	}

	@GetMapping({"/test/{name}"})
	public String test(@PathVariable String name) {
		this.log.debug("@@inside test with name:{}", name);
		return "hello " + name + " !";
	}

	@GetMapping({"/search/{q}"})
	public String search(@PathVariable String q) {
		this.log.debug("@@inside search with query param:{}", q);
		String url = "http://acoustic-search/search/search-with-get?q={q}";
		String result = "failed!";

		try {
			result = restTemplate.getForObject(url, String.class, new Object[]{q});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@GetMapping({"/testdb"})
	public List<TestEntity> testdb() {
		log.debug("@@inside test db");
		return testDao.findAll();
	}
	
	@GetMapping({"/savedb/{name}/{id}"})
	public UsersRes savedb(@PathVariable String name, @PathVariable int id) {
		log.debug("@@inside savedb");
		return usersService.saveDb(name, id);
	}
	
	@GetMapping(value = {"/users"})
	public List<UsersRes> users() {
		log.debug("@@inside users111");
		return Arrays.asList(new UsersRes(100, "accpeted !"));
	}
}