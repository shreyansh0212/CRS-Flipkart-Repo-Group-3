package com.flipkart.dao;

import com.flipkart.constants.SQLQueriesConstants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.flipkart.application.CRSApplication.connection;

public class NotificationDAOOperation implements NotificationDAOInterface{
    /**
     * @param notificationID
     * @param studentID
     * @param message
     */
    @Override
    public void sendNotification(String notificationID, String adminID, String studentID, String message) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_NOTIFICATION);
            statement.setString(1,notificationID);
            statement.setString(2,adminID);
            statement.setString(3,studentID);
            statement.setString(4,message);
            int row = statement.executeUpdate();
            if(row > 0) {
                System.out.println("Notification sent Successfully with NotificationID: " + notificationID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
