package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User_dao;
import pro.dao.show_data_dao;
import pro.javabean.*;

public class show_data_servlet extends HttpServlet {

	public show_data_servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		//int currentPage=1;
		//Notice_dao naDao=new Notice_dao();
		//String currString=request.getParameter("currentPage");
		String ope=request.getParameter("oper");
		show_data_dao sddao = new show_data_dao();
		
		oper o=oper.valueOf(ope);//String转换为枚举
//		if(currString!=null){
//			currentPage=Integer.valueOf(currString);
//		}
		HashMap<String,Object> hashMap=null;
		try {
			hashMap=sddao.getData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("totalPatients",hashMap.get("totalPatients") );
		request.setAttribute("totalExamRecords",hashMap.get("totalExamRecords") );
		request.setAttribute("totalTestRecords",hashMap.get("totalTestRecords") );
		
		
		switch (o) {
		case year:
			try {
				hashMap=sddao.getYear();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("m1",hashMap.get("m1") );
			request.setAttribute("m2",hashMap.get("m2") );
			request.setAttribute("m3",hashMap.get("m3") );
			request.setAttribute("m4",hashMap.get("m4") );
			request.setAttribute("m5",hashMap.get("m5") );
			request.setAttribute("m6",hashMap.get("m6") );
			request.setAttribute("m7",hashMap.get("m7") );
			request.setAttribute("m8",hashMap.get("m8") );
			request.setAttribute("m9",hashMap.get("m9") );
			request.setAttribute("m10",hashMap.get("m10") );
			request.setAttribute("m11",hashMap.get("m11") );
			request.setAttribute("m12",hashMap.get("m12") );
			request.setAttribute("oper", ope);
			request.getRequestDispatcher("/sup/pro/Home_pro_sup.jsp").forward(request, response);
			break;
		case month:
			try {
				hashMap=sddao.getMonth();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int[] a=(int[]) hashMap.get("dnum");
			request.setAttribute("dnum0", a[0]);request.setAttribute("dnum1", a[1]);request.setAttribute("dnum2", a[2]);
			request.setAttribute("dnum3", a[3]);request.setAttribute("dnum4", a[4]);request.setAttribute("dnum5", a[5]);
			request.setAttribute("dnum6", a[6]);request.setAttribute("dnum7", a[7]);request.setAttribute("dnum8", a[8]);
			request.setAttribute("dnum9", a[9]);request.setAttribute("dnum10", a[10]);request.setAttribute("dnum11", a[11]);
			request.setAttribute("dnum12", a[12]);request.setAttribute("dnum13", a[13]);request.setAttribute("dnum14", a[14]);
			request.setAttribute("dnum15", a[15]);request.setAttribute("dnum16", a[16]);request.setAttribute("dnum17", a[17]);
			request.setAttribute("dnum18", a[18]);request.setAttribute("dnum19", a[19]);request.setAttribute("dnum20", a[20]);
			request.setAttribute("dnum21", a[21]);request.setAttribute("dnum22", a[22]);request.setAttribute("dnum23", a[23]);
			request.setAttribute("dnum24", a[24]);request.setAttribute("dnum25", a[25]);request.setAttribute("dnum26", a[26]);
			request.setAttribute("dnum27", a[27]);request.setAttribute("dnum28", a[28]);request.setAttribute("dnum29", a[29]);
			Calendar cal=Calendar.getInstance();
			request.setAttribute("ddnum0", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum1", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum2", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum3", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum4", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum5", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum6", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum7", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum8", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum9", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum10", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum11", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum12", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum13", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum14", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum15", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum16", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum17", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum18", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum19", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum20", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum21", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum22", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum23", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum24", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum25", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum26", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum27", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum28", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			request.setAttribute("ddnum29", String.valueOf(cal.get(Calendar.YEAR))+"."+String.valueOf(cal.get(Calendar.MONTH)+1)+"."+String.valueOf(cal.get(Calendar.DATE)));cal.add(Calendar.DATE,-1);
			
			
			request.setAttribute("oper", ope);
			request.getRequestDispatcher("/sup/pro/Home_pro_sup.jsp").forward(request, response);
			break;
		case week:
			try {
				hashMap=sddao.getWeek();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int[] a1=(int[]) hashMap.get("dnum");
			request.setAttribute("dnum0", a1[0]);request.setAttribute("dnum1", a1[1]);request.setAttribute("dnum2", a1[2]);
			request.setAttribute("dnum3", a1[3]);request.setAttribute("dnum4", a1[4]);request.setAttribute("dnum5", a1[5]);
			request.setAttribute("dnum6", a1[6]);
			
			Calendar cal1=Calendar.getInstance();
			request.setAttribute("ddnum0", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum1", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum2", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum3", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum4", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum5", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			request.setAttribute("ddnum6", String.valueOf(cal1.get(Calendar.YEAR))+"."+String.valueOf(cal1.get(Calendar.MONTH)+1)+"."+String.valueOf(cal1.get(Calendar.DATE)));cal1.add(Calendar.DATE,-1);
			
			request.setAttribute("oper", ope);
			request.getRequestDispatcher("/sup/pro/Home_pro_sup.jsp").forward(request, response);
			//response.sendRedirect("/test_7/pro_sup_proinfo_servlet.do?dev_type=&currentPage=1&oper=search");
			break;
		default:
			break;
		}
		
	}
	
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
	enum oper{
		year,month,week;
	}
}
