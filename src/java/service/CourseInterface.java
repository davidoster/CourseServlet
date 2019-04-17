/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.CourseDAO;
import entities.Course;
import java.util.List;

/**
 *
 * @author George.Pasparakis
 */
public interface CourseInterface {
    
    public List<Course> getAllCourses(CourseDAO dao);
    public boolean saveCourse(CourseDAO dao, Course course);
}
