package com.newtop.demo.vo;

import java.util.Date;
import java.util.List;

import com.newtop.demo.entity.User;

public class ArticleVO {
	// 文章id
	private String aid;
	// 文章标题
	private String articleTitle;
	// 文章内容
	private String articleBody;
	// 文章创建时间
	private Date createTime;
	// 文章状态 草稿：0 ； 发布：1 ； 撤回：2
	private Integer statu;
	// 是否逻辑删除 是：true ； 否：false
	private Boolean isDeleted;
	// 创建人
	private User createUser;
	// 文章分类
	private List<CategoryVO> categories;
	
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
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public List<CategoryVO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryVO> categories) {
		this.categories = categories;
	}
	
	
}
