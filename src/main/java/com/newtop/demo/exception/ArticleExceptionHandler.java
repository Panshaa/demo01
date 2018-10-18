package com.newtop.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.newtop.demo.exception.ArticleException;;

/**   
 * 文章类自定义异常处理，抛出出错的方法警告信息
 * @author pdl
 * @date 2018年10月18日  
 */ 
@ControllerAdvice
public class ArticleExceptionHandler {
	
	@ExceptionHandler(ArticleException.class)
	@ResponseBody
	public Map<String, Object> exceptionHandler(ArticleException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorMsg", e.getMessage());
		return map;
	}
}
