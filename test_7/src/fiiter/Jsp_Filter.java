package fiiter;

import java.io.IOException;
import java.util.Date;
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

import com.mysql.fabric.xmlrpc.base.Data;

public class Jsp_Filter implements Filter {
	private static final String error="/test_7/error.jsp";
	public static final String logout_page = "/test_7/Loginout/Loginoutservlet";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;  
        HttpServletResponse response = (HttpServletResponse)servletResponse;  
        HttpSession session = request.getSession(true); 
        String currentURL = request.getRequestURI();  
        String ctxPath = request.getContextPath();  
        String role_id=(String) session.getAttribute("role_id");
        //除掉项目名称时访问页面当前路径  
        String targetURL = currentURL.substring(ctxPath.length());  
       
        System.out.println("jsp");
        System.out.println(targetURL);
        System.out.println(role_id);
        	if (role_id==null) {
        		if (targetURL.contains("/Login.jsp")||targetURL.contains("/user_id.jsp")||targetURL.contains("/error")||targetURL.contains("/")) {
        			System.out.println("test1");
        			filterChain.doFilter(request, response);
            		return;
				}
        		else {
        			response.sendRedirect(error);
                 	return;
				}
        		
    		}
        	else {
        		System.out.println("test");
        		if (targetURL.contains("/Login.jsp")||targetURL.equals("/")) {
        			
        			if ("r1".equals(role_id)) {
						response.sendRedirect("/test_7/user_servlet.do?currentPage=1&oper=search");
					}else {
						response.sendRedirect("/test_7/pro_sup_servlet.do?currentPage=1&oper=search");
					}
            		return;
				}
        		if (role_id.equals("r1")&&(targetURL.contains("role.jsp")||targetURL.contains("mod_security.jsp"))) {
                	filterChain.doFilter(request, response);
            		return;
        		}
                else if((!role_id.equals("r1"))&&(targetURL.contains("Home_sup_pro_count_change.jsp")||targetURL.contains("Home_sup_pro_add.jsp")||targetURL.contains("mod_security.jsp"))){
                	
                	filterChain.doFilter(request, response);
            		return;
        		}
                else {
                	if (targetURL.contains("/error")) {
                		filterChain.doFilter(request, response);
                		return;
            			
					}
                	response.sendRedirect(error);
                 	return;
        		}
			}
        	
       
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
