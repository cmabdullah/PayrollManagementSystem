<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--JSTL prifix added-->
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!-- you must have the security taglib declared in your JSP -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <img src="static/images/Payroll-Management.jpg" width="100%" height="100%">
 
 
 
 
 
 
 <!-- 
<h1>This is homepage</h1>

<a href="${pageContext.request.contextPath}/usersinfo">Show Users Info</a><br>

<a href="${pageContext.request.contextPath}/registration">registration</a>
	

<sec:authorize access="!isAuthenticated()">
	<a href="<c:url value='/login'/>">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/j_spring_security_logout'/>">Log Out</a>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin Page</a>
</sec:authorize>

 -->