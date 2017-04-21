<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>商务合同</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
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
		function div_page(currentPage,totalPage){ 
	 
	       var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		     }
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\ContServlet.do?cont_name=${cont_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\ContServlet.do?cont_name=${cont_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		   		
		   	 
		    	 for(var i=1;i<=totalPage;i++){
		    	 
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\ContServlet.do?cont_name=${cont_name}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    	 
		    		
		   		}
		   
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\ContServlet.do?cont_name=${cont_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\ContServlet.do?cont_name=${cont_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
			 
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
 

	   
}  

	
	function test(cont_id,cont_name,start_date,buy_date){
			  document.getElementById("cont_id1").value=cont_id;
			   document.getElementById("cont_name1").value=cont_name;
			    document.getElementById("start_date1").value=start_date;
			     document.getElementById("buy_date1").value=buy_date;
	      
	}
 
 
function test1(cont_id){
  
		    document.getElementById("cont_id1").value=cont_id;
	}
 
 
 
function chkn(o)
{
 
 var str=o.value.replace(/^\s$/,'');
 
  if(str=='')
  {
    if(o.id=="cont_name")
    document.getElementById("name").innerHTML="输入不能为空";
    else
      document.getElementById("name1").innerHTML="输入不能为空";
    return false;
  }
  else
    {
     document.getElementById("name").innerHTML='';    
      document.getElementById("name1").innerHTML='';return true;
  
    }
   }
 function chknum(o)
{ 
   var str=o.value.replace(/^\s$/,'');
   var s=/^[A-Za-z0-9]+$/gi.test(str);  
  
  if(str=='')
  {
   
    document.getElementById("num").innerHTML="输入不能为空";
   
    return false;
  }
  else
    {
       if(!s)
       {
        
        document.getElementById("num").innerHTML="编号只能是数字字母组合";
       
        return false;
       }
       else{
       document.getElementById("num").innerHTML='';
        
       return true;
       }
    }   
  }
    
   function chkdate_end(o)
{
  var str=o.value.replace(/^\s$/,'');
  var st=/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/gi.test(str);
   if(!st)
   {
     if(o.id=="start_date")
     {
        if(str=='')
        document.getElementById("end").innerHTML="请输入日期";
        
        else
        document.getElementById("end").innerHTML="日期不合法";
     }
     else
      {
        if(str=='')
        document.getElementById("end1").innerHTML="请输入日期";
        else
         document.getElementById("end1").innerHTML="日期不合法";
      }
     return false;
   }
    else
   {
       document.getElementById("end").innerHTML='';
       document.getElementById("end1").innerHTML='';
     return true;
   }
}
function chkdate_star(o)
{
    var str=o.value.replace(/^\s$/,'');
   st=/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/gi.test(str);
     if(!st)
   {
     if(o.id=="buy_date")
      {
        if(str=='')
        document.getElementById("start").innerHTML="请输入日期";
        else
         document.getElementById("start").innerHTML="日期不合法";
        
      }
     else
     {
         if(str=='')
        document.getElementById("start1").innerHTML="请输入日期";
        else
        document.getElementById("start1").innerHTML="日期不合法";
     }
    return false;
   }
   else
   {
   document.getElementById("start").innerHTML='';
   document.getElementById("start1").innerHTML='';
   return true;
   }
}


function chkfrm(o)
{  
   var beginDate=0;
   var endDate=0;
   var count=0;
   var d=0;
    if(chkdate_star(o.buy_date)) {beginDate=new Date(o.buy_date.value.replace(/-/g,"/"));count++;d++}
    if(chkdate_end(o.start_date)) {  endDate=new Date(o.start_date.value.replace(/-/g,"/"));count++;d++}
    if(chkn(o.cont_name)) count++;
    if(chknum(o.cont_id)&&judge){judge=false; count++;}
    if(beginDate>endDate&&d>=2){
     document.getElementById("re1").innerHTML="购置日期不能大于启用日期";
     }
    else
   count++;
   if(count>=5)
    {
      return true;
   }
   
   if(count<5)
     {
      return false;
     }
}


function chkfrmxiugai(o)
{  
   var beginDate=0;
   var endDate=0;
   var count=0;
   var d=0;
    if(chkdate_star(o.buy_date)) {beginDate=new Date(o.buy_date.value.replace(/-/g,"/"));count++;d++;}
    if(chkdate_end(o.start_date)) {  endDate=new Date(o.start_date.value.replace(/-/g,"/"));count++;d++}
    if(chkn(o.cont_name)) count++;
     
    if(beginDate>endDate&&d>=2){
     document.getElementById("re2").innerHTML="购置日期不能大于启用日期";
     }
      else
    count++;
 
   if(count>=4)
    {
      return true;
   }
   
   if(count<4)
     {
      return false;
     }
}

  
 	 
var judge=false;
  function show(str)
{

  var xmlhttp;
  if (str.length==0)
  { 
   
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
    
   
    if(xmlhttp.responseText.length>0){
     document.getElementById("num").innerHTML=xmlhttp.responseText;
    judge=false;
     
   
    }else
     judge=true;
     
    }
    console.log(judge2);
  }
  xmlhttp.open("GET","/test_7/Valid?jsp=cont&cont_id="+str,true);
  xmlhttp.send();
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
					class="icon-home"></i> Home</a> <a>商务合同</a>

			</div>
		
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							
						</div>
					</div>
				</div>
			<form action="<%=basePath %>ContServlet.do" method="get">
				<div class="widget-box">
								<div class="widget-content nopadding">
									<div class="row-fluid search_single">
										<div class="control-group search_single_input">
											<div class="controls">
												<input type="text" name="cont_name" id="pwd" placeholder="合同名称">
												<input type="hidden" name="currentPage" value="1"  />
												<input type="hidden" name="oper" value="search"  />
											</div>
										</div>
										<div class="control-group btn_double">
											<div class="controls">
												<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#add">添加</button>
												<button type="submit" class="btn btn-primary">查询</button>
											</div>
										</div>
									</div>
								</div>
							</div>
		       </form>
				<div class="table-responsive">
				
				<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="10" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">商务合同信息表</h6>
<div  class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>									          
										         
									          </th>									        
							      </tr>
							<tr>
								<th>合同名称</th>
								<th>合同编号</th>
								<th>购置日期</th>
								<th>起用时间</th>
								<th>操作</th>
							</tr>
						</thead>
                   <tbody>
                   
 	 
 
	       			   <tr>
						<c:forEach items="${contlist}" var="list">
							<tr>
								<td>${list.cont_name}</td>
								<td>${list.cont_id}</td>
								 
								<td>${list.buy_date}</td>
								<td>${list.start_date}</td>  
							  
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test('${list.cont_id}','${list.cont_name}','${list.start_date}','${list.buy_date}');">修改</a>/ 
								<a href="#" data-toggle="modal" data-target="#delete" onclick="test1('${list.cont_id}')";  >删除</a></td>
								</tr>
						</c:forEach>
				</tr>
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
	

		<div class="modal fade " id="add" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商务合同</h4>
				</div>
				<form name="add"  action="<%=basePath %>ContServlet.do"  method="get"  onsubmit="javascript:return chkfrm(this);">
				
					<div class="modal-body">
						<table style=" margin-top:20px;width:400px">
							<tr>
								<td style="width:64px">合同名称</td>
								 
								<td style="width:100px"><input name="cont_name" id="cont_name" type="text" onblur="javascript:chkn(this)"/></td><td><span id="name" style="color:red;"></span></td>
							</tr>
							<tr>
								<td>合同编号</td>
								<td><input placeholder="请输入字母数字组合" name="cont_id" id="cont_id" type="text" onblur="javascript:chknum(this);javascript:show(this.value)" /></td><td><span id="num" style="color:red"/></td>
							</tr>
							<tr>
								<td>购置日期</td>
								<td><input  placeholder="日期格式:例2016-01-01" name="buy_date"  id="buy_date" type="text" onblur="javascript:chkdate_star(this);"  /></td><td><span id="start" style="color:red"/></td>
							</tr>
							<tr>
								<td>启用时间</td>
								<td>
							 
								<input placeholder="日期格式:例2016-01-01" name="start_date" id="start_date" type="text"  onblur="javascript:chkdate_end(this);" /></td><td><span id="end" style="color:red"/></td>
							</tr>
							 <tr><td></td><td><span style="color:red;" id="re1"></span></td></tr> 
						</table>
			        <input type="hidden" name="oper" value="add"  />
		
          	</div>

				        
			    			   	<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
		            <input type="submit" value="保存"  class="btn btn-primary"  
					  />		
				 
			      	</div>
						</form>
						
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	
	<div class="modal fade " id="xiugai" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商务合同</h4>
				</div>


				<form name="add"  action="<%=basePath %>ContServlet.do"  method="get" onsubmit="javascript:return chkfrmxiugai(this);">
				<input type="hidden" id=""/>
					<div class="modal-body" style="width:400px">
						<table style=" margin-top:20px;width:400px" >
							<tr>

								<td style="width:64px">合同名称</td>
								<td style="width:100px"><input name="cont_name" id="cont_name1" type="text" onblur="javascript:chkn(this);"   /></td><td><span id="name1" style="color:red;"></span></td>
							</tr>
							<tr>
								<td>合同编号</td>
								<td><input    placeholder="请输入字母数字组合" name="cont_id" id="cont_id1" type="text"  readonly="readonly" onblur="javascript:chknum(this);"/></td><td><span id="num1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>购置日期</td>
								<td><input placeholder="日期格式:例2016-01-01" name="buy_date"  id="buy_date1" type="text" onblur="javascript:chkdate_star(this);" /></td><td><span id="start1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>启用时间</td>
								<td>
							 
								<input  placeholder="日期格式:例2016-01-01" name="start_date" id="start_date1" type="text"  onblur="javascript:chkdate_end(this);"/></td><td><span id="end1" style="color:red"></span></td>
							</tr>
							 <tr><td></td><td><span style="color:red;" id="re2"></span></td></tr>
						</table>
			        <input type="hidden" name="oper" value="mod"  />
		
          	</div>

				        
			    			   	<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
		            <input type="submit" value="保存"  class="btn btn-primary"  
					  />		
				 
			      	</div>
						</form>
						
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
  <div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
    <form  name="delete" id="delete" action="<%=basePath %>ContServlet.do"  method="get" >
      <div class="modal-content">
        
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除该合同信息？</p>
          </div>
             <input type="hidden" name="oper" value="delect"  />
 			  <input  type="hidden" name="cont_id1" id="cont_id1" />      
			    			   	<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
		            <input type="submit" value="保存"  class="btn btn-primary"   />		
		
				 
			      	</div>
      </div>
      </form>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
  </div>
  </body>
</html>