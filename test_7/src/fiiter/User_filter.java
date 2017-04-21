package fiiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User_filter implements Filter {
	private static final String error="/test_7/error.jsp";
	public static final String logout_page = "/test_7/Loginout/Loginoutservlet";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)servletRequest;  
        HttpServletResponse response = (HttpServletResponse)servletResponse;  
        HttpSession session = request.getSession(false); 
        String currentURL = request.getRequestURI();  
        String ctxPath = request.getContextPath();  
        String targetURL = currentURL.substring(ctxPath.length()); 
       String role_id=(String) session.getAttribute("role_id");
       HashMap<String ,HashSet<String>>hashMap=new HashMap<String, HashSet<String>>();
       ArrayList<String> r1=new ArrayList<String>();
       ArrayList<String> r23=new ArrayList<String>();
       ArrayList<String> r45=new ArrayList<String>();
      System.out.println("user_filter");
      System.out.println(targetURL);
       r1.add("/user_servlet.do");
       r1.add("/mod_security.do");
       
       //基本信息
       r23.add("/pro_sup_device_type_servelet.do");
       r23.add("/pro_sup_basic_measure.do");
       r23.add("/pro_sup_basic_device.do");
       r23.add("/pro_sup_proxyunit_servlet.do");
       r23.add("/Pro_typeServlet.do");
       r23.add("/ContServlet.do");
       r23.add("/pro_sup_proinfo_servlet.do");
       r23.add("/pro_sup_productor_servlet.do");
       
       //首页
       r23.add("/asset_all_modservlet.do");
       r23.add("/pro_sup_servlet.do");
       //统计
       r23.add("/pro_change_servlet.do");
       r23.add("/pro_count_change_servlet.do");
       r23.add("/pro_count_condition_summary_servlet.do");
       r23.add("/pro_count_condition_servlet.do");
       
       //报表
       r23.add("/Zichan_servlet.do");
       r23.add("/Zaixian_servlet.do");
       r23.add("/Zhuangu_Servlet.do");
       r23.add("/Liushi_servlet.do");
       r23.add("/forms_sunhuai.do");
       r23.add("/forms_baofei.do");
       
       //市级
       
       r45.add("/pro_sup_servlet.do");
       //统计
       r45.add("/pro_change_servlet.do");
       r45.add("/pro_count_change_servlet.do");
       r45.add("/pro_count_condition_summary_servlet.do");
       r45.add("/pro_count_condition_servlet.do");
       //报表
       r45.add("/Zichan_servlet.do");
       r45.add("/Zaixian_servlet.do");
       r45.add("/Zhuangu_Servlet.do");
       r45.add("/Liushi_servlet.do");
       r45.add("/forms_sunhuai.do");
       r45.add("/forms_baofei.do");
       if (targetURL.contains("/user_servlet.do")&&"info".equals(request.getParameter("form"))) {
    	   filterChain.doFilter(request, response);
	   		return;
	}
       if (targetURL.contains("/Noticeservlet.do")) {
    	   if (role_id.equals("r1")) {
    		   filterChain.doFilter(request, response);
    	   		return;
		}
    	   else {
			if (targetURL.contains("page=Record")) {
				response.sendRedirect(logout_page);
             	return;
			}
			else {
				  filterChain.doFilter(request, response);
	    	   		return;
			}
		}
    	   
	}
       //管理员
       if (role_id.equals("r1")) {
		for (int i = 0; i < r23.size(); i++) {
			if (targetURL.contains(r23.get(i))) {
				response.sendRedirect(error);
             	return;
			}
		}
		filterChain.doFilter(request, response);
   		return;
	}
       else if(role_id.equals("r2")||role_id.equals("r3")) {
    	   for (int i = 0; i < r1.size(); i++) {
   			if (targetURL.contains(r1.get(i))) {
   				response.sendRedirect(error);
                	return;
   			}
   		}
   		filterChain.doFilter(request, response);
      		return;
	}
       else {
    	   for (int i = 0; i < r45.size(); i++) {
   			if (targetURL.contains(r45.get(i))) {
   				filterChain.doFilter(request, response);
   	      		return;
   				
   			}
   		}
    	   response.sendRedirect(error);
       	return;   
		
	}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
