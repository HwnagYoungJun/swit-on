package com.ssafy.switon.dto;

import java.util.List;

public class BestArticles {
	
	List<ArticleWithLikesDTO> bestQnas;
	List<ArticleWithLikesDTO> bestRepos;
	public List<ArticleWithLikesDTO> getBestQnas() {
		return bestQnas;
	}
	public void setBestQnas(List<ArticleWithLikesDTO> bestQnas) {
		this.bestQnas = bestQnas;
	}
	public List<ArticleWithLikesDTO> getBestRepos() {
		return bestRepos;
	}
	public void setBestRepos(List<ArticleWithLikesDTO> bestRepos) {
		this.bestRepos = bestRepos;
	}
	@Override
	public String toString() {
		return "BestArticles [bestQnas=" + bestQnas + ", bestRepos=" + bestRepos + "]";
	}
	public BestArticles(List<ArticleWithLikesDTO> bestQnas, List<ArticleWithLikesDTO> bestRepos) {
		this.bestQnas = bestQnas;
		this.bestRepos = bestRepos;
	}
}
