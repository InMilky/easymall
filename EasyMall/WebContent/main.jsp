<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
		<title>欢迎光临EasyMall后台</title>
		<style type="text/css">
			
			#main{
				text-align: center; 
				width:100%; 
				height: 300px;
			}
			#main p{
				 font-size: 36px; 
				 color: #C4C4C4; 
				 padding-top: 6rem;
			}
		</style>
	</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/_top.jsp"/>
	<div id="main">
		<p>欢迎来到EasyMall后台主页</p>
	</div>
<jsp:include page="/WEB-INF/jsp/_foot.jsp"/>
+
</body>
</html>