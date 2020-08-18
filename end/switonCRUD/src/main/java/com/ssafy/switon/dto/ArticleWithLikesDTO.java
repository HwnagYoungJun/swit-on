package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class ArticleWithLikesDTO {
	int id;
	String title;
	String content;
	int user_id;
	String user_name;
	String file;
	int board_type;
	String board_name;
	Timestamp created_at;
	Timestamp updated_at;
	int likes;

	
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "ArticleWithLikesDTO [id=" + id + ", title=" + title + ", content=" + content + ", user_id=" + user_id
				+ ", file=" + file + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", likes=" + likes + ", user_name=" + user_name + ", board_name=" + board_name + "]";
	}
}
