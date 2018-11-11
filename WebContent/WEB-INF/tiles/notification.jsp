<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="form-horizontal" method="post"
	action="${pageContext.request.contextPath}/sendMessage">
	<div class="col-md-offset-1 col-md-10">
		<div class="page-header">
			<h1>
				Spring MVC RabbitMQ Sample <br />
				<small> a sample application demonstrating how Spring MVC
					can be used to produce and consume messages using RabbitMQ</small>
			</h1>
		</div>
		<div class="form-group">
			<label>Enter message</label>
			<textarea class="form-control input-sm" cols="3" rows="5"
				id="message" placeholder="Enter your message"></textarea>
		</div>
		<div class="form-group">
			<button class="btn btn-sm btn-primary" id="sendMessage">Send
				message</button>
		</div>
	</div>
</form>