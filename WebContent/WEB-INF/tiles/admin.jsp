<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<table class="table table-striped">
		<tr>
			<th scope="col">Username </th>
			<th scope="col">Email</th>
			<th scope="col">Authority</th>
			<th scope="col">Enabled</th>
		</tr>
		<c:forEach var="user" items="${users}">
			<tr>
				<td><c:out value="${user.username}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<td><c:out value="${user.authority}"></c:out></td>
				<td><c:out value="${user.enabled}"></c:out></td>
			</tr>
		</c:forEach>
	</table>