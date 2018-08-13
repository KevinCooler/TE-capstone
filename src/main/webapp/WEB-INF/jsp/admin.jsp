<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="addCoachURL" value="/addCoach"/>

<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<h1>Manage Coaches</h1>
		</div>
		<div class="col-sm-2"></div>
	</div>
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<a class="btn btn-primary" href="${addCoachURL}">Add New Coach</a>
		</div>
		<div class="col-sm-2"></div>
	</div>
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
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
							<a class="btn btn-info" href="${addCoachURL}">Update</a>
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
		<div class="col-sm-2"></div>
	</div>
</div>


<c:import url="/WEB-INF/jsp/footer.jsp" />