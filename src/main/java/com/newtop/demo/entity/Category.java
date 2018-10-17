package com.newtop.demo.entity;

import java.util.List;

import lombok.Data;

/**   
 * 文章分类
 * @author pdl
 * @date 2018年10月16日  
 */ 
@Data
public class Category {
	//分类id
	private String cid;
	//分类名
	private String cname;
	//父分类
	private String parentId;
	//包含此分类的文章
	private List<Article> articles;
}
