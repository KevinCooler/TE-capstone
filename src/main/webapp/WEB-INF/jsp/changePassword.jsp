<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
/* 	$(document).ready(function () {
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
					minlength: 15,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	}); */
</script>

<c:url var="formAction" value="/changePassword" />
<form method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>Change Password</h2>
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="oldPassword">Old Password: </label>
				<input type="password" id="oldPassword" name="oldPassword" placeHolder="Old Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="newPassword">New Password: </label>
				<input type="password" id="newPassword" name="newPassword" placeHolder="New Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmNewPassword">Confirm New Password: </label>
				<input type="password" id="confirmNewPassword" name="confirmNewPassword" placeHolder="Re-Type New Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-default">Change Password</button>
		</div>
		<div class="col-sm-4"></div>
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/footer.jsp" />