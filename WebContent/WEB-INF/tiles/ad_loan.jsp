<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${loanAllPendingRequests != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">Full Name</th>
			<th scope="col">Email</th>
			<th scope="col">Reason</th>
			<th scope="col">Amount</th>
			<th scope="col">Delete</th>
			<th scope="col">Accept</th>

		</tr>
		<c:forEach var="loanAllPendingRequest" items="${loanAllPendingRequests}">
			<tr>
				<td><c:out value="${loanAllPendingRequest.fullname}"></c:out></td>
				<td><c:out value="${loanAllPendingRequest.email}"></c:out></td>
				<td><c:out value="${loanAllPendingRequest.reason}"></c:out></td>
				
				<td><c:out value="${loanAllPendingRequest.amount}"></c:out></td>
				
				<td><a  href="<c:url value='/deleteemp/${loanAllPendingRequest.id}'/>">Delete</a></td>
				<td><a  href="<c:url value='/accept_loan_request/${loanAllPendingRequest.id}'/>">Accept</a></td>
				
				<!--  <td>${emp.designation}</td>   -->
				<td></td>  

			</tr>
		</c:forEach>
	</table>

</c:if>