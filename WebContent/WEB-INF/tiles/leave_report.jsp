<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${leaveStatusGroupByLeavetype != null}">
<div align="center"><h3> Leave Details </h3><br></div>
<table class="table table-striped">
		<tr>
			<th scope="col">Full Name</th>
			<th scope="col">status</th>
			<th scope="col">Reason</th>
			<th scope="col">Leave Type</th>
			<th scope="col">Entry From</th>
			<th scope="col">Entry To</th>

		</tr>
		<c:forEach var="leaveStatusGroupByLeavetypeData" items="${leaveStatusGroupByLeavetype}">
			<tr>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.fullname}"></c:out></td>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.status}"></c:out></td>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.reasone}"></c:out></td>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.leavetype}"></c:out></td>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.entryfrom}"></c:out></td>
				<td><c:out value="${leaveStatusGroupByLeavetypeData.entryto}"></c:out></td>

			</tr>
		</c:forEach>
	</table>

</c:if>








<p class="print-align-right">
     <a href="#" onclick="javascript:window.print();" >Print this page</a>
</p>