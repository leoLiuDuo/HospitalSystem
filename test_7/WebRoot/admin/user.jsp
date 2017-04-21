
<%@page import="admin.Notice_admin.User"%>
<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>用户管理</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7//css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7//css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7//css/fullcalendar.css" />
<link rel="stylesheet" href="/test_7//css/matrix-style.css" />
<link rel="stylesheet" href="/test_7//css/matrix-media.css" />
<link rel="stylesheet" href="/test_7//css/pro_.css" />
<link href="/test_7//font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/test_7/css/jquery.gritter.css" />
<link rel="stylesheet" href="/test_7//css/wwj_modify.css" />
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
<script src="/test_7//js/jquery.min.js"></script>
	<script src="/test_7//js/bootstrap.min.js"></script>
	<script src="/test_7//js/matrix.js"></script>
	<script type="text/javascript">
	// This function is called from the pop-up menus to transfer to
	// a different page. Ignore if the value returned is a null string:
	
	function test(user_id){
	user_id5=user_id;
	 document.getElementById("mod_user_id").value=user_id;
	var name1;
	var user_idtest;
	<%	 ArrayList<admin.Notice_admin.User> arrayList=(ArrayList<admin.Notice_admin.User>)request.getAttribute("userlist"); 
		int size=arrayList.size();
		 for(int i=0;i<size;i++){
		 admin.Notice_admin.User user=new admin.Notice_admin.User();
		 user=arrayList.get(i);
		request.setAttribute("user", user);
		 %>
		 user_idtest="<%out.print(user.getUser_id());%>";
		 if(user_id==user_idtest){
		  document.getElementById("user_id1").value=${user.user_id};
		
		
		 name1 = document.getElementById("name1");
		 name1.value="${user.name}";
		mod_password= document.getElementById("mod_password");
		mod_password.href+=user_id;
		mod_question=document.getElementById("mod_question");
		mod_question.href+=user_id;
		 city_id_obj = document.getElementById("city1");
		  city_id_obj["${user.city-1}"].selected = true;
		 role = document.getElementById("role1");
		 role.value="${user.role}";
		 }
		 <%
		 }
		%>
	}
		function div_page(currentPage,totalPage,pagename){ 
		//显示分页栏部分
		    var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		}
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\user_servlet.do?user_id1=${user_id1}&user_name=${user_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\user_servlet.do?user_id1=${user_id1}&user_name=${user_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\user_servlet.do?user_id1=${user_id1}&user_name=${user_name}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\user_servlet.do?user_id1=${user_id1}&user_name=${user_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\user_servlet.do?user_id1=${user_id1}&user_name=${user_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  



function test1(user_id){
console.log(user_id);
	var string;
	var user_idtest;
<%	 ArrayList<admin.Notice_admin.User> arrayList1=(ArrayList<admin.Notice_admin.User>)request.getAttribute("userlist"); 
		int size1=arrayList.size();
		 for(int i=0;i<size1;i++){
		 admin.Notice_admin.User user1=new admin.Notice_admin.User();
		 user1=arrayList.get(i);
		request.setAttribute("user1", user1);
		 %>
		 user_idtest="<%out.print(user1.getUser_id());%>";
		 if(user_id==user_idtest){
		 document.getElementById("del_user_id").innerHTML="确认删除该用户${user1.user_id}？";
		
		document.getElementById("del").value=${user1.user_id};
		 }
		 <%
		 }
		%>
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
					class="icon-home"></i> Home</a> <a>用户管理</a>
			</div>
				<form action="user_servlet.do" method="get">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<div class="widget-box">
								<div class="widget-content nopadding">
									<div class="row-fluid search_double_admin">
										<div class="control-group search_double_admin_input">
											<div class="controls" >
												<input type="text" name="user_id1" id="pwd" placeholder="请输入用户名" />
												<input type="text" name="user_name" id="pwd" placeholder="请输入用户姓名" />
												<input type="hidden" name="currentPage" value="1"  />
												<input type="hidden" name="oper" value="search"  />
											</div>
										</div>
										<div class="control-group btn_double">
											<div class="controls" style="text-align: center;">
												<input type="submit" value="搜索" class="btn btn-primary ">
													 <button   class="btn btn-primary "
													data-toggle="modal" data-target="#add">添加</button>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
 			</form>
 			
				<div class="table-responsive">
					<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="9" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">用户管理</h6>
<div  class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>									          
									          </th>									        
							      </tr>

							<tr>
								<th scope="col">用户名</th>
								<th scope="col">姓名</th>
								<th scope="col">城市</th>
								<th scope="col">角色</th>
								
								<th scope="col">操作</th>
							</tr>
						</thead>
					<tbody>
								<c:forEach items="${userlist }" var="list">
							<tr>
								<td>${list.user_id }</td>
								<td>${list.name }</td>
								<td>
								<script>
								var mycity=new Array("长沙","浏阳","湘潭","株洲","益阳","岳阳","常德","凤凰","娄底","醴陵","韶山","衡阳","洪江","吉首","邵阳");
								var string=mycity[${list.city }-1];
								document.write(string);
								</script>
								</td>
								<td>${list.role }</td>
								
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test(${list.user_id });">修改</a>
								<c:if test="${list.user_id!=user_person.user_id }">
								/<a href="#" data-toggle="modal" data-target="#delete" onclick="test1(${list.user_id})">删除</a>
								</c:if>
								</td>
								</tr>
						</c:forEach>
						</tbody>
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

	<%@include file="/common/Footer.jsp"%>
	<script type="text/javascript"> 
	var judge=false;
	function show(o)
{
var str=o.value;
  var xmlhttp;
  if (str.length==0)
  { 
     document.getElementById("test1").innerHTML="";
    judge=false;
    
  }
  if (window.XMLHttpRequest)
  {
    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
  }
  else
  {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
     document.getElementById("test1").innerHTML=xmlhttp.responseText;
    if(xmlhttp.responseText.length>0){
    judge=false;
    }else
    {
     judge=true;
       console.log(judge);
     
    }
     
    }
  }
  xmlhttp.open("GET","/test_7/Valid?jsp=user&user_id="+str,true);
  xmlhttp.send();
}
	var user_id5;
		function show1(o)
{
var str=o.value;
  var xmlhttp;
  
  
  if (str.length==0)
  { 
     document.getElementById("test2").innerHTML="";
    judge=false;
    return ;
  }
  if(user_id5==str){
   judge=true;
    document.getElementById("test2").innerHTML="";
    return ;
  
  }
  if (window.XMLHttpRequest)
  {
    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
  }
  else
  {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    
   console.log(user_id5);
    console.log(str);
    if(user_id5!=str){
    
     if(xmlhttp.responseText.length>0){
     document.getElementById("test2").innerHTML=xmlhttp.responseText;
    judge=false;
    }else if(xmlhttp.responseText.length==0)
    {
    console.log("test2");
    document.getElementById("test2").innerHTML="";
    console.log(/^\d+$/gi.test(str));
    if(/^\d+$/gi.test(str))
    judge=true;
    }
    else{
     console.log("test3");
    judge=false;
    }
    }
    else{
    
    document.getElementById("test2").innerHTML="";
     judge=true;
    }
   
     
    }
  }
  xmlhttp.open("GET","/test_7/Valid?jsp=user&user_id="+str,true);
  xmlhttp.send();
}
	
	
  var st=false;

function chkid(o){
   
   st=/^\d+$/gi.test(o.value);
   if(!st){
      o.nextSibling.innerHTML=(o.value==''?'请输入用户名':'用户名应位纯数字');
      o.nextSibling.style.display='inline';
      return false;
   }
   else 
   {o.nextSibling.style.display='none';
     return true;
   }
}

 
function chkfrm(f){
  var count=0;
   if(chkid(f.user_id)) count++;
   if(chkname(f.name)&&judge) {judge=false;count++};
    
  if(count<2)
  { 
   document.getElementById("r").innerHTML="保存失败";return false;
   
  
  }
  else
  {
   
   return true;
   }
}


function chkfrmxiugai(f){
  var count=0;
 
  var i=document.getElementById("user_id1");
   show1(i)
  
   if(chkid(i)) count++;
   if(chkname(f.name)) { console.log(judge);if(judge){judge=false;count++;}}
    
  if(count<2)
  { 
   document.getElementById("r").innerHTML="保存失败";return false;
 }
  else
  {
   
   return true;
   }
}


function chkname(o){

   var str=o.value.replace(/^\s$/,'');  
   
   if(str=="")
   {
  
     o.nextSibling.innerHTML='请输入姓名';return false;
   }
   else
  { 
  
  o.nextSibling.style.display=(st?'none':'inline');
     return true;
  }
}
</script>

			
	<!--弹出添加用户-->
	<div class="modal fade " id="add" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
				</div>

				<form action="<%=basePath%>user_servlet.do?" method="get"  onsubmit="javascript:return chkfrm(this);">
				<input type="hidden" name="currentPage" value="1"  />
					<input type="hidden" name="oper" value="add"  />
				<div class="modal-body" style="width:400px;height:250px;">
				
							<table style="margin-left:auto; margin-right:auto;margin-top:20px;">
							<tr>
					
							<td>用户名</td>
							
						<td style="width:350px"><input name="user_id" type="text" onblur="javascript:chkid(this);show(this)"   /><span id="test"style="color:red"></span><span id="test1"style="color:red"></span></td>
						</tr>
						
						<tr>
							<td>姓名</td>
						<td style="width:350px"><input name="name" type="text"  onblur="javascript:chkname1(this);" /><span style="color:red"></span></td>
						</tr>
						<tr>
							<td>城市</td>
							<td> <select  name="city_id" id="city_id" >
                        <option value="1" >长沙</option>
                        <option value="2">浏阳</option>
                        <option value="3">湘潭</option>
                        <option value="4">株洲</option>
                        <option value="5">益阳</option>
                        <option value="6">岳阳</option>
                        <option value="7">常德</option>
                        <option value="8">凤凰</option>
                        <option value="9">娄底</option>
                        <option value="10">醴陵</option>
                        <option value="11">韶山</option>
                        <option value="12">衡阳</option>
                        <option value="13">邵阳</option>
                        <option value="14">吉首</option>
                        <option value="15">洪江</option>
                    </select></td>
						</tr>
						<tr>
							<td>角色</td>
							<td> <select  name="role_id"  >
                        <option value="r1" >系统管理员</option>
                        <option value="r2">省级超级用户</option>
                        <option value="r3">市级超级用户</option>
                        <option value="r4">省级一般用户</option>
                        <option value="r5">市级一般用户</option>
                    </select></td>
						</tr>
					  <tr> <td> </td><td style="text-align:center "><span id="r" name="result" style="color:red " ></span></td></tr>
						</table>

					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary" >
						保存</button>
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


	<!--弹出修改-->
	<div class="modal fade " id="xiugai" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
					<form action="<%=basePath%>user_servlet.do" method="get" onsubmit="javascript:return chkfrmxiugai(this);">
					<input type="hidden" name="currentPage" value="1"  />
					<input type="hidden" name="oper" value="mod"  />
					<input type="hidden" name="user_id" id="mod_user_id" />
					
				<div class="modal-body" style="width:400px;height:250px;">
				
							<table id="modsimple" style="margin-left:auto; margin-right:auto;margin-top:20px;">
								<tr>
							<td >用户名</td>
							<td style="width:350px"> <input name="reuser_id" type="text" id="user_id1"  onblur="javascript:chkid(this);show1(this)"    /><span style="color:red"></span><span id="test2"style="color:red"></span></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td style="width:350px"><input name="name" type="text" id="name1" onblur="javascript:chkname(this);" /><span style="color:red"></span></td>
						</tr>
						<tr>
							<td>城市</td>
							<td> <select  name="city_id" id="city1" >
                        <option value="1" >长沙</option>
                        <option value="2">浏阳</option>
                        <option value="3">湘潭</option>
                        <option value="4">株洲</option>
                        <option value="5">益阳</option>
                        <option value="6">岳阳</option>
                        <option value="7">常德</option>
                        <option value="8">凤凰</option>
                        <option value="9">娄底</option>
                        <option value="10">醴陵</option>
                        <option value="11">韶山</option>
                        <option value="12">衡阳</option>
                        <option value="13">邵阳</option>
                        <option value="14">吉首</option>
                        <option value="15">洪江</option>
                    </select></td>
						</tr>
						<tr>
							<td>角色</td>
							<td><input name="reuser_id" type="text" id="role1"  readonly="readonly"  /><span style="color:red"></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><a id="mod_password" href="<%=basePath%>mod_security.do?mark=mod_password&user_id=">重置密码</a></td>
						</tr>
						
						<tr>
								<td>密保问题重置</td>
								<td><a id="mod_question"href="<%=basePath%>mod_security.do?mark=mod_question&user_id=">重置密保</a></td>
						</tr>
						 <tr> <td> </td><td style="text-align:center "><span id="r" name="result" style="color:red " ></span></td></tr>
						</table>

					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary" >
						保存</button>
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--弹出删除-->
	<div class="modal fade " id="delete" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-body">
					<p id="del_user_id" align="center" style="font-family:'微雅软黑'; font-size:24px;"></p>
				</div>
				<div class="modal-footer">
					
					<form action="<%=basePath%>user_servlet.do" method="get" >
				<input id="del" type="hidden" name="user_id">
					<input type="hidden" name="currentPage" value="1"  />
					<input type="hidden" name="oper" value="delect"  />
					  <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
          <input type="submit" class="btn btn-primary"  value="确认">  
        </div>
					</form>
					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!-- 重置密码时回显示信息 -->
	
	
</body>
</html>