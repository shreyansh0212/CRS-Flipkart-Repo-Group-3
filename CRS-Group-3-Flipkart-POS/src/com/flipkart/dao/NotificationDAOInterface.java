package com.flipkart.dao;

public interface NotificationDAOInterface {
    public void sendNotification(String notificationID, String adminID, String studentID, String message);
}
