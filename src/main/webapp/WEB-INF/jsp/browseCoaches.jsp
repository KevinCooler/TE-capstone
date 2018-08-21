<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<h1>Browse Coaches</h1>
		<table class="table">
			<c:forEach var="coach" items="${coaches}">
				<c:url var="coachURL" value="/coach">
					<c:param name="coachId" value="${coach.id}"/>
				</c:url>
				<c:url var="newMessageURL" value="/messageCoach">
					<c:param name="coachId" value="${coach.id}"/>
				</c:url>
				
				<tr>
					<td>
						<a href="${coachURL}"><img class="coach-image hidden-xs" src="img/empty_profile.png" alt="empty profile picture"/></a>
					</td>
					<td>
						<div class="browse-name">
							<h4><c:out value="${coach.firstName} ${coach.lastName}"/></h4>
						</div>
						<c:choose>
							<c:when test="${coach.averageReview == 0}">
							</c:when>
							<c:otherwise>
								<img style="height:15px" class="img img-responsive" 
							src="img/<c:out value="${coach.averageReview}"/>-star.png" alt="star rating"/>
							</c:otherwise>
						</c:choose>
						<div>
							<c:out value="${coach.city}, ${coach.state}"/>
						</div>
					</td>
					<td>
						<a href="${coachURL}" class="browse-button btn btn-success">Details <i class="fa fa-info-circle" aria-hidden="true"></i></a>
						<a href="${newMessageURL}" class="browse-button btn btn-primary">Contact <i class="fa fa-comments" aria-hidden="true"></i></a>
					</td>
					<td>
						<p class="hidden-xs"><c:out value="${coach.aboutMe}"/></p>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	<div class="col-sm-1"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />

