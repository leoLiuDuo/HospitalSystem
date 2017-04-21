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
import pro.dao.Liushi_dao;


public class Liushi_servlet extends HttpServlet {
	public Liushi_servlet() {
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
		Liushi_dao liushi_dao=new Liushi_dao();      
		HttpSession session=request.getSession();
		
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//分页
		}

		String city=request.getParameter("city");
		String startTime=request.getParameter("date1");
		System.out.println(startTime);
		String endTime=request.getParameter("date2");
		HashMap<String,Object> hashMap=null;
        String city_id=(String) request.getSession().getAttribute("city_id");
		String role_id=(String) request.getSession().getAttribute("role_id");
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			System.out.println(startTime);
			System.out.println(endTime);
			hashMap=liushi_dao.Searchls(currentPage-1, city, startTime, endTime);
			request.setAttribute("Liushi_list", hashMap.get("Liushi_list"));
			request.setAttribute("totalPage", hashMap.get("totalPage"));
		}
		request.setAttribute("city",city);
		request.setAttribute("startTime",startTime);
		request.setAttribute("endTime",endTime);
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/sup/pro/forms/liushi.jsp").forward(request, response);
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
	
	
}
