package com.ssafy.switon.dto;

public class Like {
	int like_cnt;
	boolean liked;
	public Like(int like_cnt, boolean liked) {
		this.like_cnt = like_cnt;
		this.liked = liked;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	@Override
	public String toString() {
		return "Like [like_cnt=" + like_cnt + ", liked=" + liked + "]";
	}
}
