<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="common_head">
	<div id="line1">
		<div id="con">
		  <c:if test="${ empty sessionScope.user }">
		 	<a href="${ pageContext.request.contextPath }/index/login">LOGIN</a>&nbsp;	
			 <a href="${ pageContext.request.contextPath }/index/regist">REGISTER</a>&nbsp;	
			  <a href="${ pageContext.request.contextPath }/index/adminlogin">ADMINLOGIN</a>&nbsp; 	 
		 </c:if>	
		 <c:if test="${ !(empty sessionScope.user) }">
		 	 	WELCOME,${ sessionScope.user.username }&nbsp;&nbsp; 
		 	<a href="${ pageContext.request.contextPath }/index/logout">LOGOUT</a>&nbsp; 		 
			<a href="${ pageContext.request.contextPath }/index/adminlogin">ADMINLOGIN</a>&nbsp; 
		 </c:if>		
		</div>	
	</div>
	<div id="line2">
		<img id="logo" src="${ pageContext.request.contextPath }/img/head/logo.png"/>
		<form method="post" action="${pageContext.request.contextPath }/searchProds" style="display: inline;">
			<input type="text" name="name" value="${name }"/>
			<input type="submit" value="SEARCH"/>
		</form>
		<span id="goto">
			<a id="goto_order" href="${pageContext.request.contextPath }/order/showorder">My Orders</a>
			<a id="goto_cart" href="${pageContext.request.contextPath }/cart/showcart">My Cart</a>
		</span>
		<img id="erwm" src="${ pageContext.request.contextPath }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${ pageContext.request.contextPath }/index.jsp">INDEX</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodlist">ALL</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodclass/1">CLOTHES</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodclass/2">ORNAMENTS</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodclass/3">COSMETICS</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodclass/4">SHOES</a></li>
				<li><a href="${ pageContext.request.contextPath }/prodclass/5">WATCHES</a></li>
			</ul>
		</div>
	</div>
</div>