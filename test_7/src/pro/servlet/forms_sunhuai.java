package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.dao.Forms_sunhuai_dao;
import pro.dao.Sup_pro_basic_device_dao;
import pro.servlet.pro_sup_basic_device.oper;

public class forms_sunhuai extends HttpServlet {
	public forms_sunhuai() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		Forms_sunhuai_dao sunhuai_dao=new Forms_sunhuai_dao();      
		HttpSession session=request.getSession();
		oper o=oper.valueOf(ope);//String转换成枚举
		
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//分页
		}
		
		switch(o){
		case search:
			
		    String city=request.getParameter("city");
			String startTime=request.getParameter("startTime");
			System.out.println(startTime);
			String endTime=request.getParameter("endTime");
			HashMap<String,Object> hashMap=null;
            String city_id=(String) request.getSession().getAttribute("city_id");
			String role_id=(String) request.getSession().getAttribute("role_id");
			if(city!=""){
				city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(city);
				System.out.println(startTime);
				System.out.println(endTime);
				hashMap=sunhuai_dao.Searchsh(currentPage-1, city, startTime, endTime);
				request.setAttribute("sunhuai_list", hashMap.get("sunhuai_list"));
				request.setAttribute("totalPage", hashMap.get("totalPage"));
			}
			request.setAttribute("city",city);
			request.setAttribute("startTime",startTime);
			request.setAttribute("endTime",endTime);
			request.setAttribute("city_list", sunhuai_dao.getCity());
			request.setAttribute("currentPage", currentPage);
			System.out.println("inservlet");
			request.getRequestDispatcher("/sup/pro/forms/sunhuai.jsp").forward(request, response);
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
