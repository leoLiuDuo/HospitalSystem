<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
<title>modify</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/matrix-login.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />


<script type="text/javascript">
	function chkchina(o)
	{
	 
	 var  ch=/.*[\u4e00-\u9faf]+.*/gi.test(o.value);//false说明是中文
     if(ch)
     {
       document.getElementById("new").innerHTML="密码不能含有中文" ;
       return false;
     }
     else
     {
        if(o.value=='')
       {
           document.getElementById("new").innerHTML="密码不能为空";return false;
       }
        document.getElementById("new").innerHTML='';return true;
       
     }
	
	} 
	
	
		function chkchina1(o)
	{
	 
	 var  ch=/.*[\u4e00-\u9faf]+.*/gi.test(o.value);//false说明是中文
     if(ch)
     {
       document.getElementById("renew").innerHTML="密码不能含有中文" ;
       return false;
     }
     else
     {
        if(o.value=='')
       {
           document.getElementById("renew").innerHTML="密码不能为空";return false;
       }
       else
       {
         document.getElementById("renew").innerHTML='';return true;
       }
     }
	
	} 
	
function chkfrm1(o)
{
     var count=0;
    if( chkchina1(o.password)) count++;
    if(chkchina1(o.repassword)) count++;
     
    if(count<2)
     {document.getElementById("re").innerHTML="修改失败";
     return false;}
    if(count>=2)
    {
       if(o.password.value==o.repassword.value)
         return true;
       else
         {document.getElementById("re").innerHTML="修改失败";
         return false;}
    }
   
}
</script>
</head>

<body>
	<div id="loginbox" style="width:600px">
		<form id="changeform" action="Sear_quesservlet" class="form-vertical" onsubmit="javascript:return chkfrm1(this);">
			
			<table style="width:600px; height:300px">
			<tr>
			<td><p class="normal_text">请输入新密码：</p></td>
			<td></td>
			</tr>
			<tr>
			 <td style="width:500px"> <div class="controls" >
				  <div class="main_input_box">
					<span class="add-on bg_ly"><i class="icon-lock"></i></span><input
					name="password"	type="password" placeholder="请输入新密码" onblur="chkchina(this);"/>
				  </div>
			       </div>
			</td>
			<td  style="color:red;width:200px;font-size:12" > <span id="new"></span> </td>
			</tr>
			<tr>
			 <td><div class="controls">
				<div class="main_input_box">
					<span class="add-on bg_ly"><i class="icon-lock"></i></span><input
					name="repassword"	type="password" placeholder="请再次输入新密码" onblur="chkchina1(this);"/>
				</div>
			</div></td>
			<td  style="color:red;width:100px;font-size:12"><span  id="renew"></span></td>
			</tr>
			<tr>
			<td>
			<p style="color:red;height:17px; text-align:center;font-size:12px " id="re"></p>
			<div class="form-actions">
				<span class="pull-left"><button
						onclick="window.history.back();" class="flip-link btn btn-success"
						id="reto-recover">&laquo; 上一步</button></span> <span class="pull-right"><input
				type="submit"	class="btn btn-info" value="确定"></span>
			</div></td>
			<td></td>
			</tr>
			</table>
			
			</br>
			
			
		<input type="hidden" name="form" value="modify">
				<input type="hidden" name="Username" value="${Username}">
		</form>
	</div>

	
</body>
</html>