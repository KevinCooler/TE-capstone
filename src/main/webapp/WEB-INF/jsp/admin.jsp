<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-7">
		<h1>Manage Coaches</h1>
		<table class="table">
			<tbody>
				<c:forEach var="coach" items="${coaches}">
					<c:url var="editCoachURL" value="/editCoach">
						<c:param name="coachId" value="${coach.id}"/>
					</c:url>
					<c:url var="deleteCoachURL" value="/deleteCoach">
						<c:param name="coachId" value="${coach.id}"/>
					</c:url>
					<c:url var="coachURL" value="/coach">
						<c:param name="coachId" value="${coach.id}"/>
					</c:url>
					<tr>
						<td><c:out value="${coach.firstName} ${coach.lastName}"/></td>
						<td>
							<a href="${coachURL}" class="btn btn-primary">View</a>
						</td>
						<td>
							<a href="${editCoachURL}" class="btn btn-success">Edit</a>
						</td>
						<td>
							<a href="${deleteCoachURL}" class="btn btn-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-4">
		<h1>Add New Coach</h1>
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

<c:import url="/WEB-INF/jsp/footer.jsp" />