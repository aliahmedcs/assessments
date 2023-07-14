package com.stc.assessments.auth;

import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Length;

public class AuthRequest {
	@NotNull
	@Length(min = 4, max = 20)
	private String password;

	@NotNull
	@Length(min = 4, max = 50)
	private String userEmail;

	@Length(min = 4, max = 50)
	private String address;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String permissionLevel;
	private int groupId;

	public String getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}