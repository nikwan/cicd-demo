package com.acoustic.gateway.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	
	@RequestMapping(value = "/searchServiceFB")
	public Fall emailServiceFallBack() throws InterruptedException {
		return new Fall("SEARCH SERVICE DOWN", -1, "SEARCH SERVICE");
	}
	
	@RequestMapping(value = "/usersServiceFB")
	public Fall edastakhatServiceFallBack(Throwable t) throws InterruptedException {
		return new Fall("USERS SERVICE DOWN:", -1, "USERS SERVICE");
	}
	
	
	
	@RequestMapping(value = "/notFoundFallBack")
	public Fall notFoundFallBack() throws InterruptedException {
		return new Fall("Requested Url Not Found For Edastakhat", -1, "EDASTAKHAT");
	}
	
	static class Fall{
		String msg;
		int sts;
		String sid;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public int getSts() {
			return sts;
		}
		public void setSts(int sts) {
			this.sts = sts;
		}
		public String getSid() {
			return sid;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public Fall(String msg, int sts, String sid) {
			super();
			this.msg = msg;
			this.sts = sts;
			this.sid = sid;
		}
		
		
		
	}

}

