$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$("#changePassword").validate({
			
			rules : {
				oldPassword : {
					required : true
				},
				newPassword : {
					required : true,
					minLength : 8,
					capitals : true
				},
				confirmNewPassword : {
					required : true,		
					equalTo : "#newPassword"  
				}
			},
			messages : {			
				newPassword : {
				//	required : "password is required"
					minLength : "password must be at least 8 charaters",
					capitals : "password must contain at least one capital"
				},
				confirmNewPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});