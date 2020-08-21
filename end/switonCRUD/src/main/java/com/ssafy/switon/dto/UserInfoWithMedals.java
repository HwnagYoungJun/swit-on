package com.ssafy.switon.dto;

import io.swagger.annotations.ApiParam;

public class UserInfoWithMedals {
	
	@ApiParam(value = "유저 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "유저 닉네임(닉네임이 이거로 변경됨)")
	String name;
	@ApiParam(value = "유저 아이디(이메일)", required = false, hidden = true)
	String email;
	@ApiParam(value = "유저 소갯말")
	String introduce;
	@ApiParam(value = "유저 프로필 이미지", required = false, hidden = true)
	String profile_image;
	@ApiParam(value = "유저 관심 카테고리(소카테고리) id (FK)")
	String interests;
	@ApiParam(value = "유저 탈퇴시 닉네임 백업", required = false, hidden = true)
	String name_legacy;
	
	Medals medals;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getName_legacy() {
		return name_legacy;
	}

	public void setName_legacy(String name_legacy) {
		this.name_legacy = name_legacy;
	}

	public Medals getMedals() {
		return medals;
	}

	public void setMedals(Medals medals) {
		this.medals = medals;
	}

	public UserInfoWithMedals(UserInfoDTO dto, Medals medals) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.email = dto.getEmail();
		this.introduce = dto.getIntroduce();
		this.profile_image = dto.getProfile_image();
		this.interests = dto.getInterests();
		this.name_legacy = dto.getName_legacy();
		this.medals = medals;
	}
	
	
}
