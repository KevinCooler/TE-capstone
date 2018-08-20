<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="newMessageURL" value="/messageClient">
	<c:param name="clientId" value="${client.id}"/>
</c:url>
<c:url var="editClientURL" value="/editClient">
	<c:param name="clientId" value="${client.id}"/>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	
	<div class="col-sm-8">
		<c:if test="${currentUser.id == client.id}">
			<a href="${editClientURL}">Edit Profile</a>
		</c:if>
		
		<h1 style="text-align: center">
			<c:out value="${client.firstName} ${client.lastName}" />
		</h1>
		
      	<c:if test = "${client.isLookingForCoach && currentUser.role == 'coach'
      		&& client.completed == 'false'}">
	      	<c:url var="coachingLink" value="/coachClient"/>
        	<p class="looking-for-coach">Looking for Coach</p>
        	<div class="client-status">
				<form method="POST" action="${coachingLink}">
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
					<input type="hidden" name="clientId" value="${client.id}">
	        		<input type="submit" class="btn btn-primary" value="Coach This Client">
	        	</form>
        	</div>
      	</c:if>
      	
      	<c:if test="${client.pairedWith == currentUser.id && client.completed == 'false'}">
      		<p class="looking-for-coach">
      			<c:out value="You're currently coaching ${client.firstName} ${client.lastName}"/>
      		</p>
      		<c:url var="noCoachingLink" value="/noLongerCoaching"/>
      		<div class="client-status">
      		<form method="POST" action="${noCoachingLink}">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
				<input type="hidden" name="clientId" value="${client.id}">
        		<input type="submit" class="btn btn-primary" value="I'm No Longer Coaching This Client">
        	</form>
        	<c:url var="completedLink" value="/completedCourse"/>
        	<form method="POST" action="${completedLink}">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
				<input type="hidden" name="clientId" value="${client.id}">
        		<input type="submit" class="btn btn-primary" value="Client Has Completed The Program">
        	</form>
        	</div>
      	</c:if>
	</div>
	
	<div class="col-sm-2"></div>
</div>

<div class="row text-center">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><c:out value="${client.city}, ${client.state}"/></div>
		<p><c:out value="${clientUser.userName}"/></p>
	</div>
	
	<div class="col-sm-2"></div>
</div>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<p><c:out value="${client.aboutMe}"/></p>
		<a class="btn btn-primary btn-block" href="${newMessageURL}">Contact</a>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:if test="${currentUser.role == 'client' || currentUser.role == 'admin' }">
	<div>
		<div class="col-sm-2"></div>
		
		<div class="col-sm-8">
			<h3>Module Feedback</h3>
			<table class="table">
				<c:forEach var="feedback" items="${feedbacks}">
					<tr>
						<td>
							<c:out value="${feedback.module}"/>
						</td>
						<td>
							<p><c:out value="${feedback.detail}"/></p>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="col-sm-2"></div>
	</div>
</c:if>

<c:import url="/WEB-INF/jsp/footer.jsp" />