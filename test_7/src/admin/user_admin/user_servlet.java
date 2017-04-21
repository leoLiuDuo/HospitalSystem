package admin.user_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Login.UserLogin;
import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

public class user_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public user_servlet() {
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
		
		int currentPage=1;
		Notice_dao naDao=new Notice_dao();
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		String role_id1=(String) request.getSession().getAttribute("role_id");
		User_dao user_dao=new User_dao();
		oper o=oper.valueOf(ope);
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		switch (o) {
		case search:
			String user_id1=request.getParameter("user_id1");
			String user_name=request.getParameter("user_name");
			HashMap<String,Object> hashMap=null;
			if (user_id1==null||user_name==null) {
				try {
					hashMap=user_dao.getUser(currentPage-1,"");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				user_name=new String( user_name.getBytes("ISO-8859-1"),"UTF-8");
				hashMap=user_dao.SearchUser(currentPage-1, user_id1, user_name,"");
				
			}
			
			 request.setAttribute("user_id1",user_id1);
			 request.setAttribute("user_name", user_name);
			 request.setAttribute("userlist", hashMap.get("userlist"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
			break;
		
		case mod:
			String user_idString= request.getParameter("user_id");
			String user_id=request.getParameter("reuser_id");
			user_name=request.getParameter("name");
			String city_id=request.getParameter("city_id");
			
			user_name=new String( user_name.getBytes("ISO-8859-1"),"UTF-8");
			boolean j=false;
			if (user_id==null) {
				System.out.println(user_idString+city_id+user_name+user_id);
				j=user_dao.mod_user_info(user_idString, city_id, user_name, user_idString);
				request.getSession().setAttribute("user_person", user_dao.SearchUser((String)request.getSession().getAttribute("user_id")));
				
			}
			else {
				
				j=user_dao.mod_user_info(user_idString, city_id, user_name, user_id);
				request.getSession().setAttribute("user_person", user_dao.SearchUser((String)request.getSession().getAttribute("user_id")));
			}
			if("r1".equals(role_id1)){
				response.sendRedirect("/test_7/user_servlet.do?currentPage=1&oper=search");
			}
			else {
				response.sendRedirect("/test_7/pro_sup_servlet.do?currentPage=1&oper=search");
			}
			
			break;
		case add:
			user_id=request.getParameter("user_id");
			user_name=request.getParameter("name");
			city_id=request.getParameter("city_id");
			String role_id=request.getParameter("role_id");
			user_name=new String( user_name.getBytes("ISO-8859-1"),"UTF-8");
			j=false;
			try {
				j=user_dao.add(user_id, user_name, city_id, role_id);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			response.sendRedirect("/test_7/user_servlet.do?currentPage=1&oper=search");
			break;
		case delect:
			user_id=request.getParameter("user_id");
			j=false;
			try {
				j=user_dao.delete(user_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect("/test_7/user_servlet.do?currentPage=1&oper=search");
			break;
		default:
			break;
		}
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
		
	}
enum oper{
	search,add,mod,delect;
}
}
