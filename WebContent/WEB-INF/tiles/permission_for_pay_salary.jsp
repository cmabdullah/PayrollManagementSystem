<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
<form method="post"
	action="${pageContext.request.contextPath}/permission_for_pay_salary_accept_by_admin">
	
	<input type="radio" name="bonus" value="give_bonus">Bonus<br>
	
	
	<button id="submit" name="submit" class="btn btn-primary">permission_for_pay_salary</button>

</form>
 -->


    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading" align="center">
                        <h3 class="panel-title">Salary Payment Panel</h3>
                    </div>
                    <div class="panel-body">
                        
                        <form method="post" role="form"
	action="${pageContext.request.contextPath}/permission_for_pay_salary_accept_by_admin">
                            <fieldset>
                                
                                
                                <div class="form-group">
                                            <label>There are two bonus can get an employee each year, do you wanted to pay salary with bonus.&nbsp; &nbsp;</label>
                                            <label class="radio-inline">
                                                <input type="radio" name="bonus" id="optionsRadiosInline1" value="give_bonus"> &nbsp; &nbsp; Bonus
                                            </label>
                                            
                                        </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button id="submit" name="submit" class="btn btn-lg btn-success btn-block">Give Permission</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>