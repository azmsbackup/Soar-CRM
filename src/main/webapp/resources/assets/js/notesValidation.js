function Validate1()
{
	var notes = document.getElementById("notesarea").value;
	var notesDate = document.getElementById("notesDate").value;
	var statusId = document.getElementById("statusId").value;
	var followUpDate = document.getElementById("followUpDate").value;
	var appointmentWith = document.getElementById("appointmentWith").value;
	var bucketId = document.getElementById("bucketId").value;
	alert("statusId outside  "+ statusId);
	
	if(notesDate == "")
	{
		alert("Please Select Notes Date!");
		document.getElementById("notesDate").focus();
		return false;
	}
	else if(statusId == "")
	{
		alert("Please Select Status!");
		document.getElementById("statusId").focus();
		return false;
	}
	else if(statusId == "3")
	{
		alert("statusId "+ statusId);
		if(followUpDate == "")
		{
			alert("Please Select FollowUp Date!");
			document.getElementById("followUpDate").focus();
			return false;
		}
		else if(appointmentWith == "")
		{
			alert("Please Select Followup With!");
			document.getElementById("appointmentWith").focus();
			return false;
		}
	}
	else if(bucketId == "")
	{ 
		alert("Please Select Bucket Name!");
		document.getElementById("bucketId").focus();
		return false;
	}
	else if(notes == "")
	{
		alert("Please Enter Notes!");
		document.getElementById("notesarea").focus();
		return false;
	}
	else
	{
		return true;
	}


}