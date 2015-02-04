package com.mojio.mojiosdk.models;

public class User {

	private int Type;
	private String UserName;
	private String FirstName;
	private String LastName;
	private String Email;
	private int UserCount;
	private int Credits;
	private String CreationDate;
	private String LastActivityDate;
	private String LastLoginDate;
	private String Locale;
	private String _id;
	private boolean _deleted;
	
	public User(){
		
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getUserCount() {
		return UserCount;
	}

	public void setUserCount(int userCount) {
		UserCount = userCount;
	}

	public int getCredits() {
		return Credits;
	}

	public void setCredits(int credits) {
		Credits = credits;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public String getLastActivityDate() {
		return LastActivityDate;
	}

	public void setLastActivityDate(String lastActivityDate) {
		LastActivityDate = lastActivityDate;
	}

	public String getLastLoginDate() {
		return LastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		LastLoginDate = lastLoginDate;
	}

	public String getLocale() {
		return Locale;
	}

	public void setLocale(String locale) {
		Locale = locale;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public boolean is_deleted() {
		return _deleted;
	}

	public void set_deleted(boolean _deleted) {
		this._deleted = _deleted;
	}
}
