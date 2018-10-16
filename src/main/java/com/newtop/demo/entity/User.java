package com.newtop.demo.entity;


import lombok.Data;

/**   
 * 用户
 * @author pdl
 * @date 2018年10月16日  
 */ 
@Data
public class User {
	private String uid;
	private String uname;
	
	public User() {}
	
	public User(String uid, String uname) {
		super();
		this.uid = uid;
		this.uname = uname;
	}
	
}
