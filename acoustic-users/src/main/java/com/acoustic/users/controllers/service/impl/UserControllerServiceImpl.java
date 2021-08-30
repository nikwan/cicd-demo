package com.acoustic.users.controllers.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acoustic.users.dao.TestDao;
import com.acoustic.users.dto.UsersRes;
import com.acoustic.users.entities.TestEntity;

@Service("usersService")
public class UserControllerServiceImpl {
	
	Logger log = LoggerFactory.getLogger(UserControllerServiceImpl.class);

	RestTemplate restTemplate;

	@Autowired
	TestDao testDao;
	
	public UsersRes saveDb(String name, int id) {
		log.info("@inside savedb");
		try {
			TestEntity e = new TestEntity();
			e.setId(id);e.setName(name);
			TestEntity t = testDao.save(e);
			return new UsersRes("user saved in db with id:" + t.getId(), 1);
		} catch (Exception e) {
			log.error("err_in_savedb:{}", e);
			return new UsersRes(0, "Error: unable to save user!");
		}
	}

}
