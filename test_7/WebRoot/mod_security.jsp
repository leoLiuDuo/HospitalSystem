<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>

<script src="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/js/lib/jquery-1.10.2_d88366fd.js"></script>
<script>
$(document).ready(function(){
    $('select').change(function(e){
        var oldvalue=$(this).attr('old');
        var currentvalue=$(this).val();
        if(oldvalue){
            $('select option[value='+oldvalue+']').show();
        }
 
        $('select option[value='+currentvalue+']').hide();
        $(this).attr('old',currentvalue);
    });
});
</script>

<html lang="en">
<head>
<title>设定密保问题</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/matrix-style.css" />
<link rel="stylesheet" href="/test_7/css/matrix-media.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />

</head>
<body>

<script>
function testInput(form1){
	var select1=document.getElementById("question1").value;
	var select2=document.getElementById("question2").value;
	var select3=document.getElementById("question3").value;
	var answer1=form1.answer1.value;
	var answer2=form1.answer2.value;
	var answer3=form1.answer3.value;
	var temp1=answer1;
	var temp2=answer2;
	var temp3=answer3;
	while(temp1.lastIndexOf(" ")>=0){
      temp1 = temp1.replace(" ","");
    }
    while(temp2.lastIndexOf(" ")>=0){
      temp2 = temp2.replace(" ","");
    }
    while(temp3.lastIndexOf(" ")>=0){
      temp3 = temp3.replace(" ","");
    }
	if((select1=="0")||(select2=="0")||(select3=="0")||(answer1=="")||(answer2=="")||(answer3=="")||(temp1.length == 0)||(temp2.length == 0)||(temp3.length == 0)){
		if(select1=="0"){
			document.getElementById("stips1").style.visibility="visible";
		}else{
			document.getElementById("stips1").style.visibility="hidden";
		}
		if(select2=="0"){
			document.getElementById("stips2").style.visibility="visible";
		}else{
			document.getElementById("stips2").style.visibility="hidden";
		}
		if(select3=="0"){
			document.getElementById("stips3").style.visibility="visible";
		}else{
			document.getElementById("stips3").style.visibility="hidden";
		}
		
		if((answer1=="")||(temp1.length == 0)){
			document.getElementById("tips1").style.visibility="visible";
		}else{
			document.getElementById("tips1").style.visibility="hidden";
		}
		if((answer2=="")||(temp2.length == 0)){
			document.getElementById("tips2").style.visibility="visible";
		}else{
			document.getElementById("tips2").style.visibility="hidden";
		}
		if((answer3=="")||(temp3.length == 0)){
			document.getElementById("tips3").style.visibility="visible";
		}else{
			document.getElementById("tips3").style.visibility="hidden";
		}
		return false;
	}else if((temp1.length == 0)||(temp2.length == 0)||(temp3.length == 0)){
		
	}else{
		return true;
	}
}

</script>

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a href="#" class="current">Error</a> </div>
    <h1>未设定密码问题</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>密保问题</h5>
          </div>
          <div class="widget-content">
            <div class="error_ex">
            	<div class="table-responsive">
            	<form action="<%=basePath%>login/User_modservlet" onsubmit="return testInput(this)">
            		<table style="margin-left:auto; margin-right:auto;">	
            	
					<tr>	
							<td>问题一：</td>
							<td> <select  name="question1" id="question1" >
						<option value="0" selected="selected" disabled="true">请选择</option>
                        <option value="1" >你的生日？</option>
                        <option value="2">你的小学班主任姓名？</option>
                        <option value="3">你的初恋叫什么？</option>
                        <option value="4">您的出生地是？</option>
                        <option value="5">您父亲的生日是？</option>
                        <option value="6">您母亲的生日是？</option>
                        <option value="7">您的小学校名是？</option>
                        <option value="8">您配偶的生日是？</option>
                    </select></td>
                    		<td>
                    		<p style="color:red;visibility:hidden;" id="stips1">请选择问题</p>
                    		</td>
						</tr>
						<tr>
							<td>答案一：</td>
							<td><input name="answer1" id="answer1" type="text" /></td>
							<td>
							<p style="color:red;visibility:hidden;" id="tips1">请填写答案</p>
							</td>
							</tr>
							<tr>	
							<td>问题二：</td>
							<td> <select  name="question2" id="question2" >
						<option value="0" selected="selected" disabled="true">请选择</option>
                        <option value="1" >你的生日？</option>
                        <option value="2">你的小学班主任姓名？</option>
                        <option value="3">你的初恋叫什么？</option>
                        <option value="4">您的出生地是？</option>
                        <option value="5">您父亲的生日是？</option>
                        <option value="6">您母亲的生日是？</option>
                        <option value="7">您的小学校名是？</option>
                        <option value="8">您配偶的生日是？</option>
                    </select></td>
                    		<td>
                    		<p style="color:red;visibility:hidden;" id="stips2">请选择问题</p>
                    		</td>
						</tr>
						<tr>
							<td>答案二：</td>
							<td><input name="answer2" id=="answer2" type="text" /></td>
							<td>
							<p style="color:red;visibility:hidden;" id="tips2">请填写答案</p>
							</td>
							</tr>
							<tr>	
							<td>问题三：</td>
							<td> <select  name="question3" id="question3" >
                        <option value="0" selected="selected" disabled="true">请选择</option>
						<option value="1" >你的生日？</option>
                        <option value="2">你的小学班主任姓名？</option>
                        <option value="3">你的初恋叫什么？</option>
                        <option value="4">您的出生地是？</option>
                        <option value="5">您父亲的生日是？</option>
                        <option value="6">您母亲的生日是？</option>
                        <option value="7">您的小学校名是？</option>
                        <option value="8">您配偶的生日是？</option>
                    </select></td>
                    		<td>
                    		<p style="color:red;visibility:hidden;" id="stips3">请选择问题</p>
                    		</td>
						</tr>
						<tr>
							<td>答案三：</td>
							<td><input name="answer3" id="answer3" type="text" /></td>
							<td>
							<p style="color:red;visibility:hidden;" id="tips3">请填写答案</p>
							</td>
							</tr>
					</table>
					<p style="text-align:center; color:red;" id="tips"></p>
					<input type="hidden" value="add"name="oper">
					  <input class="btn btn-warning btn-big"  value="确定" type="submit">
              <a class="btn btn-warning btn-big"  href="<%=session.getAttribute("pathurl") %>" >取消</a>
            		</form>
            
					</div>
					
					
					
            	
					
            
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="/test_7/js/jquery.min.js"></script> 
<script src="/test_7/js/jquery.ui.custom.js"></script> 
<script src="/test_7/js/bootstrap.min.js"></script> 
<script src="/test_7/js/maruti.html"></script>
</body>
</html>