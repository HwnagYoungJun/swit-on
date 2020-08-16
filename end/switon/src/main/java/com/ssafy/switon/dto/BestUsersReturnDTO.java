package com.ssafy.switon.dto;

import java.util.List;

public class BestUsersReturnDTO {
	
	List<BestWriterReturnDTO> bestWriters;
	List<BestLikeReturnDTO> bestLikes;
	List<Ranker> rankers;
	
	public List<Ranker> getRankers() {
		return rankers;
	}
	public void setRankers(List<Ranker> rankers) {
		this.rankers = rankers;
	}
	public List<BestWriterReturnDTO> getBestWriters() {
		return bestWriters;
	}
	public void setBestWriters(List<BestWriterReturnDTO> bestWriters) {
		this.bestWriters = bestWriters;
	}
	public List<BestLikeReturnDTO> getBestLikes() {
		return bestLikes;
	}
	public void setBestLikes(List<BestLikeReturnDTO> bestLikes) {
		this.bestLikes = bestLikes;
	}
	public BestUsersReturnDTO(List<BestWriterReturnDTO> bestWriters, List<BestLikeReturnDTO> bestLikes) {
		this.bestWriters = bestWriters;
		this.bestLikes = bestLikes;
	}
	
	public BestUsersReturnDTO(List<BestWriterReturnDTO> bestWriters, List<BestLikeReturnDTO> bestLikes,
			List<Ranker> rankers) {
		this.bestWriters = bestWriters;
		this.bestLikes = bestLikes;
		this.rankers = rankers;
	}
	@Override
	public String toString() {
		return "BestUsersReturnDTO [bestWriters=" + bestWriters + ", bestLikes=" + bestLikes + "]";
	}
}
	
	
