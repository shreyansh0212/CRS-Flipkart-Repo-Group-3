package com.flipkart.bean;

public class Notification {
    private String message;
    private String notificationID;
    

    /**
	 * @param message
	 * @param notificationID
	 */
	public Notification(String message, String notificationID) {
		super();
		this.message = message;
		this.notificationID = notificationID;
	}

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


}
