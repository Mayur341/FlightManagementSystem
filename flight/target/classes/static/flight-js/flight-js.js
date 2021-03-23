/**
 * 
 */
if(document.getElementById("search-field") != null){	
document.getElementById("search-field").addEventListener("focus", setup);
document.getElementById("search-field").focus();
}

function setup(){
if(document.getElementById("search-value").value != null){
var searchValue = document.getElementById("search-value").value;
document.getElementById("search-field").value = searchValue;
}
}

function search(){
	var str = document.getElementById("search-field").value;
	location.href = location.origin + "/flight/search?text=" + str;
}

function send(id){
	
	location.href = location.origin + "/flight/cancel?id=" + id;
}

function getArrivalDate(){
	var date = document.getElementById("dep-date").value;
	document.getElementById("arr-date").value = date;
}
	
if(document.getElementById("usrnameExists") != null){
	alert(document.getElementById("usrnameExists").value);
}

if(document.getElementById("pass") !== null )
document.getElementById("pass").addEventListener("mouseover", showInfoMessage);

if(document.getElementById("pass") !== null)
document.getElementById("pass").addEventListener("mouseout", clearInfoMessage);

if(document.getElementById("uname") !== null){

document.getElementById("uname").addEventListener("blur", function validate(){

	if(isValidUsr()){
		document.getElementById("username-correct").innerHTML = document.getElementById("username-correct-msg").value;
		document.getElementById("username-error").innerHTML = "";
		document.getElementById("username-correct").style.color= "green";
	}
	else{
		document.getElementById("username-error").innerHTML = document.getElementById("username-error-msg").value;
		document.getElementById("username-correct").innerHTML = "";
		document.getElementById("username-error").style.color= "red";
	}
	
}
);
}

if(document.getElementById("pass") !== null){

document.getElementById("pass").addEventListener("blur", function validate(){

	if(isValidPwd()){
		document.getElementById("password-correct").innerHTML = document.getElementById("password-correct-msg").value;
		document.getElementById("password-error").innerHTML = "";
		document.getElementById("password-correct").style.color= "green";
	}
	else{
		document.getElementById("password-error").innerHTML = document.getElementById("password-error-msg").value;
		document.getElementById("password-correct").innerHTML = "";
		document.getElementById("password-error").style.color= "red";
	}
}
);
}

if(document.getElementById("passagain") !== null){

document.getElementById("passagain").addEventListener("blur", function validate(){

	if(isEqual()){
		document.getElementById("passwords-equal").innerHTML = document.getElementById("passwords-equal-msg").value;
		document.getElementById("passwords-non-equal").innerHTML = "";
		document.getElementById("passwords-equal").style.color= "green";
	}
	else{
		document.getElementById("passwords-non-equal").innerHTML = document.getElementById("passwords-non-equal-msg").value;
		document.getElementById("passwords-equal").innerHTML = "";
		document.getElementById("passwords-non-equal").style.color= "red";
	}
}
);
}

function setLanguage() {
	
	 var lang = document.getElementById("langSelect").value.toLowerCase();
	
	if(location.pathname === "/flight/search"){
			location.href = location.origin + "/flight/reservations?lang=" + lang;
	}
	else if(location.pathname === "/flight/reg"){
			location.href = location.origin + "/login?lang=" + lang;
	}
	else {
		location.href = "?lang=" + lang;
	}		
}

function showInfoMessage(){
document.getElementById("password-info").innerHTML = document.getElementById("password-info-msg").value;
document.getElementById("password-info").style.color = "green";
}

function clearInfoMessage(){
	document.getElementById("password-info").innerHTML = "";
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
	if(document.getElementById("pass").value === document.getElementById("passagain").value){	
		return true;
	}
	else{
		return false;
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