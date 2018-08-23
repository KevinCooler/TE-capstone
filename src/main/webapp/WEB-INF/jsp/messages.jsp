<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">

	<div class="col-sm-1"></div>
	
	<div id="messages" class="col-sm-10">
		<h1>Messages</h1>
		<table id="messages-table" class="table">
		<c:url var="addMessage" value="/addMessage"/>
		<c:forEach var="message" items="${messages}" varStatus="count">
			<!-- If the current user sent a message, it's marked as "To", otherwise "From" -->
				<c:choose>
					<c:when test="${message.didUserSend}">
					<c:url var="profileLink" value="/viewProfile">
						<c:param name="userId" value="${message.receiverId}"/>
					</c:url>
						<tr>
							<td>
								To:
							</td>
							
							<td>
								<a href="${profileLink}">
									<c:out value="${message.receiverName}"/>
								</a>
							</td>
							<td>
								<c:out value="${message.messageText}"/>
							</td>
							<td>
								<c:out value="${message.createDate}"/>
							</td>
							<td class="response" data-count="${count.index}">
								<button type="button" class="btn btn-primary">Reply <i class="fa fa-reply" aria-hidden="true"></i></button>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<form id="response-${count.index}" 
									method="POST" action="${addMessage}">
									<input type="hidden" name="receiverId" 
										value="${message.receiverId}">
									<input type="hidden" name="receiverName" 
										value="${message.receiverName}">
									<input type="hidden" name="CSRF_TOKEN" 
										value="${CSRF_TOKEN}">
								</form>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:url var="profileLink" value="/viewProfile">
							<c:param name="userId" value="${message.senderId}"/>
						</c:url>
						<tr>
							<td>
								From:
							</td>
							<td>
								<a href="${profileLink}">
									<c:out value="${message.senderName}"/>
								</a>
							</td>
							<td>
								<c:out value="${message.messageText}"/>
							</td>
							<td>
								<c:out value="${message.createDate}"/>
							</td>
							<td class="response" data-count="${count.index}">
								<button type="button" class="btn btn-primary">Reply <i class="fa fa-reply" aria-hidden="true"></i></button>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<form id="response-${count.index}" 
									method="POST" action="${addMessage}">
									<input type="hidden" name="receiverId" 
										value="${message.senderId}">
									<input type="hidden" name="receiverName" 
										value="${message.senderName}">
									<input type="hidden" name="CSRF_TOKEN" 
										value="${CSRF_TOKEN}">
								</form>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
		</c:forEach>
		</table>
	</div>
	
	<div class="col-sm-1"></div>
	
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />