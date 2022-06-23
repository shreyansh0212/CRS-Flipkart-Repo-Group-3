package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.util.List;

public interface StudentDAOInterface {
    public boolean isRegistered(Student student);
    public boolean isApproved(String studentID);
}
