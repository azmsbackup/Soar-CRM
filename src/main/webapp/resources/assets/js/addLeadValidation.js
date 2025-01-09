function leadValidate() {
	var companyName = $("#companyName").val();
	var conFirstName = $("#conFirstName").val();
	var conLastName = $("#conLastName").val();
	var phoneNumber = $("#phoneNumber").val();
	var statusId = $("#statusId").val();
	var sourceId = $("#sourceId").val();
	var email = $("#email").val();
    
    if(companyName == ""){
    	alert("Please enter company name !");
    	document.getElementById("companyName").focus();
    	return false; 
    }
    else if(conFirstName==""){
    	alert("Please enter contact first name!");
    	document.getElementById("conFirstName").focus();
    	return false; 
    }
    else if(Ischar(conFirstName)==false){
	   	  alert( "Please enter character in contact first name!");
	   	  document.getElementById("conFirstName").focus();
		  return false;
   } 
    else if(conLastName != "" &&Ischar(conLastName)==false)  {
    	   	  alert( "Please enter character in contact last name!");
    	   	  document.getElementById("conLastName").focus();
    		  return false;
    }
    else if(sourceId == "") {
    	alert("Please choose source!");
    	document.getElementById("sourceId").focus();
    	return false; 
    }
    else if(phoneNumber == ""&&email == "") {
    	alert("Please Enter phone number or email!");
    	document.getElementById("phoneNumber").focus();
    	return false;        	
    }
    else if(phoneNumber !=""&&phoneNumber.length != 12){
		alert("Invalid Phone number!");
		console.log("phoneNumber : "+phoneNumber);
		document.getElementById("phoneNumber").focus();
		return false;
	}
    else {
    	return true; 
    }


}