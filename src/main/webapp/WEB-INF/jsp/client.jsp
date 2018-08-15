<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="contactURL" value="/contact">
	<c:param name="coachId" value="${client.id}"></c:param>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h1 style="text-align: center"><c:out value="${client.firstName} ${client.lastName}" /></h1>
      	<c:if test = "${client.isLookingForCoach}">
        	<p>Looking for Coach</p>
      	</c:if>
	</div>
	<div class="col-sm-2"></div>
</div>
<!-- <div class="row">
	<div class="col-sm-5"></div>
	<div class="col-sm-5"></div>
</div> -->
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div style="text-align: center">
			<c:out value="${client.city}, ${client.state}"/>
		</div>
		<p><c:out value="${client.aboutMe}"/></p>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		
		<a class="btn btn-primary btn-block" href="${contactURL}">Contact</a>
	</div>
	<div class="col-sm-2"></div>
</div>
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

<c:import url="/WEB-INF/jsp/footer.jsp" />