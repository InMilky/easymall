<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ pageContext.request.contextPath }/css/admin/orderLists.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	
	function gotoUnorders() {
		window.location.href="${ pageContext.request.contextPath }/admin/showunorder";
	}
	function gotoOrders() {
		window.location.href="${ pageContext.request.contextPath }/admin/showorder";
	}
	</script>
</head>
<body>
<%@ include file = "_top.jsp" %>
<div id="content">
	<div id="sel">
		<ul><li>
			<input name="o" value="all" type="radio" onclick="gotoOrders()"/>
			<span>所有订单</span></li>
		<li>
			<input name="o" value="undo" type="radio" checked="checked"  onclick="gotoUnorders()"/>
			<span>未发货订单</span></li>
		</ul>
	</div>
	<div id="no_order_info" style="text-align: center; font-size: 18px">
	<c:if test="${empty allunorderInfos }">
		<h2>目前没有任何订单需要发货！</h2>
	</c:if>
	</div>
	<!-- start -->
	<c:if test="${!empty allunorderInfos }">
		<c:forEach items="${allunorderInfos}" var="allunorderInfo">
		<c:set value="${allunorderInfo.uname}" var="username"></c:set>
		<c:forEach items="${allunorderInfo.orderInfo}" var="unorderInfo">
		<div id="do" style="margin: 36px auto;">
			<dl class="Order_information">
				<dt>
					<h2>未发货订单信息</h2>
				</dt>
				<table id="oT" rules="rows">
				<tr>
				<td class="tT">订单编号</td><td>${unorderInfo.order.id }</td>
				<td class="tT">支付状态</td>
				<td><c:if test="${unorderInfo.order.paystate == 1 }">已支付</c:if></td>
				</tr>
				<tr>
				<td class="tT">下单用户</td><td>${username }</td>
				<td class="tT">下单时间</td><td>${unorderInfo.order.ordertime }</td>
				</tr>
				<tr>
				<td class="tT">订单金额</td><td>${unorderInfo.order.money }</td>
				<td class="tT">收货地址</td><td>${unorderInfo.order.receiverinfo }</td>
				</tr>
				</table>
				<div id="send" >
				<form action="${ pageContext.request.contextPath }/admin/sendorder?orderID=${unorderInfo.order.id }" method="post" name="f">
					快递单号：<input type="text" name="kddh" value="123123">
					<input type="submit" value="发 货" name="sub">
				</form>	</div>
			</dl>
		
			<table id="pT">
				<tr>
					<th class="pth">商品图片</th>
					<th class="pth">商品名称</th>
					<th class="pth">商品单价</th>
					<th class="pth">购买数量</th>
					<th class="pth">总价</th>
				</tr>
				<c:forEach items="${unorderInfo.map }" var="entry">
				<tr>
					<td >
						<img src="${pageContext.request.contextPath }${entry.key.imgurl }" width="66" height="88">
					</td>
					<td>${entry.key.name }</td>
					<td>${entry.key.price }元</td>
					<td>${entry.value }件</td>
					<td>${entry.key.price*entry.value }元</td>
				</tr>
				</c:forEach>
			</table>
			<div class="Order_price">总计:￥${unorderInfo.order.money }元</div>
		</div>
		</c:forEach>
		</c:forEach>
	</c:if>
</div>
	<!-- 模版数据 -end -->
	<%@ include file = "/WEB-INF/jsp/_foot.jsp" %>
</body>
</html>
