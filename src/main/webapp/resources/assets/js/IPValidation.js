function Validate()
{
	var ip = document.getElementById("IP").value;
	
	if(ip == "")
	{
		alert("Please Enter IP No.!");
		document.getElementById("IP").focus();
		return false;
	}
	else if(IPaddress(ip)==false)
    {
	   	  alert("Please Enter Valid IP!");
	   	  document.getElementById("IP").focus();
		  return false;
    } 
	else
	{
		return true;
	}
	
}