package com.ers.Model;

public class User {

	private int userId;
	private String userName;
	private String userPwd;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private int userRoleId;
	
	public User()
	{}
	
	public User(int userId,String userName, String userPwd, String userFirstName, String userLastName,
			String userEmail, int userRoleId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}

	public User(String userName, String userPwd, String userFirstName, String userLastName,
			String userEmail, int userRoleId) {
		super();
	//	this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userRoleId = userRoleId;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + ", userRoleId="
				+ userRoleId + "]";
	}

	/*
	 * @Override public String toString() { return "User [userName=" + userName +
	 * ", userPwd=" + userPwd + ", userFirstName=" + userFirstName +
	 * ", userLastName=" + userLastName + ", userEmail=" + userEmail +
	 * ", userRoleId=" + userRoleId + "]"; }
	 */

}
