package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req_path = request.getRequestURI();
		System.out.println(req_path);
		if(req_path.endsWith("add.do")) {
			System.out.println("Add student details");
		}
		if(req_path.endsWith("search.do")) {
			
		}
		if(req_path.endsWith("delete.do")) {
			
		}
	}

}
