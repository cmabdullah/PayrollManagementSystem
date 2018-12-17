<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${wrongpattern == true}">
	<h1>Wrong pattern data input</h1>
</c:if>

<!-- 
<div class="col-xs-12 col-md-offset-12">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/report_process">

		<div class="form-group">
			<label class="col-xs-12 control-label" for="text">From</label>
			<div class="col-xs-12">
				<input type="date" name="entryfromString" required><br>

			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 control-label" for="text">To</label>
			<div class="col-xs-12">
				<input type="date" name="entrytoString" required><br>

			</div>
		</div>

		
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline1"
				name="reportType" value="attendance" checked> <label
				class="form-check-label" for="materialInline1">Attendance</label>
		</div>

		
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline2"
				name="reportType" value="salary"> <label
				class="form-check-label" for="materialInline2">Salary</label>
		</div>
		
		
		<div class="form-check form-check-inline">
			<input type="radio" class="form-check-input" id="materialInline2"
				name="reportType" value="loan"> <label
				class="form-check-label" for="materialInline2">Loan</label>
		</div>

		<input name="create query" type="submit"
			class="btn btn-primary btn-lg" />

	</form>
</div>

-->





<div class="container">
	<div class="row">
		<div class="col-md-12 col-md-offset-12">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please Enter Date Range what you wanted to search from given calendar</h3>
				</div>
				<div class="panel-body">

					<form class="form-horizontal" method="post" role="form"
						action="${pageContext.request.contextPath}/report_process">

						<fieldset>

							<div class="form-group">
								<span class="badge badge-secondary"><h6>Select Date
										From</h6></span> <input type="date" name="entryfromString" required>
								<span class="badge badge-secondary"><h5>Select Date
										To</h5></span> <input type="date" name="entrytoString" required>
							</div>




							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline1" name="reportType" value="attendance"
									checked> <label class="form-check-label"
									for="materialInline1">Attendance</label>
							</div>

							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="reportType" value="salary">
								<label class="form-check-label" for="materialInline2">Salary</label>
							</div>

							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="reportType" value="loan"> <label
									class="form-check-label" for="materialInline2">Loan</label>
							</div>



							<input name="create query" type="submit"
								class="btn btn-lg btn-success btn-block" />


						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>













