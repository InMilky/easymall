<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<link href="${ pageContext.request.contextPath }/css/admin/addProd.css" rel="stylesheet" type="text/css">
<script  type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
		
	<div class="addprod">
	<jsp:useBean id="products" class="easymall.po.Products" scope="request"></jsp:useBean>
	<form:form modelAttribute="products" onsubmit="return formobj.checkForm();" method="POST" 
	   action="${ pageContext.request.contextPath }/adminProducts/update" enctype="multipart/form-data">	
	<fieldset>
	<legend> 修改商品 </legend>			
		<table >
		<tr><td colspan="2" style="color:red;text-align:center;font-size: 15px">${ msg }</td></tr>		
		<tr>
            <td>
            <font color="red">*</font>商品名：</td>
            <td><form:input path="name" id="name"/><span></span>
            </td>
        </tr>
        <tr>
        	<td><font color="red">*</font>商品价格：</td>
            <td><form:input path="price" id="price"/></td><span></span>
        </tr>
        <tr>
        	<td><font color="red">*</font>商品类别：</td>
            <td><form:select path="category" id="category">
            <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
			<c:forEach items="${categorys}" var="c">
				<option value="${c.id }" ${c.id eq product.category? "selected":"" }>${c.name }</option>
			</c:forEach>
			</form:select>
			</td>
        </tr>
        <tr><td>
            <font color="red">*</font>库存：</td>
            <td><form:input path="pnum" id="pnum"/></td><span></span>
        </tr>
        <tr><td>
            <font color="red">*</font>图片：</td>
            <td><input type="file" name="imgurl"/></td>
        </tr>
        <tr><td>
            <font color="red">*</font>商品描述：</td> <!-- <form:input path="description"/> <textarea name="description" id="textarea"></textarea> -->
            <td><form:textarea path="description" id="description"/> </td><span></span>
        </tr>
        <tr></tr>
        <tr><td colspan="2" style="text-align: center;">
        	<input id="back" type="button" onclick="javascript:window.location.href='${ pageContext.request.contextPath }/adminProducts/updateprod'" value="返 回">&nbsp;&nbsp;
            <input id="reset" type="reset" value="重 置">&nbsp;&nbsp;
            <input id="submit" type="submit" value="修 改">
        </td></tr>
        </table>
        </fieldset>
    	<!-- 取出所有验证错误 -->
    	<form:errors path="*"/>
	</form:form>
	</div>	

