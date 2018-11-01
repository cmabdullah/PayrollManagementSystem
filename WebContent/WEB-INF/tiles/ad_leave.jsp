<h1>FFFF</h1>

<form
	action="${pageContext.request.contextPath}/ad_leave_req_process"
	method="post">


	<!-- Form Name -->
	<h1>Permit leave request</h1>

	<input id="userinfo_id" name="userinfo_id"  placeholder="Enter your user id" type="text" required><br><br>
	
	<input type="date" name="entryfromString"><br> 
	
	<br>
	
	<input type="date" name="entrytoString"><br> 
	
	<br>

	<br> <label class="control-label" for="submit"></label>
	<button id="submit" name="submit" type="submit">Submit</button>

</form>