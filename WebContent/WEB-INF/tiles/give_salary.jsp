<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!--
<form method="post"
	action="${pageContext.request.contextPath}/prcess_salary">



 
<c:if test="${isBonusPermissionGiven == true}">

<input type="radio" name="bonus" value="give_bonus">Bonus<br> 

</c:if>



<c:if test="${isSalaryPermissionGiven == true}">

<button id="submit" name="submit" class="btn btn-primary">Pay Salary</button> 

</c:if>



</form>
 -->


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="login-panel panel panel-default">
					<div class="panel-heading" align="center">
						<h3 class="panel-title">Salary Payment Panel</h3>
					</div>
					<div class="panel-body">

						<form method="post" role="form"
							action="${pageContext.request.contextPath}/prcess_salary">
							<fieldset>


								<div class="form-group">
									<label>There are two bonus can get an employee each
										year, do you wanted to pay salary with bonus.&nbsp; &nbsp;</label>
									<c:if test="${isBonusPermissionGiven == true}">
										<label class="radio-inline"> <input type="radio"
											name="bonus" id="optionsRadiosInline1" value="give_bonus">
											&nbsp; &nbsp; Bonus
										</label>
									</c:if>

								</div>
								<c:if test="${isSalaryPermissionGiven == true}">
									<button id="submit" name="submit"
										class="btn btn-lg btn-success btn-block">Give
										Permission</button>
								</c:if>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>