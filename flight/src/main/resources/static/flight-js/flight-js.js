/**
 * 
 */

if(document.getElementById("pass") !== null )
document.getElementById("pass").addEventListener("mouseover", showInfoMessage);

if(document.getElementById("pass") !== null)
document.getElementById("pass").addEventListener("mouseout", clearInfoMessage);

if(document.getElementById("uname") !== null){

document.getElementById("uname").addEventListener("blur", function validate(){

	if(isValidUsr()){
		
		document.getElementById("username-correct").style.color= "green";
		document.getElementById("username-error").style.color= "white";
	}
	else{
		document.getElementById("username-correct").style.color= "white";
		document.getElementById("username-error").style.color= "red";
	}
	
}
);
}

if(document.getElementById("pass") !== null){

document.getElementById("pass").addEventListener("blur", function validate(){

	if(isValidPwd()){
		document.getElementById("password-correct").style.color= "green";
		document.getElementById("password-error").style.color= "white";
	}
	else{
		document.getElementById("password-correct").style.color= "white";
		document.getElementById("password-error").style.color= "red";
	}
}
);
}

function setLanguage() {
	 var lang = document.getElementById("langSelect").value.toLowerCase();
	location.href = "?lang=" + lang;		
}

function showInfoMessage(){
document.getElementById("password-info").style.color = "green";
}

function clearInfoMessage(){
	document.getElementById("password-info").style.color = "white";
}

function isValidUsr() {
		
		var user = document.getElementById("uname").value;
		
		if(user === "") {
			return false;
		}
		if( !user.includes("@")) {
			return false;
		}
		
		var userNameComponents = user.split("@");
		
		if(userNameComponents.length >= 3 ) {
			return false;
		}
		
		if(userNameComponents[0].length < 3) {
			return false;
		}
		
		if( !userNameComponents[1].includes(".")) {
			return false;
		}
		
		var serverForMailComponents = userNameComponents[1].split(".");
		
		if(serverForMailComponents.length >= 3) {
			return false;
		}
		
		if(serverForMailComponents[0].length < 3) {
			return false;
		}
		
		if(serverForMailComponents[1].length < 2) {
			return false;
		}
		
		return true;
	}

function isValidPwd(){
	
	var pass = document.getElementById("pass").value;
			
	if(pass.length < 8) {
			return false;
		}
		
	var upperCase = 0;

	for(var index = 0; index < pass.length; index++){
		for(var upperChar = 65; upperChar < 91; upperChar++){
		if(pass.charCodeAt(index) === upperChar){
			upperCase++;
		}	
	}
}
	if(upperCase < 2) {
			return false;
		}
	
	var digit = 0;
	
	for(var index = 0; index < pass.length; index++){
		for(var digitChar = 48; digitChar < 58; digitChar++){
		if(pass.charCodeAt(index) === digitChar){
			digit++;
		}	
	}
}
	
	if(digit < 1) {
			return false;
		}
	
	var lowerCase = 0;
	
	for(var index = 0; index < pass.length; index++){
		for(var lowerChar = 97; lowerChar < 123; lowerChar++){
		if(pass.charCodeAt(index) === lowerChar){
			lowerCase++;
		}	
	}
}
	
	if(pass.length - lowerCase - upperCase - digit < 1) {
			return false;
		}
	
	return true;
}

function isEqual(){
	if(document.getElementById("pass").value !== document.getElementById("passagain").value){
		document.getElementById("password-again-error").style.color= "red";
		document.getElementById("password-again-correct").style.color= "white";
		return false;
	}
	else{
		document.getElementById("password-again-error").style.color= "white";
		document.getElementById("password-again-correct").style.color= "green";
		return true;
	}
}

function isOK(){
	
	
	 if(isValidUsr() && isValidPwd() && isEqual()){
		
		document.getElementById("regist-submit-btn").disabled = false;
	}
	else{
		document.getElementById("regist-submit-btn").disabled = true;
	}
}		
		
		
		
		
		
		
		
		
		
		
