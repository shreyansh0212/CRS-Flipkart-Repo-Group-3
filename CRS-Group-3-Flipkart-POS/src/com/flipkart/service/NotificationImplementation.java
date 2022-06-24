package com.flipkart.service;

import com.flipkart.dao.NotificationDAOInterface;
import com.flipkart.dao.NotificationDAOOperation;

import java.time.LocalDateTime;

public class NotificationImplementation implements NotificationInterface{
    /**
     *
     */
    @Override
    public void sendNotification() {

    }

    public void sendNotifCourseReg(String adminID, String studentId, boolean approval){
        String text;
        if(approval){
            text = "Your course registration has been approved by " + adminID;
        }
        else {
            text = "Your course registration has been rejected by " + adminID + ". Please update your preferences.";
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        String notifid = adminID + studentId + localDateTime;
        NotificationDAOInterface notificationDAOInterface = new NotificationDAOOperation();
        notificationDAOInterface.sendNotification(notifid,adminID,studentId,text);
    }
}
