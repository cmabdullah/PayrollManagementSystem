<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<div class="container">
	<sf:form method="get" action="${pageContext.request.contextPath}/DEU"
		commandName="userinfo">

		<fieldset>



			<!-- Text input-->
			<div class="form-group">
				<label class="col-xs-12 control-label" for="name">username :</label>
				<div class="col-xs-12">
					<sf:input id="username" path="username" name="username" type="text"
						placeholder="Enter your username" class="form-control input-md" />
					<!-- Show error message into view  -->
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</div>
			</div>


			<!-- Material inline 1 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="enabled" value="true" checked> <label
					class="form-check-label" for="materialInline1">Enable</label>
			</div>

			<!-- Material inline 2 -->
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="enabled" value="false"> <label
					class="form-check-label" for="materialInline2">Disable</label>
			</div>
			<input name="create query" type="submit" class="btn btn-primary btn-lg"/>
		</fieldset>



		<!-- Show error message into view  
			<table>
				<tr>
					<td>username :</td>
					<td><sf:input name="username" type="text" path="username" /></td>
					
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</tr>

				<tr>
					<td><input type="radio" name="enabled" value="true" checked>Enable<br>
						<input type="radio" name="enabled" value="false">Disable<br>
					<td>&nbsp;</td>
					<td><input name="create query" type="submit" /></td>
			</table>
			-->
	</sf:form>
</div>
