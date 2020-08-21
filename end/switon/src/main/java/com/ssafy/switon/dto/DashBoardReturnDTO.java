package com.ssafy.switon.dto;

import java.util.List;

public class DashBoardReturnDTO {
	
	List<ArticleReturnDTO> notice;
	List<ArticleReturnDTO> qna;
	List<ArticleReturnDTO> repository;
	public List<ArticleReturnDTO> getNotice() {
		return notice;
	}
	public void setNotice(List<ArticleReturnDTO> notice) {
		this.notice = notice;
	}
	public List<ArticleReturnDTO> getQna() {
		return qna;
	}
	public void setQna(List<ArticleReturnDTO> qna) {
		this.qna = qna;
	}
	public List<ArticleReturnDTO> getRepository() {
		return repository;
	}
	public void setRepository(List<ArticleReturnDTO> repository) {
		this.repository = repository;
	}
	public DashBoardReturnDTO(List<ArticleReturnDTO> notice, List<ArticleReturnDTO> qna,
			List<ArticleReturnDTO> repository) {
		this.notice = notice;
		this.qna = qna;
		this.repository = repository;
	}
	@Override
	public String toString() {
		return "DashBoardReturnDTO [notice=" + notice + ", qna=" + qna + ", repository=" + repository + "]";
	}
}
