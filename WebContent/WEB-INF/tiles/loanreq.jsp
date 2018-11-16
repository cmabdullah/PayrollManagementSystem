<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${isPandingLoanRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>

<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/loanreq_process" >

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Loan Reason :</label>
				<div class="col-md-4">
					<textarea class="form-control"  id="reason" name="reason"></textarea>
					
				</div>
			</div>
			
			
			
			<!-- Text input-->
				<div class="form-group">
					
					<div class="col-md-4">
						
							<input type="radio" name="loanType" value="give_medical">Medical<br>
							<input type="radio" name="loanType" value="give_study">Study<br>
							<input type="radio" name="loanType" value="give_business">Business<br>
							
							
					</div>
				</div>
			
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit request</button>
				</div>
			</div>

	</form>
</div>
