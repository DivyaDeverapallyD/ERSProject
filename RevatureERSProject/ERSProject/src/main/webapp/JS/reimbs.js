/**
 * 
 */
 
window.onload = function(){
	
	console.log('Window Loaded');
	let button = document.getElementById('signin').addEventListener('click', login);
	console.log(button);
//	alert(button);
}
	
let button1=document.getElementById("signin").addEventListener('click',login);
//alert("Alert button"+ button1);
	
function login() {
		
		let uname=document.getElementById("username").value;
		//alert(document.getElementById("username").value);
	let user = {
			
		userName: document.getElementById('username').value,
		userPwd: document.getElementById('password').value
	
	};
//	alert(user);
	console.log(user);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		
		if(xhr.readyState == 4 && xhr.status == 200) {
				let success = xhr.responseText;
			//	console.log('data is sent successfully');
			console.log(success);
			if(success == 'employee'){
			location.href = "http://localhost:8081/ERS/HTML/createNew.html";
			}
			else if(success == 'manager')
			location.href = "http://localhost:8081/ERS/HTML/welcomeManager.html";
			else if(success == 'invalid'){
				document.getElementById("invalid").innerHTML = "Invalid Credentials";
				
			//location.href = "http://localhost:8081/ERS/HTML/error.html";
			}
			
		}
		
	}
	
	xhr.open("POST", "http://localhost:8081/ERS/login");
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(user);
	console.log("send in login:" + toSend);
	//alert("send in login:" + toSend);
	xhr.send(toSend);
	
	
}


  /*<!-- <li class="active"><a href="#">Home</a></li> -->
      <li><a id ="newReimbursement" href="login.html">Create Reimbursement</a></li>
      <li><a id="pastTickets">View Past Tickets</a></li>
*/

//let a= document.getELementById('newReimbursement').addEventListener('click',create)


