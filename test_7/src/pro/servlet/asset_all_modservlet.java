package pro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.dao.Asset_info_dao;
import pro.dao.Cont_dao;
import pro.dao.Proxy_unit_dao;
import pro.dao.Sup_pro_basic_device_dao;
import pro.dao.Sup_pro_basic_proinfo_dao;
import pro.dao.Valid_asset_dao;
import pro.javabean.Assert_info;
import pro.javabean.Asset_change;
import pro.javabean.Contract;
import pro.javabean.Pro_info;
import pro.javabean.Proxy_unit;
import pro.javabean.Valid_asset;
import pro.servlet.pro_sup_proinfo_servlet.oper;
import admin.Notice_admin.Notice_dao;
import admin.Notice_admin.User;
import admin.Notice_admin.User_dao;


public class asset_all_modservlet extends HttpServlet {

	
	public asset_all_modservlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int currentPage=1;
		
		  response.setContentType("text/html;charset=utf-8");
		  request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		Asset_info_dao asset_info_dao=new Asset_info_dao();
		String ope=request.getParameter("oper");
		String method=request.getParameter("method");
		info o=info.valueOf(ope);
		String currString=request.getParameter("currentPage");
		User_dao user_dao = new User_dao();
		String asset_id=(String) request.getSession().getAttribute("asset_id");
		if(currString!=null){
			currentPage=Integer.valueOf(currString);
		}
		switch (o) {
		case user_mod:
			if("search".equals(method)){
				User user=new User();
				try {
					user=asset_info_dao.get_user(asset_id);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				String user_id1=request.getParameter("user_id1");
				String user_name=request.getParameter("user_name");
				String city_id=(String) request.getSession().getAttribute("city_id");
			
				String role_id=(String) request.getSession().getAttribute("role_id");
				if ("r2".equals(role_id)||"r4".equals(role_id)) {
					city_id="";
				}
				HashMap<String,Object> hashMap=null;
				if (user_id1==null||user_name==null) {
					
					try {
						hashMap=user_dao.getUser(currentPage-1,city_id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					user_name=new String( user_name.getBytes("ISO-8859-1"),"UTF-8");
					hashMap=user_dao.SearchUser(currentPage-1, user_id1, user_name,city_id);
				}
				ArrayList<User> arrayList=(ArrayList<User>) hashMap.get("userlist");
				for(int i=0;i<arrayList.size();i++){
					if(arrayList.get(i).getUser_id().equals(user.getUser_id())){
						arrayList.remove(i);
						break;
					}
				}
				 request.setAttribute("user_id1",user_id1);
				 request.setAttribute("user_name", user_name);
				 request.setAttribute("current_user", user);
				 request.setAttribute("userlist", hashMap.get("userlist"));
				 request.setAttribute("totalPage", hashMap.get("totalPage"));
				 request.setAttribute("currentPage", currentPage);
				 request.getRequestDispatcher("/sup/pro/user_mod2.jsp").forward(request, response);
			}else if ("mod".equals(method)) {
				asset_id=(String) request.getSession().getAttribute("asset_id");
				String user_id=request.getParameter("user_id");
				Asset_info_dao assert_info_dao=new Asset_info_dao();
				long time=session.getLastAccessedTime();
				Date date=new Date(time); 
				SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String   date1   =   sDateFormat.format(date);
				String user=(String) session.getAttribute("user_id");
				String ip=request.getRemoteAddr();
				try {
					
					assert_info_dao.mod_user(asset_id, user_id,date1, user, ip);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.sendRedirect("/test_7/asset_all_modservlet.do?oper=user_mod&method=search&currentPage=1");
			}
					
		
			break;
			//商务合同
			case cont_mod:
				String cont_name= request.getParameter("cont_name");
				ArrayList<Contract> contlist=new ArrayList<Contract>();
				Contract contract=new Contract();
				if("search".equals(method)){
					
					HashMap<String,Object> hashMap=null;
					Contract cont=new Contract();
					Cont_dao cont_Dao=new Cont_dao(); 
					contract=cont_Dao.getsinglecont(asset_id);
			 	 if(cont_name==""||cont_name==null)
			 	 {
						try {
							hashMap=cont_Dao.getCont(currentPage-1);
							ArrayList<Contract> arrayList=(ArrayList<Contract>) hashMap.get("contlist");
							for(int i=0;i<arrayList.size();i++){
								if(arrayList.get(i).getCont_id().equals(contract.getCont_id())){
									arrayList.remove(i);
									break;
								}
							}
							 request.setAttribute("cont_name", cont_name);
							
						    request.setAttribute("contlist", arrayList);
						    request.setAttribute("totalPage",  hashMap.get("totalPage"));
						    request.setAttribute("currentPage", currentPage);
						    request.setAttribute("contract", contract);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 	 }
				 else {
						 cont_name=new String( cont_name.getBytes("ISO-8859-1"),"UTF-8");
						 hashMap=cont_Dao.Searchcont(cont_name,currentPage-1);
						  ArrayList<Contract> arrayList=(ArrayList<Contract>) hashMap.get("contlist");
							for(int i=0;i<arrayList.size();i++){
								if(arrayList.get(i).getCont_id().equals(contract.getCont_id())){
									arrayList.remove(i);
									break;
								}
							}
							 request.setAttribute("cont_name", cont_name);
						   request.setAttribute("contlist", arrayList);
						    request.setAttribute("totalPage",  hashMap.get("totalPage"));
						    request.setAttribute("currentPage", currentPage);
						    request.setAttribute("contract", contract);
					}  
					 request.getRequestDispatcher("/sup/pro/cont_mod2.jsp").forward(request, response);
					}
					else{
						String cont_id=request.getParameter("cont_id");
						Asset_info_dao assert_info_dao=new Asset_info_dao();
						long time=session.getLastAccessedTime();
						Date date=new Date(time); 
						SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String   date1   =   sDateFormat.format(date);
						String user=(String) session.getAttribute("user_id");
						String ip=request.getRemoteAddr();
						try {
							assert_info_dao.mod_cont(asset_id,cont_id,date1,user,ip);
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						response.sendRedirect("/test_7/asset_all_modservlet.do?oper=cont_mod&method=search&currentPage=1");
					}
					
				
			
				break;
				//代维单位
			case proxy_mod:
				String DaiWei_unit= request.getParameter("DaiWei_unit");
				Proxy_unit_dao proxy_dao = new Proxy_unit_dao();
				Proxy_unit proxy_unit=new Proxy_unit();
			try {
				proxy_unit=proxy_dao.get_asset_proxy_ubit(asset_id);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
				if("search".equals(method)){
					HashMap<String,Object> hashMap=null;
					if (DaiWei_unit==null) {
						try {
							hashMap=proxy_dao.getProxy(currentPage-1);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
					
						DaiWei_unit=new String( DaiWei_unit.getBytes("ISO-8859-1"),"UTF-8");
						hashMap=proxy_dao.SearchProxy(currentPage-1, DaiWei_unit);
					}
					request.setAttribute("proxy_unit", proxy_unit);
					  ArrayList<Proxy_unit> arrayList=(ArrayList<Proxy_unit>) hashMap.get("proxylist");
						for(int i=0;i<arrayList.size();i++){
							if(arrayList.get(i).getDaiwei_id().equals(proxy_unit.getDaiwei_id())){
								arrayList.remove(i);
								break;
							}
						}
						request.setAttribute("DaiWei_unit", DaiWei_unit);
						
					 request.setAttribute("proxylist",arrayList );
					 request.setAttribute("totalPage", hashMap.get("totalPage"));
					 request.setAttribute("currentPage", currentPage);
					 request.getRequestDispatcher("/sup/pro/proxy_mod2.jsp").forward(request, response);
			 	 }
				 else {
					 
					 String daiwei_id=request.getParameter("daiwei_id");
						
						Asset_info_dao assert_info_dao=new Asset_info_dao();
						long time=session.getLastAccessedTime();
						Date date=new Date(time); 
						SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String   date1   =   sDateFormat.format(date);
						String user=(String) session.getAttribute("user_id");
						String ip=request.getRemoteAddr();
						try {
							assert_info_dao.mod_proxy_unit(asset_id, daiwei_id,date1, user, ip);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						response.sendRedirect("/test_7/asset_all_modservlet.do?oper=proxy_mod&method=search&currentPage=1");
					 
				 }
				
			
				break;
				
				
				//项目信息：
			case proj_mod:
				Sup_pro_basic_proinfo_dao pro_dao = new Sup_pro_basic_proinfo_dao();

				if(currString!=null){
					currentPage=Integer.valueOf(currString);
				}
				
				if("search".equals(method)){
					String pro_type=request.getParameter("dev_type");
					HashMap<String,Object> hashMap=null;
					Pro_info pro_info=new Pro_info();
					try {
						pro_info=pro_dao.get_asset_Proinfo(asset_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (pro_type==""||pro_type==null){
						try {
//							System.out.println(pro_dao);
							hashMap=pro_dao.getProinfo(currentPage-1);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						pro_type=new String( pro_type.getBytes("ISO-8859-1"),"UTF-8");
						hashMap=pro_dao.SearchProinfo(currentPage-1,pro_type);
					}
					
					request.setAttribute("pro_info", pro_info);
					request.setAttribute("dev_type", pro_type);
					request.setAttribute("type_list", pro_dao.getType());
					
					  ArrayList<Pro_info> arrayList=(ArrayList<Pro_info>) hashMap.get("Proinfo_list");
						for(int i=0;i<arrayList.size();i++){
							if(arrayList.get(i).getPro_id().equals(pro_info.getPro_id())){
								arrayList.remove(i);
								break;
							}
						}
						request.setAttribute("pro_type", pro_type);
					 request.setAttribute("Proinfo_list",arrayList);
					 request.setAttribute("totalPage", hashMap.get("totalPage"));
					 request.setAttribute("currentPage", currentPage);
					 request.getRequestDispatcher("/sup/pro/proj_mod2.jsp").forward(request, response);
				}else{
					
					String pro_id=request.getParameter("pro_id");
					
					Asset_info_dao assert_info_dao=new Asset_info_dao();
					long time=session.getLastAccessedTime();
					Date date=new Date(time); 
					SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String   date1   =   sDateFormat.format(date);
					String user=(String) session.getAttribute("user_id");
					String ip=request.getRemoteAddr();
					try {
						assert_info_dao.mod_proj_info(asset_id, pro_id,date1, user, ip);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					response.sendRedirect("/test_7/asset_all_modservlet.do?oper=proj_mod&method=search&currentPage=1");
				}
						
			
				break;
				
				
		
			
			//设备信息：
			case device_mod:
				Sup_pro_basic_device_dao device_dao=new Sup_pro_basic_device_dao();  
				if(currString!=null){
					currentPage=Integer.valueOf(currString);
				}
				pro.javabean.Device_info device_info = new pro.javabean.Device_info();
				if("search".equals(method)){
					String prod_name=request.getParameter("prod_name");                                     //从搜索框中获取设备类型dev_type 
					HashMap<String,Object> hashMap=null;
					try {
						device_info=device_dao.get_asset_device(asset_id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if ("".equals(prod_name)||prod_name==null){                                                                 //如果搜索框全部为空 
						
						    try {
								hashMap=device_dao.getDevice_info(currentPage-1);                              //调用DAO层搜索全部数据的方法
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}                                 
						
						 
					}
					else {	
						
						prod_name=new String( prod_name.getBytes("ISO-8859-1"),"UTF-8");
//						dev_type=new String( dev_type.getBytes("ISO-8859-1"),"UTF-8");
						try {
							hashMap=device_dao.SearchDevice_info2(currentPage-1, prod_name);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					 request.setAttribute("prod_name", prod_name);
					 request.setAttribute("device_info", device_info);//当前
					 request.setAttribute("productor_list", device_dao.getProductor());//设置生产厂家
					 request.setAttribute("measure_list", device_dao.getMeasure());//设置计量单位
					 request.setAttribute("Device_info_list", hashMap.get("Device_info_list"));
					 request.setAttribute("totalPage", hashMap.get("totalPage"));
					 request.setAttribute("currentPage", currentPage);
					 request.getRequestDispatcher("/sup/pro/device_mod2.jsp").forward(request, response);
				}else{
					
					String dev_mod=request.getParameter("dev_mod");
				
					Asset_info_dao assert_info_dao=new Asset_info_dao();
					long time=session.getLastAccessedTime();
					Date date=new Date(time); 
					SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String   date1   =   sDateFormat.format(date);
					String user=(String) session.getAttribute("user_id");
					String ip=request.getRemoteAddr();
					try {
						assert_info_dao.mod_device_info(asset_id, dev_mod,date1, user, ip);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					response.sendRedirect("/test_7/asset_all_modservlet.do?oper=device_mod&method=search&currentPage=1");
				}
						
			
				break;
				
		
			
		case others_mod:
			
			String storage_loca=request.getParameter("storage_loca");
			String allo_info=request.getParameter("allo_info");
			String daiWei_cont_peri=request.getParameter("daiWei_cont_peri");
			String serv_end_time=request.getParameter("serv_end_time");
			String maint_end_time=request.getParameter("maint_end_time");
			String ass_controller=request.getParameter("ass_controller");
			String maint_period=request.getParameter("maint_period");
			String asset_attribute=request.getParameter("asset_attribute");
			String asset_status=request.getParameter("asset_status");
			String asset_use_status=request.getParameter("asset_use_status");
			String asset_acq_type=request.getParameter("asset_acq_type");
			String dev_appli_sta=request.getParameter("dev_appli_sta");
			
			
			System.out.println(storage_loca);
			storage_loca=new String( storage_loca.getBytes("ISO-8859-1"),"UTF-8");
			allo_info=new String( allo_info.getBytes("ISO-8859-1"),"UTF-8");
			 daiWei_cont_peri=new String( daiWei_cont_peri.getBytes("ISO-8859-1"),"UTF-8");
			serv_end_time=new String( serv_end_time.getBytes("ISO-8859-1"),"UTF-8");
			maint_end_time=new String( maint_end_time.getBytes("ISO-8859-1"),"UTF-8");
			ass_controller=new String( ass_controller.getBytes("ISO-8859-1"),"UTF-8");
			maint_period=new String( maint_period.getBytes("ISO-8859-1"),"UTF-8");
		
			asset_attribute=new String( asset_attribute.getBytes("ISO-8859-1"),"UTF-8");
			asset_status=new String( asset_status.getBytes("ISO-8859-1"),"UTF-8");
			asset_use_status=new String( asset_use_status.getBytes("ISO-8859-1"),"UTF-8");
			asset_acq_type=new String( asset_acq_type.getBytes("ISO-8859-1"),"UTF-8");
			dev_appli_sta=new String( dev_appli_sta.getBytes("ISO-8859-1"),"UTF-8");
			Assert_info assert_info=new Assert_info();
			assert_info.setAllo_info(allo_info);
			assert_info.setAss_controller(ass_controller);
			assert_info.setAsset_acq_type(asset_acq_type);
			assert_info.setAsset_attribute(asset_attribute);
			
			assert_info.setAsset_status(asset_status);
			assert_info.setAsset_use_status(asset_use_status);
			assert_info.setDaiWei_cont_peri(daiWei_cont_peri);
			assert_info.setDev_appli_sta(dev_appli_sta);
			assert_info.setMaint_end_time(maint_end_time);
			assert_info.setMaint_period(maint_period);
			assert_info.setServ_end_time(serv_end_time);
			assert_info.setStorage_loca(storage_loca);
			assert_info.setDev_type(request.getParameter("asset_type"));
				assert_info.setCity_id(request.getParameter("city_id"));
			
			
			method=request.getParameter("method");
			boolean j =false;
			if("add".equals(method)){
				
				try {
					long time=session.getLastAccessedTime();
					Date date=new Date(time); 
					SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String   date1   =   sDateFormat.format(date);
					String user_id=(String) session.getAttribute("user_id");
					String ip=request.getRemoteAddr();
					String asset_id1=asset_info_dao.add_assert_info(assert_info,date1,user_id,ip);
					if(!"".equals(asset_id1)){
						Valid_asset valid_asset=null;
						Valid_asset_dao valid_asset_dao=new Valid_asset_dao();
						try {
						valid_asset=valid_asset_dao.getasset(asset_id1);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("asser_valid", valid_asset);
						request.getSession().setAttribute("asset_id", asset_id1);
						request.setAttribute("assert_info", assert_info);
						request.setAttribute("asset_id", assert_info.getAsset_id());
						request.getRequestDispatcher("/sup/pro/Home_pro_sup_mod.jsp").forward(request, response);
					}else {
						response.sendRedirect("/test_7/sup/pro/Home_sup_pro_add.jsp");
					}
				} catch (SQLException e) {
					response.sendRedirect("/test_7/sup/pro/Home_sup_pro_add.jsp");
					e.printStackTrace();
				}
			}
			else {
				try {
					long time=session.getLastAccessedTime();
					Date date=new Date(time); 
					SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String   date1   =   sDateFormat.format(date);
					String user_id=(String) session.getAttribute("user_id");
					String ip=request.getRemoteAddr();
					
					String old_asset_attribute=request.getParameter("old_asset_attribute");
					String old_asset_status=request.getParameter("old_asset_status");
					String old_asset_use_status=request.getParameter("old_asset_use_status");
					String old_asset_acq_type=request.getParameter("old_asset_acq_type");
					String old_dev_appli_sta=request.getParameter("old_dev_appli_sta");
					
					
					old_asset_attribute=new String( old_asset_attribute.getBytes("ISO-8859-1"),"UTF-8");
					old_asset_status=new String( old_asset_status.getBytes("ISO-8859-1"),"UTF-8");
					old_asset_use_status=new String( old_asset_use_status.getBytes("ISO-8859-1"),"UTF-8");
					old_asset_acq_type=new String( old_asset_acq_type.getBytes("ISO-8859-1"),"UTF-8");
					old_dev_appli_sta=new String( old_dev_appli_sta.getBytes("ISO-8859-1"),"UTF-8");
					
				
					Assert_info assert_info2=new Assert_info();
					assert_info2.setAsset_attribute(old_asset_attribute);
					assert_info2.setAsset_status(old_asset_status);
					assert_info2.setAsset_use_status(old_asset_use_status);
					assert_info2.setAsset_acq_type(old_asset_acq_type);
					assert_info2.setDev_appli_sta(old_dev_appli_sta);
					assert_info2.setAsset_id((String)request.getSession().getAttribute("asset_id"));
					
					j=asset_info_dao.mod_assert_info(assert_info2.getAsset_id(), assert_info,assert_info2,user_id,date1,ip);
					
					if(j){
						Valid_asset valid_asset=null;
						Valid_asset_dao valid_asset_dao=new Valid_asset_dao();
						try {
							System.out.println("test"+assert_info2.getAsset_id());
						valid_asset=valid_asset_dao.getasset(assert_info2.getAsset_id());
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("asser_valid", valid_asset);
						request.getSession().setAttribute("asset_id", assert_info2.getAsset_id());
						request.setAttribute("assert_info", assert_info);
						
						request.setAttribute("asset_id", assert_info2.getAsset_id());
						request.getRequestDispatcher("/sup/pro/Home_pro_sup_mod.jsp").forward(request, response);
					}else {
						response.sendRedirect("/test_7/pro_sup_servlet.do?oper=mod&asset_id1="+asset_id);
					}
				} catch (SQLException e) {
					response.sendRedirect("/test_7/pro_sup_servlet.do?oper=mod&asset_id1="+asset_id);
					e.printStackTrace();
				}
			}
		
			
			break;
			
			
			
			
		default:
			break;
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
	
	}
enum info{
	cont_mod,device_mod,others_mod,proj_mod,proxy_mod,user_mod
}//商务合同，设备信息，其他信息，项目信息，代维单位，用户
}
