package com.newtop.demo.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newtop.demo.bo.ArticleBO;
import com.newtop.demo.entity.Article;
import com.newtop.demo.entity.Category;
import com.newtop.demo.entity.User;
import com.newtop.demo.service.ArticleService;
/**   
 * @author pdl
 * @date 2018年10月16日  
 */ 
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 通过ID查找文章
	 */
	@Override
	@Transactional(readOnly = true)
	public Article findArticleById(String id) {
		
		String sql = "select * from article where aid = ?";
		Article article = jdbcTemplate.query(sql,new RowMapper<Article>() {

			@Override
			public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
				Article article = new Article();
				article.setAid(rs.getString("aid"));
				article.setArticleTitle(rs.getString("articleTitle"));
				article.setArticleBody(rs.getString("articleBody"));
				article.setCreateTime(rs.getDate("createTime"));
				
				//根据用户ID查找文章作者
				String sql = "select * from user where uid = ?";
				User user = jdbcTemplate.query(sql, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new User(rs.getString("uid"),rs.getString("uname"));
					}
					
				},rs.getString("createId")).get(0);
				
				article.setUser(user);
				return article;
			}
			
		},id).get(0);
		//判断是否存在这个ID对应的文章，不存在则返回NULL
		if(article == null) return null;
		
		//在中间表查找文章分类信息
		String sql2 = "select * from category_article ca,category c,article a where a.aid = ca.aid and c.cid = ca.cid and ca.aid = ?";
		List<Category> list = jdbcTemplate.query(sql2, new RowMapper<Category>() {

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category =  new Category();
				category.setCid(rs.getString("cid"));
				category.setCname(rs.getString("cname"));
				return category;
			}
			
		},id);
		
		article.setCategories(list);	
		
		return article;
	}
	
	/**
	 * 新添一篇文章
	 */
	@Override
	public void insertArticle(ArticleBO bo) {
		//使用uuid为文章添加主键id
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String sql = "insert into article values(?,?,?,?,?)";
		jdbcTemplate.update(sql,uuid,bo.getArticleTitle(),bo.getArticleBody(),new Date(),bo.getCreateId());
		
		//为文章添加分类
		for (String cid : bo.getCids()) {
			String c_a_uuid = UUID.randomUUID().toString().replace("-", "");
			String c_a_sql = "insert into category_article values(?,?,?)";
			jdbcTemplate.update(c_a_sql,c_a_uuid,uuid,cid);
		}
	}
	
	/**
	 * 更新文章信息
	 */
	@Override
	public void updateArticle(ArticleBO bo) {
		String sql1 = "update article set articleTitle = ?,articleBody = ? where aid = ?";
		jdbcTemplate.update(sql1,bo.getArticleTitle(),bo.getArticleBody(),bo.getAid());
		//删除原来的分类
		String sql2 = "delete from category_article where aid = ?";
		jdbcTemplate.update(sql2,bo.getAid());
		
		//为文章添加改变更新后的分类
		for (String cid : bo.getCids()) {
			String c_a_uuid = UUID.randomUUID().toString().replace("-", "");
			String c_a_sql = "insert into category_article values(?,?,?)";
			jdbcTemplate.update(c_a_sql,c_a_uuid,bo.getAid(),cid);
		}
	}
	
	/**
	 * 根据文章ID删除文章及相关中间表信息
	 */
	@Override
	public void deleteArticleById(String id) {
		String sql1 = "delete from category_article where aid = ?";
		jdbcTemplate.update(sql1,id);
		String sql2 = "delete from article where aid = ?";
		jdbcTemplate.update(sql2,id);
	}

}
