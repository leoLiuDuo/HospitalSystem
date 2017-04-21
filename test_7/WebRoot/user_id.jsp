<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>questions</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-login.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/wwj_modify.css" />
</head>

<body>
	<div id="loginbox">
	
		<form  action="Sear_quesservlet" class="form-vertical">
			<div class="controls">
				<p style="padding-left:30px;font-size:17px;color:white;">请填写账号：</p>
				<p style="color:red">${notice }</p>
				<div class="main_input_box">
					<span class="add-on bg_lg"><i class="icon-user"></i></span> <input
					name="Username"	type="text" placeholder="Username" />
				</div>
				<br>
			</div>
			<br>
			<div class="form-actions">
				<span class="pull-left"><a href="Login.jsp"
					class="flip-link btn btn-success" id="to-login"
					style="color:white;">&laquo; 返回登录界面</a></span> <span class="pull-right">
					<input
					type="submit" class="btn btn-info" id="to-change"
					style="color:white;"value="下一步"></span>
			</div>
			<input type="hidden" name="form" value="user_id">
		</form>

	</div>


</body>
</html>