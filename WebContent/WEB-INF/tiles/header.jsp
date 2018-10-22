<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<a class="navbar-brand" href="#">Notices</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<!-- Header file in -->
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="<c:url value='/'/>">Home
			</a></li>
			
			<li class="nav-item"><a class="nav-link" href="<c:url value='/registration'/>">Registration</a></li>
			<li class="nav-item"><a class="nav-link " href="<c:url value='/usersinfo'/>">Show All users information</a>

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