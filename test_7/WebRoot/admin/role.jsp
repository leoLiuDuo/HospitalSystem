<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>角色管理</title>
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
<body>
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
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
	<%@include file="sidebar.jsp"%>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath%>user_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>角色管理</a>
			</div>
			 
			<div class="table-responsive">
				<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="4" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">角色管理</h6></th>									        
							      </tr>
						<tr>
							<th scope="col">角色名称</th>
							<th scope="col">权限</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>省超级用户</td>
							<td><a href="#" data-toggle="modal" data-target="#xq1">详情</a></td>
						</tr>
						<tr>
							<td>市超级用户</td>
							<td><a href="#" data-toggle="modal" data-target="#xq2">详情</a></td>
						</tr>
						<tr>
							<td>省一般用户</td>
							<td><a href="#" data-toggle="modal" data-target="#xq3">详情</a></td>
						</tr>
						<tr>
							<td>市一般用户</td>
							<td><a href="#" data-toggle="modal" data-target="#xq4">详情</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		 
		</div>
	</div>

	<!--弹出省超级用户-->
	<div class="modal fade " id="xq1" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">省超级用户</h4>
				</div>
				<div class="modal-body">
					<form action="" method="get">
						<table style="margin-left:auto; margin-right:auto;">
							<tr>
								<td>基本信息</td>
								<td><input name="username" type="text" value="增/删/改/查"
									readonly /></td>
							</tr>
							<tr>
								<td>资产信息</td>
								<td><input name="name" type="text" value="全省：增/删/改/查"
									readonly="readonly" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--弹出市超级用户-->
	<div class="modal fade " id="xq2" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">市超级用户</h4>

				</div>
				<div class="modal-body">
					<form action="" method="get">
						<table style="margin-left:auto; margin-right:auto;">
							<tr>
								<td>基本信息</td>
								<td><input name="username" type="text" value="增/删/改/查"
									readonly /></td>
							</tr>
							<tr>
								<td>资产信息</td>
								<td><input name="name" type="text" value="本市：增/删/改/查"
									readonly="readonly" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--弹出省一般用户-->
	<div class="modal fade " id="xq3" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">省一般用户</h4>
				</div>
				<div class="modal-body">
					<form action="" method="get">
						<table style="margin-left:auto; margin-right:auto;">
							<tr>
								<td>基本信息</td>
								<td><input name="username" type="text" value="增/删/改/查"
									readonly /></td>
							</tr>
							<tr>
								<td>资产信息</td>
								<td><input name="name" type="text" value="全省：查"
									readonly="readonly" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--弹市一般用户-->
	<div class="modal fade " id="xq4" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">市一般用户</h4>
				</div>
				<div class="modal-body">
					<form action="" method="get">
						<table style="margin-left:auto; margin-right:auto;">
							<tr>
								<td>基本信息</td>
								<td><input name="username" type="text" value="增/删/改/查"
									readonly /></td>
							</tr>
							<tr>
								<td>资产信息</td>
								<td><input name="name" type="text" value="本市：查"
									readonly="readonly" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/common/Footer.jsp"%>

</body>
</html>