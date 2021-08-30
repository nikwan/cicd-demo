package com.acoustic.users.dto;

public class UsersRes{
	
	String msg;
	String err;
	int sts;
	
	public UsersRes(String msg, int sts) {
		super();
		this.msg = msg;
		this.sts = sts;
	}

	public UsersRes(int sts, String err) {
		super();
		this.err = err;
		this.sts = sts;
	}

	@Override
	public String toString() {
		return "UsersResponse [msg=" + msg + ", err=" + err + ", sts=" + sts
				+ "]";
	};
	
	public UsersRes(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public int getSts() {
		return sts;
	}

	public void setSts(int sts) {
		this.sts = sts;
	}

	
}
