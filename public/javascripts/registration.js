
function pass_validationCheck()
{
	var isGood = true;
	var pass = document.getElementById("password").value;

	if(pass.length < 6){
		$("#password").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Your password must be at least 6 characters</div>"));
		styleTest();
		isGood = false;
	}
	if(pass.search(/[a-z]/) < 0){
		$("#password").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Your password must contain at least one lowercase letter</div>"));
		styleTest();
		isGood = false;

	}
	if(pass.search(/[A-Z]/) < 0){
		$("#password").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Your password must contain at least one uppercase letter</div>"));
		isGood = false;
		styleTest();


	}
	if(pass.search(/[0-9]/) < 0){
		$("#password").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Your password must contain at least one digit</div>"));
		styleTest();

		isGood = false;

	}

	if (isGood){
		$("#password").after($("<div class='alert alert-success' id='test1' role'alert' style='width:13%;'>Good</div>"));
		styleTest1();


	}

}

function styleTest(){
	$("#test").css("margin-left", "28.5%");
	$("#test").css("padding", "1%");
	$("#test").css("margin-bottom", "0%");
	$("#test").css("border", "2px solid #42753E");
}

function styleTest1(){
	$("#test1").css("margin-left", "28.5%");
	$("#test1").css("padding", "1%");
	$("#test1").css("margin-bottom", "0%");
	$("#test1").css("border", "2px solid #42753E");
}

function pass_validationUncheck()
{
	var pass = document.getElementById("password").value;

	if(pass.length < 6){
		$("#test").remove();
	}
	if(pass.search(/[a-z]/) < 0){
		$("#test").remove();
	}
	if(pass.search(/[A-Z]/) < 0){
		$("#test").remove();
	}
	if(pass.search(/[0-9]/) < 0){
		$("#test").remove();
	}

	$("#test1").remove();

}

function checkRepeatPassCheck(){
	var pass = document.getElementById("password").value;

	var rePass = document.getElementById("password-again").value;

	if(pass.equals(rePass)){
		$("#password-again").after($("<div class='alert alert-success' id='test1' role'alert' style='width:13%;'>Good</div>"));
		styleTest1();


	} else if(!pass.equals(rePass)){
		$("#password-again").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Password does not match</div>"));
		styleTest();


	}
}


function checkRepeatPassUncheck(){
	var pass = document.getElementById("password").value;

	var rePass = document.getElementById("password-again").value;

	if(pass.equals(rePass)){
		$("#test1").remove();

	} else if(!pass.equals(rePass)) {
		$("#test").remove();

	}
}

function firstToUpperCase(uname){
    return uname[0].toUpperCase() + uname.slice(1);
}


function allLetterCheck(uname)
{
var letters = /^[A-Za-z]+$/;
if(uname.value.match(letters))
{
	$("#name").after($("<div class='alert alert-success' id='test1' role'alert' style='width:13%;'>Good</div>"));
	styleTest1();

}
else if(!uname.value.match(letters))
{
	$("#name").after($("<div class='alert alert-warning' id='test' role='alert' style='width:50%;'>Only letters allowed</div>"));
	styleTest();

}
}


function allLetterUncheck(uname)
{
	var letters = /^[A-Za-z]+$/;
	if(uname.value.match(letters))
	{
		$("#test1").remove();
	}
	else
	{
		$("#test").remove();
	};
}

function checkEmail(email){
var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
if(email.value.match(mailformat))  
{  
return true;  
}
else  
{  
alert("You have entered an invalid email address!");  
email.focus();  
return false;  
};  
}  

function validsex(umsex,ufsex)
{
x=0;

if(umsex.checked) {
 x++;
}else if(ufsex.checked) {
 x++;
};
if(x==0)
{
alert('Select Male/Female');
umsex.focus();
return false;
}
else if (x>0) {
	return true;
};
}











