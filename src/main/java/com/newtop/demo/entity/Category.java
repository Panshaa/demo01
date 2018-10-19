package com.newtop.demo.entity;

/**   
 * 文章分类
 * @author pdl
 * @date 2018年10月16日  
 */ 
public class Category {
	//分类id
	private String cid;
	//分类名
	private String cname;
	//父分类
	private String parentId;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
}
