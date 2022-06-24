package com.flipkart.service;

import com.flipkart.dao.NotificationDAOInterface;
import com.flipkart.dao.NotificationDAOOperation;

import java.time.LocalDateTime;

public class NotificationImplementation implements NotificationInterface{

    NotificationDAOInterface notificationDAOInterface = new NotificationDAOOperation();
    /**
     * @param senderID
     * @param receiverID
     * @param message
     */
    @Override
    public void sendNotification(String senderID, String receiverID, String message) {

    }

    public void sendNotifCourseReg(String adminID, String studentId){
        String text = "Your course registration has been approved by " + adminID;
        LocalDateTime localDateTime = LocalDateTime.now();
        String notificationID = adminID + studentId + localDateTime;
        NotificationDAOInterface notificationDAOInterface = new NotificationDAOOperation();
        notificationDAOInterface.sendNotification(notificationID,adminID,studentId,text);
    }
}
