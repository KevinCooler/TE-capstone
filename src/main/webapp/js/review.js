$(document).ready(function () {
	
	let formInit = $("#review-form").html();
	
	$("#new-review").on("click", function() {
		if($("#new-review").text() === "New Review") {
			let formInput = 'Rating: <select name="rating">';
			for(let i = 1; i <= 5; i++) {
				formInput += '<option value="' + i + '">' + i + '</option>';
			}
			formInput += '</select>';
			formInput += '<textarea name="reviewText" rows="4" cols="60"></textarea>';
			formInput += '<input type="submit" class="btn btn-primary" value="Submit">';
			
			$("#review-form").append(formInput);
			
			$("#new-review").html("<u>Cancel</l>");
		} else {
			$("#new-review").html("<u>New Review</u>");
			$("#review-form").html(formInit);
		}
	});
});