function Validate()
	{
		var deptcode = document.getElementById("departmentCode").value;
		var deptname = document.getElementById("departmentName").value;
		
		if(deptcode == "")
		{
			alert("Please Enter Department Code!");
			document.getElementById("departmentCode").focus();
			return false;			
		}
		else if(deptname == "")
		{
			alert("Please Enter Department name!");
			document.getElementById("departmentName").focus();
			return false;			
		}
		else if(Ischar(deptname)==false)
        {
    	   	  alert( "Please Enter Character in Department name!");
    	   	  document.getElementById("departmentName").focus();
    		  return false;
        } 
		else
		{
			return true;
		}
	}