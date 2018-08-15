$(document).ready(function () {
	
	let formInit = $("#review-form").html();
	
	$("#new-review").on("click", function() {
		if($("#new-review").text() === "New Review") {
			let formInput = 'Rating: <select name="rating">';
			formInput += '<option value="1">1</option>';
			formInput += '<option value="2">2</option>';
			formInput += '<option value="3">3</option>';
			formInput += '<option value="4">4</option>';
			formInput += '<option value="5">5</option>';
			formInput += '</select>';
			formInput += '<textarea name="reviewText" rows="4" cols="60"></textarea>';
			formInput += '<input type="submit" value="Submit">';
			
			$("#review-form").append(formInput);
			
			$("#new-review").html("<u>Cancel</l>");
		} else {
			$("#new-review").html("<u>New Review</u>");
			$("#review-form").html(formInit);
		}
	});
});