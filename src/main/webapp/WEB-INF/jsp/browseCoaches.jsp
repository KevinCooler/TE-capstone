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
				<c:url var="newMessageURL" value="/newMessage">
					<c:param name="coachId" value="${coach.id}"/>
				</c:url>
				
				<tr>
					<td>
						<c:out value="${coach.firstName} ${coach.lastName}"/>
						<img style="height:15px" class="img img-responsive" src="img/3-star.png" alt="star rating"/>
						<c:out value="${coach.city}, ${coach.state}"/>
					</td>
					<td>
						<a href="${coachURL}" class="btn btn-success">Details</a>
						<a href="${newMessageURL}" class="btn btn-primary">Contact</a>
					</td>
					<td>
						<p><c:out value="${coach.aboutMe}"/></p>
					</td>
				</tr>
			</c:forEach>
		</table>
		
	</div>
	<div class="col-sm-1"></div>
</div>









<c:import url="/WEB-INF/jsp/footer.jsp" />

