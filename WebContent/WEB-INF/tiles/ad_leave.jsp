<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${wrongpattern == true}">
	<h1>Wrong pattern data input</h1>
</c:if>

<c:if test="${wrongid == true}">
	<h1>Wrong id input</h1>
</c:if>

<!-- 
<sf:form
	action="${pageContext.request.contextPath}/ad_leave_req_process"
	method="post" commandName="leave">


	
	<h1>Confirm leave request</h1>
	
	<input id="userinfo_id" name="userinfo_id"  placeholder="Enter your user id" type="text" required/><br><br>
	<input type="date" name="entryfromString" required><br> 
	
	<br>
	
	<input type="date" name="entrytoString" required><br> 
	
	<br>
	
	<tr>
			<td><input type="radio" name="leavetype" value="regular" checked>Regular<br>
				<input type="radio" name="leavetype" value="study">Study<br>
				<input type="radio" name="leavetype" value="medical">Medical<br>
			<td>&nbsp;</td>

	<br> <label class="control-label" for="submit"></label>
	<button id="submit" name="submit" type="submit">Submit</button>

</sf:form>

 -->
<div align="center"><h3> You can Accept or Deny Leave Request following the table below </h3><br></div>
<c:if test="${leaveInit != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">Fullname</th>
			<th scope="col">Email</th>
			<th scope="col">Reason</th>
			<th scope="col">Leave Type</th>
			<th scope="col">Entry From</th>
			<th scope="col">Entry To</th>
			<th scope="col">Total Leave Days</th>
			<th scope="col">Ignore Leave</th>
			<th scope="col">Delete</th>
			<th scope="col">Accept</th>
		</tr>
		<c:forEach var="leaveInitSigle" items="${leaveInit}">
			<tr>
				<td><c:out value="${leaveInitSigle.fullname}"></c:out></td>
				<td><c:out value="${leaveInitSigle.email}"></c:out></td>
				<td><c:out value="${leaveInitSigle.reasone}"></c:out></td>
				<td><c:out value="${leaveInitSigle.leavetype}"></c:out></td>
				<td><c:out value="${leaveInitSigle.entryfrom}"></c:out></td>
				<td><c:out value="${leaveInitSigle.entryto}"></c:out></td>
				<td><c:out value="${leaveInitSigle.total_leave_days}"></c:out></td>
				<td><c:out value="${leaveInitSigle.deniedLeaveRequest}"></c:out></td>
				<td><a  href="<c:url value='/deleteleave/${leaveInitSigle.id}'/>">Delete</a></td>
				<td><a  href="<c:url value='/acceptleave/${leaveInitSigle.id}'/>">Accept</a></td>


			</tr>
		</c:forEach>
	</table>

</c:if>


