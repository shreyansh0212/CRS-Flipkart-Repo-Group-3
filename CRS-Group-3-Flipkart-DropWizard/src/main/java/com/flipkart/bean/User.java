package com.flipkart.bean;

/**
 * User Bean Class
 */
public class User {
    private String userID;
    private String name;
    private String role;
    private String password;

    /**
     * Constructor
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

    /**
     * Default Constructor
     */
    public User() {

    }

    /**
     * Get user ID
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Set User ID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Get Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Role
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * Set Role
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get Password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
