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


	<!-- name is: ${one } -->



	<sql:query var="rs" dataSource="jdbc/spring">
			select id, username, password, usertype from userinfo
		</sql:query>
	<!--For each loop-->
	<c:forEach var="row" items="${rs.rows}">
		    userame: ${row.username}<br />
		    password: ${row.password}<br />
	</c:forEach>
	
</body>
</html>