package com.newtop.demo.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 文章属性实体类
 * @author pdl
 * @date 2018年10月16日  
 */ 
@Data
public class Article {
	private String aid;
	private String articleTitle;
	private String articleBody;
	private Date createTime;
	private User user;
	private List<Category> categories;
}
