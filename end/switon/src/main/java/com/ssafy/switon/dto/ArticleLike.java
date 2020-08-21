package com.ssafy.switon.dto;

public class ArticleLike {
	int id;
	int user_id;
	int article_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	
	@Override
	public String toString() {
		return "ArticleLike [id=" + id + ", user_id=" + user_id + ", article_id=" + article_id + "]";
	}
	
	
}
