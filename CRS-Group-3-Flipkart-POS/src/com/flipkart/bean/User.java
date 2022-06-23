package com.flipkart.bean;

public class User {
    private String userID;
    private String name;
    private String role;
    private String password;
    private String address;
    // date of creation/ login time

    /**
	 * @param userID
	 * @param name
	 * @param role
	 * @param password
	 */
	public User(String userID, String name, String role, String password) {
		super();
		this.userID = userID;
		this.name = name;
		this.role = role;
		this.password = password;
	}

    public User() {

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}


}
