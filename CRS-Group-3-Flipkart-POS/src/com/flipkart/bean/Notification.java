package com.flipkart.bean;

public class Notification {
    private String message;
    private String notificationID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    private String senderID;
    private String receiverID;
    

	public Notification(String senderID, String receiverID, String message) {
		super();
		this.message = message;
		this.senderID = senderID;
        this.receiverID = receiverID;
	}

}
