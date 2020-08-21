package com.ssafy.switon.dto;

public class BestLikeReturnDTO {
	
	int user_id;
	String user_name;
	int like_cnt;
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
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public BestLikeReturnDTO(int user_id, String user_name, int like_cnt) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.like_cnt = like_cnt;
	}
	@Override
	public String toString() {
		return "BestLikeReturnDTO [user_id=" + user_id + ", user_name=" + user_name + ", like_cnt=" + like_cnt + "]";
	}
	
	
	
}