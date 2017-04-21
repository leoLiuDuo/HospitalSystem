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
import pro.dao.Sup_pro_basic_productor_dao;
import pro.javabean.*;

public class pro_sup_productor_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_sup_productor_servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage=1;
		Notice_dao naDao=new Notice_dao();
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		String prod_idString=(String) request.getSession().getAttribute("prod_id");
	
		Sup_pro_basic_productor_dao prod_dao = new Sup_pro_basic_productor_dao();
//		request.setAttribute("user_person", prod_dao.SearchUser(user_idString));
		
		oper o=oper.valueOf(ope);//String转换为枚举
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		
		switch (o) {
		case search:
			String prod_name=request.getParameter("prod_name");
			HashMap<String,Object> hashMap=null;
			if (prod_name==""){
				try {
//					System.out.println(pro_dao);
					hashMap=prod_dao.getProductor(currentPage-1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				prod_name=new String( prod_name.getBytes("ISO-8859-1"),"UTF-8");
				hashMap=prod_dao.SearchProductor(currentPage-1,prod_name);
			}
			 request.setAttribute("prod_name", prod_name);
			 request.setAttribute("Productor_list", hashMap.get("Productor_list"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/sup/pro/Home_basic_product.jsp").forward(request, response);
			break;
		case add:
			prod_dao.add(new String( request.getParameter("prod_name").getBytes("ISO-8859-1"),"UTF-8"));
			response.sendRedirect("/test_7/pro_sup_productor_servlet.do?prod_name=&currentPage=1&oper=search");
			break;
		case mod:
			System.out.println("test prod id:"+request.getParameter("prod_id1"));
			System.out.println("test prod name:"+new String( request.getParameter("prod_name1").getBytes("ISO-8859-1"),"UTF-8"));
			prod_dao.revise(request.getParameter("prod_id1"),new String( request.getParameter("prod_name1").getBytes("ISO-8859-1"),"UTF-8"));
			response.sendRedirect("/test_7/pro_sup_productor_servlet.do?prod_name=&currentPage=1&oper=search");
			break;
		case delect:
			System.out.println(request.getParameter("productid"));
			prod_dao.delete(request.getParameter("productid"));
			response.sendRedirect("/test_7/pro_sup_productor_servlet.do?prod_name=&currentPage=1&oper=search");
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
