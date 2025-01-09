function Validate()
{
	var statusDescription = document.getElementById("statusDescription").value;
	
	if(statusDescription == "")
	{
		alert("Please Enter Status Description!");
		document.getElementById("statusDescription").focus();
		return false;		
	}
	/*else if(IsAlphaNum(statusDescription)==false)
    {
	   	  alert( "Please Enter Characters and Numbers in Status Description!");
	   	  document.getElementById("statusDescription").focus();
		  return false;
    } */
	else
	{
		return true;
	}
}