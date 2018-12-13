<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--JSTL prifix added-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
<table class="table table-striped">
		<tr>
			<th scope="col">Name</th>
			<th scope="col">Address</th>
			<th scope="col">Email</th>
			<th scope="col">Phone</th>
			<th scope="col">Your Loan</th>
			<th scope="col">Paid Amount</th>
			<th scope="col">Update</th>
		</tr>
		<c:if test="${usersinfo != null}">
		
		<tr>
				<td><c:out value="${usersinfo.fullname}"></c:out></td>
				<td><c:out value="${usersinfo.address}"></c:out></td>
				<td><c:out value="${usersinfo.email}"></c:out></td>
				<td><c:out value="${usersinfo.phone}"></c:out></td>
				<td><c:out value="${runningLoanInformations.amount}"></c:out></td>
				<td><c:out value="${runningLoanInformations.paidamount}"></c:out></td>
				<td><a  href="<c:url value='/usersinfo_profile_update/${usersinfo.id}'/>">Update</a></td>

			</tr>
			</c:if>

	</table>
	<div align="center"><h1>Loan Status in Details</h1></div>
	
	</div>
	
	
	<div id="piechart" align="center"></div>
	
	
	

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Hours per Day'],
  ['Total Amount', <c:out value='${runningLoanInformations.amount}'/>],
  ['Paid Amount', <c:out value='${runningLoanInformations.paidamount}'/>]
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Your Loan Status', 'width':950, 'height':600};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>