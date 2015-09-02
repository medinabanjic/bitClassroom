function validateLogin(email, password){
	if(checkEmail(email.value) && checkPassword(password.value)){

	}
}

function checkEmail(email){
	if(email !== undefined){	
	   var atposition = email.indexOf("@");
	   var dotposition = email.lastIndexOf(".");	
	}
	
	if(atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length){
			alert("Input mail in correct form!")
			return false;
		}
		
	if(email.indexOf("bitcamp") == -1){
		alert("Email must contain 'bitcamp'!");
		return false;
	}
	return true;
}

function checkPassword(password){
	if(password !== undefined){
	var pass = document.getElementById("password").value;
	}
	if(pass.length < 6){
		alert("Your password must be at least 6 characters");
		return false;
	}
	if(pass.search(/[a-z]/) < 0){
		alert("Your password must contain at least one lowercase letter.");
		return false;
	}
	if(pass.search(/[A-Z]/) < 0){
		alert("Your password must contain at least one uppercase letter.");
		return false;
	}
	if(pass.search(/[0-9]/) < 0){
		alert("Your password must contain at least one digit.");
		return false;
	} 
	return true;
}

function popitup(url) {
	newwindow=window.open(url,'name','height=200,width=150');
	if (window.focus) {newwindow.focus()}
	return false;
}