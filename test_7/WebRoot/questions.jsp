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
<title>questions</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/matrix-login.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/test_7/css/wwj_modify.css" />
</head>

<body>
	<div id="loginbox">
		<form id="" action="Sear_quesservlet" class="form-vertical">
			<p class="normal_text">（若忘记密保问题，请联系管理员：1976572727@qq.com）</p>
			<c:out value="${error}"></c:out>
			
		<div class="controls">
				<div class="main_input_box">
					<span class="add-on bg_lo"><i class="icon-envelope" ></i></span>
                    <select  name="question" id="question" style="border:0px;" >
                    <c:forEach items="${question}" var="q">
						<option >${q }</option>
					</c:forEach>
                 
                 </select>
			</div>
			</div>
			
			<div class="controls">
				<div class="main_input_box">
					<span class="add-on bg_lo"><i class="icon-envelope"></i></span><input
						name ="answer"type="text" placeholder="您的答案" />
				</div>
			</div>
			<div class="form-actions">
				<span class="pull-left"><input onclick="window.location.href='<%=basePath %>user_id.jsp'" type="button"
					class="flip-link btn btn-success" id="to-login" style="color:white;" value="上一步"></span>
				<span class="pull-right"><input type="submit"class="btn btn-info"
					id="to-change" style="color:white;"value="下一步"></span>
			</div>
				<input type="hidden" name="form" value="questions">
				<input type="hidden" name="Username" value="${Username}">
				
		</form>
		
	</div>

	
</body>
</html>