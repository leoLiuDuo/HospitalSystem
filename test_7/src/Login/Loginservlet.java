package Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import admin.Notice_admin.User;
import admin.Notice_admin.User_dao;
import DB.DBManager;

public class Loginservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Loginservlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 判斷是否存在一個用戶
		 */
		boolean j=false;
		String Username= request.getParameter("Username");
		String Password=request.getParameter("Password");
		
		HttpSession session=request.getSession();
		if(Username!=null&&Password!=null){
			
			UserLogin user =new UserLogin();
			user.setUser_id(Username);
			user.setPassword(Password);
			UserLogin_dao user_dao=new UserLogin_dao();
			HashMap<String, Object> hashMap;
			try {
				hashMap = user_dao.search(user);
				String position = (String) hashMap.get("position");
				String sex=(String) hashMap.get("sex");
				/*
				 * 執行dao層的語句,返回的是hashmap对象，存着role_id的值和执行结果布尔值
				 * 如果说查询数据库存在这个用，返回查询结果，并且将用户的属性带出来
				 */
				
				if((Boolean)hashMap.get("judge")){		
					
					session.setAttribute("user_id", user.getUser_id());//存着用户名
					session.setAttribute("role_id", position);//存着用户权限
					session.setAttribute("city_id", sex);//存着用户城市
					String user_idString=(String) request.getSession().getAttribute("user_id");
					User_dao user_dao1 = new User_dao();
					request.getSession().setAttribute("user_person", user_dao1.SearchUser(user_idString));
					//修改登录次数
					
						String pathurl="/test_7/pro_sup_servlet.do?currentPage=1&oper=search";
						session.setAttribute("pathurl", pathurl);
						response.sendRedirect("/test_7/login/User_modservlet?oper=mod");
					
				}
				/*
				 * 如果不存在
				 */
				else{

					request.setAttribute("error", "登录密码或者用户不存在");
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
				}
				
			} catch (SQLException e) {
				System.out.println("Login查找用户异常");
				e.printStackTrace();
			}	
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
