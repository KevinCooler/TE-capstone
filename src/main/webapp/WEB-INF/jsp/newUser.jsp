<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("form").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true,
					minlength: 8,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, make it at least 8 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>
<div class="row text-center">
	<h2>Welcome to MHM</h2>
	<p>Please start by creating an account.</p>
	<p> Next, you will be able to customize your profile and contact potential coaches.</p>
</div>
<c:url var="formAction" value="/signUp" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="firstName">First Name: </label>
					<input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label for="lastName">Last Name: </label>
					<input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="userName">User Name: </label>
					<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="password">Password: </label>
					<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label for="confirmPassword">Confirm Password: </label>
					<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Create Account</button>
		</div>
		<div class="col-sm-3"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/footer.jsp" />