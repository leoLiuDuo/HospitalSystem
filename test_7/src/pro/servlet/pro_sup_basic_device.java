package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.javabean.Measure;
import pro.dao.Sup_pro_basic_device_dao;
import pro.dao.Sup_pro_basic_measure_dao;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;

public class pro_sup_basic_device extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public pro_sup_basic_device() {
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

		int currentPage=1;
		Sup_pro_basic_device_dao device_dao=new Sup_pro_basic_device_dao();                           //新建一个DAO的对象
		String currString=request.getParameter("currentPage");                                     
		String ope=request.getParameter("oper");
		oper o=oper.valueOf(ope);
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		switch (o) { 
		case search:                                                                                   //搜索操作时
			String prod_name=request.getParameter("prod_name");                                        //从搜索框中获取生产厂家名字prod_name
//			String dev_type=request.getParameter("dev_type");                                          //从搜索框中获取设备类型dev_type 
			HashMap<String,Object> hashMap=null;
			if (prod_name.equals("") ){                                                                 //如果搜索框全部为空 
				System.out.println("搜索框为空");
				    try {
						hashMap=device_dao.getDevice_info(currentPage-1);                              //调用DAO层搜索全部数据的方法
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}                                 
				 
			}
			else {	
				System.out.println("搜索框不为空");
				prod_name=new String( prod_name.getBytes("ISO-8859-1"),"UTF-8");
//				dev_type=new String( dev_type.getBytes("ISO-8859-1"),"UTF-8");
				try {
					hashMap=device_dao.SearchDevice_info2(currentPage-1, prod_name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			request.setAttribute("prod_name", prod_name);
			 request.setAttribute("dev_type_list", device_dao.getDev_Type());//设置设备类型
			 request.setAttribute("productor_list", device_dao.getProductor());//设置生产厂家
			 request.setAttribute("measure_list", device_dao.getMeasure());//设置计量单位
			 System.out.println(device_dao.getMeasure());
			 request.setAttribute("Device_info_list", hashMap.get("Device_info_list"));
			 request.setAttribute("totalPage", hashMap.get("totalPage"));
			 request.setAttribute("currentPage", currentPage);
			 request.getRequestDispatcher("/sup/pro/Home_basic_device.jsp").forward(request, response);
			break;
       
		    case add:                                                                                        //添加操作时			

		    String dev_mod=request.getParameter("dev_mod");
		    dev_mod=new String( dev_mod.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String dev_name=request.getParameter("dev_name");
		    dev_name=new String( dev_name.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String meas_id=request.getParameter("meas_id");
		    
		    String dev_type_id=request.getParameter("dev_type_id");
		    dev_type_id=new String( dev_type_id.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String prod_id1=request.getParameter("prod_id");
		    
		  
		    
		    String money=request.getParameter("money");
		    
		    
		    System.out.println("这是servlet中的钱钱"+money);           
		    System.out.println("这是servlet中的设备类型"+dev_type_id);
		    System.out.println("这是servlet中的计量单位id，说明从jsp获取到了"+meas_id);   
		    
			device_dao.add(dev_mod, dev_name, meas_id, dev_type_id,prod_id1, money);
			response.sendRedirect("/test_7/pro_sup_basic_device.do?prod_name=&currentPage=1&oper=search");
			break;
			
			
		case mod:
			String dev_mod2=request.getParameter("dev_mod1");
		    dev_mod2=new String( dev_mod2.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String dev_name2=request.getParameter("dev_name1");
		    dev_name2=new String( dev_name2.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String meas_id2=request.getParameter("meas_id1");

		    String dev_type_id2=request.getParameter("dev_type_id1");
		    dev_type_id2=new String( dev_type_id2.getBytes("ISO-8859-1"),"UTF-8");
		    
		    String prod_id2=request.getParameter("prod_id1");
	
		
		    
		    
		    String money2=request.getParameter("money1");
		    
		    System.out.println("这是servlet中的钱钱"+money2); 
		    
			device_dao.revise(dev_mod2, dev_name2, meas_id2, dev_type_id2, prod_id2, money2);
			
			response.sendRedirect("/test_7/pro_sup_basic_device.do?prod_name=&currentPage=1&oper=search");
			break;
		
		case delect:
			System.out.println("执行删除操作");
			device_dao.delete(request.getParameter("device_mod"));
			response.sendRedirect("/test_7/pro_sup_basic_device.do?prod_name=&currentPage=1&oper=search");
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

