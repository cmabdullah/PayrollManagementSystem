<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:if test="${attendanceListByUser != null}">
<div align="center"><h3> Attendance Activity </h3><br></div>
<table class="table table-striped">
		<tr>
			<th scope="col">Login Time</th>
			<th scope="col">Working Hours</th>
		</tr>
		<c:forEach var="singleUserOneDaysSplitData" items="${attendanceListByUser}">
			<tr>
				<td><c:out value="${singleUserOneDaysSplitData.logintime}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.workinghours}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</c:if>






<c:if test="${salaryListByUser != null}">
<div align="center"><h3> Salary Activity </h3><br></div>
<table class="table table-striped">
		<tr>
			<th scope="col">Salary Id</th>
			<th scope="col">Name</th>
			<th scope="col">email</th>
			<th scope="col">Total Salary</th>
		</tr>
		<c:forEach var="singleUserOneDaysSplitData" items="${salaryListByUser}">
			<tr>
				<td><c:out value="${singleUserOneDaysSplitData.id}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.fullname}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.email}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.totalsalary}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</c:if>



<c:if test="${loanListByUser != null}">
<div align="center"><h3> Loan Activity </h3><br></div>
<table class="table table-striped">
		<tr>
			<th scope="col">Loan Id</th>
			<th scope="col">Name</th>
			<th scope="col">Loan Place Date</th>
			<th scope="col">Amount</th>
			<th scope="col">Status</th>
		</tr>
		<c:forEach var="singleUserOneDaysSplitData" items="${loanListByUser}">
			<tr>
				<td><c:out value="${singleUserOneDaysSplitData.id}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.fullname}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.placedate}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.amount}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.status}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</c:if>





<p class="print-align-right">
     <a href="#" onclick="javascript:window.print();" >Print this page</a>
</p>