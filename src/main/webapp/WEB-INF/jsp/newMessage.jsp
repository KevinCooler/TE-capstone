<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">

	<div class="col-sm-2"></div>
	
	<div id="new-message" class="col-sm-8" style="text-align: center">
		<h1>Message ${recipientName}</h1>
		<c:url var="addMessage" value="/addMessage"/>
		<form id="message-form" method="POST" action="${addMessage}">
			<input type="hidden" name="receiverId" value="${recipientId}">
			<input type="hidden" name="receiverName" 
				value="${recipientName}">
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
			<textarea name="messageText" rows="10" cols="80"></textarea>
			<div><input type="submit" class="btn btn-primary" value="Submit"></div>
		</form>
	</div>
	
	<div class="col-sm-2"></div>
	
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />