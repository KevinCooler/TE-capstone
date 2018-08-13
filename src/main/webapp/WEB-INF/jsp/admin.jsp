<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="addCoachURL" value="/addCoach"/>

<div class="container">
	<div class="row">
		<div class="col-sm-7">
			<h1>Manage Coaches</h1>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<h1>Add New Coach</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-7">
			<table class="table">
				<thead>
					<tr>
						<th colspan="3">Coaches</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Name</td>
						<td>
							<a class="btn btn-primary" href="${addCoachURL}">Update</a>
						</td>
						<td>
							<a class="btn btn-danger" href="${addCoachURL}">Delete</a>
						</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>Update</td>
						<td>Delete</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>Update</td>
						<td>Delete</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>Update</td>
						<td>Delete</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-4">
			<c:url var="formAction" value="/addCoach" />
			<form method="POST" action="${formAction}">
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
				<div class="row">
					<div class="form-group">
						<label for="firstName">First Name: </label>
						<input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
					</div>
					<div class="form-group">
						<label for="lastName">Last Name: </label>
						<input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
					</div>
					<div class="form-group">
						<label for="password">Password: </label>
						<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
					</div>
					<div class="form-group">
						<label for="confirmPassword">Confirm Password: </label>
						<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />	
					</div>
					<button type="submit" class="btn btn-default">Create Coach</button>
				</div>
			</form>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />