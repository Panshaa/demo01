package com.newtop.demo.vo;

import java.util.Date;
import java.util.List;

import com.newtop.demo.entity.User;

import lombok.Data;

@Data
public class ArticleVO {
	// 文章id
	private String aid;
	// 文章标题
	private String articleTitle;
	// 文章内容
	private String articleBody;
	// 文章创建时间
	private Date createTime;
	// 文章状态 草稿：0 ； 发布：1 ； 撤回：2
	private Integer statu;
	// 是否逻辑删除 是：true ； 否：false
	private Boolean isDeleted;
	// 创建人
	private User createUser;
	// 文章分类
	private List<CategoryVO> categories;
}
