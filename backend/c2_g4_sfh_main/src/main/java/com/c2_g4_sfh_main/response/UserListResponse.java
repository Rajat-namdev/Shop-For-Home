package com.c2_g4_sfh_main.response;

import java.util.List;

import com.c2_g4_sfh_main.entity.User;

public class UserListResponse {

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
