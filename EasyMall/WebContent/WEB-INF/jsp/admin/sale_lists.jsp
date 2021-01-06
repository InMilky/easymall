<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/admin/saleLists.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<%@ include file = "_top.jsp" %>
	<div id="content">
	<div id="search_div">
		<!-- 类别  名称  价格	数量	查询按钮	导出Excel -->
		<form method="post" action="${pageContext.request.contextPath }/prodtoplist">
			<span class="input_span">商品名：<input type="text" name="name" value="${name}"/></span>
			<span class="input_span" style="margin-left: 2rem;">商品种类：</span>
			<select name="category" id="category">
				<option value="">不限</option>
				<!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
				<c:forEach items="${categorys}" var="c">
					<option value="${c.id}" 
					<c:if test="${c.id == category}">selected="selected"</c:if> 
					>${c.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="查 询">
			<input type="button" value="导出Excel" onclick="javascript:window.location.href='${pageContext.request.contextPath}/chart/printtable'">
		</form>
	</div>
	<div id="salesRanking" style="text-align: center">
		<c:if test="${prodtops.size() == 0 }">
			<h2>您还没有出售任何商品</h2>
		</c:if>
		<c:if test="${prodtops.size() != 0 }">
		<table rules="rows">
			<tr class="th">
				<th>销售排名</th>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>出售数量</th>
			</tr>
			<c:set var="order" value="1" />
			<c:forEach items="${prodtops }" var="ptop">
			<tr>
				<td>${order}</td>
				<td>
				<a href="${pageContext.request.contextPath }/prodInfo?pid=${ptop.id}" target="_blank">
				<img width="100px" height="100px" src="${ pageContext.request.contextPath }${ptop.imgurl }" alt="${ptop.name}" style="border: 1px solid #CA141D;" >
				</a>
				</td>
				<td>${ptop.name }</td>
				<td>${ptop.price }</td>
				<td>${ptop.soldnum }</td>
			</tr>
			<c:set var="order" value="${order+1}" />
			</c:forEach>
		</table>
		</c:if>
	</div>
	</div>
	<%@ include file="/WEB-INF/jsp/_foot.jsp" %>
	</body>
</html>