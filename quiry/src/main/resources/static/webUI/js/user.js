function edit() { 
	document.getElementById("editB").style.display = "none";
	document.getElementById("subB").style.display = "unset";
	document.getElementById("canB").style.display = "unset";
	document.getElementById("email").disabled = false;
	document.getElementById("fname").disabled = false;
	document.getElementById("lname").disabled = false;
	document.getElementById("course").disabled = false;
	document.getElementById("password").style.display = "unset";
}
// IMPORTAN!!! When clicks on cancel button, all the information need to get from server again!
function can() {
	document.getElementById("editB").style.display = "unset";
	document.getElementById("subB").style.display = "none";
	document.getElementById("canB").style.display = "none";
	document.getElementById("email").disabled = true;
	document.getElementById("fname").disabled = true;
	document.getElementById("lname").disabled = true;
	document.getElementById("course").disabled = true;
	document.getElementById("password").style.display = "none";
}
// IMPORTANT!!! When click the submit button, this function will need to work!
// If any of the three password is entered, then 1 old and 2 new password will also need to enter
function sub() {

}