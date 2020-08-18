package com.ssafy.switon.dto;

public class BoardIndexDTO {
	
	int board_id;
	int start_idx;
	int end_idx;
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getStart_idx() {
		return start_idx;
	}
	public void setStart_idx(int start_idx) {
		this.start_idx = start_idx;
	}
	public int getEnd_idx() {
		return end_idx;
	}
	public void setEnd_idx(int end_idx) {
		this.end_idx = end_idx;
	}
	@Override
	public String toString() {
		return "BoardIndexDTO [board_id=" + board_id + ", start_idx=" + start_idx + ", end_idx=" + end_idx + "]";
	}
	public BoardIndexDTO(int board_id, int start_idx, int end_idx) {
		this.board_id = board_id;
		this.start_idx = start_idx;
		this.end_idx = end_idx;
	}
}
