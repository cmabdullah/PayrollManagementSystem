<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Sql prefix added-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!-- you must have the security taglib declared in your JSP -->
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@ page import = "com.abdullah.PayrollManagementSystem.dao.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c-rt" %>
<div class="container-fluid">


	<div class="row">
		<div class="col-sm-6" style="background-color: lavender;">
			<div class="col-xs-6">
				<i class="fa fa-bell-o fa-5x" style="color: green"></i>
			</div>
			<div class="col-xs-9 text-right">
				<h1>26</h1>
				<div>New Leave Request</div>
			</div>
			<a href="#"> <span class="pull-left">View Details</span> <span
				class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			</a>
		</div>
		<div class="col-sm-6" style="background-color: lavenderblush;">
			<div class="col-xs-6">
				<i class="fa fa-credit-card-alt fa-5x" style="color: red"></i>
			</div>
			<div class="col-xs-9 text-right">
				<h1>26</h1>
				<div>New Loan Request</div>
			</div>
			<a href="#"> <span class="pull-left">View Details</span> <span
				class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
			</a>
		</div>
	</div>
</div>


<c:if test="${attendanceVisualizer != null}">
	<h1>show data</h1>
</c:if>

<c:if test="${attendanceVisualizer != null}">
<table class="table table-striped">
		<tr>
			<th scope="col">Date</th>
			<th scope="col">unit</th>


		</tr>
		<c:forEach var="attendanceVisualizerData" items="${attendanceVisualizer}">
			<tr>
				<td><c:out value="${attendanceVisualizerData.localDate}"></c:out></td>
				<td><c:out value="${attendanceVisualizerData.totalDays}"></c:out></td>

			</tr>
		</c:forEach>
	</table>

</c:if>


<div id="container" style="width: 550px;  margin: 0 auto">
</div>

<!-- 
myTime
<c:out value='${y}'/>
<c:if test="${myTime != null}">
	<h1><c:out value='${myTime}'/></h1>
</c:if>


<c:set var="year" value="${myTime}"/>
<h1><c:out value='${fn:substring(year, 0, 4)}'/></h1>

<c:set var="month" value="${myTime}"/>
<h1><c:out value='${fn:substring(year, 5, 7)}'/></h1>

<c:set var="day" value="${myTime}"/>
<h1><c:out value='${fn:substring(year, 8, 10)}'/></h1>


day
<fmt:parseNumber var="intValue" value="${fn:substring(year, 8, 10)}" integerOnly="true"/>
my int day 
<h1><c:out value='${intValue}'/></h1>



month
<fmt:parseNumber var="myMonth" value="${fn:substring(year, 5, 7)}" integerOnly="true"/>
my int month 
<h1><c:out value='${myMonth}'/></h1>

year
<fmt:parseNumber var="myYear" value="${fn:substring(year, 0, 4)}" integerOnly="true"/>
my int month 
<h1><c:out value='${myYear}'/></h1>

 -->




<%
        int accounts[];
        accounts = new int[1000];
       
%>

<c:if test="${attendanceVisualizer != null}">
	<table class="table table-striped">
		<tr>
			<th scope="col">Date</th>
		</tr>
		
		<%! int count =0; %>
		<c:forEach var="attendanceVisualizerData"
			items="${attendanceVisualizer}">
			<tr>
				<td><c:out value="${attendanceVisualizerData.localDate}"></c:out>
					<c:out value="${attendanceVisualizerData.localDate}"></c:out>
					<c:set var="localDate" value="${attendanceVisualizerData.localDate}" />
					 Year <fmt:parseNumber var="myY" value="${fn:substring(year, 0, 4)}" integerOnly="true" /> 
					 my int month <h1>  <c:out value='${myY}' /> </h1> 
					 
					 <%accounts[0] = 0; %>
					 

					 month <fmt:parseNumber var="myM" value="${fn:substring(localDate, 5, 7)}" integerOnly="true" /> 
					 my int month <h1>  <c:out value='${myM}' /> </h1> 
					 month <fmt:parseNumber var="myD" value="${fn:substring(year, 8, 10)}" integerOnly="true" /> 
					 my int month <h1>  <c:out value='${myD}' /> </h1> 	
					 
					 renge <c:set var="renge" value="${attendanceVisualizerData.totalDays}"/>
					 
					 my int renge <h1>  <c:out value='${renge}' /> </h1> 	
			</tr>
		</c:forEach>
	</table>
</c:if>




<%
List<AttendanceVisualizer>  attendanceVisualizer =  (List<AttendanceVisualizer>)request.getAttribute("attendanceVisualizer");
%>



<%!
  String[] names = { "A","B", "C", "D" };
  int[]    ages  = { 29, 8, 6, 5};
%>

<c-rt:forEach var="person" items="<%= names %>">
        <TR>
          <TD><c:out value="${person}"  /></TD>
          <TD><c:out value="${ages[i]}" /></TD>
        </TR>
      </c-rt:forEach>



<%
  int[] names= {20, 30, 40, 50};
  pageContext.setAttribute("names", names);
%>
  
<c:set var="msg" value="The names are:"/>
<c:forEach var="name" items="${names}" varStatus="status">
  <c:set var="msg">
    <c:out value="${msg} ${name}"/>
    <c:if test="${not status.last}">,</c:if>
  </c:set>
</c:forEach>
<c:out value="${msg}"/>




<img src="static/images/Payroll-Management.jpg" width="100%"
	height="100%">





<script type = "text/javascript">


         google.charts.load('current', {packages: ['corechart','calendar']});     
      
         function drawChart() {
            // Define the chart to be drawn.
            
            var d = ${intValue};
            var m = ${myMonth};
            var y = ${myYear};
            
            var data = new google.visualization.DataTable();
            data.addColumn({ type: 'date', id: 'Date' });
            data.addColumn({ type: 'number', id: 'Students' });
            data.addRows([
               [ new Date(2018, 3, 13), 50 ],
               [ new Date(2018, 3, 14), 50 ],
               [ new Date(2018, 3, 15), 49 ],
               [ new Date(y, m, d), <c:out value='${y}'/> ],
            ]);
            
            // Set chart options
            var options = {'title':'Attendence', 'width':800};

            // Instantiate and draw the chart.
            var chart = new google.visualization.Calendar(document.getElementById('container'));
            chart.draw(data, options);
         }
         google.charts.setOnLoadCallback(drawChart);
      </script>


