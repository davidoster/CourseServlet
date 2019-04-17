/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.*;

public class oldDatabase {

    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String Url = "jdbc:mysql://ra1.anystream.eu:1011/lkrepeat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String Username = "root";
    private static String Password = "aDifficultPassword$";
    
    private static Connection connection = null;
    private static Statement statement = null; 
    private static ResultSet resultSet = null; 

    public oldDatabase() throws SQLException {
        try {
            connection = DriverManager.getConnection(Url, Username, Password);
            } catch (SQLException ex) {System.out.println("The following error has occured: " + ex.getMessage());}
    }
    
    public void DisconnectFromDB() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
              }catch (SQLException ex) {System.out.println("The following error has occured: " + ex.getMessage());} 
    }
    public ResultSet ReadRecords(String sql_stmt) {
        try {
            statement = connection.createStatement();                                   
            resultSet = statement.executeQuery(sql_stmt);
            return resultSet;
            } catch (SQLException ex) {System.out.println("The following error has occured: " + ex.getMessage());}                                                    
        return resultSet;
    }
    
    public int ExecuteSQLStatement(String sql_stmt) {
        try {
            statement = connection.createStatement();                                    
            return statement.executeUpdate(sql_stmt);
            } catch (SQLException ex) {
                System.out.println("The following error has occured: " + ex.getMessage());
            }
        return -1;
    }
    
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<Course>();
        oldDatabase db = new oldDatabase();
        ResultSet rs = db.ReadRecords("SELECT * FROM Course");
        while(rs.next()) {
            courses.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3), 
                                   rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        return courses;
    }
    
    public boolean saveCourse(Course course) throws SQLException {
        String sql = "INSERT INTO Course('Title', 'Stream', 'Type', 'StartDate', 'EndDate') "
                + "VALUES('" + course.getTitle() + "','"
                + course.getStream() + "','"
                + course.getType() + "','"
                + course.getStartDate() + "','"
                + course.getEndDate() + "')";
        oldDatabase db = new oldDatabase();
        if(db.ExecuteSQLStatement(sql) != 1) return false; else return true;
    }
}
