<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
<!-- --------------------------------------------------------------- -->
<!-- Manage Coaches Table -->
    <div class="col-sm-7">
        <h1>Manage Coaches</h1>
        <table id="manage-coaches" class="table">
            <tbody>
                <c:forEach var="coach" items="${coaches}" varStatus="status">
                    <c:url var="editCoachURL" value="/editCoach">
                        <c:param name="coachId" value="${coach.id}"/>
                    </c:url>
                    <c:url var="deleteCoachURL" value="/deleteCoach">
                        <c:param name="coachId" value="${coach.id}"/>
                    </c:url>
                    <c:url var="coachURL" value="/coach">
                        <c:param name="coachId" value="${coach.id}"/>
                    </c:url>
                    <tr>
                        <td>
                        	<c:out value="${coach.firstName} ${coach.lastName}"/>
                        	<p class="font-weight-light"><c:out value="${users[status.index].userName}"/></p>
                        </td>
                        <td>
                            <a href="${coachURL}" class="admin-button btn btn-primary">View <i class="fa fa-info-circle" aria-hidden="true"></i></a>
                        </td>
                        <td>
                            <a href="${editCoachURL}" class="admin-button btn btn-success">Edit <i class="fa fa-edit" aria-hidden="true"></i></a>
                        </td>
                        <td>
                            <a href="${deleteCoachURL}" class="admin-button btn btn-danger deleteCoach">Delete <i class="fa fa-trash" aria-hidden="true"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<!-- End Manage Coaches Table -->
<!-- --------------------------------------------------------------- -->
<!-- Add New Coach Form -->
    <div class="col-sm-1"></div>
    <div class="col-sm-4">
        <h1>Add New Coach</h1>
        <c:url var="formAction" value="/addCoach" />
        <form id="newCoachForm" method="POST" action="${formAction}">
        	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
            <div class="row">
                <div class="form-group">
                    <label for="firstName">First Name: </label>
                    <input type="text" id="firstName" name="firstName" placeHolder="First Name" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="lastName">Last Name: </label>
                    <input type="text" id="lastName" name="lastName" placeHolder="Last Name" class="form-control" />
                </div>
                <div class="form-group">
					<label for="userName">Email: </label>
					<input type="text" id="userName" name="userName" placeHolder="Email" class="form-control" />
				</div>
                <div class="form-group">
                    <label for="password">Password: </label>
                    <input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password: </label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />   
                </div>
                <p class="error"><c:out value="${duplicateUsername}"/></p>
                <button type="submit" class="btn btn-default">Create Coach</button>
            </div>
        </form>
    </div>
<!-- End Add New Coach Form -->
<!-- --------------------------------------------------------------- -->
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />