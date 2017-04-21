<%@page import="pro.javabean.Pro_info"%>
<%@page import="pro.javabean.Pro_type"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<title>项目信息</title>
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

</head>
<body onload="div_page(${currentPage},${ totalPage})">
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
		    		tempStr = "<li><a href=\"\\test_7\\pro_sup_proinfo_servlet.do?dev_type=${dev_type}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		tempStrSimple = "<li><a href=\"\\test_7\\pro_sup_proinfo_servlet.do?dev_type=${dev_type}&oper=search&currentPage="+(currentPage-1)+"\""+" >&laquo;</a></li>";
		    		//这里也要改，改成相应的servlet
		   		}
		    	for(var i=1;i<=totalPage;i++){
		    		if(currentPage==i){
		    			tempStr+="<li><a>"+i+"</a></li>";
		    		}
		    		else{
		    			tempStr+="<li><a href=\"\\test_7\\pro_sup_proinfo_servlet.do?dev_type=${dev_type}&oper=search&currentPage="+i+"\">"+i+"</a></li>";
		    		}
		    		console.log(i);
		    		
		   		}
		    	if(currentPage==totalPage||totalPage==0){
		    		tempStr+="<li><a>&raquo;</a></li>";
		    		tempStrSimple+="<li><a>&raquo;</a></li>";
		    	}
		    	else{
		    		tempStr+= "<li><a href=\"\\test_7\\pro_sup_proinfo_servlet.do?dev_type=${dev_type}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    		tempStrSimple+="<li><a href=\"\\test_7\\pro_sup_proinfo_servlet.do?dev_type=${dev_type}&oper=search&currentPage="+(currentPage+1)+"\""+" >&raquo;</a></li>";
		    	}
			
			tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
		    document.getElementById("barcon").innerHTML = tempStr;
		    document.getElementById("barconSimple").innerHTML = tempStrSimple;
}  

function test(pro_id){
	var pro_name;
	var pro_idtest;
	<%	 ArrayList<pro.javabean.Pro_info> arrayList=(ArrayList<pro.javabean.Pro_info>)request.getAttribute("Proinfo_list"); 
		int size=arrayList.size();
		 for(int i=0;i<size;i++){
		 pro.javabean.Pro_info proj=new pro.javabean.Pro_info();
		 proj=arrayList.get(i);
		request.setAttribute("proj", proj);
		 %>
		 pro_idtest="<%out.print(proj.getPro_id());%>";
		 if(pro_id==pro_idtest){
		 
		 document.getElementById("pro_id1").value="${proj.pro_id}";
		
		 pro_name=document.getElementById("pro_name1");
		 pro_name.value="${proj.pro_name}";

		 
		 dev_type=document.getElementById("pro_type_id1");
		 dev_type["${proj.dev_type.pro_type_id -1}"].selected=true;
		 
		 aproval_num=document.getElementById("aproval_num1");
		 aproval_num.value="${proj.aproval_num}";
		 }
		 <%
		 }
		%>
}


function test1(pro_id,pro_name){
	
	document.getElementById("projectid").value=pro_id;
	document.getElementById("delid").innerHTML=pro_name;
}
	
  
    
    
 
function chknadd(o)
{
 
 var str=o.value.replace(/^\s$/,'');
 
  if(str=='')
  {
    document.getElementById("name2").innerHTML="输入不能为空";
    return false;
  }
  else
    {
         document.getElementById("name2").innerHTML='';return true;
  
    }
   }
   
    
function chkpiadd(o)
{
 
 var str=o.value.replace(/^\s$/,'');
 
  if(str=='')
  {
    document.getElementById("pi").innerHTML="输入不能为空";
    return false;
  }
  else
    {
         document.getElementById("pi").innerHTML='';return true;
  
    }
   }
 function chknumadd(o)
{ 
   var str=o.value.replace(/^\s$/,'');
   var s=/^[A-Za-z0-9]+$/gi.test(str);  
  if(str=='')
  {
    document.getElementById("num2").innerHTML="输入不能为空";
    return false;
  }
  else
    {
       if(!s)
       {
        document.getElementById("num2").innerHTML='编号只能是数字字母组合';return false;
       }
       else{
       document.getElementById("num2").innerHTML='';
       return true;
       }
    }   
  }
    
    


function chkn(o)
{
 
 var str=o.value.replace(/^\s$/,'');
 
  if(str=='')
  {
    document.getElementById("name").innerHTML="输入不能为空";
    return false;
  }
  else
    {
         document.getElementById("name").innerHTML='';return true;
  
    }
   }
   
    
