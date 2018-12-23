<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${wrongpattern == true}">
	<h1>Wrong pattern data input</h1>
</c:if>


<c:if test="${isPandingRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>

<c:if test="${isLeaveRequestOutOfLimit == true}">
	<h1>You ARE not eligible for request</h1>
</c:if>

<!-- 

<div class="col-xs-12 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/leavereq_process">



	
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
				
		
			<div class="form-group">
				<label class="col-xs-12 control-label" for="text">Leave Reason :</label>
				<div class="col-xs-12">
					<textarea class="form-control"  id="reasone" name="reasone"></textarea>
					
					
				</div>
			</div>
		
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="leavetype" value="regular" checked> <label
					class="form-check-label" for="materialInline1">Regular</label>
			</div>

	
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="leavetype" value="study"> <label
					class="form-check-label" for="materialInline2">Study</label>
			</div>

			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="leavetype" value="medical"> <label
					class="form-check-label" for="materialInline2">Medical</label>
			</div>
			
			<input name="create query" type="submit" class="btn btn-primary btn-lg"/>
		
	</form>
</div>

-->

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">Please Enter Date Range what you
						wanted to leave from given calendar</h4>
				</div>
				<div class="panel-body">


					<form class="form-horizontal" method="post" role="form"
						action="${pageContext.request.contextPath}/leavereq_process">

						<fieldset>
							<div class="form-group" align="center">
								<span class="badge badge-secondary"><h5>Select Date
										From</h5></span> <input type="date" name="entryfromString" required>
								<span class="badge badge-secondary"><h5>Select Date
										To</h5></span> <input type="date" name="entrytoString" required>
							</div>

							<div class="form-group">
								<label class="col-xs-12" for="text">Enter Your Leave
									Reason :</label>
								<div class="col-xs-12">
									<textarea class="form-control" id="reasone" name="reasone"></textarea>
									<!-- Show error message into view -->

								</div>
							</div>


							<!-- Material inline 1 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline1" name="leavetype" value="regular" checked>
								<label class="form-check-label" for="materialInline1">Regular</label>
							</div>

							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="leavetype" value="study"> <label
									class="form-check-label" for="materialInline2">Study</label>
							</div>
							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="leavetype" value="medical">
								<label class="form-check-label" for="materialInline2">Medical</label>
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

<script>
if ( $('[type="date"]').prop('type') != 'date' ) {
    $('[type="date"]').datepicker({format : 'yyyy-mm-dd'});
}
</script>


