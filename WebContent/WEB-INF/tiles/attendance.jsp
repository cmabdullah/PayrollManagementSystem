<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="${pageContext.request.contextPath}/start"   commandName="userinfo">
<!-- <input name = "create query" type = "submit"/> -->
<!-- -->
<button id="submit" name="submit" class="btn btn-primary">start</button> 


	

<!-- 
	<c:choose>
		<c:when test="${!hasLogin}">
		
			<a href="<c:url value='/createnotice' />">Edit or delete your current notice</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/createnotice' />">Add a new notice</a>
		</c:otherwise>
	</c:choose>
-->
</form>

