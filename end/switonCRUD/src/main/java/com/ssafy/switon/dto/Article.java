package com.ssafy.switon.dto;

import java.sql.Date;
import java.sql.Timestamp;

import io.swagger.annotations.ApiParam;

public class Article {
	
	@ApiParam(value = "게시글 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "게시글 제목", required = true)
	String title;
	@ApiParam(value = "게시글 내용", required = true)
	String content;
	@ApiParam(value = "작성자 (토큰에서 긁어옴)", required = false, hidden = true)
	int user_id;
	@ApiParam(value = "첨부파일", required = false, hidden = true)
	String file;
	@ApiParam(value = "게시판 아이디 (스터디id에서 얻어냄)", required = false, hidden = true)
	int board_id;
	@ApiParam(value = "게시글 작성시간", required = false, hidden = true)
	Timestamp created_at;
	@ApiParam(value = "게시글 수정시간", required = false, hidden = true)
	Timestamp updated_at;
	
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
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", user_id=" + user_id + ", file="
				+ file + ", board_id=" + board_id + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
	
}
