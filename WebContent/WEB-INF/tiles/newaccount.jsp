<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

	<div class="col-md-6 col-md-offset-3">
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/createaccount"
			commandName="user">
			<fieldset>
				<!-- Form Name -->
				<legend>Create User</legend>
				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Username :
					</label>
					<div class="col-md-4">
						<!-- Path must be equal to input name -->
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter your username"
							class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="username" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!-- email input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email :</label>
					<div class="col-md-4">
						<sf:input id="email" name="email" path="email" type="text"
							placeholder="Enter your email" class="form-control input-md" />
						<!-- Show error message into view -->
						<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Password
						:</label>
					<div class="col-md-4">
						<sf:input id="password" name="password" path="password"
							type="text" class="form-control input-md" />
							<!-- Show error message into view -->
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
							
					</div>
				</div>
				<!--Conform Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Confirm
						Password :</label>
					<div class="col-md-4">
						<input id="confirmpassword" name="confirmpassword" type="text"
							class="form-control input-md" />
					</div>
				</div>
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Create
							User</button>
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
            password1.setCustomValidity("<fmt:message key='UnmatchedPasswords.user.password'/>");
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