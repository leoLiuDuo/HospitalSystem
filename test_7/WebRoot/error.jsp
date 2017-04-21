<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>错误提示</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="/test_7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/test_7/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="/test_7/css/matrix-style.css" />
<link rel="stylesheet" href="/test_7/css/matrix-media.css" />
<link href="/test_7/font-awesome/css/font-awesome.css" rel="stylesheet" />

</head>
<body>

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Sample pages</a> <a href="#" class="current">Error</a> </div>
    <h1>Error 404</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-info-sign"></i> </span>
            <h5>非法访问页面</h5>
          </div>
          <div class="widget-content">
            <div class="error_ex">
              <h1>非法访问页面</h1>
              <h3> You're lost.</h3>
              <p>We can not find the page you're looking for.</p>
              <a class="btn btn-warning btn-big"  href="/test_7/Loginout/Loginoutservlet">返回登录</a> </div>
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