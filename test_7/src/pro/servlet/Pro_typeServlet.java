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

import pro.dao.Pro_type_dao;
import pro.javabean.Pro_type;
import pro.servlet.ContServlet.oper;
 
public class Pro_typeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8450086607250279785L;

	/**
	 * Constructor of the object.
	 */
	public Pro_typeServlet() {
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
		oper o=oper.valueOf(ope);
  
		
		String pro_type=request.getParameter("pro_type");
		String pro_type_id=request.getParameter("pro_type_id");
		int currentPage=1;
		String currString="0";
		currString=request.getParameter("currentPage");//得到当前页数 
		ArrayList<Pro_type> pro_type_list=new ArrayList<Pro_type>();
		Pro_type_dao pro_type_dao=new Pro_type_dao();
		 
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//如果是对象，没有值就不要使用它的一些函数什么
		}
		 switch (o) {
			case search:
			 
				HashMap<String,Object> hashMap=null;
				 
		 	 if("".equals(pro_type)||pro_type==null)
		 	    {
		 	 
					try {
					   pro_type_dao=new Pro_type_dao();
					 
					    hashMap=pro_type_dao.search_proall(currentPage-1); 
					 
					   
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("有错误");
					}
		 	 }
			 else {
					 
				 System.out.println("进入else搜索");
					 pro_type=new String( pro_type.getBytes("ISO-8859-1"),"UTF-8");
					  Pro_type pro=null;
					 request.setAttribute("pro_type", pro_type);
					   try {
						hashMap=pro_type_dao.Searchpro(pro_type,currentPage-1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				    
				  	    
				}  
		  
		 
		     	request.setAttribute("pro_type_list", hashMap.get("pro_type_list"));
			   
		        request.setAttribute("totalPage",  hashMap.get("totalPage"));
		 
		        request.setAttribute("currentPage", currentPage);
				 request.getRequestDispatcher("/sup/pro/Home_basic_proj_type.jsp").forward(request, response);
				 
				break;  
			case add:
				  
				 pro_type=new String( pro_type.getBytes("ISO-8859-1"),"UTF-8");
			     pro_type_dao.add( pro_type);
			     response.sendRedirect("/test_7/Pro_typeServlet.do?pro_type=&oper=search&currentPage=1");
		        break;
			case mod:
				 
				 pro_type=new String( pro_type.getBytes("ISO-8859-1"),"UTF-8");
				 pro_type_dao.revise(pro_type_id, pro_type);
				 response.sendRedirect("/test_7/Pro_typeServlet.do?pro_type=&oper=search&currentPage=1");
				 break;
			case del:
				 pro_type_id=request.getParameter("pro_type_id1");
				 
				 pro_type_dao.delete(pro_type_id);
				 response.sendRedirect("/test_7/Pro_typeServlet.do?pro_type=&oper=search&currentPage=1");
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
