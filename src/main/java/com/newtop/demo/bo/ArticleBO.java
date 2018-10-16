package com.newtop.demo.bo;

import lombok.Data;

@Data
public class ArticleBO {
	private String aid;
	private String articleTitle;
	private String articleBody;
	private String createId;
	private String[] cids;
}
