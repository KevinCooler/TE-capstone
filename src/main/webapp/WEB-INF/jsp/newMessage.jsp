<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8" style="text-align: center">
		<h1>Message ${recipient.firstName} ${recipient.lastName}</h1>
		<c:url var="addMessage" value="/addMessage"/>
		<form id="message-form" method="POST" action="${addMessage}">
		<c:choose>
			<c:when test="${isCoach}">
				<input type="hidden" name="coachId" value="${recipient.id}">
			</c:when>
			<c:otherwise>
				<input type="hidden" name="clientId" value="${recipient.id}">
			</c:otherwise>
		</c:choose>
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
			<textarea name="messageText" rows="10" cols="80"></textarea>
			<div><input type="submit" value="Submit"></div>
		</form>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />