function Validate()
{
	var eventCode = document.getElementById("eventCode").value;
	var eventName = document.getElementById("eventName").value;
	var eventMonth = document.getElementById("eventMonth").value;
	var deptId = document.getElementById("deptId").value;
	var description = document.getElementById("description").value;
	
	if(eventCode == "")
	{
		alert("Please Enter Conference Code!");
		document.getElementById("eventCode").focus();
		return false;		
	}
	else if(IsAlphaNum(eventCode)==false)
    {
	   	  alert( "Please Enter Characters and Numbers in Conference Code!");
	   	  document.getElementById("eventCode").focus();
		  return false;
    } 
	else if(eventName == "")
	{
		alert("Please Enter Conference Name!");
		document.getElementById("eventName").focus();
		return false;
	}
	/*else if(IsAlphaNum(eventName)==false)
    {
	   	  alert( "Please Enter Characters and Numbers in Event Name!");
	   	  document.getElementById("eventName").focus();
		  return false;
    } */
	else if(eventMonth == "")
	{
		alert("Please Select Conference Month!");
		document.getElementById("eventMonth").focus();
		return false;
	}
	else if(deptId == "")
	{
		alert("Please Select Department Name!");
		document.getElementById("deptId").focus();
		return false;
	}
	else if(description == "")
	{
		alert("Please Enter Description!");
		document.getElementById("description").focus();
		return false;
	}
	else
	{
		return true;
	}
}