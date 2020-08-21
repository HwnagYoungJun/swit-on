package com.ssafy.switon.dto;

public class Awake {
	int studyId;
	int boardId;
	int articleId;
	String type;
	
	public Awake(int studyId, int boardId, int articleId, String type) {
		super();
		this.studyId = studyId;
		this.boardId = boardId;
		this.articleId = articleId;
		this.type = type;
	}
	public Awake() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getStudyId() {
		return studyId;
	}
	public void setStudyId(int studyId) {
		this.studyId = studyId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Awake [studyId=" + studyId + ", boardId=" + boardId + ", articleId=" + articleId + ", type=" + type
				+ "]";
	}
	
	
	
}
