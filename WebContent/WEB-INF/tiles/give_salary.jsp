<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="${pageContext.request.contextPath}/prcess_salary">




<c:if test="${isBonusPermissionGiven == true}">

<input type="radio" name="bonus" value="give_bonus">Bonus<br> 

</c:if>



<c:if test="${isSalaryPermissionGiven == true}">

<button id="submit" name="submit" class="btn btn-primary">Pay Salary</button> 

</c:if>



</form>

