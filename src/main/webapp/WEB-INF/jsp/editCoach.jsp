<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="contactURL" value="/contact">
	<c:param name="coachId" value="${coach.id}"></c:param>
</c:url>


<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h2 class="underline">Edit Coaching Profile</h2>
	</div>
	<div class="col-sm-2"></div>
</div>

<hr>

<!-- Profile Pic Upload -->
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-4 current-pic">
		<div>
			<c:url var="profilePicture" value="/image/coach${coach.id}"/>
			<c:url var="emptyProfilePicture" value="/img/empty_profile"/>
			<img class="coach-image hidden-xs img profilePicture" 
				id="profile-pic" src="${profilePicture}" alt="empty profile picture"/>
			<c:url var="picDeleteLink" value="/deleteProfilePic"/>
			<br>
			<b class="underline">Current Profile</b>
		</div>
		<div>
			<form method="POST" id="delete-pic" action ="${picDeleteLink}">
				<input type="hidden" name="coachId" value=<c:out value="${coach.id}"/>>
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
				<input type="submit" class="btn btn-primary" value="Delete Picture">
			</form>
		</div>
	</div>
	<div class="col-sm-4">
		<c:if test="${currentUser.id == coach.id}">
			<c:url var="picUploadLink" value="/uploadProfilePic">
				<c:param name="coachId" value="${coach.id}"/>
				<c:param name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			</c:url>
			<form method="POST" id="upload-pic" action="${picUploadLink}" 
				enctype="multipart/form-data">
				<h4>Upload Profile Picture - <br>
				(Size must be under 1MB)</h4>
				<input type="file" name="file" accept=".jpg, .gif, .png"><br>
				<input type="submit" class="btn btn-primary" value="Submit">
			</form>
			<span class="error">${errorMessage}</span>
		</c:if>
	</div>
	<div class="col-sm-2"></div>
</div>
<!-- Profile Pic Upload -->
	<br>
	<hr>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:url var="formAction" value="/editCoach" />
		<form method="POST" id="submit-edits" action="${formAction}">
		<input type="hidden" name="coachId" value=<c:out value="${coach.id}"/>>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="firstName">First Name: </label>
					<input type="text" id="firstName" name="firstName" 
						value=<c:out value="${coach.firstName}"/> class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label for="lastName">Last Name: </label>
					<input type="text" id="lastName" name="lastName" 
						value=<c:out value="${coach.lastName}"/> class="form-control" />
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="city">City: </label>
					<input type="text" id="city" name="city" 
						value=<c:out value="${coach.city}"/> class="form-control" />
				</div>
				<div class="form-group col-sm-6">
					<label for="state">State: </label>
					<input type="text" id="state" name="state" 
						value=<c:out value="${coach.state}"/> class="form-control" />	
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
					<label for="aboutMe">About Me: </label>
					<textarea class="form-control" id="aboutMe" name="aboutMe" rows="5"><c:out value="${coach.aboutMe}"/></textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-block">Submit Changes</button>
			</div>
		</form>
	</div>
	<div class="col-sm-2"></div>
</div>

<hr>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-4">
		<h3>Availability</h3>
		<div>
		<table id="avail-table">
			<c:forEach var="avail" items="${coach.available}">
				
				<c:url var="deleteAvailURL" value="/deleteAvailability">
					<c:param name="availId" value="${avail.id}"/>
					<c:param name="coachId" value="${coach.id}"/>
				</c:url>
				<fmt:parseDate var="start" value="${avail.hourStart}" pattern="HH" />
				<fmt:parseDate var="end" value="${avail.hourEnd}" pattern="HH"/>
				<tr>
					<td>	
						<%-- <c:out value="${day}: "/> --%>
						<c:out value="${avail.dayName}"/>
					</td>
					<td class="text-right">
						<fmt:formatDate value="${start}" pattern="ha"/>
						<c:out value=" - "/>
						<fmt:formatDate value="${end}" pattern="ha"/>
					</td>
					<td>
						<a id="availability-delete" href="${deleteAvailURL}" class="btn btn-danger">Delete</a>
					</td>
				</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
	<div class="col-sm-4">
		<h3>Add Time Slot</h3>
		<c:url var="addAvailURL" value="/addAvailability">
			<c:param name="coachId" value="${coach.id}"/>
		</c:url>
		<form method="POST" id="submit-new-avail" action="${addAvailURL}">
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="form-group">
				<label for="day">Day: </label>
				<select id="day" name="day" class="form-control">
				    <option value="1">Sunday</option>
				    <option value="2">Monday</option>
				    <option value="3">Tuesday</option>
				    <option value="4">Wednesday</option>
				    <option value="5">Thursday</option>
				    <option value="6">Friday</option>
				    <option value="7">Saturday</option>
				</select>
			</div>
			<div class="form-group">
				<label for="startTime">Start Time: </label>
				<select id="startTime" name="startTime" class="form-control">
				    <option value="7">7am</option>
				    <option value="8">8am</option>
				    <option value="9">9am</option>
				    <option value="10">10am</option>
				    <option value="11">11am</option>
				    <option value="12">12pm</option>
				    <option value="13">1pm</option>
				    <option value="14">2pm</option>
				    <option value="15">3pm</option>
				    <option value="16">4pm</option>
				    <option value="17">5pm</option>
				    <option value="18">6pm</option>
				    <option value="19">7pm</option>
				    <option value="20">8pm</option>
				    <option value="21">9pm</option>
				</select>
			</div>
			<div class="form-group">
				<label for="endTime">End Time: </label>
				<select id="endTime" name="endTime" class="form-control">
				    <option value="8">8am</option>
				    <option value="9">9am</option>
				    <option value="10">10am</option>
				    <option value="11">11am</option>
				    <option value="12">12pm</option>
				    <option value="13">1pm</option>
				    <option value="14">2pm</option>
				    <option value="15">3pm</option>
				    <option value="16">4pm</option>
				    <option value="17">5pm</option>
				    <option value="18">6pm</option>
				    <option value="19">7pm</option>
				    <option value="20">8pm</option>
				    <option value="21">9pm</option>
				    <option value="22">10pm</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary btn-block">Add Availability</button>
		</form>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />