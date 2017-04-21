package fiiter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sql implements Filter {
	private static final String error="/test_7/error_input.jsp";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest req = (HttpServletRequest) request;  
	        HttpServletResponse res = (HttpServletResponse) response;  
	        //获得所有请求参数名  
	        Enumeration params = req.getParameterNames();  
	  System.out.println("sql");
	        String sql = "";  
	        while (params.hasMoreElements()) {  
	            //得到参数名  
	            String name = params.nextElement().toString();  
	            //System.out.println("name===========================" + name + "--");  
	            //得到参数对应值  
	            String[] value = req.getParameterValues(name);  
	            for (int i = 0; i < value.length; i++) {  
	                sql = sql + value[i];  
	            }  
	        }  
	        System.out.println("被匹配字符串："+sql);  
	        if (sqlValidate(sql)) {  
	            res.sendRedirect(error);  
	            return;
	        } else {  
	            chain.doFilter(req, res);  
	            return;
	        }  
	    }  
	  
	    //校验
	    protected static boolean sqlValidate(String str) {  
	        str = str.toLowerCase();//统一转为小写
	     
	       
	        String badStr = "'|and|exec|execute|insert|create|drop|table|from|grant|left|" +  
	                "information_schema.columns|join|union|where|select|delete|update|order|by|count|*|" +  
	                "like|#";        //过滤掉的sql关键字，可以手动添加  
	        String[] badStrs = badStr.split("\\|");  
	        for (int i = 0; i < badStrs.length; i++) {
	            if (str.indexOf(badStrs[i]) !=-1) { 
	                System.out.println("匹配到："+badStrs[i]);
	                return true;  
	            }  
	        }  
	        return false;  
	    }  
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
