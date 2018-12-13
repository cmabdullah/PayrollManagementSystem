<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
<table class="table table-striped">
		<tr>
			<th scope="col">Name</th>
			<th scope="col">Address</th>
			<th scope="col">Email</th>
			<th scope="col">Phone</th>
			<th scope="col">Update</th>
		</tr>
		<c:if test="${usersinfo != null}">
		
		<tr>
				<td><c:out value="${usersinfo.fullname}"></c:out></td>
				<td><c:out value="${usersinfo.address}"></c:out></td>
				<td><c:out value="${usersinfo.email}"></c:out></td>
				<td><c:out value="${usersinfo.phone}"></c:out></td>
				<td><a  href="<c:url value='/usersinfo_profile_update/${usersinfo.id}'/>">Update</a></td>

			</tr>
			</c:if>

	</table>
	</div>