package com.flipkart.bean;

public class Payment {
    private String referenceID;
    private float amount;
    private boolean isSuccessful;

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


}
