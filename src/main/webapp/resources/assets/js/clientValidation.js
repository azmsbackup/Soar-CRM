function Validate()
{
	var clientName = document.getElementById("clientName").value;
	
	var address = document.getElementById("address").value;
	
	var countriesId = document.getElementById("countriesId").value;
	
	var statesId = document.getElementById("statesId").value;
	 
	var citiesId = document.getElementById("citiesId").value;
	var zip = document.getElementById("zip").value;
	var deptId = document.getElementById("deptId").value;
	var contactPerson = document.getElementById("contactPerson").value;
	var phoneNumber = document.getElementById("phoneNumber").value;
	var mobileNumber = document.getElementById("mobileNumber").value;
	var alterMobileNumber = document.getElementById("alternateMobileNumber").value;
	
	//extension = document.getElementById("extension").value;
	var fax = document.getElementById("fax").value;
	var assignToUser = document.getElementById("assignToUser").value;
	var dataCollectedUser = document.getElementById("dataCollectedUser").value;

	//var statusId = document.getElementById("statusId").value;

	//var followUpDate = document.getElementById("followUpDate").value;
	
	
	var sourceId =  document.getElementById("sourceId").value;
	//var eventId =  document.getElementById("eventId").value;
	var email = document.getElementById("email").value;
	var website = document.getElementById("website").value;
	var annualRevenue = document.getElementById("annualRevenue").value;
	
	var description = document.getElementById("description").value;

	
	if(clientName == "")
	{
		alert("Please Enter Client Name!");
		document.getElementById("clientName").focus();
		return false;
	}
	/*else if(Ischar(clientName)==false)
    {
	   	  alert( "Please Enter Characters in Client name!");
	   	  document.getElementById("clientName").focus();
		  return false;
    } */
	else if(address != "")
	{
		 if(Isaddress(address)==false)
	    {
		   	  alert( "Please Enter Valid Character in Address!");
		   	  document.getElementById("address").focus();
			  return false;
	    } 
	}
	if(countriesId == "")
	{
		alert("Please Select Country!");
		document.getElementById("countriesId").focus();
		return false;
	}
	
	if(statesId == "")
	{
		alert("Please Enter State!");
		document.getElementById("statesId").focus();
		return false;
	}
	if(citiesId == "")
	{
		alert("Please Enter City!");
		document.getElementById("citiesId").focus();
		return false;
	}
	/*if(zip == "")
	{
		alert("Please Enter Zip!");
		document.getElementById("zip").focus();
		return false;
	}*/
	

	if(zip != "")
	{
		if(Isnum(zip)==false)
	    {
		   	  alert( "Please Enter Numbers in Zip!");
		   	  document.getElementById("zip").focus();
			  return false;
	    }
		
		if(countriesId != '231')
		{
			if(zip.length != 6)
			{
				alert( "Please Enter Valid Zip!");
			   	document.getElementById("zip").focus();
				return false;
			}
		}
		else
		{
			if(zip.length != 5)
			{
				alert( "Please Enter Valid Zip!");
			   	document.getElementById("zip").focus();
				return false;
			}
		}
	}
	
	if(deptId == "")
	{
		alert("Please Select Department!");
		document.getElementById("deptId").focus();
		return false;
	}
	if(contactPerson == "")
	{
		alert("Please Enter Contact Person!");
		document.getElementById("contactPerson").focus();
		return false;
	}
	if(sourceId == ""){
		alert("Please Select Source!");
		document.getElementById("sourceId").focus();
		return false;
	}
/*	if(Ischar(contactPerson)==false)
    {
		  alert( "Please Enter Character in Contact Person!");
		  document.getElementById("contactPerson").focus();
		  return false;
    } */
	if(phoneNumber == "" && email == "" && mobileNumber == "" &&  alterMobileNumber == "")
	{
		alert("Please Enter Phone Number or Email!");
		return false;
	}
	if(phoneNumber !="")
	{
		
		//alert("phoneNumber"+phoneNumber);
		//if(countriesId != '231')
		//{
			if(phoneNumber.length != 12)
			{
				alert("Invalid Work Phone Number!");
				document.getElementById("phoneNumber").focus();
				return false;
			}
		/*}
		else
		{
			if(phoneNumber.length != 12)
			{
				alert("Invalid Work Phone Number!");
				document.getElementById("phoneNumber").focus();
				return false;
			}
		}*/
		
	}
	if(mobileNumber !="")
	{
		//if(countriesId != '231')
		//{
			if(mobileNumber.length != 12)
			{
				alert("Invalid Mobile Number!");
				document.getElementById("mobileNumber").focus();
				return false;
			}
		/*}
		else
		{
			if(mobileNumber.length != 12)
			{
				alert("Invalid Mobile Number!");
				document.getElementById("mobileNumber").focus();
				return false;
			}
		}*/
	}
	if(alterMobileNumber !="")
	{
		//if(countriesId != '231')
		//{
			if(alterMobileNumber.length != 12)
			{
				alert("Invalid Alernate Mobile Number!");
				 document.getElementById("alternateMobileNumber").focus();
				 return false;
			}
		/*}
		else
		{
			if(alterMobileNumber.length != 12)
			{
				alert("Invalid Alernate Mobile Number!");
				 document.getElementById("alternateMobileNumber").focus();
				 return false;
			}
		}*/
	}
	
	
	/*if(phoneNumber != "")
	{
		 if(extension == "")
			{
				alert("Please Enter Extension!");
				document.getElementById("extension").focus();
				return false;
			}
	}*/
	
	
		if(dataCollectedUser == "")
		{
			alert("Please Select Data Collected user!");
			document.getElementById("dataCollectedUser").focus();
			return false;
		}
		
	
	if(sourceId == "")
	{
		alert("Please Select Source!");
		document.getElementById("sourceId").focus();
		return false;		
	}
	/*else if(sourceId == "4")
	{
		if(eventId == "")
		{
			alert("Please Select Event!")
			document.getElementById("eventId").focus();
			return false;
		}
		
	}
	else if(Isnum(phoneNumber)==false)
    {
	   	  alert( "Please Enter Numbers in Phone Number!");
	   	  document.getElementById("phoneNumber").focus();
		  return false;
    }
	else if(fax == "")
	{
		alert("Please Enter Fax!");
		document.getElementById("fax").focus();
		return false;
	}*/
	if(fax != "")
	{
		 //if(Isnum(fax)==false)
		 if(fax.length != 12)
		 {
		   	  alert( "Please Enter valid Fax!");
		   	  document.getElementById("fax").focus();
			  return false;
		 }
	}
	if(email != "")
	{
		 if(IsEmail(email)==false)
		 {
		   	  alert( "Please Enter Valid Email Format!");
		   	  document.getElementById("email").focus();
			  return false;
		 }
	}
	/*else if(statusId == "")
	{
		alert("Please Select Status Name!");
		document.getElementById("statusId").focus();
		return false;
	}
	else if(website == "")
	{
		alert("Please Enter Website!");
		document.getElementById("website").focus();
		return false;
	}
	else if(sourceId == "")
	{
		alert("Please Select Source Name!");
		document.getElementById("sourceId").focus();
		return false;
	}*/
	if(annualRevenue != "")
	{
		if(Isdecimal(annualRevenue)==false)
	    {
		   	  alert( "Please Enter Numbers in Annual Revenue!");
		   	  document.getElementById("annualRevenue").focus();
			  return false;
	    }
	}
	/*if(website != "")
	{
		if(website.endsWith(".com") || website.endsWith(".co")  || website.endsWith(".org")
 				|| website.endsWith(".net")  || website.endsWith(".int") || website.endsWith(".edu")
 				|| website.endsWith(".gov")  || website.endsWith(".mil") || website.endsWith(".us")
 				|| website.endsWith(".xyz")  || website.endsWith(".io")  || website.endsWith(".healthcare")
 				|| website.endsWith(".ca"))
 		{
			return true;
 		}
		else
		{
			 alert( "Please Enter Valid Website Format!");
		   	 document.getElementById("website").focus();
			 return false;
		}
	}*/
	else
	{
		return true;
	}
}

function setAgree()
{
	document.getElementById('addForm').action = "insertDupclient"; 
	document.getElementById('addForm').submit();
}

function enableSubmit()
{
	$("#sbutton").show();
	$("#derror").hide();
	
}