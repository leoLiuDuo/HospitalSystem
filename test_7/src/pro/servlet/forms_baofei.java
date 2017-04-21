package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;
import pro.dao.Sup_pro_basic_proinfo_dao;
import pro.javabean.*;
import pro.servlet.pro_sup_proinfo_servlet.oper;
import pro.dao.Forms_baofei_dao;


public class forms_baofei extends HttpServlet {
	public forms_baofei() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("test");
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		  HttpSession session=request.getSession();
		Forms_baofei_dao baofei_dao = new Forms_baofei_dao();
//改到这里了
//		request.setAttribute("proinfo_test", pro_dao.SearchProinfo(currentPage-1,pro_idString));
		
		oper o=oper.valueOf(ope);//String转换为枚举
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		
		switch (o) {
		case search:
			
			String city=request.getParameter("city");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			HashMap<String,Object> hashMap=null;
           String city_id=(String) request.getSession().getAttribute("city_id");
			String role_id=(String) request.getSession().getAttribute("role_id");
			if(city!=""){
				city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(city);
				System.out.println(startTime);
				hashMap=baofei_dao.Searchbf(currentPage-1, city, startTime, endTime);
				request.setAttribute("baofei_list", hashMap.get("baofei_list"));
				request.setAttribute("totalPage", hashMap.get("totalPage"));
			}
			
			request.setAttribute("startTime",startTime);
			request.setAttribute("endTime",endTime);
			request.setAttribute("city",city);
			request.setAttribute("city_list", baofei_dao.getType());
			request.setAttribute("currentPage", currentPage);
			System.out.println("inservlet");
			request.getRequestDispatcher("/sup/pro/forms/baofei.jsp").forward(request, response);
			break;}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}


	public void init() throws ServletException {
	
	}

}
