<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="newMessageURL" value="/messageCoach">
	<c:param name="coachId" value="${coach.id}"/>
</c:url>
<c:url var="editCoachURL" value="/editCoach">
	<c:param name="coachId" value="${coach.id}"/>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:if test="${currentUser.id == coach.id}">
			<a style="text-align: right" href="${editCoachURL}">Edit Profile</a>
		</c:if>
		<h1 style="text-align: center"><c:out value="${coach.firstName} ${coach.lastName}" /></h1>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-5"></div>
	<div class="col-sm-2">
		<img style="height:15px" class="img img-responsive" 
			src="img/<c:out value="${coach.averageReview}"/>-star.png" alt="star rating"/>
		<div style="text-align: center"><c:out value="${coach.city}, ${coach.state}"/></div>
	</div>
	<div class="col-sm-5"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h3 style="text-align: center">About Me:</h3>
		<p><c:out value="${coach.aboutMe}"/></p>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<a class="btn btn-primary btn-block" href="${newMessageURL}">Contact</a>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-3">
		<h3>Availability</h3>
		<div>
		<table class="coach-avail">
			<c:forEach var="avail" items="${coach.available}">
					<fmt:parseDate var="start" value="${avail.hourStart}" pattern="HH" />
					<fmt:parseDate var="end" value="${avail.hourEnd}" pattern="HH"/>
					<tr>
						<td>
						<c:out value="${avail.dayName}"/>
						</td>
						<td class="text-right">
						<fmt:formatDate value="${start}" pattern="ha"/>
						<c:out value=" - "/>
						<fmt:formatDate value="${end}" pattern="ha"/>
						</td>
					</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
	<div class="col-sm-5">
		<h3>Reviews</h3>
		<c:if test="${currentUser.role == 'client' && client.completed == 'true' 
			&& client.pairedWith == coach.id}">
			<span id="new-review" class="response">New Review</span>
			<c:url var="reviewLink" value="/addReview"/>
			<form id="review-form" method="POST" action="${reviewLink}">
				<input type="hidden" name="coachId" value="${coach.id}">
				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
			</form>
			<br>
		</c:if>
		<table class="table">
			<c:forEach var="review" items="${coach.reviews}">
				<tr>
					<td>
						<img style="height:15px" class="img img-responsive" 
							src="img/<c:out value="${review.rating}"/>-star.png" alt="star rating">
						<c:out value="${review.reviewText}"/>
						- <c:out value="${review.createDate}"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:if test="${currentUser.role == 'coach' || currentUser.role == admin}">
	<div class="row">
		<div class="col-sm-2"></div>
		
		<div class="col-sm-8">
			<h3>Current Clients:</h3>
			<table class="table">
				<c:forEach var="currClient" items="${clients}">
					<c:url var="profileLink" value="/client">
						<c:param name="clientId" value="${currClient.id}"/>
					</c:url>
					<tr>
						<td>
							${currClient.firstName} ${currClient.lastName}
						</td>
						<td>
							<c:choose>
								<c:when test="${currClient.completed}">
									<span class="looking-for-coach">Completed</span>
								</c:when>
								<c:otherwise>
									In Progress
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="${profileLink}">
								<button class="btn btn-primary">View Profile</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="col-sm-2"></div>
	</div>
</c:if>

<c:import url="/WEB-INF/jsp/footer.jsp" />