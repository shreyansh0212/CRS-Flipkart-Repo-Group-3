package com.flipkart.service;

/**
 * Service Interface for Notification
 */
public interface NotificationInterface {
    public void sendNotification(String senderID,String receiverID,String message);
}
