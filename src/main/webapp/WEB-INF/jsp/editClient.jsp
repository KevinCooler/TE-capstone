<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<c:url var="formAction" value="/editClient" />
		<h2>Edit Client</h2>
		<form method="POST" action="${formAction}">
			<input type="hidden" name="clientId" value=<c:out value="${client.id}"/>>
			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="row">
				<label for="isLookingForCoach">Looking For a Coach: </label>
  					<label class="radio-inline">
    					<input type="radio" name="isLookingForCoach" id="Radios1" value="TRUE" <c:if test="${client.isLookingForCoach}">checked</c:if>>Yes</label>
 					<label class="radio-inline">
 						<input type="radio" name="isLookingForCoach" id="Radios2" value="FALSE" <c:if test="${!client.isLookingForCoach}">checked</c:if>>No</label>
				<div class="form-group">
					<label for="firstName">First Name:</label>
					<input type="text" id="fisrtName" name="firstName" value=<c:out value="${client.firstName}"/> class="form-control"/>
				</div>
				<div class="form-group">
					<label for="lastName">Last Name:</label>
					<input type="text" id="lastName" name="lastName" value=<c:out value="${client.lastName}"/> class="form-control" />
				</div>
				<div class="form-group">
					<label for="city">City:</label>
					<input type="text" id="city" name="city" value=<c:out value="${client.city}"/> class = "form-control" />
				</div>
				<div class="form-group">
					<label for="state">State:</label>
					<input type="text" id="state" name="state" value=<c:out value="${client.state}"/> class="form-control"/>
				</div>
				<div class="form-group">
					<label for="aboutMe">About Me:</label>
					<textarea id="aboutMe" name="aboutMe" class="form-control"><c:out value="${client.aboutMe}"/></textarea>
				</div>
				<button type="submit" class="btn btn-primary btn-block">Submit Changes</button>
			</div>
		</form>
		<h2>Module Feedback</h2>
		<c:forEach var = "i" begin = "1" end = "12">
			<div class="row feedbackButton">
				<c:url var="moduleFormAction" value="/submitModuleFeedback" />
				<button type="button" id="module${i}Button" class="btn btn-primary browse-button"><c:out value="module ${i}"/></button>
				<form method="POST" action="${moduleFormAction}" id="module${i}" style="display:none">
					<input type="hidden" id="module" name="module" value="${i}">
					<input type="hidden" id="clientId" name="clientId" value=<c:out value="${client.id}"/>>
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
					<div class="form-group">
						<label for="detail"><c:out value="Module ${i} Feedback:"/></label>
						<textarea id="detail" name="detail" class="form-control"><c:out value="${feedbackList.get(i-1).detail}"/></textarea>
					</div>
					<button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		</c:forEach>
	</div>
</div>
	





<c:import url="/WEB-INF/jsp/footer.jsp" />