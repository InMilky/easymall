<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ pageContext.request.contextPath }/css/addOrder.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file = "_head.jsp" %>
	<div class="warp">
		<p id="daohangtiao">BEAUTYMALL > SHOPPING CART > PAYMENT</p>
		<form action="${ pageContext.request.contextPath }/order/addOrder" name="form1" method="post">
			<div id="forminfo">
				<p> PAYMENT：<input name="" type="radio" value="1" checked>&nbsp;online payment
				  	   <input type="hidden" name="cartIds" value="${cartIds }"></p>
				<span class="lf">ADDRESS：</span> 
				<label for="textarea"></label>
				<textarea name="receiverinfo" id="textarea" cols="56" rows="2" style="resize:none">广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区</textarea>
			</div>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<th width="276">PICTURE</th>
					<th width="247">ITEM</th>
					<th width="231">PRICE</th>
					<th width="214">QUANTITY</th>
					<th width="232">TOTAL</th>
				</tr>
				<c:set var="sum" value="0"/>
				<c:forEach items="${carts }" var="cart">
				<tr>
					<td><img src="${ pageContext.request.contextPath }${cart.imgurl}" 
					width="90" height="90" class="prodimg" /></td>
					<td>${cart.name}</td>
					<td>￥${cart.price}</td>
					<td>${cart.num}</td>
					<td>￥${cart.price*cart.num}</td>
				</tr>
				<c:set var="sum" value="${sum + cart.price*cart.num }"/>				
				</c:forEach>
			</table>

			<div class="Order_price">TOTAL：￥${sum }</div>

			<div class="add_orderbox">
				<input name="" type="submit" value="CHECKOUT" class="add_order_btn">
			</div>
		</form>
		<div style="clear: both"></div>
	</div>
	<%@ include file="_foot.jsp" %>
</body>
</html>
