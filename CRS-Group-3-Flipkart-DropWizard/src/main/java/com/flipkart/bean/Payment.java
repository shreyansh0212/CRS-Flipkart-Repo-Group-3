package com.flipkart.bean;

/**
 * Payment Bean Class
 */

public class Payment {
    private String referenceID;
    private float amount;
    private String mode;
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Parameterized Constructor
     * @param referenceID
     * @param amount
     * @param mode
     */
	public Payment(String referenceID, String userID, float amount, String mode) {
		super();
        this.userID = userID;
		this.referenceID = referenceID;
		this.amount = amount;
		this.mode = mode;
	}

    public Payment() {

    }

    /**
     * Get Payment Reference ID
     * @return
     */
	public String getReferenceID() {
        return referenceID;
    }

    /**
     * Set Payment Reference ID
     * @param referenceID
     */
    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    /**
     * Get Payment Amount
     * @return
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set Payment Amount
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Check Payment Status
     * @return
     */

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
