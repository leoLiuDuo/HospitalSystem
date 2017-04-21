<%@page import="pro.javabean.DuoWeiCX"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<DuoWeiCX>  duoWeiCXs=(List<DuoWeiCX>)request.getAttribute("duoWeiCXs");
System.out.println(duoWeiCXs+"************************");
System.out.println(duoWeiCXs+"************************");
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<title>多维查询</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link href="/test_7/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="/test_7/css/fullcalendar.css" />
	<link rel="stylesheet" href="/test_7/css/matrix-style.css" />
	<link rel="stylesheet" href="/test_7/css/matrix-media.css" />
	<link rel="stylesheet" href="/test_7/css/pro_.css" />
	<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/jquery.gritter.css" />
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
<body onload="div_page(${currentPage},${ totalPage})">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	<script type="text/javascript">
		
		function TestBlack(TagName){
			var obj = document.getElementById(TagName);
			if(obj.style.display==""){
				obj.style.display = "none";
			}else{
				obj.style.display = "";
			}
		}


	</script>
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/user-nav.jsp"%>
	<%@include file="/admin/sidebar.jsp"%>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath %>duoWeiCXServlet?method=query"
					title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
					Home</a> <a>多维查询</a>
				</div>
				<div class="container" style="margin-top:5px;">
					<div class="row">
						<form class="form-horizontal"  role="form"  method="post" action="duoWeiCXServlet?method=query" name="password_validate"
						id="password_validate" novalidate>
						<div style="margin:20px 15%;border:1px solid #CCF ;border-radius: 15px;background-color:#D1E0F3;">
						<table class="table_duowei table " align="center" >
							<tr>
								<td width="79" align="left">性别</td>
								<td colspan="20"><p align="left">
									<select name="select" style="width:70px;">
										<option value="不限" selected="selected">不限</option>
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
								</p></td>
							</tr>

							<tr>
								<td width="79">住院号</td>
								<td colspan="20"><p align="left">
									<input type="text" name="input7">
								</p></td>
							</tr>

							<tr>
								<td>年龄</td>
								<td colspan="20">
									<p align="left">
										<input type="checkbox" checked="checked" name="input3"
										value="all"/ > 不限&nbsp; <input type="text"
										name="input5" style="width:80px;"> -- <input
										type="text" name="input6" style="width:80px;">
									</p>
								</td>
							</tr>

							<tr>
								<td>科室</td>

								<td ><p align="left">
									<input type="checkbox" checked="checked" name="input4"
									value="all"/ > 不限
								</p></td>
								
								<td >
                                <div class="panel-group" id="accordion">
									<div class="panel panel-default">
										<div class="panel-heading" style="text-align:left;">
											<h4 class="panel-title">
												<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseOne" title="展开" style="color:#666;"> 内科</a>
											</h4>
										</div>
										<div id="collapseOne" class="panel-collapse collapse">
                                            <div class="form-group" style="text-align:left;">
									<input type="checkbox" class="form-control" id="xueyeke" style="display:inline-block;"/> 
                                    <label for="xueyeke" style="display:inline-block;">血液科</label>
                                              </div>  
                                              <div class="form-group" style="text-align:left;">
                                                 <input type="checkbox" class="form-control" id="ganranbing" style="display:inline-block;"/>
                                                 <label for="ganranbing" style="display:inline-block;">感染病科</label>
                                                 </div>
                                                <div class="form-group" style="text-align:left;">
												<input type="checkbox" class="form-control" id="xiaohuaneike" style="display:inline-block;"/>
												<label for="xiaohuaneike" style="display:inline-block;">消化内科</label>
												</div>
									</div>
								</div></td>

<td >
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading" style="text-align: left;">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseTwo" title="展开" style="color:#666;"> 外科</a>
				</h4>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse">
				<div class="form-group" style="text-align: left;" >
					<input type="checkbox" name="" id="qgyzzx" style="display: inline-block;">
					<label for="qgyzzx" style="display: inline-block;">器官移植中心</label>
				</div>
				<div class="form-group" style="text-align: left;">
					<input type="checkbox" name="" id="gdwk" style="display: inline-block;">
					<label for="gdwk" style="display: inline-block;">肝胆外科</label>
				</div>
			</div>
		</div>
	</div>
</td>

<td >
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading" style="text-align: left;">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseThree" title="展开" style="color:#666;"> 妇产科</a>
				</h4>
			</div>
			<div id="collapseThree" class="panel-collapse collapse">
				<div class="form-group" style="text-align: left;" >
					<input type="checkbox" name="" id="szyxzx" style="display: inline-block;">
					<label for="szyxzx" style="display: inline-block;">生殖医学中心</label>
				</div>
				<div class="form-group" style="text-align: left;">
					<input type="checkbox" name="" id="ck" style="display: inline-block;">
					<label for="ck" style="display: inline-block;">产科</label>
				</div>
			</div>
		</div>
	</div>
</td>


