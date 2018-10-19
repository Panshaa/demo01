package com.newtop.demo.entity;

import java.util.Date;

/**
 * 文章
 * @author pdl
 * @date 2018年10月16日  
 */ 
public class Article {
	//文章id
	private String aid;
	//文章标题
	private String articleTitle;
	//文章内容
	private String articleBody;
	//文章创建时间
	private Date createTime;
	//文章状态  草稿：0 ； 发布：1 ； 撤回：2
	private Integer statu;
	//草稿
	public static final Integer DRAFT = 0;
	//发布
	public static final Integer RELEASE = 1;
	//撤回
	public static final Integer WITHDRAW = 2;
	
	//是否逻辑删除 是：true ； 否：false
	private Boolean isDeleted;
	//创建人
	private String createId;
	
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleBody() {
		return articleBody;
	}
	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public static Integer getDraft() {
		return DRAFT;
	}
	public static Integer getRelease() {
		return RELEASE;
	}
	public static Integer getWithdraw() {
		return WITHDRAW;
	}
	
	
}
