package Modify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Notice_admin.Answer;
import admin.Notice_admin.User_dao;

public class User_modservlet extends HttpServlet {

	
	public User_modservlet() {
		super();
	}

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathurl=(String) request.getSession().getAttribute("pathurl");
		String oper=request.getParameter("oper");
		String user_id=(String)request.getSession().getAttribute("user_id");
		User_mod_dao user_mod_dao=new User_mod_dao();
		boolean j=false;
		
		if ("mod".equals(oper)&&user_mod_dao.mark_search(user_id)) {
			
			response.sendRedirect("/test_7/mod_security.jsp");
		}
		else if ("add".equals(oper)) {
			String answer1=request.getParameter("answer1");
			String answer2=request.getParameter("answer2");
			String answer3=request.getParameter("answer3");
			String question1=request.getParameter("question1");
			String question2=request.getParameter("question2");
			String question3=request.getParameter("question3");
			answer1=new String(answer1.getBytes("ISO-8859-1"),"utf-8");
			answer2=new String(answer2.getBytes("ISO-8859-1"),"utf-8");
			answer3=new String(answer3.getBytes("ISO-8859-1"),"utf-8");
			
			Answer answer = new Answer(question1, question2, question3, answer1, answer2, answer3);
			try {
				j=user_mod_dao.mod_question(answer, user_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(j){
				User_dao user_dao=new User_dao();
				request.getSession().setAttribute("user_person", user_dao.SearchUser(user_id));
				response.sendRedirect(pathurl);
			}else {
				response.sendRedirect("/test_7/error.jsp");
			}
			
		} 
		else {
			response.sendRedirect(pathurl);
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	}

}
