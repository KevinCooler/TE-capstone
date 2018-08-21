$(document).ready(function () {
	$(".deleteCoach").click(function(){
		return confirm("Are you sure you want to delete?");
	});
	$(".clientCompletedProgram").click(function(){
		return confirm("Are you sure this client has completed the program?");
	});
});