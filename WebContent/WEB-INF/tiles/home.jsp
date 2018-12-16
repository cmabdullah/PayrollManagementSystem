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

<!-- 
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

 -->
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



<!-- 


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

 -->


<%
List<AttendanceVisualizer>  attendanceVisualizer =  (List<AttendanceVisualizer>)request.getAttribute("attendanceVisualizer");
%>
<%-- <% out.println("This is comments example"+attendanceVisualizer.size()+ " \n"); %> --%>

<%


int[] year = new int[1000];
int[] month = new int[1000];
int[] day = new int[1000];
int[] renge = new int[1000];

for(int i = 0 ; i< attendanceVisualizer.size() ; i++){
	
	//out.println("This is comments example un bboxed data "+attendanceVisualizer.get(i).getLocalDate().substring(5, 7)+ " \n");
	year[i] = Integer.parseInt(attendanceVisualizer.get(i).getLocalDate().substring(0, 4));
	month[i] = Integer.parseInt(attendanceVisualizer.get(i).getLocalDate().substring(5, 7));
	day[i] = Integer.parseInt(attendanceVisualizer.get(i).getLocalDate().substring(8, 10));
	renge[i] = attendanceVisualizer.get(i).getTotalDays();
	//out.println("This is comments example boxed data "+month[i]+ " \n");
}
// out.println("year length"+year.length+ " \n");
// out.println("month length"+month.length+ " \n");
// out.println("day length"+day.length+ " \n");
// out.println("renge length"+renge.length+ " \n");

// for(int i = 0 ; i <20 ; i++){
// 	out.println("This is comments example"+month[i]+ " \n");
// }

%>

<img src="static/images/Payroll-Management.jpg" width="100%"
	height="100%">

<script type = "text/javascript">

console.log("hello world");


<%if(year != null) { %>
var yyyy = new Array(<%
for(int i = 0; i < year.length; i++) {
  out.print("\""+year[i]+"\"");
  if(i+1 < year.length) {
    out.print(",");
  }
}
%>);
<% } %>
console.log(yyyy.length);
// for(count = 0; count < 10; count++){
//     console.log(yyyy[count]);
//  }

<%if(month != null) { %>
var mm = new Array(<%
for(int i = 0; i < month.length; i++) {
  out.print("\""+month[i]+"\"");
  if(i+1 < month.length) {
    out.print(",");
  }
}
%>);
<% } %>
console.log(mm.length);

<%if(day != null) { %>
var dd = new Array(<%
for(int i = 0; i < day.length; i++) {
  out.print("\""+day[i]+"\"");
  if(i+1 < day.length) {
    out.print(",");
  }
}
%>);
<% } %>
console.log(dd.length);


<%if(renge != null) { %>
var rr = new Array(<%
for(int i = 0; i < renge.length; i++) {
  out.print("\""+renge[i]+"\"");
  if(i+1 < renge.length) {
    out.print(",");
  }
}
%>);
<% } %>
console.log(rr.length);




var d = ${intValue};
var m = ${myMonth};
var y = ${myYear};
var flag = 17;
var fruits = [ 2018, 2018, 2018 ];
         google.charts.load('current', {packages: ['corechart','calendar']});     
      
         function drawChart() {
            // Define the chart to be drawn.
            
            
            
            var data = new google.visualization.DataTable();
            data.addColumn({ type: 'date', id: 'Date' });
            data.addColumn({ type: 'number', id: 'Students' });
            data.addRows([
            	
            	
            	
               [ new Date(2018, mm[0], dd[0]), <%out.println(renge[0]);%>],
               [ new Date(2018, mm[1], dd[1]), <%out.println(renge[1]);%>],
               [ new Date(2018, mm[2], dd[2]), <%out.println(renge[2]);%>],
               [ new Date(2018, mm[3], dd[3]), <%out.println(renge[3]);%>],
               [ new Date(2018, mm[4], dd[4]), <%out.println(renge[4]);%>],
               [ new Date(2018, mm[5], dd[5]), <%out.println(renge[5]);%>],
               [ new Date(2018, mm[6], dd[6]), <%out.println(renge[6]);%>],
               
               
               
               
               
            ]);
            
            // Set chart options
            var options = {'title':'Attendence', 'width':800 };

            // Instantiate and draw the chart.
            var chart = new google.visualization.Calendar(document.getElementById('container'));
            chart.draw(data, options);
         }
         google.charts.setOnLoadCallback(drawChart);
      </script>


