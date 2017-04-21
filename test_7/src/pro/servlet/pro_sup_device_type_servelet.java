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

import pro.javabean.Dev_type;
import pro.dao.Sup_pro_basic_device_type_dao;
import pro.servlet.ContServlet.oper;
 
public class pro_sup_device_type_servelet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8450086607250279785L;

	/**
	 * Constructor of the object.
	 */
	public pro_sup_device_type_servelet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	 enum oper
		{search,add,del,mod};
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
		String ope=request.getParameter("oper");
		System.out.println(ope);
		oper o=oper.valueOf(ope);
  
		
		String dev_type=request.getParameter("dev_type");
		String dev_type_id=request.getParameter("dev_type_id");
		int currentPage=1;
		String currString="0";
		currString=request.getParameter("currentPage");//得到当前页数 
	 
		 
	 
		ArrayList<Dev_type> dev_type_list=new ArrayList<Dev_type>();
		Sup_pro_basic_device_type_dao dev_type_dao=new Sup_pro_basic_device_type_dao();
		 
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//如果是对象，没有值就不要使用它的一些函数什么
		}
		 switch (o) {
			case search:
			 
				HashMap<String,Object> hashMap=null;
				 
		 	 if(dev_type==""||dev_type==null)
		 	    {
		 	 
					try {
					   dev_type_dao=new Sup_pro_basic_device_type_dao();
					 
					    hashMap=dev_type_dao.search_proall(currentPage-1); 
					   
			 		   
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("有错误");
					}
		 	 }
			 else {
					 
					 
					 dev_type=new String( dev_type.getBytes("ISO-8859-1"),"UTF-8");
 				    hashMap=dev_type_dao.Searchdev(dev_type,currentPage-1);
				     
				}  
		 	
		 	   request.setAttribute("dev_type_list", hashMap.get("dev_type_list"));
			   
			    request.setAttribute("totalPage",  hashMap.get("totalPage"));
			 
			    request.setAttribute("currentPage", currentPage);
			    request.setAttribute("dev_type", dev_type);
				 request.getRequestDispatcher("/sup/pro/Home_basic_device_type.jsp").forward(request, response);
				 
				break;  
			case add:
				  
				 dev_type=new String( dev_type.getBytes("ISO-8859-1"),"UTF-8");
			     dev_type_dao.add( dev_type);
			     response.sendRedirect("/test_7/pro_sup_device_type_servelet.do?dev_type=&oper=search&currentPage=1");
		        break;
			case mod:
				 
				 dev_type=new String( dev_type.getBytes("ISO-8859-1"),"UTF-8");
				 dev_type_dao.revise(dev_type_id, dev_type);
				 response.sendRedirect("/test_7/pro_sup_device_type_servelet.do?dev_type=&oper=search&currentPage=1");
				 break;
			case del:
				 dev_type_id=request.getParameter("dev_type_id1");
				 
				 dev_type_dao.delete(dev_type_id);
				 response.sendRedirect("/test_7/pro_sup_device_type_servelet.do?dev_type=&oper=search&currentPage=1");
			default:
				break;
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

