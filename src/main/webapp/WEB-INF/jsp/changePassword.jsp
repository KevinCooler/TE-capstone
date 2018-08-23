<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


<c:url var="formAction" value="/changePassword" />

<form id="changePassword" method="POST" action="${formAction}">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	
	<div class="row">
	
		<div class="col-sm-4"></div>
		
		<div id="change-password-form" class="col-sm-4">
			<h2>Change Password</h2>
			<div class="form-group">
				<label for="oldPassword">Old Password: </label>
				<input type="password" id="oldPassword" name="oldPassword" placeHolder="Old Password" class="form-control" />
				<p class="error"><c:out value="${incorrectPassword}"/></p>
			</div>
			<div class="form-group">
				<label for="newPassword">New Password: </label>
				<input type="password" id="newPassword" name="newPassword" placeHolder="New Password" class="form-control" />
			</div>
			<div class="form-group">
				<label for="confirmNewPassword">Confirm New Password: </label>
				<input type="password" id="confirmNewPassword" name="confirmNewPassword" placeHolder="Re-Type New Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-primary">Change Password</button>
		</div>
		
		<div class="col-sm-4"></div>
		
	</div>
</form>
		
<c:import url="/WEB-INF/jsp/footer.jsp" />