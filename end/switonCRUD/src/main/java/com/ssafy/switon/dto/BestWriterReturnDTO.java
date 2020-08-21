package com.ssafy.switon.dto;

public class BestWriterReturnDTO {
	
	int user_id;
	String user_name;
	int article_cnt;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getArticle_cnt() {
		return article_cnt;
	}
	public void setArticle_cnt(int article_cnt) {
		this.article_cnt = article_cnt;
	}
	@Override
	public String toString() {
		return "BestWriterReturnDTO [user_id=" + user_id + ", user_name=" + user_name + ", article_cnt=" + article_cnt
				+ "]";
	}
	public BestWriterReturnDTO(int user_id, String user_name, int article_cnt) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.article_cnt = article_cnt;
	}
	
	

}
