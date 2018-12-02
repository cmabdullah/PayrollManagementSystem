<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="post"
	action="${pageContext.request.contextPath}/permission_for_pay_salary_accept_by_admin">
	
	<input type="radio" name="bonus" value="give_bonus">Bonus<br>
	
	
	<button id="submit" name="submit" class="btn btn-primary">permission_for_pay_salary</button>

</form>
