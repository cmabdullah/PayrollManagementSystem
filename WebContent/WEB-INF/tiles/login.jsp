<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="aa">
		<form 
			action="${pageContext.request.contextPath}/j_spring_security_check"
			method="post">
		

				<!-- Form Name -->
				<h1>Log in</h1>
				
				<!-- <label  for="j_username">Username</label> -->
				<input id="j_username" name="j_username"  placeholder="Enter your username" type="text" required><br><br>

				<!-- <label  for="j_password">Password</label> -->
				<input id="j_password" name="j_password"  placeholder="Enter your password" type="password" required><br><br>
				
				<label  for="j_password">Remember me:</label>
				<input id="_spring_security_remember_me" name="_spring_security_remember_me"  type="checkbox" checked="checked"><br><br>
					
				<label class="control-label" for="submit"></label>
				<button id="submit" name="submit" type="submit">Submit</button>

		</form>

	</div>
	

	
	<!--Account create link added-->
		<p><a href="<c:url value='/newaccount'/>">create new Account</a> </p>
