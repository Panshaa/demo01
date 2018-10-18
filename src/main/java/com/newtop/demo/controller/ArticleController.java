package com.newtop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newtop.demo.bo.ArticleBO;
import com.newtop.demo.service.ArticleService;
import com.newtop.demo.vo.ArticleVO;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	/**  
	 * 根据ID查找某篇文章
	 * @return 
	 */	
	@GetMapping(value = "query/{id}")
	public ArticleVO findArticleById(@PathVariable String id) {
		ArticleVO vo = articleService.findArticleById(id);
		return vo;
	}
	
	/**  
	 * 新添一篇文章
	 * @param bo 
	 */ 
	@PostMapping(value = "insert")
	public void insertArticle(@Validated ArticleBO bo) {
		articleService.insertArticle(bo);
	}
	
	/**  
	 * 根据文章ID查找文章并修改
	 * @param bo 
	 */ 
	@PutMapping(value = "update")
	public void updateArticle(@Validated ArticleBO bo) {
		articleService.updateArticle(bo);
	}
	
	/**  
	 * 根据文章ID物理删除文章
	 * @param id 
	 */ 
	@DeleteMapping(value = "delete/{id}")
	public void deleteArticeById(@PathVariable String id) {
		articleService.deleteArticleById(id);
	}
	
	
}
