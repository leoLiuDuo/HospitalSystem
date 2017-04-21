package pro.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.dao.DuoWeiCX_Dao;
import pro.javabean.DuoWeiCX;

public class DuoWeiCXServlet extends HttpServlet {
	
	private DuoWeiCX_Dao duoWeiCX_Dao=new DuoWeiCX_Dao();

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);

	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String method=request.getParameter("method");
      
      switch (method) {
	case "query": query(request,response);break;
	
	case "seeDatil":seeDatil(request,response);break;
	
	

	default:
		break;
	}

	}

	private void seeDatil(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
	private void query(HttpServletRequest request, HttpServletResponse response) {
		List<DuoWeiCX> duoWeiCXs=duoWeiCX_Dao.getAll();
		
		request.setAttribute("duoWeiCXs", duoWeiCXs);
		
		try {
			request.getRequestDispatcher("/sup/pro/forms/zichan.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
