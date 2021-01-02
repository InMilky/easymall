<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加商品类别</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/admin/categoryLists.css" rel="stylesheet" type="text/css"><base>
		
		<script type="text/javascript">
		// onclick="editTx(this.id)" 
			function reinitIframe(){ //iframe 实现基于内容的高度自适应
				var iframe = document.getElementById("cframe");
				try{
					var bHeight = iframe.contentWindow.document.body.scrollHeight;
					var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
					var height = Math.max(bHeight, dHeight);
					iframe.height = height;
					console.log(height);
				}catch (ex){}
				}
				window.setInterval("reinitIframe()", 200);
			
		</script>
		
	</head>
	<body>
	<%@ include file = "_top.jsp" %>
	<div class="content">
	<div class="categoryLists" style="text-align: center;">
	<c:if test="${categorys.size() == 0 }">
		您还没有类型。
	</c:if>
	<c:if test="${categorys.size() != 0 }">
		<table>
			<tr>
				<th>类型ID</th>
				<th>类型名称</th>
				<th class="ths">类型描述</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${categorys }" var="c">
				<tr>
					<td>${c.id }</td>
					<td>${c.name }</td>
					<td>${c.description } </td> 
					<td>
						<a href="${ pageContext.request.contextPath }/adminCategory/toeditCategory?id=${c.id }" id="${c.id }" target="categoryframe" name="editbtn" >修改</a>&nbsp;&nbsp;
						<a href="${ pageContext.request.contextPath }/adminCategory/delCategory?categoryID=${c.id }" name="editbtn" onSubmit="return check()">
						删除</a>
					</td>
				</tr>
			</c:forEach>
		
		</table>
	</c:if>
	</div>
	<div class="Categoryiframe" style="width: 40%;float: left;margin:0 auto ;display:inline; text-align:center;" >
		<iframe src="${ pageContext.request.contextPath }/adminCategory/toaddCategory" onload="this.height=100" width="100%" scrolling="no" id="cframe" name="categoryframe" frameborder="0"></iframe>
	</div>
	</div>
	<br clear="all"/>
	<%@ include file="/WEB-INF/jsp/_foot.jsp" %>
	</body>
</html>