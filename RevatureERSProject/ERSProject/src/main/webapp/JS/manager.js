/**
 * 
 */
 
 window.onload = function(){
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
		//alert("User fname, lanme"+ success);
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

	function loadReimbursements() {
		
	//	alert('inside loadreimburse');
		console.log('inside reimburse');
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				
				let reimbursements = JSON.parse(xhr.responseText);

				for(let reims of reimbursements) {
					//alert(reims);
					console.log(reims);
					reimburseLists(reims);
					
				}
				
			}
			
		};
		
		xhr.open("GET", "http://localhost:8081/ERS/manager", true);
		xhr.send();
		
	}
	
	
	
	function reimburseLists(reims){
	
	
	
		
		//	alert('inside reimbList method');
		let res="";  let recImg="";
		if(reims.reimb_rec != null)
		{
			 res = reims.reimb_rec.split("\\");
			  recImg= res.pop();
		}
	//alert(reims.reimb_rec);
	
	
	
	
	
	//let recImg= "foodBill.jpg";
	let f= recImg.split(".");
	//alert("f1"+ f[1]);
	let car = {fname:f[0], lname:f[1]};
	//alert("Car obj is "+ car);
	
	let btnid= `${reims.reimb_id}+btn3`;
	//alert(btnid);
/*	let a= "E:/Divya/Revature/Projects/ERSProject/ersproject/ERS/src/main/webapp/images/"+recImg;
//	alert("a is "+ a);*/
	
	//	alert("images/"+recImg.split(".").pop());
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
		
var submitTime = new Date(reims.reimb_submitted);
		//var resTime = new Date(reims.reimb_resolved);
		
	//	alert("subTime" + submitTime);
		if(reims.reimb_resolved == null)
   {
   resTime=0;
   }
   else
   {
    resTime=new Date(reims.reimb_resolved);;
   }
			 var tableRef = document.getElementById('customers').getElementsByTagName('tbody')[0];
			 if(reims.reimb_status_id ==1){
		   var tempRow= `<tr id = ${reims.reimb_id}> `+		    
        "<td>" +reims.reimb_id+ "</td>" + 
        "<td>" +reims.reimb_amount+ "</td>"+
        "<td>" +submitTime+ "</td>"+
        "<td>" +resTime+ "</td>"+
        "<td>" +reims.reimb_description+ "</td>"+
        "<td>" +reims.author+ "</td>"+
        "<td>" + status +"</td>"+
        "<td>" +type+ "</td>"+
        `<td> <button class ='btn btn-success' id = ${reims.reimb_id} value= 'approve' onclick= 'Function1(${reims.reimb_id},2)'>Approve</button>`+
     		  ` <button class = 'btn btn-danger' id = ${reims.reimb_id}  value = 'deny' onclick= 'Function1(${reims.reimb_id},3)'>Deny</button>`+
     		  " </td>" +
     		  
     		   "<td>" +
     		  `<button   class = 'btn btn-info' value='receipt' onclick="window.open('http://localhost:8081/ERS/images/${recImg}','_blank')">Receipt</button>`+
     	 "</td>" +
     		  
     		  "</tr>"   
        ;
        
		 }
		 else{
		 
		  var tempRow= `<tr id = ${reims.reimb_id}> `+		    
        "<td>" +reims.reimb_id+ "</td>" + 
        "<td>" +reims.reimb_amount+ "</td>"+
        "<td>" +submitTime+ "</td>"+
        "<td>" +resTime+ "</td>"+
        "<td>" +reims.reimb_description+ "</td>"+
        "<td>" +reims.author+ "</td>"+
        "<td>" + status +"</td>"+
        "<td>" +type+ "</td>"+
       "<td>" +"-"+
     		  " </td>" +
     		   "<td>" +
     		  `<button   class = 'btn btn-info' value='receipt' onclick="window.open('http://localhost:8081/ERS/images/${recImg}','_blank')">Receipt</button>`+
     	 "</td>" +
     		  
     		  "</tr>"   
        ;
        
		 
		 }
        
        
        var newRow = tableRef.insertRow(tableRef.rows.length);
        newRow.innerHTML = tempRow;

		
	}
	
		
	
	
	
	//////////////////// New Code
	
/*	document.getElementById("button1").addEventListener("click", Function1);
	document.getElementById("button2").addEventListener("click", Function1);*/
	
	function Function1(reim_id,status){
//	alert("Divya function1 remim id is"+ reim_id);
//	alert("Divya function1 remim status is"+ status);
	 //alert("Hello Divya Inside function1 and selected  button value is "+ document.getElementById(`${reim_id}`).value);
	
	var stat = {
				
			reimb_id: reim_id,
			reimb_status_id: status
				
		}
	
	
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function () {
			
			if(xhr.readyState == 4 && xhr.status == 200) {
				let success = xhr.responseText;
				//$(`status${r_id}`).html(status);
				console.log(success);
				//refreshStatus();
				
				window.location.reload();
				//deleteRow(reim_id);
				
			}
			
		}
		
		xhr.open("PUT", "http://localhost:8081/ERS/login");
		xhr.setRequestHeader("Content-type", "application/json");
		var toSend = JSON.stringify(stat);
		//alert(toSend);
		xhr.send(toSend);
	
	
	
	}
	
	


	
	
	function filterByStatus(){
	var a = parseInt(document.getElementById('statusId').value);
	//alert("selcted sttaus id is "+ a);


	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
	if (xhr.status == 200 && xhr.readyState == 4) {
			let reimbursements = JSON.parse(xhr.responseText);
				//	alert("got response from new servlet");
				//	$("reimInfo").empty();
				$("#customers tbody").empty();
				//deleteTbody();
			//	$("reimInfo").children().remove()
				for(let reims of reimbursements) {
				//	alert(reims);
					console.log(reims);
					reimburseLists(reims);
					
				}
				
	}
	
	}

	xhr.open("GET", `http://localhost:8081/ERS/byStatus?statusId=${a}`);
	//xhr.setRequestHeader("Content-type", "application/json");
	//var toSend = JSON.stringify(reimb);
	console.log("sending status id in request:" + a);
	//alert("send in login:" + toSend);
	xhr.send();
	
	}
	
	function deleteTbody(){
		
		$("#customers tbody").empty();
		console.log("In delete t body method");
	}
	
