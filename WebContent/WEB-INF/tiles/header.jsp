<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
		<!-- 
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
			 -->
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="nav-item"><a class="nav-link"
				href="<c:url value='/'/>">Home </a></li>

			<li class="nav-item"><a class="nav-link "
				href="<c:url value='/usersinfo'/>">Profile</a> <sec:authorize
					access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/registration'/>">Registration</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/disable_enable_user'/>">Enable</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/attendance'/>">Attendance</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/leavereq'/>">Leave</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/ad_leave'/>">Leave</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/loanreq'/>">Loan</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/ad_loan'/>">Loan</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ACCOUNTANT')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/give_salary'/>">Salary</a></li>
				</sec:authorize>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/notification'/>">Notification</a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/permission_for_pay_salary'/>">Salary</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/report'/>">Report</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_ACCOUNTANT')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/report'/>">Report</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/send_mail'/>">Email</a></li>
			</sec:authorize>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/login'/>">Log In</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/j_spring_security_logout'/>">Log Out</a></li>
			</sec:authorize>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>





<!-- old navigation bar
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/'/>">Home </a></li>

			<li class="nav-item"><a class="nav-link "
				href="<c:url value='/usersinfo'/>">Profile</a> <sec:authorize
					access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/registration'/>">Registration</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/disable_enable_user'/>">Enable</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/attendance'/>">Attendance</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/leavereq'/>">Leave</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/ad_leave'/>">Leave</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/loanreq'/>">Loan</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/ad_loan'/>">Loan</a></li>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_ACCOUNTANT')">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/give_salary'/>">Salary</a></li>
				</sec:authorize>
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/notification'/>">Notification</a></li>

			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/permission_for_pay_salary'/>">Salary</a></li>
			</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/report'/>">Report</a></li>
			</sec:authorize>

		</ul>

		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="!isAuthenticated()">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/login'/>">Log In</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value='/j_spring_security_logout'/>">Log Out</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>
 -->
<!--</br>-->
</br>