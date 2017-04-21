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
import pro.dao.Zhuangu_dao;
import pro.javabean.*;
import pro.servlet.pro_sup_proinfo_servlet.oper;
import pro.dao.Zichan_dao;

public class Zhuangu_Servlet extends HttpServlet {
	public Zhuangu_Servlet() {
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
		
		Zhuangu_dao zhuangu_dao = new Zhuangu_dao();

		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}

		String city=request.getParameter("city");
		String startTime=request.getParameter("date1");
		String endTime=request.getParameter("date2");
			System.out.println(startTime);
			
		String city_id=(String) request.getSession().getAttribute("city_id");
		String role_id=(String) request.getSession().getAttribute("role_id");
		if ("r2".equals(role_id)||"r4".equals(role_id)) {
			city_id="";
		}
			
		HashMap<String,Object> hashMap=null;
			
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			hashMap=zhuangu_dao.Searchzg(currentPage-1, city, startTime, endTime);
			request.setAttribute("Zhuangu_list", hashMap.get("Zhuangu_list"));
			request.setAttribute("totalPage", hashMap.get("totalPage"));
		}
			
		request.setAttribute("startTime",startTime);
		request.setAttribute("endTime",endTime);
		request.setAttribute("city",city);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/sup/pro/forms/zhuangu.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	public void init() throws ServletException {
		// Put your code here
	}
}
