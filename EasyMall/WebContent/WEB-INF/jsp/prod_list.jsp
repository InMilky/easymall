<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath }/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%@ include file="_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${pageContext.request.contextPath }/prodlist">
				<span class="input_span">ITEMNAME：<input type="text" name="name" value="${name}"/></span>
				<span class="input_span">CATEGORY：</span>
				<select name="category" id="category">
					<option value="">ALL</option>
					<!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
					<c:forEach items="${categorys}" var="c">
						<option value="${c.id}" 
						<c:if test="${c.id == category}">selected="selected"</c:if> 
						>${c.name}</option>
					</c:forEach>
				</select>
				<span class="input_span">PRICE：</span>
					<input type="text" name="minprice" value="${min}"/>
				  - <input type="text" name="maxprice" value="${max}"/>
				<input type="submit" value="SEARCH">
			</form>
		</div>
		<div id="prod_content">
		<c:forEach items="${ products }" var="prod">
			<div class="prod_div">
				<a href="${ pageContext.request.contextPath }/prodInfo?pid=${prod.id}" target="_blank">
					<img src="${ pageContext.request.contextPath }${prod.imgurl}" border="0"/>
				</a>
				<div id="prod_name_div">
					<a href="${ pageContext.request.contextPath }/prodInfo?pid=${prod.id}" target="_blank">
						${prod.name}
					</a></div>
				<div id="prod_price_div">￥${prod.price}</div>
				<div>
					<div id="say_div">133 reviews</div>	
					<div id="gotocart_div">
						<a href="${pageContext.request.contextPath }/cart/addCart?pid=${prod.id}&buyNum=1">ADD TO CART</a>
					</div>					
				</div>
			</div>
		</c:forEach>
		<div style="clear: both"></div>
		</div>
	</div>
	<%@ include file="_foot.jsp" %>
</body>
</html>
