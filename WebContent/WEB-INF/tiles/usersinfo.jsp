<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!--Show value tablewise-->
	<table class="table table-striped">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">username</th>
			<th scope="col">fullname</th>
			<th scope="col">address</th>
			<th scope="col">email</th>
			<th scope="col">phone</th>
		</tr>
		<c:forEach var="userinfo" items="${usersinfo}">
			<tr>
				<td><c:out value="${userinfo.id}"></c:out></td>
				<td><c:out value="${userinfo.username}"></c:out></td>
				<td><c:out value="${userinfo.fullname}"></c:out></td>
				<td><c:out value="${userinfo.address}"></c:out></td>
				<td><c:out value="${userinfo.email}"></c:out></td>
				<td><c:out value="${userinfo.phone}"></c:out></td>

			</tr>
		</c:forEach>
	</table>
