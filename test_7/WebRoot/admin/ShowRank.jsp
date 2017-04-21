<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>排行榜</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/fullcalendar.css" />
<link rel="stylesheet" href="/test_7/css/matrix-style.css" />
<link rel="stylesheet" href="/test_7/css/matrix-media.css" />
<link rel="stylesheet" href="/test_7/css/pro_.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/test_7/css/jquery.gritter.css" />
<link rel="stylesheet" href="/test_7/css/wwj_modify.css" />
<style>
.userInfoDiv {
	display: none;
	padding: 10px;
	margin-left: auto;
	margin-right: auto;
	margin-top: auto;
	margin-bottom: auto;
	width: 400px;
	height: auto;
	background-color: #f9f9f9;
	border: 1px solid #cdcdcd;
}

.userInfoDiv table {
	border: 0px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body onLoad="div_page(${currentPage},${TotalPage },&quot;ShowRank&quot;)">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	<script src="/test_7/js/page.js"></script>
	<script type="text/javascript">
	
	// This function is called from the pop-up menus to transfer to
	// a different page. Ignore if the value returned is a null string:
	
	function goPage(newURL) {

		// if url is empty, skip the menu dividers and reset the menu selection to default
		if(newURL != "") {

			// if url is "-", it is this page -- reset the menu:
			if(newURL == "-") {
				resetMenu();
			}
			// else, send page to designated URL            
			else {
				document.location.href = newURL;
			}
		}
	}

	// resets the menu selection upon entry to this page:
	function resetMenu() {
		document.gomenu.selector.selectedIndex = 2;
	}
	

	</script>
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/user-nav.jsp"%>
	<%@include file="/admin/sidebar.jsp"%>
	
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
			<c:choose>
			<c:when test="${role_id=='r1' }">
			<a href="<%=basePath%>user_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>网络登陆排行榜</a>
			</c:when>
			<c:otherwise>
			<a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>网络登陆排行榜</a>
			</c:otherwise>
			</c:choose>	
				
				
			</div>
			

			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<tr>
						<th colspan="5" bgcolor="#E0E0E0"
							style="text-align: left; background:#CDCDCD"><span
							class="icon"> <i class="icon-th"></i>
						</span>
							<h6 class="table_title_first_name ">网络登陆排行榜</h6>
								<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple"></ul></div></th>
					</tr>
					
					<tr>
						<th>用户名</th>
						<th>登录次数</th>
						<th>时长/小时</th>
						<th>所属城市</th>
						
					</tr>
					
					<tbody>
						<c:forEach items="${Rank_list }" var="list">
							<tr>
								<td>${list.name }</td>
								<td>${list.login_times }</td>
								<td>${list.duration }</td>
								<td>${list.city }</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--页码-->
			<div align="center">
				<nav style="text-align: center" class="pagination">
					<ul  id="barcon">
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<%@include file="/common/Footer.jsp"%>

</body>
</html>
