<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>商品管理</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link href="${ pageContext.request.contextPath }/css/admin/prodLists.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	function reinitIframe(){ //iframe 实现基于内容的高度自适应
		var iframe = document.getElementById("frame");
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
	<div class="left">		
	<%@ include file = "prod_left.jsp" %>	
	</ul>
	</div>
	<div class="right">	
		<iframe src="${ pageContext.request.contextPath }/adminProducts/showAllProds" onload="this.height=100" scrolling="no" id="frame" name="myframe" frameborder="0"></iframe>
		<br clear="all"/>
		<%@ include file="/WEB-INF/jsp/_foot.jsp" %>
	</div>
</div>

</body>
</html>
