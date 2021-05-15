<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<link href="${ pageContext.request.contextPath }/css/admin/prodsInfo.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>

<div class="prodsinfo">
	<table>
		<tr>
			<th>商品图片</th>
			<th class="ths">商品名称</th>
			<th class="ths">商品单价</th>
			<th class="ths">商品类型</th>
			<th class="ths">库存数量</th>
			<th>描述信息</th>
			<th>操作</th>
		</tr>
		
		<c:forEach items="${products}" var="products">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/prodInfo?pid=${products.id}" target="_blank">
				<img width="120px" height="120px" src="${pageContext.request.contextPath }${products.imgurl}" alt="${products.name}" >
				</a>
			</td>
			<td>${products.name} </td>
			<td>${products.price }</td>
			<td><c:forEach items="${categorys}" var="c">
				<c:if test="${c.id == products.category}">${c.name }</c:if>
			</c:forEach></td>
			<td>${products.pnum }</td>
			<td>${products.description} </td>
			<td><a href="${pageContext.request.contextPath }/adminProducts/editprod?pid=${products.id}"  id="${products.id }"  style="text-decoration: none">修&nbsp;改</a></td>
		</tr>
		</c:forEach>
	</table>
</div>