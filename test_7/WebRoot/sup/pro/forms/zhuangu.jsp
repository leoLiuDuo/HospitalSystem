<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>资产转固率报表</title>
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
    if( chkdate_star(o.date1)) {beginDate=new Date(o.date1.value.replace(/-/g,"/"));count++;}
    if(chkdate_end(o.date2)) {  endDate=new Date(o.date2.value.replace(/-/g,"/"));count++;}
    
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
					class="icon-home"></i> Home</a> <a>资产转固率报表</a>
			</div>

			        <div class="container-fluid">
	      <div class="row-fluid">
			<div class="span12">
			   <div class="widget-box">
			     <div class="widge-content nopadding">
			       <form method="get" action="<%=basePath %>Zhuangu_Servlet.do"  onsubmit="return chkfrm(this)">
			       <div class="row-fluid search_double">
				       <div class="control-group  search_double_input">
				         <div class="buttons" >
					        <select  name="city" id="city" size="1" >
					   <c:if test="${role_id=='r2'||role_id=='r4' }"> <option value="0">全省</option> </c:if>
                       <c:if test="${(role_id=='r3'&&city_id==1)||(role_id=='r5'&&city_id==1)||role_id=='r2'||role_id=='r4' }"> <option value="1" >长沙</option></c:if>	
                       <c:if test="${(role_id=='r3'&&city_id==2)||(role_id=='r5'&&city_id==2)||role_id=='r2'||role_id=='r4' }">   <option value="2">浏阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==3)||(role_id=='r5'&&city_id==3)||role_id=='r2'||role_id=='r4' }">    <option value="3">湘潭</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==4)||(role_id=='r5'&&city_id==4)||role_id=='r2'||role_id=='r4' }">  <option value="4">株洲</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==5)||(role_id=='r5'&&city_id==5)||role_id=='r2'||role_id=='r4' }"><option value="5">益阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==6)||(role_id=='r5'&&city_id==6)||role_id=='r2'||role_id=='r4' }"><option value="6">岳阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==7)||(role_id=='r5'&&city_id==7)||role_id=='r2'||role_id=='r4' }">  <option value="7">常德</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==8)||(role_id=='r5'&&city_id==8)||role_id=='r2'||role_id=='r4' }"> <option value="8">凤凰</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==9)||(role_id=='r5'&&city_id==9)||role_id=='r2'||role_id=='r4' }">  <option value="9">娄底</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==10)||(role_id=='r5'&&city_id==10)||role_id=='r2'||role_id=='r4' }"><option value="10">醴陵</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==11)||(role_id=='r5'&&city_id==11)||role_id=='r2'||role_id=='r4' }"> <option value="11">韶山</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==12)||(role_id=='r5'&&city_id==12)||role_id=='r2'||role_id=='r4' }">  <option value="12">衡阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==13)||(role_id=='r5'&&city_id==13)||role_id=='r2'||role_id=='r4' }"> <option value="13">邵阳</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==14)||(role_id=='r5'&&city_id==14)||role_id=='r2'||role_id=='r4' }"> <option value="14">吉首</option></c:if>
                       <c:if test="${(role_id=='r3'&&city_id==15)||(role_id=='r5'&&city_id==15)||role_id=='r2'||role_id=='r4' }"> <option value="15">洪江</option></c:if>
                  			</select> 
					       <input name="date1" type="date" id="date_star" placeholder="请输入起始时间,例2016-01-01"  onblur="javascript:chkdate_star(this);"/> - <input
						name="date2" type="date" id="date_end" placeholder="请输入截止时间，例2016-01-01" onblur="javascript:chkdate_end(this);" />
					  </div>
					 <table style="width:840px;margin-top:0px">
		<tr><td style="width:280px"></td><td style="width:280px"><span style=" color:red " id="start"></td><td style="width:280px"><span style=" color:red " id="end">  </span></td></tr>
		
		</table>
					</div>
					<div class="control-group  btn_double">
					<div class="controls">
					<input name="generate" class="btn btn-primary "
						id="generate" value="生成" type="submit">
					 <a style="color:white" class="btn btn-primary" href="<%=basePath %>Export_Servlet?date1=${date1}&date2=${date2}&city=${city}&oper=zhuangu">
					       
					       导出</a>
					             </div>
					          </div>
					     </div>
					   </form>
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
									        <th colspan="10" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">资产转固率报表</h6></th>									        
							      </tr>
										<tr>
											<th scope="col">资产类型</th>
											<th scope="col">转固资产</th><th scope="col">总资产</th>
											 
											<th scope="col">转固率</th>
										</tr>
						<tr>	
					   <c:forEach items="${zhuangu_list}" var="list">
							<tr>
								<td>${list.asset_type}</td>
								<td>${list.zhuangu_num}</td>
								 
								<td>${list.asset_sum}</td>
								<td>${list.zhuangulv}</td>  
						    </tr>
						    </c:forEach>
						    </tr>
									</tbody>
								</table>
							</div>
							 
						</div>
					</div>
				</div>
			</div>
</div>
			<%@include file="/common/Footer.jsp"%>
</body>
</html>