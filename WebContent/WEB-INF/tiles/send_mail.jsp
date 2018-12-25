<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">you can send email</h3>
                    </div>
                    <div class="panel-body">
                    <sf:form method="get" role="form" action="${pageContext.request.contextPath}/process_send_mail" commandName="email">
                        
                            <fieldset>
                            
                                <div class="form-group">
                                   <sf:input id="email" path="email" name="email" type="text" placeholder="Enter recepent email" class="form-control input-md" />
                                   <!-- Show error message into view  -->
									<sf:errors path="email" cssClass="alert-danger"></sf:errors>
                                </div>
                                
                                <!-- <sf:textarea class="form-control" path="text" id="text" name="text" ></sf:textarea> 
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                                -->
                                <div class="form-group">
                                   <sf:textarea path="text" id="text" name="text"  class="form-control input-md" ></sf:textarea>
                                   <!-- Show error message into view  -->
									<sf:errors path="text" cssClass="alert-danger"></sf:errors>
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
