<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:url var="signUpURL" value="/signUp"/>

	<div class="welcome-bar">
        <img id="banner-img" class="img-responsive hidden-xs" src="img/Smile_3.jpg" alt="MHM banner picture">
        <div class="title-details">
          <h1>Mental Health Matters</h1>
          <p>Ingenious marketing copy. And some more ingenious marketing copy.</p>
        </div>
        <a class="btn btn-primary" href="${signUpURL}">Get Started <i id="get-started" class="fas fa-arrow-alt-circle-right"></i></a>
    </div>
    
    
    <div class="row text-center feature-row">
    	<div class="col-sm-1"></div>
    	<div class="col-sm-5">
        	<i class="fas fa-search"></i>
	        <h2>Browse Coaches</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo. Vitae semper quis lectus nulla at volutpat. Egestas egestas fringilla phasellus faucibus scelerisque eleifend donec pretium. </p>
	    </div>
	    <div class="col-sm-5">
	       <i class="fas fa-users"></i>
	       <h2>Read Reviews</h2>
	       <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo. </p>
      	</div>
      	<div class="col-sm-1"></div>
	</div>
	<div class="row text-center feature-row">
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<i class="fas fa-comments"></i>
			<h2>Private Messaging</h2>
			<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
		</div>
		<div class="col-sm-5">
			<i class="fas fa-address-card"></i>
			<h2>Custom Profiles</h2>
			<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo. iverra accumsan in nisl nisi scelerisque. Ut venenatis tellus in metus vulputate eu scelerisque felis. </p>
		</div>
		<div class="col-sm-1"></div>
	</div>
	<div class="row text-center feature-row">
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<i class="fas fa-edit"></i>
			<h2>Submit Feedback</h2>
			<p>V Vitae semper quis lectus nulla. Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo. </p>
		</div>
		<div class="col-sm-5">
			<i class="fas fa-handshake"></i>
			<h2>Build Relationships</h2>
			<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam.</p>
		</div>
		<div class="col-sm-1"></div>
	</div>
  

<c:import url="/WEB-INF/jsp/footer.jsp" />