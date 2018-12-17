<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div align="center"><h3> You can check your Notification status following the table below </h3><br></div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
<table class="table table-striped">
		<tr>
			<th scope="col">Message</th>

		</tr>
			<tr>
				<td><c:out value="${salaryNotice}"></c:out></td>
				

			</tr>
			<tr>
				<td><c:out value="${leaveMessage}"></c:out></td>
				

			</tr>
			
			<tr>
				<td><c:out value="${loanMessage}"></c:out></td>
				

			</tr>
			<tr>
				<td><c:out value="${isMenagerSalaryPermissionGiven}"></c:out></td>
				

			</tr>
			<tr>
				<td><c:out value="${isMenagerBonusPermissionGiven}"></c:out></td>
				

			</tr>
	</table>
	
	-->
<div class="container">
<table class="table">
    <thead>
      
    </thead>
    <tbody>
      <tr>
        <td>Salary Status</td>
        <td><c:out value="${salaryNotice}"></c:out></td>
       
      </tr>      
      <tr class="success">
        <td>Leave Status</td>
        <td><c:out value="${leaveMessage}"></c:out></td>
       
      </tr>
      <tr class="danger">
        <td>Loan Status</td>
        <td><c:out value="${loanMessage}"></c:out></td>
     
      </tr>
      <!-- 
      <tr class="info">
        <td>Salary Permission Status</td>
        <td><c:out value="${isMenagerSalaryPermissionGiven}"></c:out></td>
  
      </tr> -->
      <tr class="info">
        <td>Bonus Status</td>
        <td><c:out value="${isMenagerBonusPermissionGiven}"></c:out></td>
  
      </tr>
      
    </tbody>
  </table>	
</div>	
	