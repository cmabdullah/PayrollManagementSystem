<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--JSTL prifix added-->
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  Request: ${name} -->

<h1>This is homepage</h1>
	<!-- name is: ${one } -->


<a href="${pageContext.request.contextPath}/usersinfo">Show Users Info</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/registration">registration</a>
	
	
	<p><a href="<c:url value='j_spring_security_logout'/>">Log out</a></p>
	<p><a href="<c:url value='admin'/>">Admin</a></p>
	
</body>
</html>