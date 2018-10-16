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
	private String cid;
	private String cname;
	private List<Article> articles;
}
