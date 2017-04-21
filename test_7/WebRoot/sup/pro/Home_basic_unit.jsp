<%@page import="pro.javabean.Measure"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>计量单位</title>
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
<body onload="div_page(${currentPage},${totalPage})">
	<script src="/test_7/js/jquery.min.js"></script>
	<script src="/test_7/js/bootstrap.min.js"></script>
	<script src="/test_7/js/matrix.js"></script>
	<script type="text/javascript">
	// This function is called from the pop-up menus to transfer to
	// a different page. Ignore if the value returned is a null string:
	function div_page(currentPage,totalPage,pagename){ 
		//显示分页栏部分
		    var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		}
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\pro_sup_basic_measure.do?meas_name=${meas_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\pro_sup_basic_measure.do?meas_name=${meas_name}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\pro_sup_basic_measure.do?meas_name=${meas_name}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\pro_sup_basic_measure.do?meas_name=${meas_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\pro_sup_basic_measure.do?meas_name=${meas_name}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  

function test(meas_id){	
	var meas_idtest;
	var meas_id;
	
	<%	 ArrayList<pro.javabean.Measure> arrayList=(ArrayList<pro.javabean.Measure>)request.getAttribute("Measure_list"); 
       
		 int size=arrayList.size();
		 for(int i=0;i<size;i++){
		 pro.javabean.Measure measure=new pro.javabean.Measure();
		 measure=arrayList.get(i);
		 request.setAttribute("measure", measure);
		%>
		 
		 meas_idtest="<%out.print(measure.getMeas_id());%>";     //输出javabean里面的id
		
		 if(meas_id==meas_idtest){                                   //如果jsp页面传入的主键等于javabean里面的主键
		 
		 document.getElementById("meas_name1").value="${measure.meas_name}";
		 
		 meas_id=document.getElementById("meas_id1");
		 meas_id.value="${measure.meas_id}";	 
		 }
		 <%
		 }
		%>
}

function test1(meas_id){//删除
	
	document.getElementById("measure_id").value=meas_id;
	//document.getElementById("delid").innerHTML=pro_name;
}


function chkn(o){
 
  var str=o.value.replace(/^\s$/,'');
   
   if(str=='')
   {
      if(o.name=="meas_name")
      document.getElementById("mea").innerHTML="请输入项目类型名称";
     else
      document.getElementById("mea1").innerHTML="请输入项目类型名称";
       return false;
   }
   else
  {  
    document.getElementById("mea").innerHTML=''; 
    document.getElementById("mea1").innerHTML=''; 
     return true;
  }
}


function chkchkform(f)
{
   
  return chkn(f.meas_name);
}

function chkchkformxiugai(f)
{
  return chkn(f.meas_name1);
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
					class="icon-home"></i> Home</a> <a>计量单位</a>
			</div>
			<form action="<%=basePath %>pro_sup_basic_measure.do" method="get">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							<div class="widget-box" >
								<div class="widget-content nopadding" >
									<div class="row-fluid search_single" >
										<div class="control-group search_single_input" >
											<div class="controls" >
												<input type="text" name="meas_name" id="pwd" placeholder="计量单位" />
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
									          <h6  class="table_title_first_name ">计量单位信息表</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
</ul>
</div>			          
									          </th>									        
							      </tr>
	
							<tr>
								<th>计量单位名称</th>
								<th>操作</th>
							</tr>
						<tbody>
							<tr>
								<c:forEach  items="${Measure_list}" var="list">
							<tr>
								<td>${list.meas_name }</td>
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test('${list.meas_id }');">修改</a>/<a
									href="#" data-toggle="modal" data-target="#delete" onclick="test1('${list.meas_id}')">删除</a></td>
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
					<h4 class="modal-title" id="myModalLabel">计量单位管理</h4>
				</div>
				<div class="modal-body">
					<form action="<%=basePath %>pro_sup_basic_measure.do" method="get"   onsubmit="javascript:return chkchkform(this);">
						<input type="hidden" name="currentPage" value="1"  />
				        <input type="hidden" name="oper" value="add"  />
				        <div class="modal-body">
				           <table id="modsimple" style=" margin-top:20px;width:410px">
							<tr>
								<td style="width:75px">计量单位名称</td>
								<td><input name="meas_name" id="meas_name" type="text" onblur="javascript:chkn(this);" /></td><td><span id="mea" style="color:red"></span></td>
							 </tr>	
							 				 
				           </table>
				           </div>				
				         </div>
				  <div class="modal-footer">
					      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					      <input type="submit" value="提交" class="btn btn-primary" >	
				  </div>
			         </form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<%@include file="/common/Footer.jsp"%>
	<!--弹出修改-->
	<div class="modal fade " id="xiugai" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">计量单位管理</h4>
				</div>
				<div class="modal-body">
					<form action="<%=basePath %>pro_sup_basic_measure.do" method="get"   onsubmit="javascript:return chkchkformxiugai(this);">
						<input type="hidden" name="currentPage" value="1"  />
				        <input type="hidden" name="oper" value="mod"  />
				        <div class="modal-body">
				           <table id="modsimple" style=" margin-top:20px;width:410px">
							<tr>
								<td style="width:75px">计量单位名称</td>
								<td><input name="meas_name1" id="meas_name1" type="text"  onblur="javascript:chkn(this);" /></td><td><span id="mea1" style="color:red"></span></td>
							 </tr>	
							<tr style="display:none;">
								<td>计量单位id</td>
								<td><input name="meas_id1" id="meas_id1" type="text" readonly="readonly"/></td>
							 </tr>			 
				           </table>
				           </div>				
				         </div>
				  <div class="modal-footer">
					      <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					      <input type="submit" value="提交" class="btn btn-primary" >	
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
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除以下设备信息？</p>
         <br/>
         <p align="center" style="font-family:'微雅软黑'; font-size:20px;" id="delid"></p>
        </div>
        <div class="modal-footer">
<!-- 原来的要配合后台修改
			<button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
          <button type="button" class="btn btn-primary" data-dismiss="modal"> 确认 </button>
 -->          
        <form action="<%=basePath %>pro_sup_basic_measure.do" method="get" >
		 <input type="hidden" name="currentPage" value="1"  />
		 <input type="hidden" name="oper" value="delect"  />
		 <input type="hidden" name="measure_id" id="measure_id"/>
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