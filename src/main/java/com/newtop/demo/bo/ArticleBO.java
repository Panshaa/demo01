package com.newtop.demo.bo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 接受前端页面文章参数
 * 
 * @author pdl
 * @date 2018年10月17日
 */
@Data
public class ArticleBO {

	private String aid; // 文章ID
	@NotBlank
	private String articleTitle; // 文章标题
	private String articleBody; // 文章内容
	@NotBlank
	private String createId; // 创建人
	@NotBlank
	private String statu; // 文章状态 草稿或发布
	private String[] cids; // 文章标签ID
}
