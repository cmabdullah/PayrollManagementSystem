<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<h1>Add deny Loan request.....</h1>


<c:if test="${loanAllPendingRequests != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">reason</th>
			<th scope="col">userinfo_id</th>
			<th scope="col">amount</th>
			<th scope="col">DELETE</th>

		</tr>
		<c:forEach var="loanAllPendingRequest" items="${loanAllPendingRequests}">
			<tr>
				<td><c:out value="${loanAllPendingRequest.id}"></c:out></td>
				<td><c:out value="${loanAllPendingRequest.reason}"></c:out></td>
				<td><c:out value="${loanAllPendingRequest.userinfo_id}"></c:out></td>
				<td><c:out value="${loanAllPendingRequest.amount}"></c:out></td>
				
				<td><a  href="<c:url value='/deleteemp/${loanAllPendingRequest.id}'/>">Delete</a></td>
				
				<!--  <td>${emp.designation}</td>   -->
				<td></td>  

			</tr>
		</c:forEach>
	</table>

</c:if>