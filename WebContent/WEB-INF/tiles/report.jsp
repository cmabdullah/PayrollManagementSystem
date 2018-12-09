<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<h1>Show Report</h1>

	<table border="1px" cellpadding="8px">
		<tr>
			<td>Month</td>
			<td>Revenue</td>
		</tr>

		<c:forEach items="${revenueData}" var="current">
			<tr>
				<td><c:out value="${current.key}" /></td>
				<td><c:out value="${current.value}" /></td>
			</tr>
		</c:forEach>
	</table>
