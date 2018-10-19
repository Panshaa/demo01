package com.newtop.demo.entity;

import java.io.Serializable;

/**   
 * 用户
 * @author pdl
 * @date 2018年10月16日  
 */ 

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String uname;
	
	public User() {
		super();
	}

	public User(String uid, String uname) {
		super();
		this.uid = uid;
		this.uname = uname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
}
