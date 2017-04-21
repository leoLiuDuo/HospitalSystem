package admin.Notice_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.lang.model.element.Element;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






import DB.DBManager;

public class Noticeservlet extends HttpServlet {
	public Noticeservlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_idString=(String) request.getSession().getAttribute("user_id");
		HttpSession session=request.getSession();
		String role_id=(String) session.getAttribute("role_id");//用户角色
		String user_id=(String) session.getAttribute("user_id");//用户名
		String city_id =(String) session.getAttribute("city_id");//用户城市
		String pageString=request.getParameter("page");
		int currentPage=1;
		Page page=Page.valueOf(pageString);
		Notice_dao naDao=new Notice_dao();
		User_dao user_dao = new User_dao();
		request.getSession().setAttribute("user_person", user_dao.SearchUser(user_idString));
		String currString=request.getParameter("currentPage");
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		switch (page) {
		case ShowRank:
		HashMap<String, Object> maplist= naDao.search_list(currentPage-1);
		ArrayList<Rank_list> arrayList=(ArrayList<Rank_list>) maplist.get("arrayList");
		int TotalPage=(Integer)maplist.get("totalPage") ;
		request.setAttribute("Rank_list", arrayList);
		request.setAttribute("TotalPage", TotalPage);
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/admin/ShowRank.jsp").forward(request, response);
			break;
		case Record:
			HashMap<String, Object> maplist1= naDao.getRecord(currentPage-1);
			ArrayList<Record> arrayList1=(ArrayList<Record>) maplist1.get("arrayList");
			int TotalPage1=(Integer)maplist1.get("totalPage") ;
			request.setAttribute("Rank_list1", arrayList1);
			request.setAttribute("TotalPage", TotalPage1);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/admin/Record.jsp").forward(request, response);
				break;
		case ShowOutdate:
			HashMap<String, Object> maplist2;
			try {
				if ("r1".equals(role_id)||"r2".equals(role_id)||"r4".equals(role_id)) {
					maplist2 = naDao.getShowOutdate(currentPage-1);
				}
				else {
					maplist2=naDao.getShowOutdate(currentPage-1, city_id);
				}
				
				ArrayList<ShowOutdate> arrayList2=(ArrayList<ShowOutdate>) maplist2.get("ShowOutdate_list");
				int TotalPage21=(Integer)maplist2.get("totalPage") ;
				request.setAttribute("ShowOutdate_list", arrayList2);
				request.setAttribute("TotalPage", TotalPage21);
				request.setAttribute("currentPage", currentPage);
				request.getRequestDispatcher("/admin/ShowOutdate.jsp").forward(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
				break;
		case ShowEdit:
			HashMap<String, Object> maplist3=new HashMap<String, Object>() ;
			try {
				if ("r2".equals(role_id)||"r4".equals(role_id)) {
					maplist3 = naDao.getEdit(currentPage-1,"");
				}
				else {
					maplist3 = naDao.getEdit(currentPage-1,city_id);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<ShowEdit> arrayList3=(ArrayList<ShowEdit>) maplist3.get("showlist");
			int TotalPage3=(Integer)maplist3.get("totalPage") ;
		
			request.setAttribute("showedit", arrayList3);
			request.setAttribute("TotalPage", TotalPage3);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("/admin/ShowEdit.jsp").forward(request, response);
				break;
				
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
		
	}

}
enum Page{
	Record,ShowEdit,ShowOutdate,ShowRank;
}

