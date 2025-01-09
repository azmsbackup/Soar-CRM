function validateClient(){
	var dataCollectedUser = $("#dataCollectedUser").val();
	var clientName = $("#clientName").val();
	var countriesId = $("#countriesId").val();
	var contactPerson = $("#contactPerson").val();
	var mobileNumber = $("#mobileNumber").val();
	var statesId = $("#statesId").val();
	var citiesId = $("#citiesId").val();
	var sourceId = $("#sourceId").val();
	var deptId = $("#deptId").val();
	var email = $("#email").val();
	var zip = $("#zip").val();
	
	if(clientName == ""){
		alert("Please Enter Client Name!");
		document.getElementById("clientName").focus();
		return false;
	}	
	if(countriesId == ""){
		alert("Please Select Country!");
		document.getElementById("countriesId").focus();
		return false;
	}
	
	if(statesId == ""){
		alert("Please Enter State!");
		document.getElementById("statesId").focus();
		return false;
	}
	if(citiesId == ""){
		alert("Please Enter City!");
		document.getElementById("citiesId").focus();
		return false;
	}
	if(zip == ""){
		alert("Please Enter Zip!");
		document.getElementById("zip").focus();
		return false;
	}
	if(Isnum(zip)==false){
	   	  alert( "Please Enter Numbers in Zip!");
	   	  document.getElementById("zip").focus();
		  return false;
    } 
	if(zip != "")	{
		if(countriesId != '231'){
			if(zip.length != 6)	{
				alert( "Please Enter Valid Zip!");
			   	document.getElementById("zip").focus();
				return false;
			}
		}
		else{
			if(zip.length != 5){
				alert( "Please Enter Valid Zip!");
			   	document.getElementById("zip").focus();
				return false;
			}
		}
	}
	
	if(deptId == ""){
		alert("Please Select Department!");
		document.getElementById("deptId").focus();
		return false;
	}
	if(contactPerson == "")	{
		alert("Please Enter Contact Person!");
		document.getElementById("contactPerson").focus();
		return false;
	}
	if(Ischar(contactPerson)==false){
		  alert( "Please Enter Character in Contact Person!");
		  document.getElementById("contactPerson").focus();
		  return false;
    }
	if(mobileNumber !=""&&mobileNumber.length != 12){
		alert("Invalid Mobile Number!");
		document.getElementById("mobileNumber").focus();
		return false;
	}
	if(dataCollectedUser == ""){
		alert("Please Select Data Collected user!");
		document.getElementById("dataCollectedUser").focus();
		return false;
	}
	
	if(sourceId == ""){
		alert("Please Select Source!");
		document.getElementById("sourceId").focus();
		return false;		
	}
	if( email == "" && mobileNumber == "" ){
		alert("Please Enter Phone Number or Email!");
		return false;
	}
	if(email != ""&&IsEmail(email)==false){
		   	  alert( "Please Enter Valid Email Format!");
		   	  document.getElementById("email").focus();
			  return false;
	}	
	else{
		return true;
	}
}

function setAgree(){
	document.getElementById("flag").value = 1;
	document.getElementById('addForm').submit();
}

function enableSubmit(){
	$("#sbutton").show();
	$("#derror").hide();	
}