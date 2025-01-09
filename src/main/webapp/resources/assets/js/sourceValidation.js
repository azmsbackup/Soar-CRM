function Validate()
{
	var sourceDescription = document.getElementById("sourceDescription").value;
	
	if(sourceDescription == "")
	{
		alert("Please Enter Source Description!")
		document.getElementById("sourceDescription").focus();
		return false;
	}
	else if(IsAlphaNum(sourceDescription)==false)
    {
	   	  alert( "Please Enter Characters and Numbers in Source Description!");
	   	  document.getElementById("sourceDescription").focus();
		  return false;
    } 
	else
	{
		return true;
	}
}