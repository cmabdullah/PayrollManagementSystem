<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/docreate">
<table>
<tr> <td>User Name :</td><td><input name = "username" type = "text"/></td>   </tr>
<tr> <td>Password :</td><td><input name = "password" type = "text"/></td> 
<tr> <td>User Type :</td><td><input name = "usertype" type = "text"/></td> 
<tr> <td>Status :</td><td><input name = "status" type = "text"/></td> 
<tr> <td>Full Name :</td><td><input name = "fullname" type = "text"/></td> 
<tr> <td>Address :</td><td><input name = "address" type = "text"/></td> 
<tr> <td>Email :</td><td><input name = "email" type = "text"/></td> 
<tr> <td>phone :</td><td><input name = "phone" type = "text"/></td> 

<tr> <td>&nbsp;</td><td><input name = "create query" type = "submit"/></td> 
</table>


</form>
</body>
</html>