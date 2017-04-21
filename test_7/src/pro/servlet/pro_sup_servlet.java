package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.dao.Asset_info_dao;
import pro.dao.Valid_asset_dao;
import pro.javabean.Assert_info;
import pro.javabean.Asset_change;
import pro.javabean.Search_asset;
import pro.javabean.Valid_asset;
import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

public class pro_sup_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_sup_servlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int currentPage=1;
		HttpSession session=request.getSession();
		String role_id=(String) session.getAttribute("role_id");//职位
		String user_id=(String) session.getAttribute("user_id");//用户名
		String city_id1 =(String) session.getAttribute("city_id");//用户性别
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		String user_idString=(String) request.getSession().getAttribute("user_id");
		oper o=null;
		try {
			 o=oper.valueOf(ope);
		} catch (Exception e) {
		o=oper.valueOf("search");
		}
		
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		switch (o) {
		case search:
			 request.getRequestDispatcher("/sup/pro/Home_pro_sup.jsp").forward(request, response);
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
