package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import pro.dao.Forms_baofei_dao;
import pro.dao.Forms_sunhuai_dao;
import pro.dao.Liushi_dao;
import pro.dao.Zaixian_dao;
import pro.dao.Zhuangu_dao;
import pro.dao.Zichan_dao;
import pro.javabean.Baofei;
import pro.javabean.Liushi;
import pro.javabean.Sunhuai;
import pro.javabean.Zhuangu;
import pro.javabean.Zichan;

/**
 * Servlet implementation class ExportDataServlet
 */
@WebServlet("/ExportDataServlet")
public class Export_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Export_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");

		
		 String oper=request.getParameter("oper");
		 ope o=ope.valueOf(oper);
		 
		  response.setHeader("Pragma", "no-cache");
		  response.setHeader("Cache-Control", "no-cache");
		  response.setDateHeader("Expires", 0);

		
		  response.setContentType("application/vnd.ms-excel;charset=utf-8");
response.setCharacterEncoding("gbk");

		  

		  PrintWriter sos = response.getWriter();
switch (o) {
case zichan:
	 String filenameString="搴撳瓨璧勪骇鎶ヨ〃.xls";

	  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
	  String city=request.getParameter("city");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		HashMap<String,Object> hashMap=null;
		int currentPage=1;
		Zichan_dao zichan_dao = new Zichan_dao();
		String currString=request.getParameter("currentPage");
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			hashMap=zichan_dao.Searchzc(currentPage-1, city, startTime, endTime);
			ArrayList<Zichan> Zichan_list=(ArrayList<Zichan>) hashMap.get("Zichan_list");
			sos.println("璧勪骇绫诲瀷\t搴撳瓨璧勪骇\t鎬昏祫浜t搴撳瓨鐜�");
			for(int i=0;i<Zichan_list.size();i++){
				Zichan zichan=Zichan_list.get(i);
				sos.print(zichan.getAsset_type()+"\t");
				sos.print(zichan.getKucun_num()+"\t");
				sos.print(zichan.getAsset_status_sum()+"\t");
				sos.print(zichan.getKucun_rate()+"%\t");
				sos.println();
				}
		}
		
	 
	
	  sos.flush();
	  sos.close();
	break;
case baofei:
	
	filenameString="璧勪骇鎶ュ簾鎶ヨ〃.xls";

	  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
	  Forms_baofei_dao baofei_dao = new Forms_baofei_dao();   
	 city=request.getParameter("city");
		startTime=request.getParameter("startTime");
	currString=request.getParameter("currentPage");
		endTime=request.getParameter("endTime");
		hashMap=null;
		currentPage=1;
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//鍒嗛〉
		}
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			System.out.println(startTime);
			System.out.println(endTime);
			hashMap=baofei_dao.Searchbf(currentPage-1, city, startTime, endTime);
			ArrayList<Baofei>baofei_list=new ArrayList<Baofei>();
			baofei_list=(ArrayList<Baofei>) hashMap.get("baofei_list");
			sos.println("璧勪骇绫诲瀷\t鎶ュ簾璧勪骇鍑�棰漒t璧勪骇鎬婚\t鎶ュ簾鐜�");
			System.out.println(baofei_list);
			for(int i=0;i<baofei_list.size();i++){
				Baofei baofei=baofei_list.get(i);
				sos.print(baofei.getAsset_type()+"\t");
				sos.print(baofei.getBaofei_num()+"\t");
				sos.print(baofei.getAsset_status_sum()+"\t");
				sos.print(baofei.getBaofei_rate()+"%\t");
				sos.println();
				}
		}
		  sos.flush();
		  sos.close();
	break;
	
case liushi:
	filenameString="璧勪骇娴佸け鎶ヨ〃.xls";

	  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
	Liushi_dao liushi_dao=new Liushi_dao();   
	 city=request.getParameter("city");
		startTime=request.getParameter("date1");
	currString=request.getParameter("currentPage");
		endTime=request.getParameter("date2");
		hashMap=null;
		currentPage=1;
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//鍒嗛〉
		}
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			System.out.println(startTime);
			System.out.println(endTime);
			hashMap=liushi_dao.Searchls(currentPage-1, city, startTime, endTime);
			ArrayList<Liushi>Liushi_list=new ArrayList<Liushi>();
			Liushi_list=(ArrayList<Liushi>) hashMap.get("Liushi_list");
			sos.println("璧勪骇绫诲瀷\t娴佸け璧勪骇鍑�棰漒t鎬昏祫浜t娴佸け鐜�");
			for(int i=0;i<Liushi_list.size();i++){
				Liushi liushi=Liushi_list.get(i);
				sos.print(liushi.getAsset_type()+"\t");
				sos.print(liushi.getLiushi_num()+"\t");
				sos.print(liushi.getAsset_status_sum()+"\t");
				sos.print(liushi.getLiushi_rate()+"%\t");
				sos.println();
				}
		}
	
	
	break;
	
