<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>资产信息修改/添加</title>
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
<body onload="others();">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/user-nav.jsp"%>
	<%@include file="/admin/sidebar.jsp"%>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search">固定资产查询</a> <a>详细信息</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">
									<tr>
								
										<td align="center">项目信息</td>
										<td>项目名称：<input  name="user_id" type="text" value="${asser_valid.pro_name}" readonly="readonly"/></td>
										<td align="center">
										<a title="" href="#" onClick="Ceng()"
											data-toggle="modal" data-target="#proj_mod"><i
												class="icon-search"></i> <span class="text">选择</span></a></td>
									</tr>
									<tr>
										<td align="center">商务合同</td>
										<td>合同名称:<input  name="user_id" type="text" value="${asser_valid.cont_name}" readonly="readonly"/></td>
										<td align="center"><a title="" href="#" onClick="Ceng()"
											data-toggle="modal" data-target="#cont_mod"><i
												class="icon-search"></i> <span class="text">选择</span></a></td>
									</tr>
									<tr>
										<td align="center">代维单位</td>
										<td>代维单位：<input  name="user_id" type="text" value="${asser_valid.daiwei_unit}" readonly="readonly"/></td>
										<td align="center"><a title="" href="#" onClick="Ceng()"
											data-toggle="modal" data-target="#proxy_mod"><i
												class="icon-search"></i> <span class="text">选择</span></a></td>
									</tr>
									<tr>
										<td align="center">设备信息</td>
										<td>设备名称：<input  name="user_id" type="text" value="${asser_valid.dev_name}" readonly="readonly"/></td>
										<td align="center"><a title="" href="#" onClick="Ceng()"
											data-toggle="modal" data-target="#device_mod"><i
												class="icon-search"></i> <span class="text">选择</span></a></td>
									</tr>
								 
								 
									<tr>
										<td align="center">其他信息</td>
										<td>资产编号：<input  value="${asser_valid.asset_id }" type="text"  readonly="readonly"/>
									
										
										</td>
										<td align="center"><a title="" href="#" onClick="Ceng()"
											data-toggle="modal" data-target="#others_mod"><i
												class="icon-search"></i> <span class="text">选择</span></a></td>
									</tr>
									<tr>
										<td align="center">资产管理人</td>
										<td><sapn>资产所属人：</span><input  name="user_id" type="text" value="${asser_valid.name}" readonly="readonly"/></td>
										<td align="center" id="test"><a title="" 
											data-toggle="modal" href="#" data-target="#user_mod" id="test1" ><i
												class="icon-search"></i> <span class="text">选择</span></a>
												</td>
									</tr>

								</table>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/common/Footer.jsp"%>
	<%@include file="/sup/pro/proj_mod.jsp"%>
	<%@include file="cont_mod.jsp"%>
	<%@include file="/sup/pro/device_mod.jsp"%>
	<%@include file="/sup/pro/proxy_mod.jsp"%>
 <%@include file="/sup/pro/user_mod.jsp"%>
      <%@include file="/sup/pro/others_mod.jsp"%>
     

</body>
</html>