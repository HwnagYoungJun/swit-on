package com.ssafy.switon.dto;

public class UserInfoDTO {
	
	int id;
	String name;
	String email;
	String introduce;
	String profile_image;
	int uppercategory_id;
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
	public int getUppercategory_id() {
		return uppercategory_id;
	}
	public void setUppercategory_id(int uppercategory_id) {
		this.uppercategory_id = uppercategory_id;
	}
	public String getName_legacy() {
		return name_legacy;
	}
	public void setName_legacy(String name_legacy) {
		this.name_legacy = name_legacy;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email +  ", introduce="
				+ introduce + ", profile_image=" + profile_image + ", uppercategory_id=" + uppercategory_id
				+ ", name_legacy=" + name_legacy + "]";
	}
}
