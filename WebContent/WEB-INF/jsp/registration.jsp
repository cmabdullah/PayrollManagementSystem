<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
 <div class="col-md-6 col-md-offset-3">
<sf:form class="form-horizontal"  method="post" action="${pageContext.request.contextPath}/docreate" commandName="userinfo">
<!-- commandName="userinfo" define autowire beans -->
<fieldset>

<!-- Form Name -->
<legend>Registration form</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">User Name  :</label>  
  <div class="col-md-4">
  <!-- Path must be equal to input name , this is the attribute of sf tag-->
  <sf:input id="username" path="username" name="username" type="text" placeholder="Enter your username" class="form-control input-md"/>
   <!-- Show error message into view -->
    <sf:errors path="username" cssClass="alert-danger"></sf:errors> 
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Password  :</label>  
  <div class="col-md-4">
  <sf:input id="password" path="password" name="password" type="text" placeholder="Enter your Password" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="password" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">User Type  :</label>  
  <div class="col-md-4">
  <sf:input id="usertype" path="usertype" name="usertype" type="text" placeholder="Enter your user type" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="usertype" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Status  :</label>  
  <div class="col-md-4">
  <sf:input id="status" path="status" name="status" type="text" placeholder="your status" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="status" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Full Name  :</label>  
  <div class="col-md-4">
  <sf:input id="fullname" path="fullname" name="fullname" type="text" placeholder="Enter your Full name" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="fullname" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Address  :</label>  
  <div class="col-md-4">
  <sf:input id="address" path="address" name="address" type="text" placeholder="Enter your address" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="address" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Eemail  :</label>  
  <div class="col-md-4">
  <sf:input id="email" path="email" name="email" type="text" placeholder="Enter your email" class="form-control input-md"/>
    <!-- Show error message into view -->
    <sf:errors path="email" cssClass="alert-danger"></sf:errors>
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Phone  :</label>  
  <div class="col-md-4">
  <sf:input id="phone" path="phone" name="phone" type="text" placeholder="Enter your phone" class="form-control input-md"/>
  <!-- Show error message into view -->
    <sf:errors path="phone" cssClass="alert-danger"></sf:errors>  
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="submit"></label>
  <div class="col-md-4">
    <button id="submit" name="submit" class="btn btn-primary">Create Notice</button>
  </div>
</div>

</fieldset>
</sf:form>
</div>


</body>
</html>