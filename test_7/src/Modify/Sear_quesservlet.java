package Modify;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DBManager;

public class Sear_quesservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Sear_quesservlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Connection connection = new DBManager().getCon();
		mod_dao mDao = new mod_dao(connection);
		String formString = request.getParameter("form");
		
		if("user_id".equals(formString)){
			String Username=request.getParameter("Username");
			ArrayList<String> list = mDao.question_search(Username);
			if(list.isEmpty()){
				request.setAttribute("notice", "没有这个用户或者没有密保问题");
			
				request.getRequestDispatcher("/user_id.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("Username", Username);
				session.setAttribute("question", list);
				request.getRequestDispatcher("/questions.jsp").forward(request, response);
			}
		}
		else if("questions".equals(formString)){
			String answer=request.getParameter("answer");
			String Username=request.getParameter("Username");
			String question=new String(request.getParameter("question").getBytes("ISO-8859-1"),"utf-8");
			System.out.println(question);
			if(mDao.search_judge(Username, answer,question)){
				request.getRequestDispatcher("/modify.jsp").forward(request, response);
			}
			else {
				request.setAttribute("error", "问题回答错误");
				request.getRequestDispatcher("/questions.jsp").forward(request, response);
				
			}
		}
		else if ("modify".equals(formString)) {
			
			String Username=request.getParameter("Username");
			String password=request.getParameter("password");
			
			
			String role_idString=null;
			HashMap<String, Object>hashMap=mDao.mod_password(Username, password);
			if ((Boolean)hashMap.get("judge")) {
					response.sendRedirect("/test_7/login/Loginservlet?Username="+Username+"&Password="+password);
				
				
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sear_quesservlet关闭异常");
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	
	}

}
