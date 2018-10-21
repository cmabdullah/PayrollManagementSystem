<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
</head>
<body onload='document.f.j_username.focus();'>

	<div class="col-md-6 col-md-offset-3">
		<form class="form-horizontal"
			action="${pageContext.request.contextPath}/j_spring_security_check"
			method="post">
			<fieldset>

				<!-- Form Name -->
				<legend>Login</legend>

				<!-- Text input-->
				<div class="control-group">
					<label class="control-label" for="j_username">Username</label>
					<div class="controls">
						<input id="j_username" name="j_username" placeholder=""
							class="input-xlarge" type="text">

					</div>
				</div>

				<!-- Password input-->
				<div class="control-group">
					<label class="control-label" for="j_password">Password</label>
					<div class="controls">
						<input id="j_password" name="j_password" placeholder=""
							class="input-xlarge" type="password">

					</div>
				</div>
				
				
				<!-- Checkbox input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="j_password">Remember me:</label>
						<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" checked="checked">
				</div>	

				<!-- Button -->
				<div class="control-group">
					<label class="control-label" for="submit"></label>
					<div class="controls">
						<button id="submit" name="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>

			</fieldset>
		</form>

	</div>
	
	<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>

</body>
</html>