<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel='stylesheet' type="text/css" />
</head>
<body>
	<!-- post মেথড হলে url এ parameter দেখা যাবে না  -->
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
</body>
</html>