<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h1>Show Report</h1>
<!-- 
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
 -->
<c:if test="${wrongpattern == true}">
	<h1>Wrong pattern data input</h1>
</c:if>


<div class="col-xs-12 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/report_process">



		<!-- Textarea -->
		<div class="form-group">
			<label class="col-xs-12 control-label" for="text">From</label>
			<div class="col-xs-12">
				<input type="date" name="entryfromString" required><br>

			</div>
		</div>
		<!-- Textarea -->
		<div class="form-group">
			<label class="col-xs-12 control-label" for="text">To</label>
			<div class="col-xs-12">
				<input type="date" name="entrytoString" required><br>

			</div>
		</div>

		<!-- Material inline 1 -->
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline1"
				name="reportType" value="attendance" checked> <label
				class="form-check-label" for="materialInline1">Attendance</label>
		</div>

		<!-- Material inline 2 -->
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline2"
				name="reportType" value="salary"> <label
				class="form-check-label" for="materialInline2">Salary</label>
		</div>
		<!-- Material inline 2 -->
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline2"
				name="reportType" value="loan"> <label
				class="form-check-label" for="materialInline2">Loan</label>
		</div>

		<input name="create query" type="submit"
			class="btn btn-primary btn-lg" />

	</form>
</div>
