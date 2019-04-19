/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controller.CourseServlet;
import entities.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George.Pasparakis
 */
public class CourseDAO extends Database {
    private String server = "ra1.anystream.eu:1011";
    private String database = "lkrepeat";
    private String username = "root";
    private String password = "aDifficultPassword$";
    private static final CourseDAO db = new CourseDAO();
    
    public List<Course> getAllCourses() throws SQLException {
        String query = "SELECT * FROM `Course`"; 
        List<Course> courses = new ArrayList<Course>();
        ResultSet rs = db.Database(server, database, username, password, query);
        while(rs.next()) {
            courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3), 
                                   rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        return courses;
    }
    
    public boolean saveCourse(Course course) throws SQLException {
        String sql = "INSERT INTO `Course` (`Title`, `Stream`, `Type`, `StartDate`, `EndDate`) "
                + "VALUES('" + course.getTitle() + "','"
                             + course.getStream() + "','"
                             + course.getType() + "','"
                             + course.getStartDate() + "','"
                             + course.getEndDate() + "')";
        Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, sql);
        return (db.Database(server, database, username, password, sql, (byte)1) == 1);
    }
    
    public boolean deleteCourse(CourseDAO dao, int id) {
        String sql = "DELETE FROM `Course` WHERE `id`=" + id;
        return(db.Database(server, database, username, password, sql, (byte)1) == 1);
    }
}
