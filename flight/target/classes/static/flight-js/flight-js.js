/**
 * 
 */
 
 

 
 
function setLanguage() {
	
	var lang = document.getElementById("langSelect").value.toLowerCase();
	location.href = "?lang=" + lang;
}

