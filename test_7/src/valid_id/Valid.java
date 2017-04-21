package valid_id;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Notice_admin.User_dao;
import pro.dao.Cont_dao;

public class Valid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Valid() {
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
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String jsp=request.getParameter("jsp");
		
		if ("cont".equals(jsp)) {
			
			Cont_dao cont_dao=new Cont_dao();
			String cont_id=request.getParameter("cont_id");
			System.out.println(cont_id);
			if (cont_dao.Searchcont_id(cont_id)) {
				out.print("合同编号重复或者输入不合法，请重新输入");
			}
		}else {
			User_dao user_dao=new User_dao();
			String user_id=request.getParameter("user_id");
		
			try {
				if (user_dao.SearchUser_id(user_id)) {
					
					out.print("用户名重复或者输入不合法，请重新输入");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
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
