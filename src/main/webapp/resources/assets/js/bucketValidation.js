function bucketValidate()
{
	
	var bucketname = document.getElementById("bucketname").value;
	var statusId=document.getElementById("statusId").value;
	if(statusId==""||bucketname=="")
	{
	if(statusId == "")
	{
		alert("Please Enter Status!");
		document.getElementById("statusId").focus();
		return false;	
	}
	if(bucketname=="")
	{
	alert("please Enter bucket name!");
	document.getElementById("bucketname").focus();
	return false;
	}
}
	else if(IsAlphaNum(bucketname)==false)
    {
	   	  alert( "Please Enter Characters and Numbers in Status name!");
	   	  document.getElementById("bucketname").focus();
		  return false;
    } 
	else
	{
		return true;
	}
}