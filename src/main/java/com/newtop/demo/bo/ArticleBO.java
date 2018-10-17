package com.newtop.demo.bo;

import lombok.Data;

/**
 * 接受前端页面文章参数   
 * @author pdl
 * @date 2018年10月17日  
 */ 
@Data
public class ArticleBO {
	private String aid;
	private String articleTitle;
	private String articleBody;
	private String createId;
	private String[] cids;
}
