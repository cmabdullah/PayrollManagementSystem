<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sf:form method="get" action="${pageContext.request.contextPath}/DEU" commandName="userinfo">
	<table>
		<tr>
			<td>username :</td>
			<td><sf:input name="username" type="text" path="username"/></td>
			<!-- Show error message into view  -->
			<sf:errors path="username" cssClass="alert-danger" ></sf:errors>
		</tr>

		<tr>
			<td><input type="radio" name="enabled" value="true" checked>Enable<br>
				<input type="radio" name="enabled" value="false">Disable<br>
			<td>&nbsp;</td>
			<td><input name="create query" type="submit" /></td>
	</table>


</sf:form>
