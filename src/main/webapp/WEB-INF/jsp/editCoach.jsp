<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="contactURL" value="/contact">
	<c:param name="coachId" value="${coach.id}"></c:param>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:url var="formAction" value="/editCoach" />
		<h2>Edit Coaching Profile</h2>
		<form method="POST" action="${formAction}">
		<input type="hidden" name="coachId" value=<c:out value="${coach.id}"/>>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="row">
				<div class="form-group">
					<label for="firstName">First Name: </label>
					<input type="text" id="firstName" name="firstName" 
						value=<c:out value="${coach.firstName}"/> class="form-control" />
				</div>
				<div class="form-group">
					<label for="lastName">Last Name: </label>
					<input type="text" id="lastName" name="lastName" 
						value=<c:out value="${coach.lastName}"/> class="form-control" />
				</div>
				<div class="form-group">
					<label for="city">City: </label>
					<input type="text" id="city" name="city" 
						value=<c:out value="${coach.city}"/> class="form-control" />
				</div>
				<div class="form-group">
					<label for="state">State: </label>
					<input type="text" id="state" name="state" 
						value=<c:out value="${coach.state}"/> class="form-control" />	
				</div>
				<div class="form-group">
					<label for="aboutMe">About Me: </label>
					<textarea class="form-control" id="aboutMe" name="aboutMe" rows="5">
						<c:out value="${coach.aboutMe}"/>
					</textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-block">Submit Changes</button>
			</div>
		</form>
	</div>
	<div class="col-sm-2"></div>
</div>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-4">
		<h3>Availability</h3>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6">
				Sunday: 8am - 9am
			</div>
			<div class="col-sm-6">
				<a class="btn btn-danger">Remove</a>
			</div>
		</div>
	</div>
	<div class="col-sm-4">
		<h3>Add Time Slot</h3>
		<form method="POST" action="${formAction}">
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