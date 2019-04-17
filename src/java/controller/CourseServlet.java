/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CourseDAO;
import entities.Course;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CourseImpl;

/**
 *
 * @author George.Pasparakis
 */
public class CourseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseImpl courseService = new CourseImpl();
        CourseDAO dao = new CourseDAO();
        
        
        
        List<Course> courses = courseService.getAllCourses(dao);
        
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>List Of Courses" + "</h1>");
            for(Course acourse : courses) {
                out.println(acourse + "<br>");
            }
            out.println("<form name=\"getcourse\" action=\"course\" method=\"POST\">\n" +
"            Title: <input type=\"text\" name=\"title\" value=\"\" /><br />\n" +
"            Stream: <input type=\"text\" name=\"stream\" value=\"\" /><br />\n" +
"            Type: <input type=\"text\" name=\"type\" value=\"\" /><br />\n" +
"            Start Date: <input type=\"text\" name=\"startdate\" value=\"\" /><br />\n" +
"            End Date:<input type=\"text\" name=\"enddate\" value=\"\" /><br />\n" +
"            <input type=\"submit\" value=\"Save\" name=\"save\" />\n" +
"        </form>");
            out.println("</body>");
            out.println("</html>");
        
       }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Course course = new Course();
        CourseDAO dao = new CourseDAO();
        
        //processRequest(request, response);
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>The record is: ");
            course.setTitle(request.getParameter("title"));
            course.setStream(request.getParameter("stream"));
            course.setType(request.getParameter("type"));
            course.setStartDate(request.getParameter("startdate"));
            course.setEndDate(request.getParameter("enddate"));
            try {
                if(dao.saveCourse(course)) out.println(" written!!! All good!</h1><br />");
                out.println("<a href=\"http://localhost:8084/CourseServlet/\">Home</a>");
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
       }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
