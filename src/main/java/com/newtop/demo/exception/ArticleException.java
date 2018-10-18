package com.newtop.demo.exception;

/**   
 * 文章自定义异常
 * @author pdl
 * @date 2018年10月18日  
 */ 
public class ArticleException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// 提供无参数的构造方法
	public ArticleException() {
	}

	// 提供一个有参数的构造方法，可自动生成
	public ArticleException(String message) {
		super(message);
	}
	
	
}
