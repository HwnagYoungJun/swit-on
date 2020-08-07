package com.ssafy.switon.dto;

import java.sql.Date;

public class Comment {
	int id;
	String content;
	int user_id;
	int article_id;
	Date created_at;
	Date updated_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", user_id=" + user_id + ", article_id=" + article_id
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
