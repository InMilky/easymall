<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<link href="${ pageContext.request.contextPath }/css/admin/addCategory.css" rel="stylesheet" type="text/css">
	<div class="addCategory" style="display: inline; text-align:center " >
		<form onsubmit="return formobj.checkForm();" target="_parent" action="${ pageContext.request.contextPath }/adminCategory/addCategory" method="post" style="text-align:center">
			<p class="title">添加类型</p>
			<div class="addcontent">
				<p>类型名称：<input type="text" name="name"/></p><span id="type_msg"></span>
				<p style="margin-top: 10px;">类型描述：<textarea name="description" id="textarea"></textarea></p>
			</div>
			<br clear="all"/>
			<div id="submit_div">
				<p style="float:left; color: red;margin-top: 0;">不能为空</p>
				<br clear="all"/>
				<input type="submit" id="submit" value="添 加"/>
			</div>
			
			<br clear="all"/>
		</form>
	</div>