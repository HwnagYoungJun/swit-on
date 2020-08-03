package com.ssafy.switon.dto;

public class CommentLike {
	int id;
	int user_id;
	int comment_id;
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
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	@Override
	public String toString() {
		return "CommentLike [id=" + id + ", user_id=" + user_id + ", comment_id=" + comment_id + "]";
	}
	
	
}
