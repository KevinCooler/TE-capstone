<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
		<h1>Messages</h1>
		<table class="table">
		<c:forEach var="message" items="${messages}">
			<c:choose>
				<c:when test="${message.didUserSend}">
					<c:url var="responseUrl" value="/newResponse">
						<c:param name="recipientName" value="${message.receiverName}"/>
						<c:param name="recipientId" value="${message.receiverId}"/>
					</c:url>
					<tr>
						<td>
							To:
						</td>
						<td>
							<a href="${responseUrl}">
								<b><c:out value="${message.receiverName}"/></b>
							</a>
						</td>
						<td>
							<c:out value="${message.messageText}"/>
						</td>
						<td>
							<c:out value="${message.createDate}"/>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:url var="responseUrl" value="/newResponse">
						<c:param name="recipientName" value="${message.senderName}"/>
						<c:param name="recipientId" value="${message.senderId}"/>
					</c:url>
					<tr>
						<td>
							From:
						</td>
						<td>
							<a href="${responseUrl}">
								<b><c:out value="${message.senderName}"/></b>
							</a>
						</td>
						<td>
							<c:out value="${message.messageText}"/>
						</td>
						<td>
							<c:out value="${message.createDate}"/>
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