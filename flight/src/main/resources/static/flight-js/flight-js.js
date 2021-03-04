/**
 * 
 */

document.getElementById("pass").addEventListener("mouseover", showInfoMessage);
document.getElementById("pass").addEventListener("mouseout", clearInfoMessage);


function setLanguage() {

	 var lang = document.getElementById("langSelect").value.toLowerCase();
	location.href = "?lang=" + lang;		
}

function showInfoMessage(){
document.getElementById("pass-info").style.color = "green";
}

function clearInfoMessage(){
	document.getElementById("pass-info").style.color = "white";
}