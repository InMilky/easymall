<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/top.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="common_head">
	<div id="line1">
		<div id="content">

		 <c:if test="${ !(empty sessionScope.admin) }">
		 	 	"欢迎${ sessionScope.admin.username }登录&nbsp;"&nbsp;&nbsp;
		 	<a href="${ pageContext.request.contextPath }/index/adminlogout">退出</a>		 
		 </c:if>		
		</div>	
	</div>
	<div id="line2">
		<img id="logo" src="${ pageContext.request.contextPath }/img/head/logo.jpg"/>
		<span>EasyMall后台主页</span>
	</div>
	<div id="line3">
		<div id="navigator">
		<ul> <!--  target="center"  如果这个指定名称或 id 的框架或者窗口不存在，浏览器将打开一个新的窗口。adminProducts adminCategorys adminOrders-->
			<li><a href="${ pageContext.request.contextPath }/adminProducts/saleLists">销售榜单</a></li>
			<li><a href="${ pageContext.request.contextPath }/admin/prodLists">商品管理</a></li>
			<li><a href="${ pageContext.request.contextPath }/adminCategory/categoryLists">类型管理</a></li>
			<li><a href="${ pageContext.request.contextPath }/admin/showorder">订单管理</a></li>
			<li><a href="${ pageContext.request.contextPath }/index.jsp">返回前台</a></li>
		</ul>
				
	</div>
	</div>
</div>