package admin.user_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modify.User_mod_dao;

public class mod_security extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public mod_security() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mark=request.getParameter("mark");
		String user_id=request.getParameter("user_id");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		User_mod_dao user_mod_dao=new User_mod_dao();
		try {
			if ("mod_question".equals(mark)&&user_mod_dao.recover_question(user_id)) {
				out.print("<script language='JavaScript'>alert('重置密保问题成功');window.location.href='/test_7/user_servlet.do?currentPage=1&oper=search';</script>");
			}
			
			else if("mod_password".equals(mark)&&user_mod_dao.recover_password(user_id)){
				out.print("<script language='JavaScript'>alert('重置密码成功');window.location.href='/test_7/user_servlet.do?currentPage=1&oper=search';</script>");
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
	}

}
