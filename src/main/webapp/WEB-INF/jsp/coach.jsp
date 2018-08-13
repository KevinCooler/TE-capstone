<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<c:url var="contactURL" value="/contact">
	<c:param name="coachId" value="${coach.Id}"></c:param>
</c:url>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h1 style="text-align: center">Insert Name</h1>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-5"></div>
	<div class="col-sm-2">
		<img style="height:15px" class="img img-responsive" src="img/2-star.png" alt="star rating"/>
		<div style="text-align: center">City, State</div>
	</div>
	<div class="col-sm-5"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<h3 style="text-align: center">About Me:</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<a class="btn btn-primary btn-block" href="${contactURL}">Contact</a>
	</div>
	<div class="col-sm-2"></div>
</div>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-2">
		<h3>Availability</h3>
		<div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
			<div>Sunday: 8am - 9am</div>
		</div>
	</div>
	<div class="col-sm-6">
		<h3>Reviews</h3>
		<table class="table">
			<tr>
				<td>
					<img style="height:15px" class="img img-responsive" src="img/2-star.png" alt="star rating"/>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Tellus mauris a diam maecenas. Accumsan sit amet nulla facilisi morbi tempus. Elit sed vulputate mi sit amet mauris commodo.
				</td>
			</tr>
			<tr>
				<td>
					<img style="height:15px" class="img img-responsive" src="img/3-star.png" alt="star rating"/>
					Vel pretium lectus quam id leo in vitae. Quis eleifend quam adipiscing vitae proin sagittis. Ut tristique et egestas quis ipsum suspendisse. Cras fermentum odio eu feugiat pretium nibh ipsum consequat nisl.
				</td>
			</tr>
			<tr>
				<td>
					<img style="height:15px" class="img img-responsive" src="img/5-star.png" alt="star rating"/>
					Aliquet bibendum enim facilisis gravida. Tempor orci dapibus ultrices in. Pharetra sit amet aliquam id. Praesent semper feugiat nibh sed. Diam sit amet nisl suscipit adipiscing bibendum est ultricies.
				</td>
			</tr>
		</table>
	</div>
	<div class="col-sm-2"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />