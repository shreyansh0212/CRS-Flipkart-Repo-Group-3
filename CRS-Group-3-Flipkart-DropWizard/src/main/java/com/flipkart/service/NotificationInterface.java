package com.flipkart.service;

/**
 * Service Interface for Notification
 */
public interface NotificationInterface {
    /**
     * Send Notification
     * @param senderID
     * @param receiverID
     * @param message
     */
    public void sendNotification(String senderID,String receiverID,String message);
}
