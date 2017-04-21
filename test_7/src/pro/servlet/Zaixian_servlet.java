package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

import pro.javabean.*;

import pro.dao.Zaixian_dao;

public class Zaixian_servlet extends HttpServlet {
	
	public Zaixian_servlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		System.out.println("zaixian Servlet中");
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		String sex=request.getParameter("sex");
		System.out.println("输出为："+sex);
 	 Zaixian_dao zaixian_dao = new Zaixian_dao();
//改到这里了
 	 	
	  ArrayList<Date> date_list=new ArrayList<Date>();
	 
	  
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		 }
  		 System.out.println("******************************************************");
	 		HashMap<String, Object> hashMap= zaixian_dao.display(currentPage-1);
				request.setAttribute("history_list", hashMap.get("history_list"));
				request.setAttribute("totalPage", hashMap.get("totalPage"));
				request.setAttribute("currentPage", currentPage);
	 			ArrayList<res_of_search> list=(ArrayList<res_of_search>) hashMap.get("history_list");
	 			System.out.println("以下为输出：");
	 			for(int i=0;i<list.size();i++)
	 			System.out.println(list.get(i).getSEARCH_DATE().get(0).getDate());
				 
		 	request.getRequestDispatcher("/sup/pro/forms/zaixian.jsp").forward(request, response);
		 
		 
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
	enum oper{
		search,add,mod,delect;
	}
}
