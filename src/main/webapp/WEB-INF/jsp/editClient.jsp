<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:url var="${formAction}" value="/editClient"/>
		<h2>Edit Client</h2>
		<form method="POST" action="${formAction}">
		<input type="hidden" name="clientId" value=<c:out value="${client.id}"/>>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
		
		</form>

	
	
	
	</div>
</div>
	





<c:import url="/WEB-INF/jsp/footer.jsp" />