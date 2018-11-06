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
	<sf:form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/loanreq_process" commandName="loan"  >

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Loan Reason :</label>
				<div class="col-md-4">
					<textarea class="form-control"  id="reason" name="reason"></textarea>
					
				</div>
			</div>
			
			
			
			<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">amount
						:</label>
					<div class="col-md-4">
						<sf:input id="amountValidation"   path="amountValidation" name="amountValidation"
							type="text" placeholder="Enter loan amount"
							class="form-control input-md" />
							
							<!-- Show error message into view -->
						<sf:errors path="amountValidation" cssClass="alert-danger"></sf:errors>

					</div>
				</div>
			
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit request</button>
				</div>
			</div>

	</sf:form>
</div>
