<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--JSTL prifix added-->
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!-- you must have the security taglib declared in your JSP -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>This is homepage</h1>

<a href="${pageContext.request.contextPath}/usersinfo">Show Users Info</a><br><!--for appropriate path
read context path from page context-->
<a href="${pageContext.request.contextPath}/registration">registration</a>
	
	
<!--role based authontication-->
<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>
