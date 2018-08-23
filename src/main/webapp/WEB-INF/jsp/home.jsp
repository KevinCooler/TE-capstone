<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

	<c:url var="signUpURL" value="/signUp"/>

	<div id="home-logo" class="welcome-bar">
        <img id="banner-img" class="img-responsive hidden-xs" src="img/Smile_3.jpg" alt="MHM banner picture">
        <div class="title-details">
          <h1 class="logo">Mental Health Matters</h1>
          <p>Building skills to last a lifetime!</p>
        </div>
        <a class="btn btn-primary" href="${signUpURL}">Get Started <i id="get-started" class="fas fa-arrow-alt-circle-right"></i></a>
    </div>
    
    <div id="home-details" class="row text-center feature-row">
    	<div class="col-sm-1"></div>
    	<div class="col-sm-5">
        	<i class="fas fa-search"></i>
	        <h2>Browse Coaches</h2>
	        <p>Connect with a coach that will reshape the way you think about mental health.  
	        	Our 12 week program will provide you with tools and techniques that will last 
	        	a lifetime.</p>
	    </div>
	    <div class="col-sm-5">
	       <i class="fas fa-users"></i>
	       <h2>Read Reviews</h2>
	       <p>See reviews and ratings for each coach before you make a choice.
	       </p>
      	</div>
      	<div class="col-sm-1"></div>
	</div>
	<div class="row text-center feature-row">
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<i class="fas fa-comments"></i>
			<h2>Private Messaging</h2>
			<p>Reach out to coaches for one on one counseling or to schedule 
			in person appointments</p>
		</div>
		<div class="col-sm-5">
			<i class="fas fa-address-card"></i>
			<h2>Custom Profiles</h2>
			<p>Create a personal profile that can be browsed by prospective coaches.  Detail your 
			goals and provide feedback as you complete the course.</p>
		</div>
		<div class="col-sm-1"></div>
	</div>
	<div class="row text-center feature-row">
		<div class="col-sm-1"></div>
		<div class="col-sm-5">
			<i class="fas fa-edit"></i>
			<h2>Submit Feedback</h2>
			<p>MHM is focused on getting feedback from our users every step of the way.  
			Give thoughts about the program over the course of your 12 weeks, and review 
			coaches after completion.</p>
		</div>
		<div class="col-sm-5">
			<i class="fas fa-handshake"></i>
			<h2>Build Relationships</h2>
			<p>Ultimately, Mental Health Matters is about building relationships and 
			showing people that they're not alone.  We're dedicated to improving lives 
			and destigmatizing mental health.</p>
		</div>
		<div class="col-sm-1"></div>
	</div>
  
<c:import url="/WEB-INF/jsp/footer.jsp" />