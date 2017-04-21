<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>项目类型</title>
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
		function div_page(currentPage,totalPage){ 
	 
	       var tempStr;//底部的页码
		    var tempStrSimple;//顶部页码
				if(currentPage==1){
		    		tempStr="<li><a>&laquo;</a></li>";
		    		tempStrSimple="<li><a>&laquo;</a></li>";
		   		     }
		   		else{
		    		tempStr = "<li><a href=\"\\test_7\\Pro_typeServlet.do?pro_type=${pro_type}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\Pro_typeServlet.do?pro_type=${pro_type}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		   		
		   	 
		    	 for(var i=1;i<=totalPage;i++){
		    	 
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\Pro_typeServlet.do?pro_type=${pro_type}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    	 
		    		
		   		}
		   
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\Pro_typeServlet.do?pro_type=${pro_type}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\Pro_typeServlet.do?pro_type=${pro_type}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
			 
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
 

	   
}  

	
	function test(pro_type2,pro_type_id2){
	 
		 
			   document.getElementById("pro_type2").value=pro_type2;
			   document.getElementById("pro_type_id2").value=pro_type_id2;
	      
	}
 
 
function test1(pro_type_id1){
  
		 
	 
		    document.getElementById("pro_type_id1").value=pro_type_id1;
	}
	

function chkn(o){
 
  var str=o.value.replace(/^\s$/,'');
   
   if(str=='')
   {
      if(o.id=="pro_type1")
      document.getElementById("pro").innerHTML="请输入项目类型名称";
     else
      document.getElementById("pro1").innerHTML="请输入项目类型名称";
       return false;
   }
   else
  {  
    document.getElementById("pro").innerHTML=''; 
    document.getElementById("pro1").innerHTML=''; 
     return true;
  }
}


function chkchkform(f)
{
   
  return chkn(f.pro_type);
}

function chkchkformxiugai(f)
{
  return chkn(f.pro_type);
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
					class="icon-home"></i> Home</a> <a>项目类型</a>
			</div>
			 
		 
		
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							<div class="widget-box" >
								<div class="widget-content nopadding" >
								<form action="<%=basePath %>Pro_typeServlet.do" method="get">
									<div class="row-fluid search_single" >
										<div class="control-group search_single_input" >
											<div class="controls" >
												<input type="text" name="pro_type"  placeholder="项目类型" />
												<input type="hidden" name="oper"  value="search"/>
												<input type="hidden" name="currentPage"  value="1"/>
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
										</form>
								</div>
							</div>
						</div>
					</div>
				</div>
 
				<div class="table-responsive">
				<table  class="table table-bordered table-striped">									
									  <tr >
									        <th colspan="10" bgcolor="#E0E0E0" style="text-align: left; background:#CDCDCD">
									        <span class="icon" >
									         <i class="icon-th" ></i>
									         </span>
									          <h6  class="table_title_first_name ">项目类型信息表</h6>
<div  class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>
 									          
									          </th>									        
							      </tr>
				 
							<tr>
								<th>项目类型名称</th>
								<th>操作</th>
							</tr>
					 
						<tbody>
						 <tr>
					 
							<c:forEach items="${pro_type_list}" var="list">
							<tr>
					   
								<td align="center">${list.pro_type}</td>
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test('${list.pro_type}','${list.pro_type_id}');">修改</a>/<a
									href="#" data-toggle="modal" data-target="#delete"  onclick="test1('${list.pro_type_id}');">删除</a></td>
							</tr>
							</c:forEach>
							</tr>
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

	<%@include file="/common/Footer.jsp"%>
	<!--弹出-->
	<div class="modal fade " id="add" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">项目类型管理</h4>
				</div>
				<form action="<%=basePath %>Pro_typeServlet.do" method="get"   onsubmit="javascript:return chkchkform(this);">
			   
				<div class="modal-body">
					<table style=" margin-top:20px;width:410px">
							<tr>
								<td style="width:75px">项目类型名称</td>
								<td><input name="pro_type"  id="pro_type1"  type="text"  onblur="javascript:chkn(this);" /></td><td><span id="pro" style="color:red"></span></td>
								
							</tr>
						</table>
						<input name="oper" value="add" type="hidden" />
           		</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<input type="submit" value="保存" class="btn btn-primary"/>
        		</div>
					</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
		<div class="modal fade " id="xiugai" tabindex="-1" role="dialog"
		style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">项目类型管理</h4>
				</div>
				<form action="<%=basePath %>Pro_typeServlet.do" method="get"   onsubmit="javascript:return chkchkformxiugai(this);">
			  
				<div class="modal-body">
					<table style=" margin-top:20px;width:410px">
							<tr>
								<td  style="width:75px">项目类型名称</td>
								<td><input name="pro_type" id="pro_type2" type="text"  onblur="javascript:chkn(this);" /></td><td><span id="pro1" style="color:red"></span></td>
								
							</tr>
						</table>
						<input name="oper" value="mod" type="hidden" />
						  <input name="pro_type_id" id="pro_type_id2" type="hidden" />
           		</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<input type="submit" value="保存" class="btn btn-primary"/>
        		</div>
					</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	  <div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
    <form  name="delete" id="delete" action="<%=basePath %>Pro_typeServlet.do"  method="get" >
      <div class="modal-content">
        
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除该项目类型信息？</p>
          </div>
             <input type="hidden" name="oper" value="del"  />
 			  <input  type="hidden" name="pro_type_id1" id="pro_type_id1" />      
			    			   	<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
		            <input type="submit" value="保存"  class="btn btn-primary"   />		
		
				 
			      	</div>
      </div>
      </form>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
  </div>
 
</body>
</html>