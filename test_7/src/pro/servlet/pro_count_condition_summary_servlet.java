package pro.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.javabean.Count_condition;
import pro.dao.Count_condition_dao;
import pro.javabean.Count_condition_summary;
import pro.dao.Count_condition_summary_dao;
import pro.dao.summary_city_dao;

public class pro_count_condition_summary_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;  
	/**
	 * Constructor of the object.
	 */
	public pro_count_condition_summary_servlet() {
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
		  response.setContentType("text");  
		  response.setCharacterEncoding("utf-8");
		  String cont_name = request.getParameter("cont_name1");
			if(cont_name.equals(""))
				cont_name="%";
			String pro_name = request.getParameter("pro_name1");
			if(pro_name.equals(""))
				pro_name="%";
			String daiwei_unit = request.getParameter("daiwei_unit1");
			if(daiwei_unit.equals(""))
				daiwei_unit="%";
			String asset_type = request.getParameter("dev_type_id1");
			if(asset_type.equals("Y"))
				asset_type="Y%";
			else if(asset_type.equals("W"))
				asset_type="W%";
			else if(asset_type.equals("R"))
				asset_type="R%";
			String dev_appli_sta = request.getParameter("dev_appli_sta1");
			if(dev_appli_sta.equals("请选择"))
				dev_appli_sta="%";
			String asset_attribute = request.getParameter("asset_attribute1");
			if(asset_attribute.equals("请选择"))
				asset_attribute="%";
			String asset_status = request.getParameter("asset_status1");
			if(asset_status.equals("请选择"))
				asset_status="%";
			String asset_use_status = request.getParameter("asset_use_status1");
			if(asset_use_status.equals("请选择"))
				asset_use_status="%";
			String asset_acq_type = request.getParameter("asset_acq_type1");
			if(asset_acq_type.equals("请选择"))
				asset_acq_type="%";
			String city=request.getParameter("city1");
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			
			if(city.equals(""))
			{
				
			ArrayList<Count_condition_summary> counlist=null;
			Count_condition_summary_dao coun_dao=new Count_condition_summary_dao();
			
					counlist=coun_dao.getsummary(cont_name, pro_name, daiwei_unit, asset_type, dev_appli_sta, asset_attribute, asset_status, asset_use_status, asset_acq_type);
			request.setAttribute("counlist", counlist);
			
			ServletContext sc = getServletContext(); 
			RequestDispatcher rd = sc.getRequestDispatcher("/sup/pro/Home_sup_pro_count_condition_summery.jsp"); //定向的页面
			  rd.forward(request, response); 
			}
			else
			{
			
				ArrayList<Count_condition_summary> counlist=null;
				summary_city_dao city_dao=new summary_city_dao();
				counlist=city_dao.getcity(cont_name, pro_name, daiwei_unit, asset_type, dev_appli_sta, asset_attribute, asset_status, asset_use_status, asset_acq_type, city);
				request.setAttribute("counlist", counlist);
				ServletContext sc = getServletContext(); 
				RequestDispatcher rd = sc.getRequestDispatcher("/sup/pro/Home_sup_pro_count_condition_summery.jsp"); //定向的页面
				  rd.forward(request, response); 
			}
		
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
