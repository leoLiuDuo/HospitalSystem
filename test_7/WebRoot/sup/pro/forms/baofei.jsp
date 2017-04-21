<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>资产报废率报表</title>
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
		    		tempStr = "<li><a href=\"\\test_7\\forms_baofei.do?oper=search&city=${city}&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\forms_baofei.do?oper=search&city=${city}&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\forms_baofei.do?oper=search&city=${city}&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\forms_baofei.do?oper=search&city=${city}&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\forms_baofei.do?oper=search&city=${city}&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
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
					class="icon-home"></i> Home</a> <a>资产报废率报表</a>
			</div>

			        <div class="container-fluid">
	      <div class="row-fluid">
			<div class="span12">
			   <div class="widget-box">
			     <div class="widge-content nopadding">
			     <form action="<%=basePath %>forms_baofei.do" method="get"  onsubmit="return chkfrm(this)"> 
			       <div class="row-fluid search_double">
				       <div class="control-group  search_double_input">
				         <div class="buttons" >
					        <select  name="city" id="city" size="1" >
					   <c:if test="${role_id=='r2'||role_id=='r4' }"> <option value="全省">全省</option> </c:if>
                       <c:if test="${(role_id=='r3'&&city_id==1)||(role_id=='r5'&&city_id==1)||role_id=='r2'||role_id=='r4' }"> <option value="长沙" >长沙</option></c:if>	
                       <c:if test="${(role_id=='r3'&&city_id==2)||(role_id=='r5'&&city_id==2)||role_id=='r2'||role_id=='r4' }">   <option value="浏阳">浏阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==3)||(role_id=='r5'&&city_id==3)||role_id=='r2'||role_id=='r4' }">    <option value="湘潭">湘潭</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==4)||(role_id=='r5'&&city_id==4)||role_id=='r2'||role_id=='r4' }">  <option value="株洲">株洲</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==5)||(role_id=='r5'&&city_id==5)||role_id=='r2'||role_id=='r4' }"><option value="益阳">益阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==6)||(role_id=='r5'&&city_id==6)||role_id=='r2'||role_id=='r4' }"><option value="岳阳">岳阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==7)||(role_id=='r5'&&city_id==7)||role_id=='r2'||role_id=='r4' }">  <option value="常德">常德</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==8)||(role_id=='r5'&&city_id==8)||role_id=='r2'||role_id=='r4' }"> <option value="凤凰">凤凰</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==9)||(role_id=='r5'&&city_id==9)||role_id=='r2'||role_id=='r4' }">  <option value="娄底">娄底</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==10)||(role_id=='r5'&&city_id==10)||role_id=='r2'||role_id=='r4' }"><option value="醴陵">醴陵</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==11)||(role_id=='r5'&&city_id==11)||role_id=='r2'||role_id=='r4' }"> <option value="韶山">韶山</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==12)||(role_id=='r5'&&city_id==12)||role_id=='r2'||role_id=='r4' }">  <option value="衡阳">衡阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==13)||(role_id=='r5'&&city_id==13)||role_id=='r2'||role_id=='r4' }"> <option value="邵阳">邵阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==14)||(role_id=='r5'&&city_id==14)||role_id=='r2'||role_id=='r4' }"> <option value="吉首">吉首</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==15)||(role_id=='r5'&&city_id==15)||role_id=='r2'||role_id=='r4' }"> <option value="洪江">洪江</option></c:if>
                  			</select> 
					     <input name="startTime" type="date" id="startTime" placeholder="请输入起始时间,例2016-01-01" onblur="javascript:chkdate_star(this);"> - <input
						name="endTime" type="date" id="endTime" placeholder="请输入截止时间例2016-01-01" onblur="javascript:chkdate_end(this);"> 
					  </div>
					 <table style="width:840px;margin-top:0px">
		<tr><td style="width:280px"></td><td style="width:280px"><span style=" color:red " id="start"></td><td style="width:280px"><span style=" color:red " id="end">  </span></td></tr>
		</table>
					</div>
					<div class="control-group  btn_double">
					<div class="controls">
					
					<input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="search"  />
					<input name="generate" class="btn btn-primary "
						id="generate" value="生成" type="submit">
					 <a style="color:white;" class="btn btn-primary" href="<%=basePath %>Export_Servlet?startTime=${startTime}&endTime=${endTime}&city=${city}&currentPage=${currentPage}&oper=baofei">
					       
					       导出</a>

					             </div>
					          </div>
					     </div>
				      </div>
				   </div>
				</div>
			</div>
		</div>
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							
							<div class="table-responsive">
								<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="4" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">资产报废率报表</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>
									          </th>									        
							      </tr>
										<tr>
											<th scope="col">资产类型</th>
											<th scope="col">报废资产净额</th>
											<th scope="col">资产总额</th>
											<th scope="col">报废率</th>
										</tr>
										<tbody>
										<c:forEach items="${baofei_list}" var="list">
										<tr>
											<td>${list.asset_type }</td>
											<td>${list.baofei_num }</td>
											<td>${list.asset_status_sum }</td>
											<td>${list.baofei_rate }%</td>
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
				</div>
			</div>
</div>
			<%@include file="/common/Footer.jsp"%>
</body>
</html>