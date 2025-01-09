function Validate() 
{
    //var userType =  document.getElementById("userType").value;
	 var firstName =  document.getElementById("firstName").value;
	 var roleId = document.getElementById("roleId").value;
	 var lastName = document.getElementById("lastName").value;
	
   // var employeeId = document.getElementById("employeeId").value;
   
    var loginName = document.getElementById("loginName").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var emailId = document.getElementById("emailId").value;
    
    /*if(userType == "--Select--")
    {
    	alert("Please Select User Type!");
    	document.getElementById("userType").focus();
    	return false;        	
    }*/	
    if(firstName == "")
    {
    	alert("Please Enter First Name!");
    	document.getElementById("firstName").focus();
    	return false; 
    }
    else if(Ischar(firstName)==false)
    {
	   	  alert( "Please Enter Character in First name!");
	   	  document.getElementById("firstName").focus();
		  return false;
    } 
    else if(lastName != "")
    {
    	if(Ischar(lastName)==false)
        {
    	   	  alert( "Please Enter Character in last name!");
    	   	  document.getElementById("lastName").focus();
    		  return false;
        } 
    }
    if(roleId == "")
    {
    	alert("Please Select User Role!");
    	document.getElementById("roleId").focus();
    	return false;        	
    }
   /* else if(employeeId == "")
    {
    	alert("Please Select Employee Name!");
    	document.getElementById("employeeId").focus();
    	return false;        	
    }*/
    
    else if(loginName == "")
    {
    	alert("Please Enter Login Name!");
    	document.getElementById("loginName").focus();
    	return false; 
    }
    else if(IsAlphaNum(loginName)==false)
    {
	   	  alert( "Please Enter Character and Numbers in Login name!");
	   	  document.getElementById("loginName").focus();
		  return false;
    } 
    else if(password == "")
    {
    	alert("Please Enter Password!");
    	document.getElementById("password").focus();
    	return false; 
    }
    else if(roleId == "1")
    {
    	if(confirmPassword == "")
        {
        	alert("Please Enter High Security Password!");
        	document.getElementById("confirmPassword").focus();
        	return false; 
        }
    }
    
   /* else if (password != confirmPassword) 
    {
        alert("Passwords do not match!.");
        return false;
    }*/
    else if(emailId == "")
    {
    	alert("Please Enter Email ID!");
    	document.getElementById("emailId").focus();
    	return false; 
    }
    else if(IsEmail(emailId)==false)
    {
	   	  alert( "Please Enter Valid Email ID!");
	   	  document.getElementById("emailId").focus();
		  return false;
    }   	
    
    else
    {
    	return true; 
    }
}