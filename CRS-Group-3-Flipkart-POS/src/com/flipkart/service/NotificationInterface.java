package com.flipkart.service;

public interface NotificationInterface {
    public void sendNotification(String senderID,String receiverID,String message);
    public void sendNotifCourseReg(String adminID, String studentId);
}
