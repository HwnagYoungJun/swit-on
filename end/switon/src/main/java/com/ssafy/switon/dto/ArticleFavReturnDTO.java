package com.ssafy.switon.dto;

import java.sql.Timestamp;

public class ArticleFavReturnDTO {

	int article_id;
	String article_title;
	String board_name;
	Timestamp created_at;
	Timestamp updated_at;
	
	UserSimpleDTO user;
	StudySimple study;
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getArticle_name() {
		return article_title;
	}
	public void setArticle_name(String article_title) {
		this.article_title = article_title;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
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
	public UserSimpleDTO getUser() {
		return user;
	}
	public void setUser(UserSimpleDTO user) {
		this.user = user;
	}
	public StudySimple getStudy() {
		return study;
	}
	public void setStudy(StudySimple study) {
		this.study = study;
	}
	public ArticleFavReturnDTO(int article_id, String article_title, String board_name, Timestamp created_at,
			Timestamp updated_at, UserSimpleDTO user, StudySimple study) {
		this.article_id = article_id;
		this.article_title = article_title;
		this.board_name = board_name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
		this.study = study;
	}
	
	public ArticleFavReturnDTO(Article article, UserSimpleDTO user, StudySimple study, String board_name) {
		this.article_id = article.getId();
		this.article_title = article.getTitle();
		this.board_name = board_name;
		this.created_at = article.getCreated_at();
		this.updated_at = article.getUpdated_at();
		this.user = user;
		this.study = study;
	}
	
	@Override
	public String toString() {
		return "ArticleFavReturnDTO [article_id=" + article_id + ", article_title=" + article_title + ", board_name="
				+ board_name + ", created_at=" + created_at + ", updated_at=" + updated_at + ", user=" + user
				+ ", study=" + study + "]";
	}
}
