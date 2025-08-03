package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import factory.StudentServiceFactory;
import service.StudentService;


/**
 * Servlet mapping using URL pattern "*.do" to handle all requests ending with .do extension.
 * This creates a front controller pattern that centralizes request handling.
 * 
 * Benefits:
 * - Single entry point for all .do actions (add.do, delete.do, update.do etc.)
 * - Clean URLs that hide implementation details
 * - Easy to maintain and extend action handlers
 * 
 * Example URLs that will trigger this servlet:
 * - /add.do       (Create operation)
 * - /delete.do    (Delete operation)
 * - /update.do    (Update operation)
 * - /search.do    (Query operation)
 * 
 * Note: The actual action is determined by parsing the request URI in doGet/doPost methods
 */
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the request URI (the part of URL after domain and context path)
		// Example: For "http://localhost:8080/Servelet-DAO-Application01/add.do", returns "/Servelet-DAO-Application01/add.do"
		String req_path = request.getRequestURI();
		System.out.println(req_path);
	
		// Get an instance of StudentService using the factory pattern
		// This provides loose coupling and easier maintenance
		// The factory decides which implementation to return
		StudentService studentService = StudentServiceFactory.getStudentService();
		
		// Initialize status variable to store operation result
		// Will hold values like "success", "failure", or "existed"
		String status = "";
		
		// Initialize RequestDispatcher to handle forwarding requests to views
		// Starts as null since we'll determine the target based on operation result
		RequestDispatcher requestDispatcher = null;  // Will be set later based on status

	
		// Check if the request path ends with "add.do" (Add Student request)
		if(req_path.endsWith("add.do")) {
		    
		    // 1. GET STUDENT DATA FROM REQUEST PARAMETERS
		    // Extract student details from the HTML form submission
		    String sid = request.getParameter("sid");      // Student ID from form
		    String sname = request.getParameter("sname");  // Student Name from form
		    String saddr = request.getParameter("saddr");  // Student Address from form
		    
		    // 2. CREATE STUDENT OBJECT AND POPULATE DATA
		    Student std = new Student();  // Instantiate new Student object
		    
		    // Set student attributes using form data
		    std.setSid(sid);      // Set student ID
		    std.setSname(sname);  // Set student name
		    std.setSaddr(saddr);  // Set student address
		    
		    // 3. CALL SERVICE LAYER TO PROCESS STUDENT ADDITION
		    // Attempt to add student through service layer
		    // Possible return values: "success", "failure", or "existed"
		    status = studentService.addStudent(std);
		    
		    // 4. HANDLE SERVICE RESPONSE AND REDIRECT APPROPRIATELY
		    
		    // Case: Student added successfully
		    if(status.equals("success")) {
		        // Forward to success page
		        requestDispatcher = request.getRequestDispatcher("success.html");
		        requestDispatcher.forward(request, response);
		    }
		    
		    // Case: Error occurred during addition
		    if(status.equals("failure")) {
		        // Forward to failure page
		        requestDispatcher = request.getRequestDispatcher("failure.html");
		        requestDispatcher.forward(request, response);
		    }
		    
		    // Case: Student already exists in system
		    if(status.equals("existed")) {
		        // Forward to "student exists" page
		        requestDispatcher = request.getRequestDispatcher("existed.html");
		        requestDispatcher.forward(request, response);
		    }
		}
		
		
		if(req_path.endsWith("search.do")) {
			String sid = request.getParameter("sid");
		 Student std = studentService.searchStudent(sid);
		 
		 if(std == null) {
			 requestDispatcher = request.getRequestDispatcher("notexisted.html");
			 requestDispatcher.forward(request, response);
		 }else {
			 request.setAttribute("std", std);
			 requestDispatcher = request.getRequestDispatcher("display.jsp");
			 requestDispatcher.forward(request, response);
		 }
			
		}
		
		
		if(req_path.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			 status = studentService.deleteStudent(sid);
			 if(status.equals("success")) {
				 requestDispatcher = request.getRequestDispatcher("success.html");
				 requestDispatcher.forward(request, response);
			 }
			 if(status.equals("failure")) {
				 requestDispatcher = request.getRequestDispatcher("failure.html");
				 requestDispatcher.forward(request, response);
			 }
			 if(status.equals("notexisted")) {
				 requestDispatcher = request.getRequestDispatcher("notexisted.html");
				 requestDispatcher.forward(request, response);
			 }
		}
	}

}
