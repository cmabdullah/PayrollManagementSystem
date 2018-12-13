<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container">
		<form:form method="post" action="${pageContext.request.contextPath}/usersinfo_profile_update_process"
		 commandName="userinfoUpdateableData">
			<form:hidden path="id" />
			<fieldset class="form-group">
				<form:label path="fullname">Fullname</form:label>
				<form:input name="fullname" path="fullname" type="text" class="form-control"
					required="required" />
				<form:errors path="fullname" cssClass="text-warning" />
			</fieldset>

			<fieldset class="form-group">
				<form:label path="address">Address</form:label>
				<form:input path="address" type="text" class="form-control"
					required="required" />
				<form:errors path="address" cssClass="text-warning" />
			</fieldset>

			<fieldset class="form-group">
				<form:label path="password">Password</form:label>
				<form:input path="password" type="password" class="form-control"
					required="required" />
				<form:errors path="password" cssClass="text-warning" />
			</fieldset>

			<fieldset class="form-group">
				<form:label path="confirmpassword">Confirm Password</form:label>
				<form:input path="confirmpassword" type="password" class="form-control"
					required="required" />
				<form:errors path="confirmpassword" cssClass="text-warning" />
			</fieldset>
			

			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
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
