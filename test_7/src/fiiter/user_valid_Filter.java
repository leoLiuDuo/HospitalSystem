package fiiter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class user_valid_Filter implements Filter {
private static final String error="/test_7/error.jsp";
public static final String logout_page = "/test_7/Loginout/Loginoutservlet";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
/*
 * 这里是验证。do的文件
 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;  
        HttpServletResponse response = (HttpServletResponse)servletResponse;  
        HttpSession session = request.getSession(false); 
        String currentURL = request.getRequestURI();  
        String ctxPath = request.getContextPath();  
        String targetURL = currentURL.substring(ctxPath.length()); 
        System.out.println("user_vail");
        System.out.println(targetURL);
        if (session==null) {
        	response.sendRedirect(logout_page);
         	return;
		}
        else {
        	String role_id=(String) session.getAttribute("role_id");
        	if (role_id==null) {
        		response.sendRedirect(error);
             	return;
			}
        	else {
        		filterChain.doFilter(request, response);
        		return;
			}
        	
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
