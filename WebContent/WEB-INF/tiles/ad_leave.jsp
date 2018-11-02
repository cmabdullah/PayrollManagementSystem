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
<sf:form
	action="${pageContext.request.contextPath}/ad_leave_req_process"
	method="post" commandName="leave">


	<!-- Form Name -->
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



<c:if test="${leaveInit != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">reason</th>
			<th scope="col">userinfo_id</th>

		</tr>
		<c:forEach var="leaveInitSigle" items="${leaveInit}">
			<tr>
				<td><c:out value="${leaveInitSigle.id}"></c:out></td>
				<td><c:out value="${leaveInitSigle.reasone}"></c:out></td>
				<td><c:out value="${leaveInitSigle.userinfo_id}"></c:out></td>


			</tr>
		</c:forEach>
	</table>

</c:if>


