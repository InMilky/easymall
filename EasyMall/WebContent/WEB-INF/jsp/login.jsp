<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
		<title>BeautyMall欢迎您登录</title>
	</head>
	<body>
		<h1>LOGIN IN BEAUTYMALL</h1>
		<form action="${ pageContext.request.contextPath }/user/login" method="POST">			
			<table>
				<tr>	
					<td class="tdx">USERNAME：</td>
					<td><input type="text" name="username" value="${ user.username }"/></td>
				</tr>
				<tr>
					<td class="tdx">PASSWORD：</td>
					<td><input type="password" name="password" value=""/></td>
				</tr>
				<tr>
					<td colspan="2" class="tds">
						<a href="${ pageContext.request.contextPath }/index/regist">Create An Account?</a>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="LOGIN"/>
					</td>	
				</tr>
				<tr><td colspan="2" style="color:red;text-align:center;font-height:bold;">
				${message}</td></tr>
			</table>
		</form>		
	
	</body>
</html>

