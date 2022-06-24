package com.flipkart.service;

public interface NotificationInterface {
    public void sendNotification();
    public void sendNotifCourseReg(String adminID, String studentId, boolean approval);
}
