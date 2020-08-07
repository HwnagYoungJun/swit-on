package com.ssafy.switon.dto;

import io.swagger.annotations.ApiParam;

public class UserDTO {
	
	@ApiParam(value = "유저 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "유저 닉네임")
	String name;
	@ApiParam(value = "유저 아이디(이메일)", required = false, hidden = true)
	String email;
	@ApiParam(value = "유저 비밀번호")
	String password;
	@ApiParam(value = "유저 소갯말")
	String introduce;
	@ApiParam(value = "유저 프로필 이미지")
	String profile_image;
	@ApiParam(value = "유저 관심 카테고리(소카테고리) id (FK)")
	String interests;
	@ApiParam(value = "유저 탈퇴시 닉네임 백업", required = false, hidden = true)
	String name_legacy;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", introduce="
				+ introduce + ", profile_image=" + profile_image + ", interests=" + interests
				+ ", name_legacy=" + name_legacy + "]";
	}
}
