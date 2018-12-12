<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${wrongpattern == true}">
	<h1>Wrong pattern data input</h1>
</c:if>


<c:if test="${isPandingRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>

<c:if test="${isLeaveRequestOutOfLimit == true}">
	<h1>You ARE not eligible for request</h1>
</c:if>



<div class="col-xs-12 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/leavereq_process">



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
				
			<!-- Textarea -->
			<div class="form-group">
				<label class="col-xs-12 control-label" for="text">Leave Reason :</label>
				<div class="col-xs-12">
					<textarea class="form-control"  id="reasone" name="reasone"></textarea>
					<!-- Show error message into view -->
					
				</div>
			</div>
			
			
			
				<!-- Material inline 1 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="leavetype" value="regular" checked> <label
					class="form-check-label" for="materialInline1">Regular</label>
			</div>

			<!-- Material inline 2 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="leavetype" value="study"> <label
					class="form-check-label" for="materialInline2">Study</label>
			</div>
			<!-- Material inline 2 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="leavetype" value="medical"> <label
					class="form-check-label" for="materialInline2">Medical</label>
			</div>
			
			<input name="create query" type="submit" class="btn btn-primary btn-lg"/>
			
			
			<!-- Textarea 
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Leave Type :</label>
				<div class="col-md-4">
					<input type="radio" name="leavetype" value="regular" checked>Regular<br>
				<input type="radio" name="leavetype" value="study">Study<br>
				<input type="radio" name="leavetype" value="medical">Medical<br>
					
				</div>
			</div>
			
		
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit request</button>
				</div>
			</div>
-->
	</form>
</div>




