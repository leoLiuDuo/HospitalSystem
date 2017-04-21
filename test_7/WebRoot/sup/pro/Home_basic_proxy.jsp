<%@ page import="pro.javabean.Proxy_unit"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>代维单位</title>
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
	// This function is called from the pop-up menus to transfer to
	// a different page. Ignore if the value returned is a null string:

		function div_page(currentPage,totalPage,pagename)
		{ 
		//显示分页栏部分
		    var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		}
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\pro_sup_proxyunit_servlet.do?DaiWei_unit=${DaiWei_unit}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\pro_sup_proxyunit_servlet.do?DaiWei_unit=${DaiWei_unit}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\pro_sup_proxyunit_servlet.do?DaiWei_unit=${DaiWei_unit}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\pro_sup_proxyunit_servlet.do?DaiWei_unit=${DaiWei_unit}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\pro_sup_proxyunit_servlet.do?DaiWei_unit=${DaiWei_unit}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  
function test(daiWei_id){
	var daiWei_idtest;
	<%	 ArrayList<pro.javabean.Proxy_unit> arrayList=(ArrayList<pro.javabean.Proxy_unit>)request.getAttribute("proxylist"); 
		int size=arrayList.size();
		 for(int i=0;i<size;i++){
		 pro.javabean.Proxy_unit proxy=new pro.javabean.Proxy_unit();
		 proxy=arrayList.get(i);
		request.setAttribute("proxy", proxy);
		 %>
		 daiWei_idtest="<%out.print(proxy.getDaiwei_id());%>";
		 if(daiWei_id==daiWei_idtest){
		 
		 document.getElementById("daiWei_id1").value="${proxy.daiwei_id}";
		
		 daiWei_unit=document.getElementById("daiWei_unit1");
		 daiWei_unit.value="${proxy.daiwei_unit}";

		 postcode=document.getElementById("postcode1");
		 postcode.value="${proxy.postcode}";
		 
		 unit_res=document.getElementById("unit_res1");
		 unit_res.value="${proxy.unit_res}";
		 
		 tel_day=document.getElementById("tel_day1");
		 tel_day.value="${proxy.tel_day}";
		 
		 tel_night=document.getElementById("tel_night1");
		 tel_night.value="${proxy.tel_night}";
		 
		 mol_tel=document.getElementById("mol_tel1");
		 mol_tel.value="${proxy.mol_tel}";
		 
		 email=document.getElementById("email1");
		 email.value="${proxy.email}";
		 
		 address=document.getElementById("address1");
		 address.value="${proxy.address}";
		 
		 dai_controller=document.getElementById("dai_controller1");
		 dai_controller.value="${proxy.dai_controller}";	 
		 }
		 <%
		 }
		%>
}
function test1(daiWei_id){
	
	document.getElementById("daiWei_id").value=daiWei_id;
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
					class="icon-home"></i> Home</a> <a>代维单位</a>
			</div>
			<form action="<%=basePath %>pro_sup_proxyunit_servlet.do" method="get">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							<div class="widget-box" >
								<div class="widget-content nopadding" >
									<div class="row-fluid search_single" >
										<div class="control-group search_single_input" >
											<div class="controls" >
												<input type="text" name="DaiWei_unit" id="pwd" placeholder="代维单位" />
													<input type="hidden" name="currentPage" value="1"  />
												<input type="hidden" name="oper" value="search"  />
											</div>
										</div>
										<div class="control-group btn_double" >
											<div class="controls" >
												<button type="submit" class="btn btn-primary"
													data-toggle="modal" data-target="#add">添加</button>
												<button type="submit" class="btn btn-primary">查询</button>
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
									        <th colspan="10" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">代维单位信息表</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>										          
									          </th>									        
							      </tr>

							<tr>
							    <th style="display:none">代维ID</th>
								<th align="center">代维单位名称</th>								 
								<th align="center">代维单位负责人</th>
								<th align="center">白天固定电话</th>
								<th align="center">夜间固定电话</th>
								<th align="center">移动电话</th>
								<th align="center">代维单位地址</th>
								<th align="center">email地址</th>
								<th align="center">邮政编码</th>
								<th align="center">代维主管单位</th>
								<th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${proxylist}" var="list">
							<tr>
                                <td style="display:none">${list.daiwei_id }</td>
								<td>${list.daiwei_unit }</td>
								<td>${list.unit_res }</td>
								<td>${list.tel_day }</td>
							    <td>${list.tel_night }</td>
								<td>${list.mol_tel }</td>
								<td>${list.address }</td>
								<td>${list.email }</td>
								<td>${list.postcode }</td>
								<td>${list.dai_controller }</td>																	
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test(${list.daiwei_id });">修改</a>/<a
									href="#" data-toggle="modal" data-target="#delete" onclick="test1(${list.daiwei_id})">删除</a></td>
								<!-- script的注释也不要随便加jsp代码，也会出错的，像之前这里插了一段list.city的，虽然注释了但是也报错 -->
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
			</form>
		</div>

	</div>

	<%@include file="/common/Footer.jsp"%>
	<!--弹出添加-->
	<div class="modal fade " id="add" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">代维信息</h4>
				</div>
<script type="text/javascript">

function chkn(o){
 
var str=o.value.replace(/^\s$/,'');
   
   if(str=='')
   {
      if(o.name=="daiWei_unit")
      document.getElementById("dai_n").innerHTML="输入不能为空";
     else
      document.getElementById("dai_n1").innerHTML="输入不能为空";
       return false;
   }
   else
  {  
    document.getElementById("dai_n").innerHTML=''; 
    document.getElementById("dai_n1").innerHTML=''; 
     return true;
  }
}


function chkfuze(o){
 
  var st=/^[\u4e00-\u9fa5A-Za-z]+$/gi.test(o.value);
    
   if(!st)
   {
      if(o.name=="unit_res")
      document.getElementById("fuze").innerHTML="输入应为字母或汉字";
      else 
      document.getElementById("fuze1").innerHTML="输入应为字母或汉字";
       return false;
   }
   else
  {   
   
    document.getElementById("fuze").innerHTML=''; 
    document.getElementById("fuze1").innerHTML=''; 
     return true;
  }
}



function chktel1(o)
{
 var st=/^0\d{2,3}-?\d{7,8}$/gi.test(o.value);
   
 if(!st)
 { 
   if(o.name=="tel_day")
  document.getElementById("tel1").innerHTML="请输入正确的固定电话号码"; 
  else  
   document.getElementById("tel11").innerHTML="请输入正确的固定电话号码"; 
  return false;
 }
 else
 {
   document.getElementById("tel11").innerHTML='';
   document.getElementById("tel1").innerHTML=''; return true;
 }
}


function chktel2(o)
{
 var st=/^0\d{2,3}-?\d{7,8}$/gi.test(o.value);
   
 if(!st)
 {
  if(o.name=="tel_night") 
  document.getElementById("tel2").innerHTML="请输入正确的固定电话号码"; 
  else 
   document.getElementById("tel21").innerHTML="请输入正确的固定电话号码"; return false;
  
 }
 else
 {document.getElementById("tel2").innerHTML='';
 document.getElementById("tel21").innerHTML='';
  return true;
 }
}



function chktel3(o)
{
 var st= /^[1-9][0-9]{10}$/gi.test(o.value);
   
 if(!st)
 {  
   if(o.name=="mol_tel")
   document.getElementById("tel3").innerHTML="请输入正确的手机号码";
   else    document.getElementById("tel31").innerHTML="请输入正确的手机号码";
    return false;
 }
 else
 {document.getElementById("tel3").innerHTML=''; 
 document.getElementById("tel31").innerHTML='';
 return true;
 }
}




function chkaddr(o)
  {
    var str=o.value.replace(/^\s$/,'');
      
    if(str=='')
    {
      if(o.name=="address")
      document.getElementById("addr").innerHTML="输入不能为空";
    else   document.getElementById("addr1").innerHTML="输入不能为空";
       return false;
     }
    else
    {
     document.getElementById("addr").innerHTML='';
          document.getElementById("addr1").innerHTML='';
     return true;
    }   
  }


 function chkemail(o)
 {
  
    var e=/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/gi.test(o.value);
     
    if(!e)
    {
      if(o.name=="email")
      document.getElementById("e").innerHTML="请输入正确的邮箱地址";
      else
       document.getElementById("e1").innerHTML="请输入正确的邮箱地址";
       return false;
    }
    else
    {
      document.getElementById("e").innerHTML='';
      document.getElementById("e1").innerHTML='';
     return true;
    }
 }
 
 
  function chkyou(o)
 { var y=/^[1-9][0-9]{5}$/gi.test(o.value);
  
   if(!y)
   {
     if(o.name=="postcode")
     document.getElementById("y").innerHTML="请输入6位的邮政编码";
     else
        document.getElementById("y1").innerHTML="请输入正确的邮政编码";
     return false;
     
   }
   else
   {document.getElementById("y").innerHTML='';
   document.getElementById("y1").innerHTML='';
   return true;
   }
 }
 
 function chkmain(o)
  {
    var str=o.value.replace(/^\s$/,'');
    
    if(str=='')
    {
    if(o.name=="dai_controller")
      document.getElementById("m").innerHTML="输入不能为空";
    else
     document.getElementById("m1").innerHTML="输入不能为空";
        return false;
    }
    else
    {
    document.getElementById("m").innerHTML='';
    document.getElementById("m1").innerHTML='';
     return true;
    }   
  }
  
 function chkform(f)
 { var count=0;
   if(chkn(f.daiWei_unit)) count++;
   if(chkfuze(f.unit_res)) count++;
   if(chktel1(f.tel_day)) count++;
   if(chktel2(f.tel_night))count++;
   if(chktel3(f.mol_tel))count++;
   if(chkaddr(f.address))count++;
   if(chkemail(f.email))count++;
   if(chkyou(f.postcode))count++;
   if(chkmain(f.dai_controller))count++;
    if(count>=9)
    return true;
    else return false;
 }
 
 
 
function chkf_xiugai(f)
{
   var count=0;
   if(chkn(f.daiWei_unit1)) count++;
   if(chkfuze(f.unit_res1)) count++;
   if(chktel1(f.tel_day1)) count++;
   if(chktel2(f.tel_night1))count++;
   if(chktel3(f.mol_tel1))count++;
   if(chkaddr(f.address1))count++;
   if(chkemail(f.email1))count++;
   if(chkyou(f.postcode1))count++;
   if(chkmain(f.dai_controller1))count++;
    if(count>=9)
    return true;
    else return false;
}
</script>			
				<form action="<%=basePath %>pro_sup_proxyunit_servlet.do" method="get" onsubmit="javascript:return chkform(this);">
				  <input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="add"  />
				<div class="modal-body">
				  <table id="modsimple" style=" margin-top:20px;width:400px">
				             
							<tr>
								<td style="width:64px">代维单位名称</td>
								<td style="width:200px"> <input name="daiWei_unit" id="daiWei_unit" type="text"  onblur="javascript:chkn(this);" /></td><td><span id="dai_n" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维单位负责人</td>
								<td><input name="unit_res" id="unit_res" type="text" onblur="javascript:chkfuze(this);" /></td><td><span id="fuze" style="color:red"></span></td>
							</tr>
							<tr>
								<td>白天固定电话</td>
								<td><input placeholder="如0898-11111111(包含区号)"name="tel_day" id="tel_day" type="text" onblur="javascript:chktel1(this);" /></td><td><span id="tel1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>夜间固定电话</td>
								<td><input placeholder="如0898-11111111(包含区号)"name="tel_night" id="tel_night" type="text" onblur="javascript:chktel2(this);" /></td><td><span id="tel2" style="color:red"></span></td>
							</tr>
							<tr>
								<td>移动电话</td>
								<td><input placeholder="如13155555555"name="mol_tel" id="mol_tel" type="text" onblur="javascript:chktel3(this);" /></td><td><span id="tel3" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维单位地址</td>
								<td><input name="address" id="address" type="text" onblur="javascript:chkaddr(this);" /></td><td><span id="addr" style="color:red"></span></td>
							</tr>
							<tr>
								<td>email地址</td>
								<td><input placeholder="如999999@163.com"name="email" id="email" type="text" onblur="javascript:chkemail(this);" /></td><td><span id="e" style="color:red"></span></td>
							</tr>
							<tr>
								<td>邮政编码</td>
								<td><input name="postcode" id="postcode" type="text" onblur="javascript:chkyou(this);" /></td><td><span id="y" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维主管单位</td>
								<td><input name="dai_controller" id="dai_controller" type="text" onblur="javascript:chkmain(this);" /></td><td><span id="m" style="color:red"></span></td>
							</tr>
						 
				  </table>
														
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<input type="submit" value="提交" class="btn btn-primary" >	<!-- 提交后不消失的话，可以传到给servlet，提交后消失的话就无法提交 -->				
					
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
					<h4 class="modal-title" id="myModalLabel">修改信息</h4>
				</div>				
				<form action="<%=basePath %>pro_sup_proxyunit_servlet.do" method="get"  onsubmit="javascript:return chkf_xiugai(this);">
				  <input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="revise"  />
				<div class="modal-body">
				  <table id="modsimple" style=" margin-top:20px;width:400px">
				            <tr>
								<td>代维ID</td>
								<td ><input name="daiWei_id1" id="daiWei_id1" readonly="readonly"  type="text" /></td>
							</tr>
							<tr>
								<td style="width:64px">代维单位名称</td>
								<td><input name="daiWei_unit1" id="daiWei_unit1" type="text"  onblur="javascript:chkn(this);" /></td><td><span id="dai_n1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维单位负责人</td>
								<td><input name="unit_res1" id="unit_res1" type="text" onblur="javascript:chkfuze(this);" /></td><td><span id="fuze1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>白天固定电话</td>
								<td><input placeholder="如0898-11111111(包含区号)"name="tel_day1" id="tel_day1" type="text" onblur="javascript:chktel1(this);" /></td><td><span id="tel11" style="color:red"></span></td>
							</tr>
							<tr>
								<td>夜间固定电话</td>
								<td><input placeholder="如0898-11111111(包含区号)"name="tel_night1" id="tel_night1" type="text" onblur="javascript:chktel2(this);" /></td><td><span id="tel21" style="color:red"></span></td>
							</tr>
							<tr>			
								<td>移动电话</td>
								<td><input placeholder="如13155555555"name="mol_tel1" id="mol_tel1" type="text" onblur="javascript:chktel3(this);" /></td><td><span id="tel31" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维单位地址</td>
								<td><input name="address1" id="address1" type="text" onblur="javascript:chkaddr(this);" /></td><td><span id="addr1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>email地址</td>
								<td><input placeholder="如999999@163.com"name="email1" id="email1" type="text" onblur="javascript:chkemail(this);" /></td><td><span id="e1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>邮政编码</td>
								<td><input name="postcode1" id="postcode1" type="text" onblur="javascript:chkyou(this);" /></td><td><span id="y1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>代维主管单位</td>
								<td><input name="dai_controller1" id="dai_controller1" type="text" onblur="javascript:chkmain(this);" /></td><td><span id="m1" style="color:red"></span></td>
							</tr>
						 
				  </table>
														
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<input type="submit" value="保存" class="btn btn-primary" >	<!-- 提交后不消失的话，可以传到给servlet，提交后消失的话就无法提交 -->				
					
				</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
<!-- 弹出删除 -->
<div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
      <div class="modal-content">       
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除该信息？</p>
        </div>
        <div class="modal-footer">        
        <form action="<%=basePath %>pro_sup_proxyunit_servlet.do" method="get" >
		 <input type="hidden" name="currentPage" value="1"  />
		 <input type="hidden" name="oper" value="delect"  />
		 <input type="hidden" name="daiWei_id" id="daiWei_id">
		 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		 <input type="submit" value="提交" class="btn btn-primary" >								
		</form>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
<!-- /.modal -->
</div>	

</body>
</html>