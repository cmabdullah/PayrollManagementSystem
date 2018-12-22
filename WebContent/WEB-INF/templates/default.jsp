<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- apache tiles taglib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<!-- Bootstrap core CSS -->
    <link href="<c:url value='/static/lib/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
    
    
    <!-- Custom styles for this template -->
    <link href="<c:url value='/static/css/notice-main.css'/>" rel="stylesheet">
    
    <link href="<c:url value='/static/css/customize.css'/>" rel="stylesheet">
    <link href="<c:url value='/static/css/datepicker.css'/>" rel="stylesheet">
   
   
    <link href="<c:url value='/static/css/font-awesome.min.css'/>" rel="stylesheet">
    
    <script type="text/javascript" src="<c:url value='/static/js/loader.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/static/js/jquery-3.3.1.slim.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/static/js/bootstrap-datepicker.js'/>"></script>
    
   
</head>
<body>

	<div>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<main role="main" class="container">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
    </main><!-- /.container -->
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>