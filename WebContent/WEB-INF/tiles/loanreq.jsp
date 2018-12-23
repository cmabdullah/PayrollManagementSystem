<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${isPandingLoanRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>
<c:if test="${isRunningLoan == true}">
	<h1>You have already a running loan</h1>
</c:if>
<!-- 
<div class="col-xs-12 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/loanreq_process" >

			<div class="form-group">
				<label class="col-xs-12 control-label" for="text">Loan Reason :</label>
				<div class="col-xs-12">
					<textarea class="form-control"  id="reason" name="reason"></textarea>
					
				</div>
			</div>
			
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="loanType" value="give_medical" checked> <label
					class="form-check-label" for="materialInline1">Medical</label>
			</div>

	
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="loanType" value="give_study"> <label
					class="form-check-label" for="materialInline2">Study</label>
			</div>
		
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="loanType" value="give_business"> <label
					class="form-check-label" for="materialInline2">Business</label>
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
					<h4 class="panel-title">You can apply for Loan , You can get
						Business loan = salary*6 , Medical loan = salary*3, Study loan =
						salary*2</h4>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" method="post" role="form"
						action="${pageContext.request.contextPath}/loanreq_process">

						<fieldset>

							<div class="form-group">
								<textarea class="form-control" id="reason" name="reason"
									autofocus></textarea>
							</div>



							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline1" name="loanType" value="give_medical"
									checked> <label class="form-check-label"
									for="materialInline1">Medical</label>
							</div>

							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="loanType" value="give_study">
								<label class="form-check-label" for="materialInline2">Study</label>
							</div>
							<!-- Material inline 2 -->
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input"
									id="materialInline2" name="loanType" value="give_business">
								<label class="form-check-label" for="materialInline2">Business</label>
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

