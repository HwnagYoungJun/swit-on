package com.ssafy.switon.dto;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiParam;

public class Comment {
	
	@ApiParam(value = "댓글 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "댓글 내용", required = true)
	String content;
	@ApiParam(value = "작성자 id", required = false, hidden = true)
	int user_id;
	@ApiParam(value = "게시글 id", required = false, hidden = true)
	int article_id;
	@ApiParam(value = "작성일", required = false, hidden = true)
	Timestamp created_at;
	@ApiParam(value = "수정일", required = false, hidden = true)
	Timestamp updated_at;
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
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", user_id=" + user_id + ", article_id=" + article_id
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
