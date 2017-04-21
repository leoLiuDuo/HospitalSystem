package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Data;

public class Loginoutservlet extends HttpServlet {

	public Loginoutservlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession(false)==null){
			response.sendRedirect("/test_7/Login.jsp");
			return;
		}
		if(request.getSession().getAttribute("user_id")==null){
			
			response.sendRedirect("/test_7/Login.jsp");
			return;
		}
		request.getSession().invalidate();
			response.sendRedirect("/test_7/Login.jsp");
		
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
	}

}
