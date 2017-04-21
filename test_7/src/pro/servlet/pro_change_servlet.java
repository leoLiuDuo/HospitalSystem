package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.javabean.Count_change;
import pro.dao.Count_change_dao;

public class pro_change_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_change_servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int currentPage=1;
		String currString=request.getParameter("currentPage");
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		Count_change_dao change=new Count_change_dao();
		ArrayList<Count_change>change_list=new ArrayList<Count_change>();
		HashMap<String,Object> hashMap=null;
		String date_start=request.getParameter("date_start1");
		String date_end=request.getParameter("date_end1");
		
		hashMap=change.detail(currentPage-1,date_start, date_end);
		request.setAttribute("date_start",date_start);
		request.setAttribute("date_end",date_end);
		request.setAttribute("totalPage", hashMap.get("totalPage"));
		request.setAttribute("change_list",hashMap.get("change_list"));
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/sup/pro/Home_sup_pro_count_change_specific.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
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
