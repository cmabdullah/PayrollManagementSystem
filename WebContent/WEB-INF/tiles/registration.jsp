<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

	<div class="col-md-6 col-md-offset-3">
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/docreate"
			commandName="userinfo">
			<!-- commandName="userinfo" define autowire beans -->
			<fieldset>

				<!-- Form Name -->
				<legend>Registration form</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Full Name
						:</label>
					<div class="col-md-4">
						<sf:input id="fullname" path="fullname" name="fullname"
							type="text" placeholder="Enter your Full name"
							class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="fullname" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Address :</label>
					<div class="col-md-4">
						<sf:input id="address" path="address" name="address" type="text"
							placeholder="Enter your address" class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="address" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Eemail :</label>
					<div class="col-md-4">
						<sf:input id="email" path="email" name="email" type="text"
							placeholder="Enter your email" class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Phone :</label>
					<div class="col-md-4">
						<sf:input id="phone" path="phone" name="phone" type="text"
							placeholder="Enter your phone" class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="phone" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">User Name
						:</label>
					<div class="col-md-4">
						<!-- Path must be equal to input name , this is the attribute of sf tag-->
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="username" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Password
						:</label>
					<div class="col-md-4">
						<sf:input id="password" name="password" path="password"
							type="password" class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>

					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="password"
							class="form-control input-md" />
					</div>
				</div>

				<label for="Authontication">Authontication</label> <br> <input
					type="radio" name="authority" value="ROLE_ADMIN" checked>ADMIN<br>
				<input type="radio" name="authority" value="ROLE_ACCOUNTANT">ACCOUNTANT<br>
				<input type="radio" name="authority" value="ROLE_EMPLOYEE">EMPLOYEE<br>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Create
							Notice</button>
					</div>
				</div>

			</fieldset>
		</sf:form>
	</div>


	<!--password pamming-->
	<script>
		var password1 = document.getElementById('password');
		var password2 = document.getElementById('confirmpassword');

		var checkPasswordValidity = function() {
			if (password1.value != password2.value) {
				password1
						.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
			} else {
				password1.setCustomValidity('');
			}
		};

		password1.addEventListener('change', checkPasswordValidity, false);
		password2.addEventListener('change', checkPasswordValidity, false);
		/***commandName="user"***/
		var form = document.getElementById('user');
		form.addEventListener('submit', function() {
			checkPasswordValidity();
			if (!this.checkValidity()) {
				event.preventDefault();
				//Implement you own means of displaying error messages to the user here.
				password1.focus();
			}
		}, false);
	</script>