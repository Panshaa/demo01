package com.newtop.demo.bo;

import lombok.Data;
/**
 * 接受前端页面文章参数   
 * @author pdl
 * @date 2018年10月17日  
 */ 
@Data
public class ArticleBO {
	//文章ID
	private String aid;
	//文章标题
	private String articleTitle;
	//文章内容
	private String articleBody;
	//创建人
	private String createId;
	//文章标签ID
	private String[] cids;
}
