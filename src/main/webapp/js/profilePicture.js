$(document).ready(function() {
	$("#profilePicture").error(function () {
		  $(this).unbind("error").attr("src", "img/empty_profile.png");
		});
}); 