function chkpi(o)
{
 
 var str=o.value.replace(/^\s$/,'');
 
  if(str=='')
  {
    document.getElementById("pii").innerHTML="输入不能为空";
    return false;
  }
  else
    {
         document.getElementById("pii").innerHTML='';return true;
  
    }
   }
  
    
    
   function chkfrm(f){
   var count=0;
   if(chkn(f.pro_name1)) count++;
    
   if( chkpi(f.aproval_num1)) count++;
   
   if(count<2)
   {
    return false
   }
   else 
   {
    return true;
   }
  
}


   function chkfrmadd(f){
    var count=0;
   if(chknadd(f.pro_name)) count++;
   if(chknumadd(f.pro_id)) count++;
   if( chkpiadd(f.aproval_num)) count++;
    if(count<3)
   {
      return false
   }
   else 
   {
    
   return true;
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
					class="icon-home"></i> Home</a> <a>项目信息</a>
			</div>
			<form action="<%=basePath %>pro_sup_proinfo_servlet.do" method="get">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" >
							<div class="widget-box" >
								<div class="widget-content nopadding" >
									<div class="row-fluid search_single" >
										<div class="control-group search_single_input" >
											<div class="controls" >
												<input type="text" name="dev_type" id="pwd" placeholder="项目编号" />
												<input type="hidden" name="currentPage" value="1"  />
												<input type="hidden" name="oper" value="search"  />
											</div>
										</div>
										<div class="control-group btn_double" >
											<div class="controls" >
												<button type="button" class="btn btn-primary"
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
									          <h6  class="table_title_first_name ">项目信息表</h6>
<div class="pagination" style="text-align:right; width:60x; height:10px;width:100px;float:right">
<ul id="barconSimple">
	
</ul>
</div>										          
									          </th>									        
							      </tr>
							<tr>
								<th align="center">项目名称</th>
								<th align="center">项目编号</th>
								<th align="center">项目类型</th>
								<th align="center">批复文号</th>
							 
								 
								<th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${Proinfo_list}" var="list">
							<tr>
								<td>${list.pro_name }</td>
								<td>${list.pro_id }</td>
								<td><!-- 这里不能直接list.dev_type -->
								${list.dev_type.pro_type }<%--这里EL表达式要这样写，用中括号比较安全 --%>
								</td>
								<td>${list.aproval_num }</td>
								<td><a href="#" data-toggle="modal" data-target="#xiugai" onclick="test('${list.pro_id }')" >修改</a>/<a
									href="#" data-toggle="modal" data-target="#delete" onclick="test1('${list.pro_id}','${list.pro_name }')">删除</a></td>
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
					<h4 class="modal-title" id="myModalLabel">项目信息</h4>
				</div>
				
				<form action="<%=basePath %>pro_sup_proinfo_servlet.do" method="get"  onsubmit="javascript:return chkfrmadd(this);">
				  <input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="add"  />
				<div class="modal-body">
				  <table id="modsimple" style=" margin-top:20px;width:400px" >
							<tr>
								<td style="width:64px">项目名称</td>
								<td><input name="pro_name" id="pro_name" type="text" onblur="javascript:chknadd(this)"/></td><td><span id="name2" style="color:red;"></span></td>
							</tr>
							<tr>
								<td>项目编号</td>
								<td><input placeholder="请输入字母数字组合"  name="pro_id" id="pro_id" type="text" onblur="javascript:chknumadd(this);" /></td><td><span id="num2" style="color:red"/></td>
							</tr>
							<tr>
								<td>项目类型</td>
								<td>
									<select  name="pro_type_id" id="pro_type_id" >
                        			  <c:forEach items="${type_list }" var="list">
                        			  <option value="${list['pro_type_id'] }">${list["pro_type"] }</option>
       								  </c:forEach>
                  					</select>
								</td><!-- 原来的有错（prjid） -->
							</tr>
							<tr>
								<td>批复文号</td>
								<td><input name="aproval_num" id="aproval_num" type="text"  onblur="javascript:chkpiadd(this)"/></td><td><span id="pi" style="color:red;"></span></td>
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
	<div class="modal fade " id="xiugai" tabindex="-1" role="dialog" style="display:none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改项目信息</h4>
				</div>
				<form action="<%=basePath %>pro_sup_proinfo_servlet.do" method="get"   onsubmit="javascript:return chkfrm(this);">
				  <input type="hidden" name="currentPage" value="1"  />
				  <input type="hidden" name="oper" value="mod"  />
				<div class="modal-body">
				  <table style=" margin-top:20px;width:400px">
							<tr>
								<td style="width:64px">项目名称</td>
								<td><input name="pro_name1" id="pro_name1" type="text" onblur="javascript:chkn(this)"/></td><td><span id="name" style="color:red;"></span></td>
							</tr>
							<tr>
								<td>项目编号</td>
								<td><input name="pro_id1" id="pro_id1" type="text" readonly="readonly" onblur="javascript:chknumadd(this);" /></td><td><span id="num" style="color:red"/></td>
							</tr>
							<tr>
								<td>项目类型</td>
								<td>
								<select  name="pro_type_id1" id="pro_type_id1" >
                        			  <c:forEach items="${type_list }" var="list">
                        			  <option value="${list['pro_type_id'] }">${list["pro_type"] }</option>
       								  </c:forEach>
                  				</select>
								</td><!-- 原来的有错（prjid） -->
							</tr>
							<tr>
								<td>批复文号</td>
								<td><input name="aproval_num1" id="aproval_num1" type="text" onblur="javascript:chkpi(this)"/></td><td><span id="pii" style="color:red;"></span></td>
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
<!-- 弹出删除 -->
<div class="modal fade small_modal" id="delete" tabindex="-1" role="dialog" style="display:none;" >
    <div class="modal-dialog">
      <div class="modal-content">
        
        <div class="modal-body" >
         <p align="center" style="font-family:'微雅软黑'; font-size:24px;">确认删除以下项目的信息？</p>
         <br/>
         <p align="center" style="font-family:'微雅软黑'; font-size:20px;" id="delid"></p>
        </div>
        <div class="modal-footer">
<!-- 原来的要配合后台修改
			<button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
          <button type="button" class="btn btn-primary" data-dismiss="modal"> 确认 </button>
 -->          
        <form action="<%=basePath %>pro_sup_proinfo_servlet.do" method="get" >
		 <input type="hidden" name="currentPage" value="1"  />
		 <input type="hidden" name="oper" value="delect"  />
		 <input type="hidden" name="projectid" id="projectid">
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