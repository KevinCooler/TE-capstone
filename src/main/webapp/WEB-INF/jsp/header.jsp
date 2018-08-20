<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>MHM Coaching</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
	    <script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	    <c:url value="/js" var="jsHref" />
    	<script src="${jsHref}/review.js"></script>
    	<script src="${jsHref}/feedback.js"></script>
    	<script src="${jsHref}/messages.js"></script>
    	<script src="${jsHref}/coachValidation.js"></script>
    	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
		<link rel="stylesheet"  href="css/site.css"></link>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("time.timeago").timeago();
				
				$("#logoutLink").click(function(event){
					$("#logoutForm").submit();
				});
				
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
				
			});
		</script>
		
	</head>
	<body>
		<header>
			<c:url var="homePageHref" value="/" />
			<c:url var="adminURL" value="/admin" />
			<c:url var="browseCoachesURL" value="/browseCoaches" />
			<c:url var="browseClientsURL" value="/browseClients" />
			<c:url var="profileHref" value="/${currentUser.role}">
			<c:url var="messagesHref" value="/messages"/>
				<c:param name="${currentUser.role}Id" value="${currentUser.id}"></c:param>
			</c:url>
		</header>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<c:url var="homePageHref" value="/" />
					<li><a style="font-size: 2em" href="${homePageHref}">MHM</a></li>	
					<li><a href="${browseCoachesURL}">Coaches</a></li>						
					<c:if test="${not empty currentUser}">
						<li><a href="${profileHref}">Profile</a></li>
						<li><a href="${messagesHref}">Messages</a></li>
						<c:if test="${currentUser.role == 'coach'}">
							<li><a href="${browseClientsURL}">Clients</a></li>
						</c:if>
						<c:if test="${currentUser.role == 'admin'}">
							<li><a href="${browseClientsURL}">Clients</a></li>
						</c:if>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty currentUser}">
							<c:url var="newUserHref" value="/signUp" />
							<li><a href="${newUserHref}">Sign Up</a></li>
							<c:url var="loginHref" value="/login" />
							<li><a href="${loginHref}">Log In</a></li>
						</c:when>
						<c:otherwise>
							<c:url var="logoutAction" value="/logout" />
							<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
							</form>
							<c:url var="changePasswordHref" value="/changePassword" />
							<li><a style="font-weight: bold" href="${profileHref}"><c:out value="${currentUser.userName}"/></a></li>
							<li><a href="${changePasswordHref}">Change Password</a></li>
							<li><a id="logoutLink" href="#">Log Out</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<div class="container">