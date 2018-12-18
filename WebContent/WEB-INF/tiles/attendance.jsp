<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
<form method="post" action="${pageContext.request.contextPath}/start"   commandName="userinfo">

<c:if test="${hasLogin == false && hasLogout == false}">
<button id="submit" name="submit" class="btn btn-primary">start</button> 
</c:if>

<c:if test="${hasLogin == true && hasLogout == false}">

<button id="away" name="away" class="btn btn-lg btn-success btn-block">Away</button> 

</c:if>

</form>
 -->
<div class="container">


       

        



<div align="center"><h3> Your Last One Week Login Activity </h3><br></div>
<c:if test="${singleUserSevenDaysAttendanceLog != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">Login Time</th>
			<th scope="col">Working Hours</th>
		</tr>
		<c:forEach var="singleUserOneDaysSplitData" items="${singleUserSevenDaysAttendanceLog}">
			<tr>
				<td><c:out value="${singleUserOneDaysSplitData.logintime}"></c:out></td>
				<td><c:out value="${singleUserOneDaysSplitData.workinghours}"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</c:if>

<br>
 <div class="row">
            <div class="col-md-12 col-md-offset-12">
                    <div  align="center">
                        <h3 class="panel-title" >Please Give Your Attendance</h3>
                    </div>
                    <div class="panel-body">
                        <form method="post" role="form" action="${pageContext.request.contextPath}/start">
                            <fieldset>
                            <c:if test="${hasLogin == false && hasLogout == false}">
								<button id="submit" name="submit" class="btn btn-lg btn-success btn-block">Check In</button> 
								</c:if>
								<c:if test="${hasLogin == true && hasLogout == false}">
								<button id="away" name="away" class="btn btn-lg btn-success btn-block">Check Out</button> 
								</c:if>
                            </fieldset>
                        </form>
                    </div>
            </div>
        </div>
        
        <div>&nbsp</div>
        
  
</div>







