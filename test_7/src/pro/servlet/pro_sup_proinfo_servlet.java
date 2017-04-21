package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;
import pro.dao.Sup_pro_basic_proinfo_dao;
import pro.javabean.*;

public class pro_sup_proinfo_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_sup_proinfo_servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8");
		  request.setCharacterEncoding("UTF-8");
		
		int currentPage=1;
		Notice_dao naDao=new Notice_dao();
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
	
		
		Sup_pro_basic_proinfo_dao pro_dao = new Sup_pro_basic_proinfo_dao();
//		request.setAttribute("proinfo_test", pro_dao.SearchProinfo(currentPage-1,pro_idString));
		
		oper o=oper.valueOf(ope);//String转换为枚举
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		
		switch (o) {
		case search:
			
			String pro_type=request.getParameter("dev_type");
			HashMap<String,Object> hashMap=null;
			if (pro_type==""){
				try {
//					System.out.println(pro_dao);
					hashMap=pro_dao.getProinfo(currentPage-1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				pro_type=new String( pro_type.getBytes("ISO-8859-1"),"UTF-8");
				hashMap=pro_dao.SearchProinfo(currentPage-1,pro_type);
			}
			 request.setAttribute("dev_type", pro_type);
			request.setAttribute("type_list", pro_dao.getType());
			 request.setAttribute("Proinfo_list", hashMap.get("Proinfo_list"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/sup/pro/Home_basic_proj.jsp").forward(request, response);
			break;
		case add:
			pro_dao.add(request.getParameter("pro_id"),request.getParameter("pro_type_id"),new String( request.getParameter("pro_name").getBytes("ISO-8859-1"),"UTF-8"),new String( request.getParameter("aproval_num").getBytes("ISO-8859-1"),"UTF-8"));
			response.sendRedirect("/test_7/pro_sup_proinfo_servlet.do?dev_type=&currentPage=1&oper=search");
			break;
		case mod:
			pro_dao.revise(request.getParameter("pro_id1"),request.getParameter("pro_type_id1"),new String( request.getParameter("pro_name1").getBytes("ISO-8859-1"),"UTF-8"),new String( request.getParameter("aproval_num1").getBytes("ISO-8859-1"),"UTF-8"));
			response.sendRedirect("/test_7/pro_sup_proinfo_servlet.do?dev_type=&currentPage=1&oper=search");
			break;
		case delect:
			pro_dao.delete(request.getParameter("projectid"));
			response.sendRedirect("/test_7/pro_sup_proinfo_servlet.do?dev_type=&currentPage=1&oper=search");
			break;
		default:
			break;
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	enum oper{
		search,add,mod,delect;
	}
}
