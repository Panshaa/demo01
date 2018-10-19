package com.newtop.demo.vo;

import java.util.List;

import com.newtop.demo.entity.Article;

import lombok.Data;

@Data
public class CategoryVO {
	// 分类id
	private String cid;
	// 分类名
	private String cname;
	// 父分类
	private CategoryVO parentCategory;
	// 包含此分类的文章
	private List<Article> articles;
}
