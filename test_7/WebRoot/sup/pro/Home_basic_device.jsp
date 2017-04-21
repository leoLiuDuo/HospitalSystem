<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="pro.javabean.Measure"%>
<%@page import="pro.javabean.Productor"%>
<%@page import="pro.javabean.Device_info"%>
<%@page import="pro.javabean.Dev_type"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>设备信息</title>
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
<body onload="div_page('${currentPage}','${totalPage}')">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	<script type="text/javascript">
		// This function is called from the pop-up menus to transfer to
		// a different page. Ignore if the value returned is a null string:
	function div_page(currentPage,totalPage,pagename){ 
		//显示分页栏部分
		<%System.out.println("test");%>
		    var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码	
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		}
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\pro_sup_basic_device.do?prod_name=${prod_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\pro_sup_basic_device.do?prod_name=${prod_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\pro_sup_basic_device.do?prod_name=${prod_name}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\pro_sup_basic_device.do?prod_name=${prod_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\pro_sup_basic_device.do?prod_name=${prod_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  


function test(dev_mod){	//修改的浮框里面显示的东西
	var dev_modtest;
	var dev_mod;
	console.log("test");
	<%	 ArrayList<pro.javabean.Device_info> arrayList=(ArrayList<pro.javabean.Device_info>)request.getAttribute("Device_info_list"); //获取这一页表格中的所有数据，JAVABEAN中
         System.out.print("==========");
		 int size=arrayList.size();
		 for(int i=0;i<size;i++){
		 pro.javabean.Device_info device_info=new pro.javabean.Device_info();
		 device_info=arrayList.get(i);
		 request.setAttribute("device_info", device_info);
		%>
		 dev_modtest="<%out.print(device_info.getDev_mod());%>";     //输出javabean里面的id
		 <%System.out.print(device_info.getDev_mod());%>
		 
		 if(dev_mod==dev_modtest){                                   //如果jsp页面传入的主键等于javabean里面的主键
	
		 document.getElementById("dev_name1").value="${device_info.dev_name}";
		
		 dev_mod=document.getElementById("dev_mod1");
		 dev_mod.value="${device_info.dev_mod}";
		
		 document.getElementById("money1").value="${device_info.money}";
		 
		 
		 		   
		 meas_id=document.getElementById("meas_id1");
		 meas_id.value=${device_info.measure.meas_id };	
		
		 dev_type_id=document.getElementById("dev_type_id222");
		 dev_type_id.value="${device_info.dev_type.dev_type_id}";			
		
		 prod_id=document.getElementById("prod_id222");
		 prod_id.value=${device_info.productor.prod_id };
		
		 }
		 <%
		 }
		%>
}

function test1(dev_mod){//删除
	
	document.getElementById("device_mod").value=dev_mod;
	//document.getElementById("delid").innerHTML=pro_name;
}



function chkn(o){
 
  var str=o.value.replace(/^\s$/,'');
   
   if(str=='')
   {
      if(o.id=="dev_name")
      document.getElementById("dev_n").innerHTML="请输入设备名称";
     else
      document.getElementById("dev_n1").innerHTML="请输入设备名称";
       return false;
   }
   else
  {  
    document.getElementById("dev_n").innerHTML=''; 
    document.getElementById("dev_n1").innerHTML=''; 
     return true;
  }
}
function chkx(o)
{
 var d=/[1-9A-Za-z]/gi.test(o.value);
 if(!d)
 {  document.getElementById("dev_m").innerHTML="型号应为字母数字组合";
      return false;
 }
else
{
      document.getElementById("dev_m").innerHTML='';
    
      return true;
}
 
}
function chkmoney(o)
{
   var s=/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,3})))$/gi.test(o.value);
   if(!s)
   {
     if(o.id=="money")
     document.getElementById("mo").innerHTML="请输入正确的金额";
     else
     document.getElementById("mo1").innerHTML="请输入正确的金额";
     return false;
   }
   
   else
   {
     document.getElementById("mo").innerHTML='';
    document.getElementById("mo1").innerHTML='';
    return true;
   }
   
}
 		function chkformxiugai(f)
				{
				  var count=0;
				  if(chkn(f.dev_name1)) count++;
			       if(chkmoney(f.money1)) count++;
			       if(count>=2)
			       return true;
			       else 
                   return false;
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
					class="icon-home"></i> Home</a> <a>设备信息</a>
			</div>
			
			<form action="<%=basePath %>pro_sup_basic_device.do" method="get">
			
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							<div class="widget-box" >
								<div class="widget-content nopadding" >
									<div class="row-fluid " search_single">
									   <div class="control-group search_single_input">								 
                                        <div class="controls" >
												<input type="text" name="prod_name" id="pwd" placeholder="生产厂家" />
												<input type="hidden" name="currentPage" value="1"  />
												<input type="hidden" name="oper" value="search"  />
											</div>
									   </div>
										<div class="control-group btn_double"  >
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
									        <th colspan="20" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">设备信息表</h6>
						<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>				          
									          </th>									        
							      </tr>

							<tr>
								<th>设备名称</th>
								<th>设备型号</th>
								<th>生产厂家</th>
								
								<th>设备类型</th>
								<th>计量单位</th>
								<th>金额</th>
								<th>操作</th>
							</tr>
						<tbody>
                         <tr>
								<c:forEach items="${Device_info_list}" var="list">
							<tr>
								<td>${list.dev_name }</td>
								<td>${list.dev_mod }</td>								
								<td>${list.productor.prod_name}</td>  <!-- 前面是主键表中的属性，后面是外键表中的属性 -->
								
								<td>${list.dev_type.dev_type }</td>
								<td>${list.measure.meas_name }</td>
								<td>${list.money }</td>
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test('${list.dev_mod}');">修改</a>/<a
									href="#" data-toggle="modal" data-target="#delete" onclick="test1('${list.dev_mod}')">删除</a></td>
								<!-- script的注释也不要随便加jsp代码，也会出错的，像之前这里插了一段list.city的，虽然注释了但是也报错 -->
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


	<!-- 弹出添加 -->
	<div class="modal fade " id="add" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">设备信息</h4>
				</div>
				<script>
				function chkform(f)
				{
				 var count=0;
				  if(chkn(f.dev_name)){ count++;  }
				  
				  if(chkx(f.dev_mod)){ count++;  }
				
                 if(chkmoney(f.money)){ count++;  }
               
                if(count>=3)
                return true;
                else 
                 return false;
				}
				
				
				
				</script>
				<form action="<%=basePath %>pro_sup_basic_device.do" method="get"  onsubmit="javascript:return chkform(this);">
				<input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="add"  />
				  <div class="modal-body">
						<table style=" margin-top:20px;width:400px">
							<tr>
								<td style="width:64px">设备名称</td>
								<td><input name="dev_name" id="dev_name" type="text" onblur="javascript:chkn(this);" /></td><td><span id="dev_n" style="color:red"></span></td>
							</tr>
							<tr>
								<td>设备型号</td>
								<td><input placeholder="请输入字母数字组合"  name="dev_mod" type="text" onblur="javascript:chkx(this);" /></td><td><span id="dev_m" style="color:red"></span></td>
							</tr>
							
							
							<tr>
								<td>生产厂家</td>
								<td>
								<select name="prod_id" >
								<c:forEach items="${productor_list}" var="list">
                        			  <option value="${list['prod_id'] }">${list["prod_name"]}</option>
       						    </c:forEach>
								</select>
								</td>
							</tr>
							
							
							<tr>
								<td>设备类型</td>
								<td><select name="dev_type_id">
								<c:forEach items="${dev_type_list }" var="list">
                        			  <option value="${list['dev_type_id'] }">${list["dev_type"]}</option>
       						    </c:forEach>
								</select></td>
							</tr>
							
							<tr>
								<td>计量单位</td>
								<td><select name="meas_id">
								<c:forEach items="${measure_list }" var="list">
                        			  <option value="${list['meas_id'] }">${list["meas_name"]}</option>
       						    </c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>金额</td>
								<td><input  placeholder="单位 :元,最多3位小数"  name="money" id="money"  type="text" onblur="javascript:chkmoney(this);" /></td><td><span id="mo" style="color:red; "></span></td>
							</tr>
						</table>
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<input type="submit" value="提交" class="btn btn-primary" >	<!-- 提交后不消失的话，可以传到给servlet，提交后消失的话就无法提交 -->				
					
				</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
			
		<!-- /.modal -->
		

	<%@include file="/common/Footer.jsp"%>
	<!-- 弹出修改 -->
	<div class="modal fade " id="xiugai" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">设备信息</h4>
				</div>
	
				<form action="<%=basePath %>pro_sup_basic_device.do" method="get"  onsubmit="javascript:return chkformxiugai(this);">
				  <input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="mod"  />
				  <div class="modal-body">
						<table style=" margin-top:20px;width:400px">
							<tr>
								<td style="width:64px">设备名称</td>
								<td><input name="dev_name1" type="text" id="dev_name1"  onblur="javascript:chkn(this);" /></td><td><span id="dev_n1" style="color:red"></span></td>
							</tr>
							<tr>
								<td>设备型号</td>
								<td><input name="dev_mod1" id="dev_mod1" type="text" readonly="readonly" /> 
							</tr>
							
							
							
							<tr>
								<td>生产厂家</td>
								<td>
								<select name="prod_id1" id="prod_id222" >
								<c:forEach items="${productor_list }" var="list">
                        			  <option value="${list['prod_id'] }">${list["prod_name"]}</option>
       						    </c:forEach>
								</select>
								</td>
							</tr>
							
							
							<tr>
								<td>设备类型</td>
								<td><select name="dev_type_id1"  id="dev_type_id222">
								<c:forEach items="${dev_type_list }" var="list">
                        			  <option value="${list['dev_type_id'] }">${list["dev_type"]}</option>
       						    </c:forEach>
								</select></td>
							</tr>
							
							<tr>
								<td>计量单位</td>
						        <td><select name="meas_id1" id="meas_id1">
								<c:forEach items="${measure_list }" var="list">
                        			  <option value="${list['meas_id'] }">${list["meas_name"]}</option>
       						    </c:forEach>
								</select>
								</td>
							</tr>
							
							<tr>
								<td>金额</td>
								<td><input placeholder="单位 :元,最多3位小数"  name="money1" id="money1" type="text" onblur="javascript:chkmoney(this);" /></td><td><span id="mo1" style="color:red"></span></td>
							</tr>
							
						</table>
						</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<input type="submit" value="修改" class="btn btn-primary" >	<!-- 提交后不消失的话，可以传到给servlet，提交后消失的话就无法提交 -->				
					
				</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
			

<!-- /.modal -->

<!-- 弹出删除 -->
<div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
      <div class="modal-content">
        
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除以下设备信息？</p>
         <br/>
         <p align="center" style="font-family:'微雅软黑'; font-size:20px;" id="delid"></p>
        </div>
        <div class="modal-footer">
<!-- 原来的要配合后台修改
			<button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
          <button type="button" class="btn btn-primary" data-dismiss="modal"> 确认 </button>
 -->          
        <form action="<%=basePath %>pro_sup_basic_device.do" method="get" >
		 <input type="hidden" name="currentPage" value="1"  />
		 <input type="hidden" name="oper" value="delect"  />
		 <input type="hidden" name="device_mod" id="device_mod">
		 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		 <input type="submit" value="提交" class="btn btn-primary" >	<!-- 提交后不消失的话，可以传到给servlet，提交后消失的话就无法提交 -->			
						
		</form>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
<!-- /.modal -->
</div>
	


</body>
</html>