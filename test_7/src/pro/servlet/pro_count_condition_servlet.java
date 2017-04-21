package pro.servlet;
import pro.javabean.Count_condition;
import pro.dao.Count_condition_dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.javabean.Count_condition;

public class pro_count_condition_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_count_condition_servlet() {
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

		doPost(request, response);
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
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String ty=request.getParameter("t");
		
		Count_condition coun=new Count_condition();
		Count_condition_dao coun_dao=new Count_condition_dao();
		ArrayList<Count_condition>counlist=null;
		if(ty.equals("1")){
			coun=coun_dao.getTotalNum("%","%","%","%","%","%","%","%","%");
			counlist=coun_dao.getCityNum("%","%","%","%","%","%","%","%","%");
			request.setAttribute("cont_name1", "%");
			request.setAttribute("pro_name1", "%");
			
			request.setAttribute("daiwei_unit1", "%");
			request.setAttribute("dev_type_id1", "%");
			request.setAttribute("dev_appli_sta1", "%");
			request.setAttribute("asset_attribute1", "%");
			request.setAttribute("asset_status1", "%");
			request.setAttribute("asset_use_status1", "%");
			request.setAttribute("asset_acq_type1", "%");
		}
		else{
			String cont_name = request.getParameter("cont_name");
			cont_name=new String(cont_name.getBytes("iso-8859-1"),"utf-8");
			if(cont_name.equals(""))
				cont_name="%";
			String pro_name = request.getParameter("pro_name");
			if(pro_name.equals(""))
				pro_name="%";
			String daiwei_unit = request.getParameter("daiwei_unit");
			if(daiwei_unit.equals(""))
				daiwei_unit="%";
			String asset_type = request.getParameter("asset_type");
			if(asset_type.equals("Y"))
				asset_type="Y%";
			else if(asset_type.equals("W"))
				asset_type="W%";
			else if(asset_type.equals("R"))
				asset_type="R%";
			String dev_appli_sta = request.getParameter("dev_appli_sta");
			dev_appli_sta=new String(dev_appli_sta.getBytes("iso-8859-1"),"utf-8");
			
			if(dev_appli_sta.equals("请选择"))
				dev_appli_sta="%";
			String asset_attribute = request.getParameter("asset_attribute");
			asset_attribute=new String(asset_attribute.getBytes("iso-8859-1"),"utf-8");
			if(asset_attribute.equals("请选择"))
				asset_attribute="%";
			String asset_status = request.getParameter("asset_status");
			asset_status=new String(asset_status.getBytes("iso-8859-1"),"utf-8");
			if(asset_status.equals("请选择"))
				asset_status="%";
			String asset_use_status = request.getParameter("asset_use_status");
			asset_use_status=new String(asset_use_status.getBytes("iso-8859-1"),"utf-8");
			if(asset_use_status.equals("请选择"))
				asset_use_status="%";
			String asset_acq_type = request.getParameter("asset_acq_type");
			asset_acq_type=new String(asset_acq_type.getBytes("iso-8859-1"),"utf-8");
			if(asset_acq_type.equals("请选择"))
				asset_acq_type="%";
			request.setAttribute("cont_name1", cont_name);
			request.setAttribute("pro_name1", pro_name);
			request.setAttribute("daiwei_unit1", daiwei_unit);
			request.setAttribute("dev_type_id1", asset_type);
			request.setAttribute("dev_appli_sta1", dev_appli_sta);
			request.setAttribute("asset_attribute1", asset_attribute);
			request.setAttribute("asset_status1", asset_status);
			request.setAttribute("asset_use_status1", asset_use_status);
			request.setAttribute("asset_acq_type1", asset_acq_type);
		coun=coun_dao.getTotalNum(cont_name, pro_name, daiwei_unit, asset_type, dev_appli_sta, asset_attribute, asset_status, asset_use_status, asset_acq_type);
		System.out.println(cont_name);
		System.out.println(pro_name);
		System.out.println(daiwei_unit);
		System.out.println(asset_type);
		System.out.println(dev_appli_sta);
		System.out.println(asset_attribute);
		System.out.println(asset_status);
		System.out.println(asset_use_status);
		System.out.println(asset_acq_type);
		counlist=coun_dao.getCityNum(cont_name, pro_name, daiwei_unit, asset_type, dev_appli_sta, asset_attribute, asset_status, asset_use_status, asset_acq_type);
		}
		
		request.setAttribute("coun",coun);
		System.out.println(counlist.get(0).getCity_id());
		request.setAttribute("counlist", counlist);
		request.getRequestDispatcher("/sup/pro/Home_sup_pro_count_condition.jsp").forward(request, response);
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
