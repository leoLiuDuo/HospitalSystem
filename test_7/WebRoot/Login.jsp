<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		
			
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
<title>Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/matrix-login.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />
<script>
function  chkusername(o)
{
 
 var  st=/^\d+$/gi.test(o.value);
 
 if(!st)
 {
     document.getElementById("user").innerHTML="用户名不存在";
 }
 else
 {
  document.getElementById("user").innerHTML='';
 }
}
</script>
</head>

<body>
	<div id="loginbox">
		<form id="loginform" class="form-vertical" action="/test_7/login/Loginservlet" method="post">
			<div class="control-group normal_text">
				<h3>
					<img src="/test_7/img/Logo.png" alt="Logo" />
				</h3>
			</div>
			<c:out value="${error }"></c:out>
			<table style="width:550px">
		 
		<tr>	
		<td></td>
		<td><div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_lg"><i class="icon-user"></i></span><input
						name="Username"	type="text" placeholder="Username" onblur="javascript:chkusername(this);"  />
				
					</div>
				</div>
			</div>
			</td>
		<td style="width:80px;"><span style="color:red;font-size:12" id="user"></span></td>
		</tr>
		<tr>	
		<td style="width:80px"></td>
		<td>	<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_ly"><i class="icon-lock"></i></span><input
							name='Password' type="password" placeholder="Password" />
					</div>
				</div>	
			</div></td>
		<td></td>
		</tr>
			
			</table>
		 
			<div class="form-actions">
				<span class="pull-left"><a href="<%=basePath %>user_id.jsp"
					class="flip-link btn btn-info" id=""> 忘记密码?</a></span> <span
					class="pull-right"><input type="submit" value="登录" class="btn btn-success" /></span>
			</div>

		</form>
	</div>


</body>
</html>