case sunhuai:
	filenameString="璧勪骇鎹熷潖鎶ヨ〃.xls";

	  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
	Forms_sunhuai_dao sunhuai_dao=new Forms_sunhuai_dao();   
	 city=request.getParameter("city");
		startTime=request.getParameter("startTime");
	currString=request.getParameter("currentPage");
		endTime=request.getParameter("endTime");
		hashMap=null;
		currentPage=1;
		if(currString!=null){
			currentPage=Integer.valueOf(currString);//鍒嗛〉
		}
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			System.out.println(startTime);
			System.out.println(endTime);
			hashMap=sunhuai_dao.Searchsh(currentPage-1, city, startTime, endTime);
			ArrayList<Sunhuai>sunhuai_list=new ArrayList<Sunhuai>();
			sunhuai_list=(ArrayList<Sunhuai>) hashMap.get("sunhuai_list");
			sos.println("璧勪骇绫诲瀷\t鎹熷潖璧勪骇鍑�棰漒t鎬昏祫浜t鎹熷潖鐜�");
			for(int i=0;i<sunhuai_list.size();i++){
				Sunhuai sunhuai=sunhuai_list.get(i);
				sos.print(sunhuai.getAsset_type()+"\t");
				sos.print(sunhuai.getSunhuai_num()+"\t");
				sos.print(sunhuai.getAsset_status_sum()+"\t");
				sos.print(sunhuai.getSunhuai_rate()+"%\t");
				sos.println();
				}
		}
	
	
	break;
	
case weidaiwei:
	break;
case zaixian:
		filenameString="璧勪骇鍦ㄧ嚎鎶ヨ〃.xls";

	  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
	  city=request.getParameter("city");
		startTime=request.getParameter("startTime");
		endTime=request.getParameter("endTime");
		 hashMap=null;
		currentPage=1;
		Zaixian_dao zaixian_dao = new Zaixian_dao();
		currString=request.getParameter("currentPage");
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		if(city!=""){
			city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println(city);
			
			ArrayList<Zichan> Zichan_list=(ArrayList<Zichan>) hashMap.get("Zaixian_list");
			sos.println("璧勪骇绫诲瀷\t鍦ㄧ嚎璧勪骇\t鎬昏祫浜t鍦ㄧ嚎鐜�");
			for(int i=0;i<Zichan_list.size();i++){
				Zichan zichan=Zichan_list.get(i);
				sos.print(zichan.getAsset_type()+"\t");
				sos.print(zichan.getKucun_num()+"\t");
				sos.print(zichan.getAsset_status_sum()+"\t");
				sos.print(zichan.getKucun_rate()+"%\t");
				sos.println();
				}
		}
		
	 
	
	  sos.flush();
	  sos.close();
	
	break;
case zhuangu:
	filenameString="璧勪骇杞浐鎶ヨ〃.xls";

  response.setHeader("Content-Disposition","attachment;filename="+new String(filenameString.getBytes("gbk"),"ISO-8859-1"));
  city=request.getParameter("city");
	startTime=request.getParameter("date1");
	endTime=request.getParameter("date2");
	 hashMap=null;
	currentPage=1;
	Zhuangu_dao zhuangu_dao = new Zhuangu_dao();
	currString=request.getParameter("currentPage");
	if(currString!=null){
		currentPage=Integer.valueOf(currString);
	}
	if(city!=""){
		city=new String( city.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(city);
		hashMap=zhuangu_dao.Searchzg(currentPage-1, city, startTime, endTime);
		ArrayList<Zhuangu> Zhuangu_list=(ArrayList<Zhuangu>) hashMap.get("Zhuangu_list");
		sos.println("璧勪骇绫诲瀷\t杞浐璧勪骇\t鎬昏祫浜t杞浐鐜�");
		
		System.out.println("======================");
		
		for(int i=0;i<Zhuangu_list.size();i++){
			Zhuangu zhuangu=Zhuangu_list.get(i);
			sos.print(zhuangu.getAsset_type()+"\t");
			sos.print(zhuangu.getZhuangu_num()+"\t");
			sos.print(zhuangu.getAsset_status_sum()+"\t");
			sos.print(zhuangu.getZhuangu_rate()+"%\t");
			sos.println();
			}
	}
	
 

  sos.flush();
  sos.close();

break;
default:
	break;
}
		
		 
	}
	enum ope{
		baofei,liushi,sunhuai,weidaiwei,zaixian,zhuangu,zichan
	}

}
