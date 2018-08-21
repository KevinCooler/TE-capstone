$(document).ready(function () {
	
	let formInit;
	let textInit;
	
	$(".edit").on("click", function() {

		if(!$(this).hasClass("open")) {
			formInit = $("#edit-review").html();
			textInit = $("#review-text").text();
			let rating = $(this).attr("data-rating");
			$(this).addClass("open");
			
			let messageText = 'Rating: <select name="rating">';
			for(let i = 1; i <= 5; i++) {
				if(i == rating)
					messageText += '<option value="' + i + '" selected>' + i + '</option>';
				else
					messageText += '<option value="' + i + '">' + i + '</option>';
			}
			messageText += '</select>';
			messageText += '<textarea name="reviewText" cols="60" rows="4">'
				+ textInit + '</textarea>';
			messageText += '<br><input type="submit" class="btn btn-primary" value="Edit Message">';
			
			$(".edit-button").text("Cancel");
			$("#edit-review").append(messageText);
		} else if($(this).hasClass("open")){
			$(this).removeClass("open");
			$(".edit-button").text("Edit");
			$("#edit-review").html(formInit);
		}
	});
});