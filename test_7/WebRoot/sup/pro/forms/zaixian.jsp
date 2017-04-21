<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="pro.javabean.res_of_search"%>
<%@page import="java.sql.Date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>影像文本自动推导</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/test_7/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
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
    	function div_page(currentPage,totalPage,pagename){ 
		//显示分页栏部分
		    var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		}
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\Zaixian_servlet.do?currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\Zaixian_servlet.do?currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\Zaixian_servlet.do?currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\Zaixian_servlet.do?currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\Zaixian_servlet.do?currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  

	</script> 
	<%@include file="/common/header.jsp"%>
	<%@include file="/common/user-nav.jsp"%>
	<%@include file="/admin/sidebar.jsp"%>
	<div id="content">
		<!--breadcrumbs-->
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>历史查询记录</a>
			</div>

			        <div class="container-fluid">
	      <div class="row-fluid">
			<div class="span12">
			   <div class="widget-box">
			     
				   </div>
				</div>
			</div>
		</div>
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<div class="table-responsive">
								<table  class="table_lishi table table-bordered table-striped">									
								  <thead>
									  <tr >
									        <th colspan="20" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">历史查询记录</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>
									          </th>									        
							      </tr>
										<tr>
											<th>性别</th>
											<th>年龄段</th>
											<th>检查类别</th>
											<th>检查子类别</th>
											<th>检验类别</th>
											<th>科室</th>
											<th>查询时间</th>
											<th>操作</th>
										</tr>
								</thead>
	 							<tbody>
	 						 
										<c:forEach items="${history_list}" var="list">
										<tr>
											<td name="sex">${list.getSEARCH_SEX()}</td>
											<td>${list.getAGE1()}-${list.AGE2}</td>
											<td>${list.getEXAM_CLASS()}</td>
											<td>${list.getTEST_CLASS_NO1()}</td>
											<td>${list.getTEST_CALSS_NO2()}</td>
											<td>${list.getHOS_DEP_NO1_ID()}</td>
											<td><select>
	 									   <c:forEach items="${list.SEARCH_DATE}" var="D">
	 									  
	 									    <option>${D.getDate()}   </option>
	 									   </c:forEach>
											 </select>
										 </td>
										   <td>查看历史结果</td>
										</tr>
										 
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div align="center">
						<nav style="text-align: center" class="pagination">
							<ul class="pagination" id="barcon">
							
							</ul>
						</nav>
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
			<%@include file="/common/Footer.jsp"%>
			<div class="modal fade " id="det1" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请查询日期</h4>
				</div>
				<div class="modal-body">
						<table  class="table table-bordered table-striped table_p" id="delSimple">
						 <th  style="width:200px;height:40px">查询日期</th>
						 	<c:forEach items="${list.SEARCH_DATE}" var="d">
						   
						 <tr>
						 <td>${list.SEARCH_DATE}</td>
						</tr>
						<tr>
						 <td>2013-12-12</td>
						</tr>
						</c:forEach>
						</table>
				</div>
					<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定
				</button>
			 
			</div>	
			</div>
		</div>
	</div>
</body>
</html>