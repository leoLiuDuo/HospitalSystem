package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.dao.Proxy_unit_dao;
import pro.javabean.Proxy_unit;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

public class pro_sup_proxyunit_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_sup_proxyunit_servlet() {
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
		System.out.println("doget");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		System.out.println(ope);
		String proxy_idString=(String) request.getSession().getAttribute("DaiWei_unit");
		
		
		Proxy_unit_dao proxy_dao = new Proxy_unit_dao();
	//	request.setAttribute("proxy_test", proxy_dao.SearchProxy(currentPage-1,proxy_idString));
	
		oper o=oper.valueOf(ope);
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		
		switch (o) {
		case search:
			String DaiWei_unit=request.getParameter("DaiWei_unit");
		    
			HashMap<String,Object> hashMap=null;
			if (DaiWei_unit==null) {
				
				try {
					hashMap=proxy_dao.getProxy(currentPage-1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
			
				DaiWei_unit=new String( DaiWei_unit.getBytes("ISO-8859-1"),"UTF-8");
				hashMap=proxy_dao.SearchProxy(currentPage-1, DaiWei_unit);
			}
			 request.setAttribute("DaiWei_unit", DaiWei_unit);
			 request.setAttribute("proxylist", hashMap.get("proxylist"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/sup/pro/Home_basic_proxy.jsp").forward(request, response);
			break;
		case add:	
			
			try {
				proxy_dao.add(new String(request.getParameter("daiWei_unit").getBytes("ISO-8859-1"),"UTF-8"),new String( request.getParameter("unit_res").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("tel_day"),
						request.getParameter("tel_night"),request.getParameter("mol_tel"),new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("email"),
						request.getParameter("postcode"),new String(request.getParameter("dai_controller").getBytes("ISO-8859-1"),"UTF-8"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("/test_7/pro_sup_proxyunit_servlet.do?currentPage=1&oper=search");
			break;
		case revise:
			proxy_dao.revise(request.getParameter("daiWei_id1"),new String(request.getParameter("daiWei_unit1").getBytes("ISO-8859-1"),"UTF-8"),new String( request.getParameter("unit_res1").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("tel_day1"),
					request.getParameter("tel_night1"),request.getParameter("mol_tel1"),new String(request.getParameter("address1").getBytes("ISO-8859-1"),"UTF-8"),request.getParameter("email1"),
					request.getParameter("postcode1"),new String(request.getParameter("dai_controller1").getBytes("ISO-8859-1"),"UTF-8"));
			     System.out.println("77777");
			response.sendRedirect("/test_7/pro_sup_proxyunit_servlet.do?currentPage=1&oper=search");
			break;
		case delect:
			proxy_dao.delete(request.getParameter("daiWei_id"));
			System.out.println("02"+request.getParameter("daiWei_id"));
			response.sendRedirect("/test_7/pro_sup_proxyunit_servlet.do?currentPage=1&oper=search");
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
	search,add,revise,delect;
}
}
