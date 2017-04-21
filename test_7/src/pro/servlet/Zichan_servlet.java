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
import pro.dao.Zichan_dao;

public class Zichan_servlet extends HttpServlet {
	public Zichan_servlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("text/html;charset=utf-8");
		  request.setCharacterEncoding("UTF-8");
		  HttpSession session=request.getSession();
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		
		Zichan_dao zichan_dao = new Zichan_dao();
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
			
			String city_id=(String) request.getSession().getAttribute("city_id");
			
			String role_id=(String) request.getSession().getAttribute("role_id");
			if ("r2".equals(role_id)||"r4".equals(role_id)) {
				city_id="";
			}
			
			HashMap<String,Object> hashMap=null;
			
			if(city!=""){
				city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(city);
				hashMap=zichan_dao.Searchzc(currentPage-1, city, startTime, endTime);
				request.setAttribute("Zichan_list", hashMap.get("Zichan_list"));
				request.setAttribute("totalPage", hashMap.get("totalPage"));
			}
			
			request.setAttribute("startTime",startTime);
			request.setAttribute("endTime",endTime);
			
			request.setAttribute("city",city);
			request.setAttribute("City_list", zichan_dao.getType());
			request.setAttribute("currentPage", currentPage);
			System.out.println("inservlet");
			request.getRequestDispatcher("/sup/pro/forms/zichan.jsp").forward(request, response);
			break;
		case add:
//			pro_dao.add(request.getParameter("pro_id"),request.getParameter("pro_type_id"),new String( request.getParameter("pro_name").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("aproval_num"));
			request.getRequestDispatcher("/Zichan_servlet.do?currentPage=1&oper=search&city=").forward(request, response);
			break;
		case mod:
//			pro_dao.revise(request.getParameter("pro_id1"),request.getParameter("pro_type_id1"),new String( request.getParameter("pro_name1").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("aproval_num1"));
			request.getRequestDispatcher("/Zichan_servlet.do?currentPage=1&oper=search&city=").forward(request, response);
			break;
		case delect:
//			pro_dao.delete(request.getParameter("projectid"));
			request.getRequestDispatcher("/Zichan_servlet.do?currentPage=1&oper=search&city=").forward(request, response);
			break;
		default:
			break;
		}
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
