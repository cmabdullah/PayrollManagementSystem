<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>leavereq</h1>



<c:if test="${isPandingRequest == true}">
	<h1>You have already a pending request</h1>
</c:if>

<c:if test="${isPandingRequest == false}">
<div class="col-md-6 col-md-offset-3">
	<form class="form-horizontal" method="post"
		action="${pageContext.request.contextPath}/leavereq_process">

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="text">Leave Request :</label>
				<div class="col-md-4">
					<textarea class="form-control"  id="reasone" name="reasone"></textarea>
					<!-- Show error message into view -->
					
				</div>
			</div>
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button id="submit" name="submit" class="btn btn-primary">Submit request</button>
				</div>
			</div>

	</form>
</div>

</c:if>