<td >
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading" style="text-align: left;">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseFour" title="展开" style="color:#666;"> 肿瘤科</a>
				</h4>
			</div>
			<div id="collapseFour" class="panel-collapse collapse">
				<div class="form-group" style="text-align: left;" >
					<input type="checkbox" name="" id="zlk" style="display: inline-block;">
					<label for="zlk" style="display: inline-block;">肿瘤科</label>
				</div>
				<div class="form-group" style="text-align: left;">
					<input type="checkbox" name="" id="fsk" style="display: inline-block;">
					<label for="fsk" style="display: inline-block;">放射科</label>
				</div>
			</div>
		</div>
	</div>
</td>

<td >
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading" style="text-align: left;">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseFive" title="展开" style="color:#666;"> 医学影像科</a>
				</h4>
			</div>
			<div id="collapseFive" class="panel-collapse collapse">
				<div class="form-group" style="text-align: left;" >
					<input type="checkbox" name="" id="yxyxk" style="display: inline-block;">
					<label for="yxyxk" style="display: inline-block;">医学影像科</label>
				</div>
				
			</div>
		</div>
	</div>
</td>

										</tr>

										<tr>
											<td>检查指标</td>
											<td colspan="20"><p align="left">
												<input type="checkbox" checked="checked" name="input4"
												value="all"/ > 不限&nbsp; <input type="checkbox"
												name="input" value="all"/ > 肝胆内科 &nbsp; <input
												type="checkbox" name="input" value="all"/ > 血液科
												&nbsp; <input type="checkbox" name="input2" value="all"/ >
												感染病科 &nbsp; <input type="checkbox" name="input2" value="all"/ >
												肝胆外科 &nbsp; <input type="checkbox" name="input8" value="all"/ >
												消化内科 &nbsp; <input type="checkbox" name="input9" value="all"/ >
												医学影像科
											</p></td>

										</tr>

										<tr>
											<td>检验指标</td>
											<td colspan="20"><p align="left">
												<input type="checkbox" checked="checked" name="input4"
												value="all"/ > 不限&nbsp; <input type="checkbox"
												name="input" value="all"/ > 肝胆内科 &nbsp; <input
												type="checkbox" name="input" value="all"/ > 血液科
												&nbsp; <input type="checkbox" name="input2" value="all"/ >
												感染病科 &nbsp; <input type="checkbox" name="input2" value="all"/ >
												肝胆外科 &nbsp; <input type="checkbox" name="input8" value="all"/ >
												消化内科 &nbsp; <input type="checkbox" name="input9" value="all"/ >
												医学影像科
											</p></td>

										</tr>


									</table>

									<p align="center">
										<input type="submit" class="btn btn-primary" style=" width:60px"
										value="查询" />
									</p>



</div>

								</form>

							</div>
						</div>


						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<div class="table-responsive">


										<div class="container-fluid">
											<div class="row-fluid">
												<div class="span12">
													<div class="table-responsive">
														<table
														class="table_huanzhe table table-bordered table-striped ">
														<thead>
															<tr>
																<th colspan="20" bgcolor="#E0E0E0"
																style="text-align: left; background:#CDCDCD"><span
																class="icon"> <i class="icon-th"></i>
															</span>
															<h6 class="table_title_first_name ">患者信息表</h6>
															<div class="pagination"
															style="text-align:right; width:60px; height:10px;width:100px;float:right">
															<ul id="barconSimple">

															</ul>
														</div></th>
													</tr>
													<tr>
														<th width="55px;">姓名</th>
														<th width="55px;">性别</th>
														<th width="55px;">年龄</th>
														<th>临床诊断</th>

														<th width="60px;">住院号</th>
														<th width="60px;">住院次数</th>
														<th width="55px;">操作</th>
													</tr>
													<%
													for(DuoWeiCX duoWeiCX:duoWeiCXs){
													%>
													<tr>
														<td><%=duoWeiCX.getYuyinname() %></td>
														<td><%=duoWeiCX.getAge() %></td>
														<td><%=duoWeiCX.getSex() %></td>
														
														<td><%=duoWeiCX.getRelevant_clinic_diag() %></td>
														<td><%=duoWeiCX.getHospital_id() %></td>
														<td><%=duoWeiCX.getHospital_time() %></td>
														<td><a href="duoWeiCXServlet?method=seeDatil" onclick="test('${duoWeiCX.getYuyinname()}');">查看详情</a></td>
													</tr>
													<%
												}
												%>
											</thead>

										</table>
										<div align="center">
											<nav style="text-align: center" class="pagination">
												<ul class="pagination" id="barcon">

												</ul>
											</nav>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div align="center">
					<nav style="text-align: center" class="pagination">
						<ul class="pagination" id="barcon">

						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<%-- 			<%@include file="/common/Footer.jsp"%>
<div class="modal fade " id="det1" tabindex="-1" role="dialog"
style="display:none;">
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">请选择要查询的住院次数</h4>
		</div>
		<div class="modal-body">
			<table  class="table table-bordered table-striped table_p" id="delSimple">
				<th  style="width:200px;height:40px">住院次数</th>
				<tr>
					<td><a href="<%=basePath %>Liushi_servlet.do?currentPage=1&oper=search&city=">第一次 </a></td>
				</tr>
				<tr>
					<td><a href="<%=basePath %>Liushi_servlet.do?currentPage=1&oper=search&city=">第二次 </a></td>
				</tr>
			</table>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" data-dismiss="modal">确定
			</button>
			
		</div>	
	</div>
</div>
</div>--%>
</body>
</html>