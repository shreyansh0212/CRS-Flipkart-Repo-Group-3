package com.flipkart.dao;

import com.flipkart.constants.DatabaseUtil;
import com.flipkart.constants.SQLQueriesConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class NotificationDAOOperation implements NotificationDAOInterface{
    /**
     * Sends Notification from one user to another one
     * @param notificationID
     * @param senderID
     * @param receiverID
     * @param message
     */

    Connection connection = DatabaseUtil.getConn();
    @Override
    public void sendNotification(String notificationID, String senderID, String receiverID, String message) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_NOTIFICATION);
            statement.setString(1,notificationID);
            statement.setString(2,senderID);
            statement.setString(3,receiverID);
            statement.setString(4,message);
            int row = statement.executeUpdate();
            if(row > 0) {
                System.out.println("Notification sent to " +receiverID + " Successfully with NotificationID: " + notificationID +  " from "+  senderID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * returns the notification id  using sender and receiver id
     * @param senderID
     * @param receiverID
     * @return
     */
    @Override
    public String getNotificationID(String senderID, String receiverID) {
        return senderID + receiverID + LocalDateTime.now();
    }
}
