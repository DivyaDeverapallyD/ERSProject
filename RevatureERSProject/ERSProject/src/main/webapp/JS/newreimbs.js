/**
 * 
 */
 

/*window.onload = function() {

	console.log('js loaded');
	loadUser();
	loadReimbursements();
}*/


document.addEventListener('DOMContentLoaded', abc);
function abc()
{
console.log('js loaded');
	loadUser();
	loadReimbursements();
}
function loadUser(){
//alert('insdie load user ');

var xhr = new XMLHttpRequest();
xhr.onreadystatechange = function () {
	if (xhr.status == 200 && xhr.readyState == 4) {
	//alert('inside sussess on loaduser');
		let success = xhr.responseText;
		console.log("Pritwrte output"+success);
	//	alert("User fname, lanme"+ success);
	//	loadReimbursements();
	document.getElementById("name").innerHTML =  success;
	}
};
	xhr.open("GET", "http://localhost:8081/ERS/login");
	//xhr.setRequestHeader("Content-type", "application/json");
	
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//alert("send in login:" + toSend);
	xhr.send();


}




//submit
let but1 = document.getElementById('submit').addEventListener('click', subReimFunction)



function subReimFunction() {
	let a = document.getElementById('reimb_amount').value;
	let b = document.getElementById('reimb_desc').value;
	let c = document.getElementById('reimb_type').value;
	console.log(a);
	console.log(b);
	console.log(c);
	console.log('hai');


let res = document.getElementById('upload').value.split("\\");
		let  recImg= res.pop();


	var reimb = {

		reimb_amount: document.getElementById('reimb_amount').value,
		reimb_description: document.getElementById('reimb_desc').value,
		reimb_author: 5,
		reimb_resolver: 8,
		reimb_status_id: 1,
		reimb_type_id: document.getElementById('reimb_type').value,
		reimb_rec: recImg
	}

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
	if (xhr.status == 200 && xhr.readyState == 4) {
		let success = xhr.responseText;
		console.log(success);
	//	alert(success);
		document.getElementById("successReimb").innerHTML = "Reimbursement Request is Submitted Successfully";
		//location.href = "http://localhost:8081/ERS/HTML/pastTickets.html";
/*	abc();
	//document.addEventListner('DOMContentLoaded',loadReimbursements);
		// window.location.reload();
		
		//successReimb
		
		/*setTimeout(function(){
		location.reload();
		},5000)*/;
	}
}
	xhr.open("POST", "http://localhost:8081/ERS/newReim",true);
	xhr.setRequestHeader("Content-type", "application/json");
	var toSend = JSON.stringify(reimb);
	console.log("send in login:" + toSend);
	//alert("send in login:" + toSend);
	xhr.send(toSend);


}

	function loadReimbursements() {
		
	//	alert("in loadReimbursements ");
		console.log('inside reimburse');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
		//		alert('inside readystate of loadReim');
	//			$('#view').html(xhr.responseText);
				let reimbursements = JSON.parse(xhr.responseText);
				console.log('1', xhr.responseText);
				for(let reims of reimbursements) {
					
					//console.log(reims);
					reimburseLists(reims);
					
				}
				
			}
			
		}
		
		xhr.open("GET",  "http://localhost:8081/ERS/newReim");
		xhr.send();
		
	}
	
	
	
		function reimburseLists(reims){
		
	//	alert('insdie reimb list');
		let status = "";
		let type = "";
		
		switch(reims.reimb_status_id) {
		    case 1:
		        status = "Pending";
		        break;
		        
		    case 2:
		        status = "Approved";
		        break;
		    
		    case 3:
		        status = "Denied";
		        break;
		     
		    default:
		    	break;
		
		}

		 switch(reims.reimb_type_id) {
		 
		    case 1:
		        type = "Lodging";
		        break;
		        
		    case 2:
		        type = "Travel";
		        break;
		    
		    case 3:
		        type = "Food";
		        break;
		    
		    case 4:
		        type = "Other";
		        break;
		        
		    default:
		    	break;
		    
		 
		}
		console.log('in remiblooop ');
	//	alert(reims.reimb);
	
	//alert(reims.reimb_id + "---"+reims.reimb_amount +"---"+ reims.reimb_resolved+"---"+ reims.reimb_description);
	
	//alert('type of reims is '+ typeof(reims));
	  var tableRef = document.getElementById('customers').getElementsByTagName('tbody')[0];

   var submitTime = new Date(reims.reimb_submitted);
   if(reims.reimb_resolved == null)
   {
   resTime=0;
   }
   else
   {
    resTime=new Date(reims.reimb_resolved);;
   }
  // var resolvedTime = new Date(reims.reimb_resolved);
        //insert Row
      //  alert('inside loop')
        tableRef.insertRow().innerHTML = 
        "<td>" +reims.reimb_id+ "</td>" + 
        "<td>" +reims.reimb_amount+ "</td>"+
        "<td>" +submitTime+ "</td>"+
        "<td>" +resTime+ "</td>"+
        "<td>" +reims.reimb_description+ "</td>"+
       /* "<td>" +reims.reimb_author+ "</td>"+*/
        "<td>" +status+ "</td>"+
        "<td>" +type+ "</td>";
  //  }
	
	
	
	
	
	
	
	
	
	
	
		
		
	}
