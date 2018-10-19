<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--Load bootstrap library-->
<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css" rel='stylesheet' type="text/css" />
</head>
<body>
 <div class="col-md-6 col-md-offset-3">
<form class="form-horizontal"  method="post" action="${pageContext.request.contextPath}/docreate">
<fieldset>

<!-- Form Name -->
<legend>Registration form</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">User Name  :</label>  
  <div class="col-md-4">
  <input id="username" name="username" type="text" placeholder="Enter your username" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Password  :</label>  
  <div class="col-md-4">
  <input id="password" name="password" type="text" placeholder="Enter your Password" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">User Type  :</label>  
  <div class="col-md-4">
  <input id="usertype" name="usertype" type="text" placeholder="Enter your user type" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Status  :</label>  
  <div class="col-md-4">
  <input id="status" name="status" type="text" placeholder="your status" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Full Name  :</label>  
  <div class="col-md-4">
  <input id="fullname" name="fullname" type="text" placeholder="Enter your Full name" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Address  :</label>  
  <div class="col-md-4">
  <input id="address" name="address" type="text" placeholder="Enter your address" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Eemail  :</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="Enter your email" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name">Phone  :</label>  
  <div class="col-md-4">
  <input id="phone" name="phone" type="text" placeholder="Enter your phone" class="form-control input-md">
    
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
</form>
</div>


</body>
</html>