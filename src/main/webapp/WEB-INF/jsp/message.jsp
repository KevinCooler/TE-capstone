<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8" style="text-align: center">
		<h1>Message ${coach.firstName} ${coach.lastName}</h1>
		<c:url var="messageLink" value="/newMessage"/>
		<form id="message-form" method="POST" action="${messageLink}">
			<input type="hidden" name="clientId" value="${clientId}">
			<input type="hidden" name="coachId" value="${coach.id}">
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
			<textarea name="messageText" rows="10" cols="80"></textarea>
			<div><input type="submit" value="Submit"></div>
		</form>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />