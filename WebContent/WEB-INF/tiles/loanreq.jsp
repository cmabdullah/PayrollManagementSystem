<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${isPandingLoanRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>

<div class="col-xs-12 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/loanreq_process" >

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-xs-12 control-label" for="text">Loan Reason :</label>
				<div class="col-xs-12">
					<textarea class="form-control"  id="reason" name="reason"></textarea>
					
				</div>
			</div>
			
			
			
			<!-- Text input
				<div class="form-group">
					
					<div class="col-md-4">
						
							<input type="radio" name="loanType" value="give_medical">Medical<br>
							<input type="radio" name="loanType" value="give_study">Study<br>
							<input type="radio" name="loanType" value="give_business">Business<br>
							
							
					</div>
				</div>
				-->
				
				<!-- Material inline 1 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="loanType" value="give_medical" checked> <label
					class="form-check-label" for="materialInline1">Medical</label>
			</div>

			<!-- Material inline 2 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="loanType" value="give_study"> <label
					class="form-check-label" for="materialInline2">Study</label>
			</div>
			<!-- Material inline 2 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="loanType" value="give_business"> <label
					class="form-check-label" for="materialInline2">Business</label>
			</div>
			
			<input name="create query" type="submit" class="btn btn-primary btn-lg"/>
			
			
			<!-- Button 
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit request</button>
				</div>
			</div>-->

	</form>
</div>



