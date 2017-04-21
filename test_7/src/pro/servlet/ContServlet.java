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



import pro.dao.Cont_dao;
import pro.javabean.Contract;

public class ContServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ContServlet() {
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
		int currentPage=1;
		Cont_dao cont_Dao=new Cont_dao(); 
		String currString="0";
		currString=request.getParameter("currentPage");//得到当前页数
		String ope=request.getParameter("oper");
		oper o=oper.valueOf(ope); 
		String cont_name= request.getParameter("cont_name");
		 
		String cont_id=request.getParameter("cont_id");
		String buy_date=request.getParameter("buy_date");
		String start_date=request.getParameter("start_date");
	 
		Contract cont=new Contract();
		ArrayList<Contract> contlist=new ArrayList<Contract>();
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
	 
		 switch (o) {
			case search:
			 
				HashMap<String,Object> hashMap=null;
			 
		 
		 	 if(cont_name==""||cont_name==null)
		 	 {
		 	 
					try {
						hashMap=cont_Dao.getCont(currentPage-1);
						
						request.setAttribute("cont_name", cont_name);
					    request.setAttribute("contlist", hashMap.get("contlist"));
					    request.setAttribute("totalPage",  hashMap.get("totalPage"));
					    request.setAttribute("currentPage", currentPage);
					 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 	 }
			 else {
					 
					 cont_name=new String( cont_name.getBytes("ISO-8859-1"),"UTF-8");
					 hashMap=cont_Dao.Searchcont(cont_name,currentPage-1);
					 request.setAttribute("cont_name", cont_name);
					   request.setAttribute("contlist", hashMap.get("contlist"));
					    request.setAttribute("totalPage",  hashMap.get("totalPage"));
					    request.setAttribute("currentPage", currentPage);
				}  
				 request.getRequestDispatcher("/sup/pro/Home_basic_cont.jsp").forward(request, response);
				break;  
			case add:
				 cont_name=new String( cont_name.getBytes("ISO-8859-1"),"UTF-8");
			     cont_Dao.add(cont_id, cont_name, buy_date, start_date);
			     response.sendRedirect("/test_7/ContServlet.do?cont_name=&currentPage=1&oper=search");
		        break;
			case mod:
				 
				 cont_name=new String( cont_name.getBytes("ISO-8859-1"),"UTF-8");
				 cont_Dao.revise(cont_id, cont_name, buy_date, start_date);
				 response.sendRedirect("/test_7/ContServlet.do?cont_name=&currentPage=1&oper=search");
				 break;
			case delect:
				String cont_id1=request.getParameter("cont_id1");
				 
				 cont_Dao.delete(cont_id1);
				 response.sendRedirect("/test_7/ContServlet.do?cont_name=&currentPage=1&oper=search");
			default:
				break;
			}
		
			 

	}
	enum oper{
		search,add,mod,delect;
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
