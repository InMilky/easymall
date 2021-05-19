<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ pageContext.request.contextPath }/css/orderList.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function cuicu() {
			alert("Your requestment has been received, please wait patiently");
		}
	</script>
</head>
<body>
<%@ include file = "_head.jsp" %>
	<p id="ptitle">BEAUTYMALL > ORDERS</p>
	
	<c:if test="${empty orderInfos }">
	<div id="no_order_info">
		<h2>There are no orders on your account.</h2>
	</div>
	</c:if>
	
	<!-- 模版数据 -start -->
	<c:forEach items="${orderInfos }" var="orderInfo">
	<div class="orderinfos_div">
		<dl class="Order_information">
			<dt><h3>ORDERINFOS</h3></dt>
			<dd>
				Order_ID：${orderInfo.order.id }
				<br />
				Order_Time：${orderInfo.order.ordertime }
				<br /> 
				Order_Amount：￥${orderInfo.order.money }
				<br /> 
				Payment_State：
				<c:if test="${orderInfo.order.paystate == 0 }">
					<font color="red">non-payment</font>&nbsp;&nbsp;
					<a href="${ pageContext.request.contextPath }/order/delorder?id=${orderInfo.order.id}" style="text-decoration: none;"}> 
					 	<img src="${ pageContext.request.contextPath }/img/orderList/del.png" alt="delorder">
					</a>
					-
				 	<a href="${ pageContext.request.contextPath }/order/payorder?id=${orderInfo.order.id}" style="text-decoration: none;"> 
					 	<img src="${ pageContext.request.contextPath }/img/orderList/pay.png" alt="OnlinePayment">
					</a>
				</c:if>
				<c:if test="${orderInfo.order.paystate == 1 }">
					<font color="blue">Paid</font>&nbsp;&nbsp;
					<a href="#" onclick="cuicu()"> Pressing for Order </a>
				</c:if>
				<c:if test="${orderInfo.order.paystate == 2 }">
					<font color="blue">Delivered</font>&nbsp;&nbsp;
					<a href="${ pageContext.request.contextPath }/order/confirmorder?id=${orderInfo.order.id}"> 
					 	Comfirm Receipt
					</a>
				</c:if>
				<c:if test="${orderInfo.order.paystate == 3 }">
					<font color="blue">Received</font>&nbsp;&nbsp;
				</c:if>
				<br /> 
				Username：${user.username }
				<br/> 
				Shipping_addr：${orderInfo.order.receiverinfo }
				<br/> 
				Payment：online
			</dd>
		</dl>
	
		<table width="1100" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<th width="256" height="30" align="center" valign="middle" bgcolor="#f3f3f3">PICTURE</th>
				<th width="220" align="center" valign="middle" bgcolor="#f3f3f3">NAME</th>
				<th width="210" align="center" valign="middle" bgcolor="#f3f3f3">PRICE</th>
				<th width="198" align="center" valign="middle" bgcolor="#f3f3f3">QUANTITY</th>
				<th width="212" align="center" valign="middle" bgcolor="#f3f3f3">TOTAL</th>
			</tr>
			<c:forEach items="${orderInfo.map }" var="entry">
			<tr>
				<td align="center" valign="middle" bgcolor="#FFFFFF">
					<img src="${pageContext.request.contextPath }${entry.key.imgurl }" width="108" height="106">
				</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.key.name }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">￥${entry.key.price }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${entry.value }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">￥${entry.key.price*entry.value }</td>
			</tr>
			</c:forEach>
		</table>
		<div class="Order_price">￥${orderInfo.order.money }</div>
	</div>
	</c:forEach>
	<!-- 模版数据 -end -->
	<%@ include file = "_foot.jsp" %>
</body>
</html>
