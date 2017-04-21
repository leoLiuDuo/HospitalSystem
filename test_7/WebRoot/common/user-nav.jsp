<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script>
var st=false;
var ch=false;
function showHint(str)
{
  var xmlhttp;
  if (str.length==0)
  { 
    document.getElementById("txtHint").innerHTML="";
    return;
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
      document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
      if(xmlhttp.responseText==0)
       {document.getElementById("txtHint").innerHTML=" 密码错误";st=false;}
       else
       {document.getElementById("txtHint").innerHTML="";st=true;}
    }
  }
  xmlhttp.open("GET","/test_7/user_valid_password?old_password="+str,true);
  xmlhttp.send();
}
 
	var ch=false;
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
	
	
	
	function chkname(o){
   st=/^[\u4e00-\u9fa5]{2,4}$/gi.test(o.value);
   if(!st)
   {
       
       document.getElementById("re").innerHTML="用户名应为2-4个汉字"; 
      
       return false;
   }
   else
  {   
     o.nextSibling.style.display=(st?'none':'inline');
     
      return true;
  }
}
	
 
function chkfrm1(f){
   var count=0;
    if(chkname(f.real)) count++;
  if(count<1)
  { 
   return false;
  
  }
  else
  {
   
   return true;
   }
}


function chkform(o)
{ if(chkchina(o.new_password)&&st)
  {
    return true;
  }
  else
  {
    document.getElementById("result").innerHTML="修改失败";return false;
  }
  
} 
</script>
<div id="user-nav" class="navbar navbar-inverse">
	<ul class="nav">
		<li class=""><a title="" href="#" onClick="Ceng(${user_person.city})"
			data-toggle="modal" data-target="#userInfo"><i
				class="icon icon-user"></i> <span class="text">我的信息</span></a></li>
		
		
		<li class=""><a title="" href="<%=basePath %>Loginout/Loginoutservlet"><i
				class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
	</ul>
</div>

<!--弹出个人信息-->
<div class="modal fade " id="userInfo" tabindex="-1" role="dialog"
	style="display:none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">我的信息</h4>
			</div>
		 
		<form action="<%=basePath%>user_servlet.do" method="get"  onsubmit="javascript:return chkfrm1(this);">
		<input type="hidden" name="oper" value="mod"  />
		<input type="hidden" name="form" value="info"  />
		<input type="hidden" name="city_id" value="${user_person.city }"  />
			<div class="modal-body">
				
					<table style="margin-left:auto; margin-right:auto;">
							<tr>
							<td>用户名</td>
							<td><input  
name="user_id"
type="text" value="${user_person.user_id}" readonly="readonly"/></td>
						</tr>
						<tr>
							<td>姓名</td>
							<td  style="width:350px"><input name="realname" type="text" 

value="${user_person.name}"  onblur="javascript:chkname(this);" /><span id="re" style="color:red"></span></td>
						</tr>
						<tr>
							<td>性别</td>
							<td><input id="sex" type="text" value="" readonly="true" /></td>
							</tr>
							<%--<td><input name="city_id1" type="text" readonly="readonly" id="city_id2"

/> --%>  
							<script>
							var v=document.getElementById("sex");
							if(${user_person.city }=="0"){
							v.value="女";
							
							}
							else{
							v.value="男";
							}
							
							</script>
								</td>
						</tr>
						<tr>
							<td>职位</td>
							<td><input type="text" value="${user_person.role }" readonly="true" /></td>
						<%--	<td><input name="role" type="text" readonly="readonly" value="${user_person.role }"></td>
						 --%></tr>
						
						<tr>
						
								<td>密保问题一</td>
								<td><input  type="text"  

value="${user_person.question1} " readonly="readonly"/></td>
							</tr>
							
							<tr>
								<td readonly="readonly">密

保问题二</td>
								<td><input  type="text"  

value="${user_person.question2} " readonly="readonly"/></td>
							</tr>
							
							<tr><td>密保问题三</td>
								<td><input  type="text"  

value="${user_person.question3} " readonly="readonly"/></td>
							</tr>
							<tr>
						<td>修改密码</td>
						<td><a href="" data-dismiss="modal" data-toggle="modal" data-target="#mod_pass" >修改密码</a></td>
						</tr>
						</table>
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
				</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


<!-- 修改密码 -->
<div class="modal fade " id="mod_pass" tabindex="-1" role="dialog"
	style="display:none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"> 修改密码</h4>
			</div>

			<form action="/test_7/person_information_servlet" method="get" onsubmit="return chkform(this)">
			<div class="modal-body" style="width:400px">
			输入原密码：<input type="text" onblur="showHint(this.value);" name="old_password"><span style="color:red" id='txtHint'></span>
			<br>
			<input type="hidden" value="mod_pass" name="oper">
			输入新密码：<input type="text" name="new_password"  onblur="chkchina(this);"/>   <span style="color:red;width:100px" id="new"></span>
			
			</div>
			<div style="color:red;text-align:center"><span id="result" ></span></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
				</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>