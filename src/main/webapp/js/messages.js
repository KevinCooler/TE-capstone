$(document).ready(function () {
	
	let formInit;
	
	$(".response").on("click", function() {
		let count = $(this).attr("data-count");
		
		if(!$(".response").hasClass("open")) {
			formInit = $("#response-" + count).html();
			$(this).addClass("open");
			
			let messageText = '<textarea name="messageText" cols="80" rows="5"></textarea>';
			messageText += '<br><input type="submit" value="Submit">';
			
			$("#response-" + count).append(messageText);
		} else if($(this).hasClass("open")){
			$(this).removeClass("open");
			$("#response-" + count).html(formInit);
		}
	});
});