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

import pro.javabean.Measure;
import pro.dao.Sup_pro_basic_measure_dao;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

public class pro_sup_basic_measure extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public pro_sup_basic_measure() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int currentPage=1;
		Notice_dao naDao=new Notice_dao();
		String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		
		
		Sup_pro_basic_measure_dao measure_dao=new Sup_pro_basic_measure_dao();          //创建一个DAO的对象
		oper o=oper.valueOf(ope);//String转换为枚举
		if(currString!=null){//如果当前页面不是空
			currentPage=Integer.valueOf(currString);
			}
		
		switch (o) {
		case search:                                    
			String meas_name=request.getParameter("meas_name");                         //从搜索框中获取计量单位名字
		
			Measure measure=new Measure();                                              //创建一个JAVABEAN对象
			HashMap<String, Object> hashMap=null;                                       //初始化哈希表
			if (meas_name==("")) {                                                      //如果搜索框内容为空
			                               
				try {
					hashMap=measure_dao.getMeasure(currentPage-1);                      //调用DAO的getMeasure方法
				                                               		
				} catch (SQLException e) {
					e.printStackTrace();
				}				 
			}
			else {	
				
			    meas_name=new String( meas_name.getBytes("ISO-8859-1"),"UTF-8");
				hashMap=measure_dao.SearchMeasure(currentPage-1, meas_name);
			   
			    
				//hashMap.put("meas_id",measure.getMeas_id() );
				//hashMap.put("meas_num", measure.getMeas_name());
				
			}
			request.setAttribute("meas_name", meas_name);
			 request.setAttribute("Measure_list", hashMap.get("Measure_list"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/sup/pro/Home_basic_unit.jsp").forward(request, response);
			
			break;
		case add:
			  String meas_name1=request.getParameter("meas_name");
			   meas_name1=new String(meas_name1.getBytes("ISO-8859-1"),"UTF-8");
	

			   measure_dao.add( meas_name1);			
			   response.sendRedirect("/test_7/pro_sup_basic_measure.do?meas_name=&currentPage=1&oper=search");
			  break;
			  
		case mod:
			String meas_name2=request.getParameter("meas_name1");
			meas_name2=new String(meas_name2.getBytes("ISO-8859-1"),"UTF-8");
			 String meas_id2=request.getParameter("meas_id1");
			 measure_dao.revise(meas_id2,meas_name2);
			 response.sendRedirect("/test_7/pro_sup_basic_measure.do?meas_name=&currentPage=1&oper=search");
			 break;
		
		case delect:
			 measure_dao.delete(request.getParameter("measure_id"));
//			 System.out.println(request.getParameter("measure_id"));
			 response.sendRedirect("/test_7/pro_sup_basic_measure.do?meas_name=&currentPage=1&oper=search");
			 break;
			 
		default:
			break;
		}
	
	}
		

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	public void init() throws ServletException {
		
	}



enum oper{
	search,add,mod,delect;
}
}

