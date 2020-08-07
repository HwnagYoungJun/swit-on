package com.ssafy.switon.dto;

public class Like {
	int like_cnt;
	boolean isLiked;
	
	public Like(int like_cnt, boolean isLiked) {
		this.like_cnt = like_cnt;
		this.isLiked = isLiked;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	
	@Override
	public String toString() {
		return "Like [like_cnt=" + like_cnt + ", isLiked=" + isLiked + "]";
	}
	
	
	
}
