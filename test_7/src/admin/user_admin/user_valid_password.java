package admin.user_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.UserLogin;
import Login.UserLogin_dao;

public class user_valid_password extends HttpServlet {

	
	public user_valid_password() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id=(String) request.getSession().getAttribute("user_id");
		String old_password=request.getParameter("old_password");
		System.out.println(old_password);
		UserLogin user=new UserLogin(user_id, old_password);
		HashMap<String ,Object> hashMap=null;
		try {
		 hashMap=new UserLogin_dao().search(user);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if((Boolean) hashMap.get("judge")){
			out.println("1");
		}else {
			out.println("0");
		}
	
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
