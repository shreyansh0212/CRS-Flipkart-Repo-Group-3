package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.List;

public class StudentDAOOperation implements StudentDAOInterface{

    /**
     * @param student
     * @return
     */
    @Override
    public boolean isRegistered(Student student) {
        return false;
    }

    /**
     * @param studentID
     * @return
     */
    @Override
    public boolean isApproved(String studentID) {
        return false;
    }
}
