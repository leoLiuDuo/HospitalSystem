<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="sidebar">
	<a href="#" class="visible-phone"><i class="icon icon-home"></i>
		Dashboard</a>
	<ul>
				<li class=""><a href="show_data_servlet.do?oper=year"><i
				class="icon icon-home"></i> <span>数据展示</span></a></li>
				<li><a href="duoWeiCXServlet?method=query"><i class="icon icon-filter"></i>多维查询</a></li>
				<li><a href="forms_sunhuai.do?currentPage=1&oper=search&city="><i class="icon icon-filter"></i>统计分析</a></li>
				<li><a href="Zaixian_servlet.do?currentPage=1&oper=search&city="><i class="icon icon-filter"></i>历史查询记录</a>
</div>


