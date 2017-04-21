<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>多维查询</title>
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
		    		tempStr = "<li><a href=\"\\test_7\\Zichan_servlet.do?oper=search&city=${city}&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\Zichan_servlet.do?oper=search&city=${city}&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\Zichan_servlet.do?oper=search&city=${city}&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\Zichan_servlet.do?oper=search&city=${city}&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\Zichan_servlet.do?oper=search&city=${city}&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  



var st=false;
function chkdate_end(o)
{
  var str=o.value.replace(/ \s+/,'');
   st=/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/gi.test(str);
   if(!st)
   {
     if(str=='')
     document.getElementById("end").innerHTML="请输入终止时间";
     else
     document.getElementById("end").innerHTML="请输入合法日期，例:2016-01-01";
     return false;
   }
    else
   {
     document.getElementById("end").innerHTML='';return true;
   }
}
function chkdate_star(o)
{
  var str=o.value.replace(/ \s+/,'');
   st=/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/gi.test(str);
     if(!st)
   {
    if(str=='')
     document.getElementById("start").innerHTML="请输入起始时间";
     else
     document.getElementById("start").innerHTML="请输入合法日期,例:2016-01-01";
    return false;
   }
   else
   {
   document.getElementById("start").innerHTML='';
   return true;
   }
}
function chkfrm(o)
{  
  var beginDate=0;
  var endDate=0;
   var count=0;
    if( chkdate_star(o.startTime)) {beginDate=new Date(o.startTime.value.replace(/-/g,"/"));count++;}
    if(chkdate_end(o.endTime)) {  endDate=new Date(o.endTime.value.replace(/-/g,"/"));count++;}
    
   if(count>=2)
    {
      if(beginDate>=endDate){
      document.getElementById("start").innerHTML='';
      document.getElementById("start").innerHTML='起始日期不能大于终止日期';
      return false;
     }
     else return true;
     
    }
   
   if(count<2)
     {
      return false;
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
				<a href="<%=basePath %>pro_sup_servlet.do?currentPage=1&oper=search" title="Go to Home" class="tip-bottom"><i
					class="icon-home"></i> Home</a> <a>患者详情</a>
			</div>
			<div class="container">
			 <form   class="form-horizontal" method="post"action="<%=basePath %>Zichan_servlet.do" name="password_validate" id="password_validate" novalidate>
    <%--<form   class="form-horizontal" method="post" action="pro_sup_servlet.do" name="password_validate" id="password_validate" novalidate> --%>
				
				
				
			
		
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<div class="table-responsive">
								<table  class="table_xiangqing table table-bordered table-striped ">									
									  <thead>
									  <tr >
									        <th colspan="20" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        
									 
									          <h5  class="table_title_first_name ">患者详情表</h5>
<div class="pagination" style="text-align:right; width:60px; height:10px;width:100px;float:right">

</div>
									          
									          </th>									        
							      </tr>
										<tr>
											<th>住院号</th>
											<th>姓名</th>
											<th>性别</th>
											<th>出生日期</th>
											<th>第几次住院</th>
											<th>年龄</th>
											<th>诊断结果</th>
										</tr>
										
										<tr>
											<td>肖盛</td>
											<td>男</td>
											<td>心肺阴性，腹软，全腹无压痛及反跳痛，双下肢不肿。</td>
											<td>356766</td>
											<td>080328689</td><td>3</td>
											<td><a href>查看详情</a></td>
										</tr>
										<tr>
											<td>龙潺</td>
											<td>性别</td>
											<td>双肺呼吸音清，未闻及干湿性啰音，心律齐，无杂音，腹软，肝脾未扪及肿大，双下肢无水肿。</td>
											<td>2827863</td>
											<td>0907058552</td><td>3</td>
											<td>查看详情</td>
										</tr>
									</thead>
									
								</table>
								</br>
								</br>
								</br>
								<table  class="table_jiancha table table-bordered table-striped ">									
									  <thead>
									  <tr >
									        <th colspan="20" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h5  class="table_title_first_name ">检查记录表</h5>
<div class="pagination" style="text-align:right; width:60px; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>
									          
									          </th>									        
							      </tr>
										<tr>
											<th>检查类别</th>
											<th>检查子类别</th>
											<th>临床症状</th>
											<th>物理症状</th>
											<th>相关诊断</th>
											<th>临床诊断</th>
											<th>申请时间</th>
											<th>结果报告日期</th>
										</tr>
										
										<tr>
											<td>肖盛</td>
											<td>男</td>
											<td>心肺阴性，腹软，全腹无压痛及反跳痛，双下肢不肿。</td>
											<td>356766</td>
											<td>080328689</td><td>3</td>
											<td><a href>查看详情</a></td>
										</tr>
										<tr>
											<td>龙潺</td>
											<td>性别</td>
											<td>双肺呼吸音清，未闻及干湿性啰音，心律齐，无杂音，腹软，肝脾未扪及肿大，双下肢无水肿。</td>
											<td>2827863</td>
											<td>0907058552</td><td>3</td>
											<td>查看详情</td>
										</tr>
									</thead>
									
								</table>
								
								</br>
								</br>
								</br>
								
								<table  class="table_jianyan table table-bordered table-striped ">									
									  <thead>
									  <tr >
									        <th colspan="20" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h5  class="table_title_first_name ">检验记录表</h5>
<div class="pagination" style="text-align:right; width:60px; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>
									          
									          </th>									        
							      </tr>
										<tr>
											<th>检验记录</th>
											<th>检验时间</th>
											<th>临床诊断</th>
											<th>抽样样本</th>
											<th>抽样时间</th>
											<th>申请时间</th>
											<th>报告结果时间</th>
										</tr>
										
										<tr>
											<td>肖盛</td>
											<td>男</td>
											<td>心肺阴性，腹软，全腹无压痛及反跳痛，双下肢不肿。</td>
											<td>356766</td>
											<td>080328689</td><td>3</td>
											<td><a href>查看详情</a></td>
										</tr>
										<tr>
											<td>龙潺</td>
											<td>性别</td>
											<td>双肺呼吸音清，未闻及干湿性啰音，心律齐，无杂音，腹软，肝脾未扪及肿大，双下肢无水肿。</td>
											<td>2827863</td>
											<td>0907058552</td><td>3</td>
											<td>查看详情</td>
										</tr>
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
			<%@include file="/common/Footer.jsp"%>
</body>
</html>