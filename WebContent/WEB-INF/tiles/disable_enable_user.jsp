<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Spring form taglib-->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!--fmt taglib added-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 
<div class="container">
	<sf:form method="get" action="${pageContext.request.contextPath}/DEU"
		commandName="userinfo">

		<fieldset>



			
			<div class="form-group">
				<label class="col-xs-12 control-label" for="name">username :</label>
				<div class="col-xs-12">
					<sf:input id="username" path="username" name="username" type="text" placeholder="Enter your username" class="form-control input-md" />
				
					<sf:errors path="username" cssClass="alert-danger"></sf:errors>
				</div>
			</div>


			
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline1"
					name="enabled" value="true" checked> <label
					class="form-check-label" for="materialInline1">Enable</label>
			</div>

			
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" id="materialInline2"
					name="enabled" value="false"> <label
					class="form-check-label" for="materialInline2">Disable</label>
			</div>
			
			
			<input name="create query" type="submit" class="btn btn-primary btn-lg"/>
		</fieldset>

	</sf:form>
</div>

 -->




    <div class="container">
        <div class="row">
            <div class="col-md-12 col-md-offset-12">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">You can enable or disable any user</h3>
                    </div>
                    <div class="panel-body">
                    <sf:form method="get" role="form" action="${pageContext.request.contextPath}/DEU" commandName="userinfo">
                        
                            <fieldset>
                            
                                <div class="form-group">
                                   <sf:input id="username" path="username" name="username" type="text" placeholder="Enter your username" class="form-control input-md" />
                                   <!-- Show error message into view  -->
									<sf:errors path="username" cssClass="alert-danger"></sf:errors>
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
                                
                                
                                <!-- Change this to a button or input when using this as a form -->
                                
                                <input name="create query" type="submit" class="btn btn-lg btn-success btn-block">
                                
                            </fieldset>
                        </sf:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
