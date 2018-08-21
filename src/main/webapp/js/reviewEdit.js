$(document).ready(function () {
	
	let formInit;
	let textInit;
	
	$(".edit").on("click", function() {
		let count = $(this).attr("data-count");
		
		if(!$(".edit").hasClass("open")) {
			formInit = $("#response-" + count).html();
			textInit = $("#review-text").text();
			$(this).addClass("open");
			
			let messageText = '<textarea name="messageText" cols="40" rows="3">'
				+ textInit + '</textarea>';
			messageText += '<br><input type="submit" class="btn btn-primary" value="Edit Message">';
			
			$("#response-" + count).append(messageText);
		} else if($(this).hasClass("open")){
			$(this).removeClass("open");
			$("#review-text").text(textInit);
			$("#response-" + count).html(formInit);
		}
	});
});