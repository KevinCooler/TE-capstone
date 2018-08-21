<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("#changePassword").validate({
			
			rules : {
				oldPassword : {
					required : true
				},
				newPassword : {
					required : true,
					minLength : 8,
					capitals : true
				},
				confirmNewPassword : {
					required : true,		
					equalTo : "#newPassword"  
				}
			},
			messages : {			
				newPassword : {
				//	required : "password is required"
					minLength : "password must be at least 8 charaters",
					capitals : "password must contain at least one capital"
				},
				confirmNewPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<c:url var="formAction" value="/changePassword" />
<form id="changePassword" method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>Change Password</h2>
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