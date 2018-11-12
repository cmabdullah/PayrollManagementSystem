<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>notification</h1>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-striped">
		<tr>
			<th scope="col">Message</th>

		</tr>
			<tr>
				<td><c:out value="${leaveMessage}"></c:out></td>
				

			</tr>
	</table>