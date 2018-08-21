<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<h1>Browse Clients</h1>
		<table class="table"> 
			<c:forEach var="client" items="${clients}">
				<c:url var="clientURL" value="/client">
					<c:param name="clientId" value="${client.id}"/>
				</c:url>
				<c:url var="newMessageURL" value="/messageClient">
					<c:param name="clientId" value="${client.id}"/>
				</c:url>
				<tr>
					<td>
						<div class="browse-name">
							<h4><c:out value="${client.firstName} ${client.lastName}"></c:out></h4>
						</div>
						<div>
							<c:out value="${client.city}, ${client.state}"/>
						</div>
						<div>
							<c:if test= "${client.isLookingForCoach}">
							<p class="looking-for-coach">Looking for Coach</p>
							</c:if>
						</div>
					</td>
					<td>
						<a href="${clientURL}" class="browse-button btn btn-success">Details <i class="fa fa-info-circle" aria-hidden="true"></i></a>
						<a href="${newMessageURL}" class="browse-button btn btn-primary">Contact <i class="fa fa-comments" aria-hidden="true"></i></a>
					</td>
					<td>
						<p><c:out value="${client.aboutMe}"/></p>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />