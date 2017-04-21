<%@page import="admin.Notice_admin.ShowEdit"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="/error404.html"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>操作动态</title>
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
<body onLoad="div_page(${currentPage},${TotalPage },&quot;ShowEdit&quot;)">
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
	
	function test1(user_id){
	console.log(user_id);
	var string;
	var user_idtest;
	<%	 ArrayList<ShowEdit> arrayList=(ArrayList<ShowEdit>)request.getAttribute("showedit"); 
		int size=arrayList.size();
		  ShowEdit user=null;
		 for(int i=0;i<size;i++){
		user=arrayList.get(i);
		request.setAttribute("user", user);
	
		 %>
		 user_idtest="<%out.print(user.getAsset_id());%>";
		 if(user_id==user_idtest){
		 string="<tr><td >操作人员</td><td > ${user.name}</td></tr>"+
		 					"<tr><td>操作时间</td><td> ${user.date}</td></tr>"+
							"<tr><td>城市</td><td>${user.city}</td></tr>"+
							"<tr><td>资产编号</td><td>${user.asset_id}</td></tr>"+
							
							"<tr><td>操作</td><td>${user.oper_type}</td></tr>"+
								"<tr><td>操作前</td><td>   ${user.content_before}</td></tr>"+
							"<tr><td>操作后</td><td>${user.content_after}</td></tr>"+
							"<tr><td>ip地址</td><td>${user.ip}</td></tr>";				
		 }
		 <%
		 }
		%>
	
	
	document.getElementById("delSimple").innerHTML=string;
	
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
					class="icon-home"></i> Home</a> <a>资产操作动态</a>
			</c:when>
			<c:otherwise>
			<a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>资产操作动态</a>
			</c:otherwise>
			</c:choose>	
				
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-content nopadding">
							  <form class="form-horizontal" method="post" action="#" name="password_validate" id="password_validate" novalidate>
                           </form>
 
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="table-responsive">
				<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="4" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">资产操作动态</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple"></ul></div>		
							         								        
							      </tr>
						<tr>
							<th>资产编号</th>
							<th>操作类型</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${showedit }" var="list">
							<tr>
								<td>${list.asset_id }</td>
								<td>${list.oper_type }</td>
								<td><a href="#" data-toggle="modal" data-target="#det1" onclick="test1(&quot;${list.asset_id}&quot;)">详情</a></td>
								
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
		</div>
	</div>


<div class="modal fade " id="det1" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">详情</h4>
				</div>
				<div class="modal-body">
						<table  class="table table-bordered table-striped" id="delSimple">
							
						</table>
				</div>
					<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定
				</button>
			 
			</div>	
			</div>
		</div>
	</div>
	
	
	<%@include file="/common/Footer.jsp"%>

</body>
</html>
