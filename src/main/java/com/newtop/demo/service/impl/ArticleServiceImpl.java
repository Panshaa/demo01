package com.newtop.demo.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newtop.demo.bo.ArticleBO;
import com.newtop.demo.entity.User;
import com.newtop.demo.exception.ArticleException;
import com.newtop.demo.service.ArticleService;
import com.newtop.demo.vo.ArticleVO;
import com.newtop.demo.vo.CategoryVO;

/**
 * @author pdl
 * @date 2018年10月16日
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	private static Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 通过ID查找文章
	 */
	@Override
	@Transactional(readOnly = true)
	public ArticleVO findArticleById(String id) {

		String sql = "select * from article where aid = ? and isDeleted = 0";
		List<ArticleVO> list = jdbcTemplate.query(sql, new RowMapper<ArticleVO>() {
			
			@Override
			public ArticleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
								
				ArticleVO vo = new ArticleVO();
				vo.setAid(rs.getString("aid"));
				vo.setArticleTitle(rs.getString("articleTitle"));
				vo.setArticleBody(rs.getString("articleBody"));
				vo.setCreateTime(rs.getTimestamp("createTime"));
				vo.setStatu(rs.getInt("statu"));
				vo.setIsDeleted(rs.getInt("isDeleted") == 1 ? true : false);
				// 根据用户ID查找文章作者
				String sql = "select * from user where uid = ?";
				User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new User(rs.getString("uid"), rs.getString("uname"));
					}

				}, rs.getString("createId"));

				vo.setCreateUser(user);
				return vo;
			}

		}, id);

		// 判断是否存在这个ID对应的文章，不存在则抛出自定义异常
		if (list == null || list.size()<1) {
			log.info("--------查找文章失败--------");
			throw new ArticleException("不存在此文章，查找失败");
		}

		// 在中间表查找文章分类信息
		String sql2 = "select c.* from category_article ca,category c,article a where a.aid = ca.aid and c.cid = ca.cid and ca.aid = ?";
		List<CategoryVO> list2 = jdbcTemplate.query(sql2, new RowMapper<CategoryVO>() {

			@Override
			public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CategoryVO categoryVO = new CategoryVO();
				categoryVO.setCid(rs.getString("cid"));
				categoryVO.setCname(rs.getString("cname"));

				CategoryVO parentVO = findParentCategory(rs.getString("parentId"));

				categoryVO.setParentCategory(parentVO);
				return categoryVO;
			}

		}, id);

		list.get(0).setCategories(list2);

		log.info("--------查找文章成功--------");

		return list.get(0);
	}

	/**
	 * 通过父分类ID使用递归来查找多层级父分类
	 */
	private CategoryVO findParentCategory(String parentId) {

		String sql = "select * from category where cid = ?";
		List<CategoryVO> list = jdbcTemplate.query(sql, new RowMapper<CategoryVO>() {

			@Override
			public CategoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CategoryVO vo = new CategoryVO();
				vo.setCid(rs.getString("cid"));
				vo.setCname(rs.getString("cname"));

				CategoryVO parentVO = findParentCategory(rs.getString("parentId"));
				vo.setParentCategory(parentVO);
				return vo;
			}

		}, parentId);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 新添一篇文章
	 */
	@Override
	public void insertArticle(ArticleBO bo) {
		// 使用uuid为文章添加主键id
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String sql = "insert into article values(?,?,?,?,?,?,0)";
		int i = jdbcTemplate.update(sql, uuid, bo.getArticleTitle(), bo.getArticleBody(), new Date(), bo.getCreateId(),
				bo.getStatu());

		if (i < 1) {
			log.info("--------新添文章失败--------");
			throw new ArticleException("文章保存失败！");
		}

		// 为文章添加分类
		for (String cid : bo.getCids()) {
			String c_a_uuid = UUID.randomUUID().toString().replace("-", "");
			String c_a_sql = "insert into category_article values(?,?,?)";
			i = jdbcTemplate.update(c_a_sql, c_a_uuid, uuid, cid);
			if (i < 1) {
				log.info("--------文章添加分类失败--------");
				throw new ArticleException("文章保存失败！");
			}
		}

		log.info("--------新添文章成功--------");
	}

	/**
	 * 更新文章信息
	 */
	@Override
	public void updateArticle(ArticleBO bo) {
		String sql1 = "update article set articleTitle = ?,articleBody = ? where aid = ?";
		int i = jdbcTemplate.update(sql1, bo.getArticleTitle(), bo.getArticleBody(), bo.getAid());
		if (i < 1) {
			log.info("--------文章更新失败--------");
			throw new ArticleException("文章更新失败！");
		}
		// 删除原来的分类
		String sql2 = "delete from category_article where aid = ?";
		i = jdbcTemplate.update(sql2, bo.getAid());
		if (i < 1) {
			log.info("--------更新文章删除原先分类失败--------");
			throw new ArticleException("文章更新失败！");
		}

		// 为文章添加改变更新后的分类
		for (String cid : bo.getCids()) {
			String c_a_uuid = UUID.randomUUID().toString().replace("-", "");
			String c_a_sql = "insert into category_article values(?,?,?)";
			i = jdbcTemplate.update(c_a_sql, c_a_uuid, bo.getAid(), cid);
			if (i < 1) {
				log.info("--------更新文章重新添加分类失败--------");
				throw new ArticleException("文章更新失败！");
			}
		}

		log.info("--------文章更新成功--------");
	}

	/**
	 * 根据文章ID删除文章及相关中间表信息
	 */
	@Override
	public void deleteArticleById(String id) {
		String sql1 = "delete from category_article where aid = ?";
		int i = jdbcTemplate.update(sql1, id);
		if (i < 1) {
			log.info("--------删除文章分类失败--------");
			throw new ArticleException("文章删除失败！");
		}
		String sql2 = "delete from article where aid = ?";
		i = jdbcTemplate.update(sql2, id);
		if (i < 1) {
			log.info("--------删除文章失败--------");
			throw new ArticleException("文章删除失败！");
		}
		log.info("--------文章删除成功--------");
	}

}
