<%@page import="pro.javabean.Assert_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head><title>铎铎医院医疗系统</title>
<%--<c:if test="${role_id=='r2' }"><title>省级超级用户</title></c:if>
<c:if test="${role_id=='r3' }"><title>市级超级用户用户</title></c:if>
<c:if test="${role_id=='r4' }"><title>省级一般用户</title></c:if>
<c:if test="${role_id=='r5' }"><title>市级一般用户</title></c:if> --%>

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
<body onload="myseclect(${oper})">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	<script src="/test_7/js/Chart.js"></script>
	<script src="/test_7/js/Chart.min.js"></script>

<script type="text/javascript">
	function submitFun(name){
		document.getElementById(name).submit();
	}
	function myseclect(oper){
		if(oper=="year"){
			options[0].selected=true;
		}else if(oper="month"){
			options[1].selected=true;
		}else{
			options[2].selected=true;
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
					class="icon-home"></i> Home</a> <a>数据展示</a>	
			</div>
				
			
			
		</div>
		</br>
		<div align="center">
		  <p style="margin-top:50px">
				<b>患者数量</b> ${totalPatients } &nbsp;&nbsp;
				<b>检验项数量</b> ${totalExamRecords } &nbsp;&nbsp;
				<b>检查项数量</b> ${totalTestRecords }
		  </p>
		  
		  <div align="center" style="select2-container;margin-top:50px">
		    <form id="form1" name="form1" action="<%=basePath %>show_data_servlet.do" method="get">
		    <select name="oper" id="oper">
		      <c:choose>
		      <c:when test="${oper=='year'}">
		       <option value="year" selected="selected" onclick="submitFun('form1')" >按一年显示</option>
		      </c:when>
		      <c:otherwise>
		       <option value="year"  onclick="submitFun('form1')" >按一年显示</option>
		      </c:otherwise>
		      </c:choose>
		      
		      <c:choose>
		      <c:when test="${oper=='month'}">
		       <option value="month" selected="selected" onclick="submitFun('form1')" >按一月显示</option>
		      </c:when>
		      <c:otherwise>
		       <option value="month" onclick="submitFun('form1')" >按一月显示</option>
		      </c:otherwise>
		      </c:choose>
		      
		      <c:choose>
		      <c:when test="${oper=='week'}">
		       <option value="week" selected="selected" onclick="submitFun('form1')" >按一周显示</option>
		      </c:when>
		      <c:otherwise>
		       <option value="week" onclick="submitFun('form1')" >按一周显示</option>
		      </c:otherwise>
		      </c:choose>
		      
		    </select>
		    </form>
		  </div>		
		<canvas id="bar" height="400" width="821" ></canvas>  
    <script>  
    var myDate = new Date();
    var myY=myDate.getFullYear();
    var myM=myDate.getMonth()+1;
    var myD=myDate.getDate();
    if("${oper}"=="year"){
    	var m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12;
    		switch(myDate.getMonth()+1){
    		  case 1: m12=myY+".1";m11=(myY-1)+".12";m10=(myY-1)+".11";m9=(myY-1)+".10";m8=(myY-1)+".9";m7=(myY-1)+".8";
    		  			m6=(myY-1)+".7";m5=(myY-1)+".6";m4=(myY-1)+".5";m3=(myY-1)+".4";m2=(myY-1)+".3";m1=(myY-1)+".2";
    		    break;
    		  case 2: m12=myY+".2";m11=myY+".1";m10=(myY-1)+".12";m9=(myY-1)+".11";m8=(myY-1)+".10";m7=(myY-1)+".9";
    		  			m6=(myY-1)+".8";m5=(myY-1)+".7";m4=(myY-1)+".6";m3=(myY-1)+".5";m2=(myY-1)+".4";m1=(myY-1)+".3";
    		    break;
    		  case 3: m12=myY+".3";m11=myY+".2";m10=myY+".1";m9=(myY-1)+".12";m8=(myY-1)+".11";m7=(myY-1)+".10";
    		  			m6=(myY-1)+".9";m5=(myY-1)+".8";m4=(myY-1)+".7";m3=(myY-1)+".6";m2=(myY-1)+".5";m1=(myY-1)+".4";
    		    break;
    		  case 4: m12=myY+".4";m11=myY+".3";m10=myY+".2";m9=myY+".1";m8=(myY-1)+".12";m7=(myY-1)+".11";
    		  			m6=(myY-1)+".10";m5=(myY-1)+".9";m4=(myY-1)+".8";m3=(myY-1)+".7";m2=(myY-1)+".6";m1=(myY-1)+".5";
    		    break;
    		  case 5: m12=myY+".5";m11=myY+".4";m10=myY+".3";m9=myY+".2";m8=myY+".1";m7=(myY-1)+".12";
    		  			m6=(myY-1)+".11";m5=(myY-1)+".10";m4=(myY-1)+".9";m3=(myY-1)+".8";m2=(myY-1)+".7";m1=(myY-1)+".6";
    		    break;
    		  case 6: m12=myY+".6";m11=myY+".5";m10=myY+".4";m9=myY+".3";m8=myY+".2";m7=myY+".1";
    		  			m6=(myY-1)+".12";m5=(myY-1)+".11";m4=(myY-1)+".10";m3=(myY-1)+".9";m2=(myY-1)+".8";m1=(myY-1)+".7";
    		    break;
    		  case 7: m12=myY+".7";m11=myY+".6";m10=myY+".5";m9=myY+".4";m8=myY+".3";m7=myY+".2";
    		  			m6=myY+".1";m5=(myY-1)+".12";m4=(myY-1)+".11";m3=(myY-1)+".10";m2=(myY-1)+".9";m1=(myY-1)+".8";
    		    break;
    		  case 8: m12=myY+".8";m11=(myY-1)+".7";m10=(myY-1)+".6";m9=(myY-1)+".5";m8=(myY-1)+".4";m7=(myY-1)+".3";
    		  			m6=(myY-1)+".2";m5=(myY-1)+".1";m4=(myY-1)+".12";m3=(myY-1)+".11";m2=(myY-1)+".10";m1=(myY-1)+".9";
    		    break;
    		  case 9: m12=myY+".9";m11=myY+".8";m10=myY+".7";m9=myY+".6";m8=myY+".5";m7=myY+".4";
    		  			m6=myY+".3";m5=myY+".2";m4=myY+".1";m3=(myY-1)+".12";m2=(myY-1)+".11";m1=(myY-1)+".10";
    		    break;
    		  case 10: m12=myY+".10";m11=myY+".9";m10=myY+".8";m9=myY+".7";m8=myY+".6";m7=myY+".5";
    		  			m6=myY+".4";m5=myY+".3";m4=myY+".2";m3=myY+".1";m2=(myY-1)+".12";m1=(myY-1)+".11";
    		    break;
    		  case 11: m12=myY+".11";m11=myY+".10";m10=myY+".9";m9=myY+".8";m8=myY+".7";m7=myY+".6";
    		  			m6=myY+".5";m5=myY+".4";m4=myY+".3";m3=myY+".2";m2=myY+".1";m1=(myY-1)+".12";
    		    break;
    		  case 12: m12=myY+".12";m11=myY+".11";m10=myY+".10";m9=myY+".9";m8=myY+".8";m7=myY+".7";
    		  			m6=myY+".6";m5=myY+".5";m4=myY+".4";m3=myY+".3";m2=myY+".2";m1=myY+".1";
    		    break;
    		  default:
    		    break;
    		}
    	var data = {
    		 
            labels : [m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12],  
            datasets : [  
                 
                {  
                    fillColor : "rgba(151,187,205,0.5)",  
                    strokeColor : "rgba(151,187,205,1)",  
                    pointColor : "rgba(151,187,205,1)",  
                    pointStrokeColor : "#fff",  
                    data : ["${m1}","${m2}","${m3}","${m4}","${m5}","${m6}","${m7}","${m8}","${m9}","${m10}","${m11}","${m12}"]  
                }  
            ],  
            scaleLable:"个"
        };  
  
      
        var ctx = document.getElementById("bar").getContext("2d");  
        var myNewChart = new Chart(ctx).Line(data);
    
    }else if("${oper}"=="month"){
    	var data = {
    		 
            labels : ["${ddnum29}","${ddnum28}","${ddnum27}","${ddnum26}","${ddnum25}","${ddnum24}","${ddnum23}","${ddnum22}","${ddnum21}","${ddnum20}",
            		"${ddnum19}","${ddnum18}","${ddnum17}","${ddnum16}","${ddnum15}","${ddnum14}","${ddnum13}","${ddnum12}","${ddnum11}","${ddnum10}",
            		"${ddnum9}","${ddnum8}","${ddnum7}","${ddnum6}","${ddnum5}","${ddnum4}","${ddnum3}","${ddnum2}","${ddnum1}","${ddnum0}"],  
            datasets : [  
                 
                {  
                    fillColor : "rgba(151,187,205,0.5)",  
                    strokeColor : "rgba(151,187,205,1)",  
                    pointColor : "rgba(151,187,205,1)",  
                    pointStrokeColor : "#fff",  
                    data : ["${dnum0}","${dnum1}","${dnum2}","${dnum3}","${dnum4}","${dnum5}","${dnum6}","${dnum7}","${dnum8}","${dnum9}",
                    "${dnum10}","${dnum11}","${dnum12}","${dnum13}","${dnum14}","${dnum15}","${dnum16}","${dnum17}","${dnum18}","${dnum19}",
                    "${dnum20}","${dnum21}","${dnum22}","${dnum23}","${dnum24}","${dnum25}","${dnum26}","${dnum27}","${dnum28}","${dnum29}"]  
                }  
            ]  
        };  
        var ctx = document.getElementById("bar").getContext("2d");  
        var myNewChart = new Chart(ctx).Line(data);
    }else{
    	var data = {
    		 
            labels : ["${ddnum6}","${ddnum5}","${ddnum4}","${ddnum3}","${ddnum2}","${ddnum1}","${ddnum0}"],  
            datasets : [  
                 
                {  
                    fillColor : "rgba(151,187,205,0.5)",  
                    strokeColor : "rgba(151,187,205,1)",  
                    pointColor : "rgba(151,187,205,1)",  
                    pointStrokeColor : "#fff",  
                    data : ["${dnum0}","${dnum1}","${dnum2}","${dnum3}","${dnum4}","${dnum5}","${dnum6}"]  
                }  
            ]  
        };  
        var ctx = document.getElementById("bar").getContext("2d");  
        var myNewChart = new Chart(ctx).Line(data);
    }
      
          
    </script>
		</div>
	</div>
  
   
	<%@include file="/common/Footer.jsp"%>
	<%@include file="/common/Delete.jsp"%>
	<%@include file="Home_pro_sup_det.jsp"%>
</body>
</html>