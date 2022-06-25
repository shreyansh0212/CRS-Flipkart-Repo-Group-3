package com.flipkart.bean;

/**
 * Payment Bean Class
 */

public class Payment {
    private String referenceID;
    private float amount;
    private boolean isSuccessful;


    /**
     * Parameterized Constructor
     * @param referenceID
     * @param amount
     * @param isSuccessful
     */
	public Payment(String referenceID, float amount, boolean isSuccessful) {
		super();
		this.referenceID = referenceID;
		this.amount = amount;
		this.isSuccessful = isSuccessful;
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
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Set Payment Status
     * @param successful
     */
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


}
