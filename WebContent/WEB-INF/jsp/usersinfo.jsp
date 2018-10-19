<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--retrive data from controller-->
<c:forEach var="userinfo" items="${usersinfo}">
    ID: ${userinfo.id}<br/>
    Username: ${userinfo.username}<br/>
    Usertype: ${userinfo.usertype}<br/>
    Address: ${userinfo.address}<br/>
</c:forEach>
</body>
</html>