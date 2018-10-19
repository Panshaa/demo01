package com.newtop.demo.bo;

import javax.validation.constraints.NotBlank;
/**
 * 接受前端页面文章参数
 * 
 * @author pdl
 * @date 2018年10月17日
 */
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
	
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleBody() {
		return articleBody;
	}
	public void setArticleBody(String articleBody) {
		this.articleBody = articleBody;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String[] getCids() {
		return cids;
	}
	public void setCids(String[] cids) {
		this.cids = cids;
	}
	
	
}
