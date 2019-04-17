/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.CourseDAO;
import entities.Course;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George.Pasparakis
 */
public class CourseImpl implements CourseInterface {
   private static CourseImpl service = new CourseImpl();
   private List<Course> courses = new ArrayList<>();
    
   public CourseImpl() { 
   
   }
   
    @Override
    public List<Course> getAllCourses(CourseDAO dao) {
       try {
           service.courses = dao.getAllCourses();
       } catch (SQLException ex) {
           Logger.getLogger(CourseImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return service.courses;
    }
    
    public boolean saveCourse(CourseDAO dao, Course course) {
       try {
           dao.saveCourse(course);
       } catch (SQLException ex) {
           Logger.getLogger(CourseImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
       return true;
    }
}
