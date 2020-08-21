package com.ssafy.switon.dto;

import io.swagger.annotations.ApiParam;

public class UserSimpleDTO {

	@ApiParam(value = "유저 id (PK)", required = false, hidden = true)
	int id;
	@ApiParam(value = "유저 닉네임")
	String name;
	@ApiParam(value = "유저 프로필 이미지")
	String profile_image;
	
	public UserSimpleDTO() {

	}
	public UserSimpleDTO(UserInfoDTO userInfo) {
		this.id = userInfo.getId();
		this.name = userInfo.getName();
		this.profile_image = userInfo.getProfile_image();
	}
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
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	@Override
	public String toString() {
		return "UserSimpleDTO [id=" + id + ", name=" + name + ", profile_image=" + profile_image + "]";
	}
	
	
}
