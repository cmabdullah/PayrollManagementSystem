<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<!-- Header file in -->
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="<c:url value='/'/>">Home
			</a></li>
			
			<li class="nav-item"><a class="nav-link " href="<c:url value='/usersinfo'/>">Users</a>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/registration'/>">Registration</a> </li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/disable_enable_user'/>">Enable</a> </li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/attendance'/>">Attendance</a> </li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
				<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/leavereq'/>">Leave</a> </li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/ad_leave'/>">ADLeave</a> </li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_EMPLOYEE')">
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/loanreq'/>">Loan_Req</a> </li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/ad_loan'/>">Loan_Req_Process</a> </li>
			</sec:authorize>
			<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/notification'/>">notification</a> </li>
			
			
		</ul>
		

		
		
		<ul class = "nav navbar-nav navbar-right">	
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item">  <a   class="nav-link"  href="<c:url value='/login'/>">Log In</a> </li>
			</sec:authorize>					
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"> <a  class="nav-link"  href="<c:url value='/j_spring_security_logout'/>">Log Out</a> </li>
			</sec:authorize>
		</ul>
	</div>
</nav>

<!--</br>-->
</br>