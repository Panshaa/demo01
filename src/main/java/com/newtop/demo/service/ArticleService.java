package com.newtop.demo.service;

import com.newtop.demo.bo.ArticleBO;
import com.newtop.demo.entity.Article;
import com.newtop.demo.vo.ArticleVO;

public interface ArticleService {
	//根据ID查找某篇文章的数据
	ArticleVO findArticleById(String id);
	//新添一篇新文章
	void insertArticle(ArticleBO bo);
	//根据文章ID查找文章并修改
	void updateArticle(ArticleBO bo);
	//根据文章ID删除文章
	void deleteArticleById(String id);

}
