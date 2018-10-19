package com.newtop.demo.vo;

import java.util.List;

import com.newtop.demo.entity.Article;


public class CategoryVO {
	// 分类id
	private String cid;
	// 分类名
	private String cname;
	// 父分类
	private CategoryVO parentCategory;
	// 包含此分类的文章
	private List<Article> articles;
	
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
	public CategoryVO getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(CategoryVO parentCategory) {
		this.parentCategory = parentCategory;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
}
