<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="contactURL" value="/contact">
	<c:param name="coachId" value="${coach.id}"></c:param>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
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
		<a class="btn btn-primary btn-block" href="${contactURL}">Contact</a>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-3">
		<h3>Availability</h3>
		<div>
		<table>
			<c:forEach var="avail" items="${coach.available}">
					<%-- <c:set value="" var="day"/>
					<c:if test="${avail.day == 1}">
						<c:set var="day" value="Sunday"/>
					</c:if>
					<c:if test="${avail.day == 2}">
						<c:set var="day" value="Monday"/>
					</c:if>
					<c:if test="${avail.day == 3}">
						<c:set var="day" value="Tuesday"/>
					</c:if>
					<c:if test="${avail.day == 4}">
						<c:set var="day" value="Wednesday"/>
					</c:if>
					<c:if test="${avail.day == 5}">
						<c:set var="day" value="Thusday"/>
					</c:if>
					<c:if test="${avail.day == 6}">
						<c:set var="day" value="Friday"/>
					</c:if>
					<c:if test="${avail.day == 7}">
						<c:set var="day" value="Saturday"/>
					</c:if> --%>
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
					</tr>
				
			</c:forEach>
			</table>
		</div>
	</div>
	<div class="col-sm-5">
		<h3>Reviews</h3>
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

<c:import url="/WEB-INF/jsp/footer.jsp" />