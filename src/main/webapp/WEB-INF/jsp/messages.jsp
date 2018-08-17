<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<h1>Messages</h1>
		<table class="table">
		<c:url var="addMessage" value="/addMessage"/>
		<c:forEach var="message" items="${messages}" varStatus="count">
				<c:choose>
					<c:when test="${message.didUserSend}">
						<tr>
							<td>
								To:
							</td>
							<td class="response" data-count="${count.index}">
								<b><u><c:out value="${message.receiverName}"/></u></b>
							</td>
							<td>
								<c:out value="${message.messageText}"/>
							</td>
							<td>
								<c:out value="${message.createDate}"/>
							</td>
						</tr>
						<tr>
							<td colspan="4">
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
						<tr>
							<td>
								From:
							</td>
							<td class="response" data-count="${count.index}">
								<b><u><c:out value="${message.senderName}"/></u></b>
							</td>
							<td>
								<c:out value="${message.messageText}"/>
							</td>
							<td>
								<c:out value="${message.createDate}"/>
							</td>
						</tr>
						<tr>
							<td colspan="4">
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