package com.ssafy.switon.dto;

public class Medals {
	int gold;
	int silver;
	int bronze;
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getBronze() {
		return bronze;
	}
	public void setBronze(int bronze) {
		this.bronze = bronze;
	}
	@Override
	public String toString() {
		return "MyMedals [gold=" + gold + ", silver=" + silver + ", bronze=" + bronze + "]";
	}
	
	
}
