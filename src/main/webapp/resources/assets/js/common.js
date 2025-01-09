/*jQuery(".charactersOnly").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32))
	{
		alert("Please Enter Characters Only!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32);
	}
});

jQuery(".clientNameOnly").keypress(function (e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    
    if (!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 47)) {
        alert("Please Enter Characters Only!");
        return false;
    } else {
        return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 47);
    }
});

jQuery(".noSpecialChar").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57)))
	{
		alert("Special Characters are Not Allowed!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
	}
});

jQuery(".decimalNumber").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k == 8 || k == 46 || (k >= 48 && k <= 57))))
	{
		alert("Please Enter Decimal Numbers!");
		return false;
	}
    else
	{
		return (k == 8 || k == 46 || (k >= 48 && k <= 57));
	}
});

jQuery(".numericOnly").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!(k == 8 || (k >= 48 && k <= 57)))
	{
		alert("Please Enter Numbers Only!");
		return false;
	}
    else
	{
		return (k == 8 || (k >= 48 && k <= 57));
	}
});

jQuery(".phoneNo").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!(k == 8 || (k >= 48 && k <= 57)  || (k==45)))
	{
		alert("Please Enter Valid Characters!");
		return false;
	}
    else
	{
		return (k == 8 || (k >= 48 && k <= 57) || (k==45));
	}
});

jQuery(".address").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 44 && k <= 58)))
	{
		alert("Not Allowed Address format!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 44 && k <= 58));
	}
});

jQuery(".mailid").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 46 || k == 64 || k == 95 || k == 32 || (k >= 48 && k <= 57)))
	{
		alert("Not Allowed Email format!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 46 || k == 64 || k == 95 || k == 32 || (k >= 48 && k <= 57));
	}
});

jQuery(".institute").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46))
	{
		alert("Please Enter Characters and Dot Only!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46);
	}
});

jQuery(".course").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46 || k==45))
	{
		alert("Please Enter valid Characters !");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46 || k==45);
	}
});

jQuery(".Loginname").keypress(function (e) {
	var k;
    document.all ? k = e.keyCode : k = e.which;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8))
	{
		alert("Please Enter Characters Only!");
		return false;
	}
    else
	{
    	return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32);
	}
});*/


jQuery(document).ready(function() {
	jQuery(".charactersOnly").keypress(function (e) {
        var k = e.which || e.keyCode;
        
        // Log key code for debugging
        console.log("Key pressed: " + k);
        
        // Check if the key is not a letter, backspace, or space
        if (!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 47)) {
            // Prevent the invalid key from being entered
            e.preventDefault();
            alert("Please Enter Characters Only!");
        }
    });
    
    
    
   jQuery(".numericOnly").keypress(function (e) {
	 var k = e.which || e.keyCode;
    if(!(k == 8 || (k >= 48 && k <= 57)))
	{
		e.preventDefault();
		alert("Please Enter Numbers Only!");
	}
   });
    
    jQuery(".clientNameOnly").keypress(function (e) {
      var k = e.which || e.keyCode;
      if (!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k == 47)) {
        e.preventDefault();
        alert("Please Enter Characters Only!");
      } 
   });
   
    jQuery(".noSpecialChar").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57)))
		{
			e.preventDefault();
			alert("Special Characters are Not Allowed!");
		}
	});
	
	 jQuery(".decimalNumber").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!((k == 8 || k == 46 || (k >= 48 && k <= 57))))
		{
			e.preventDefault();
			alert("Please Enter Decimal Numbers!");
		}
	});
	
	
	jQuery(".phoneNo").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!(k == 8 || (k >= 48 && k <= 57)  || (k==45)))
		{
			e.preventDefault();
			alert("Please Enter Valid Characters!");
		}
	});

	jQuery(".address").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 44 && k <= 58)))
		{
			e.preventDefault();
			alert("Not Allowed Address format!");
		}
	});

  jQuery(".mailid").keypress(function (e) {
	var k = e.which || e.keyCode;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 46 || k == 64 || k == 95 || k == 32 || (k >= 48 && k <= 57)))
	{
		e.preventDefault();
		alert("Not Allowed Email format!");
	}
  });

 jQuery(".institute").keypress(function (e) {
	var k = e.which || e.keyCode;
    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46))
	{
		e.preventDefault();
		alert("Please Enter Characters and Dot Only!");
	}
 });

	 jQuery(".course").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || k==46 || k==45))
		{
			e.preventDefault();
			alert("Please Enter valid Characters !");
		}
	 });

	jQuery(".Loginname").keypress(function (e) {
		var k = e.which || e.keyCode;
	    if(!((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8))
		{
			e.preventDefault();
			alert("Please Enter Characters Only!");
		}
	});  
});



function IsEmail(email)
{
	var emailReg = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
	var valid = emailReg.test(email);

	if(!valid) {
        return false;
    } else {
    	return true;
    }

	 
}

function IPaddress(ipaddress) 
{  
	var ipReg = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/);
	var valid = ipReg.test(ipaddress);
	if(!valid) 
	{
        return false;
    } 
	else 
    {
    	return true;
    }
}  

function Ischar(char)
{
	var charReg = new RegExp (/^[A-z ]+$/);
	var valid = charReg.test(char);
	
	if(!valid)
		{	
			return false;
		}
	else
		{	
			return true;
		}
}

function Isnum (num)
{
	var numReg = new RegExp (/^[0-9 ]+$/);
	var valid = numReg.test(num);
	
	if(!valid)
		{	
			return false;
		}
	else
		{	
			return true;
		}
}

function IsAlphaNum (alpnum)
{
	var alpnumReg = new RegExp (/^[0-9 a-zA-Z]+$/);
	var valid = alpnumReg.test(alpnum);
	
	if(!valid)
		{	
			return false;
		}
	else
		{	
			return true;
		}
}


function Isdecimal (decimal)
{
	var decReg = new RegExp (/^[0-9]\d*(\.\d+)?$/);
	var valid = decReg.test(decimal);
	
	if(!valid)
		{	
			return false;
		}
	else
		{
			return true;
		}
}


function Isaddress (address)
{
	var addReg = new RegExp (/^[.,':/ A-Za-z0-9]*$/);
	var valid = addReg.test(address);
	
	if(!valid)
		{	
			return false;
		}
	else
		{
			return true;
		}
}

function Isconfig (description)
{
	var desReg = new RegExp (/^[_A-Za-z0-9]*$/);
	var valid = desReg.test(description);
	
	if(!valid)
		{	
			return false;
		}
	else
		{
			return true;
		}
}

function IsconfigVal (value)
{
	var valReg = new RegExp (/^[.,/A-Za-z0-9]*$/);
	var valid = valReg.test(value);
	
	if(!valid)
		{	
			return false;
		}
	else
		{
			return true;
		}
}

/*function IsinstituteVal (value)
{
	var valReg = new RegExp (/^[.-' A-Za-z()]*$/);
	var valid = valReg.test(value);
	
	if(!valid)
		{	
			return false;
		}
	else
		{
			return true;
		}
}*/

function IsPf (alpnum)
{
	var alpnumReg = new RegExp (/^[A-Z/A-Z/0-9/0-9/]+$/);
	var valid = alpnumReg.test(alpnum);
	
	if(!valid)
		{	
			return false;
		}
	else
		{	
			return true;
		}
}
function Isbloodgp (alpnum)
{
	var alpnumReg = new RegExp (/^[ +-A-Za-z0-9]+$/);
	var valid = alpnumReg.test(alpnum);
	
	if(!valid)
		{	
			return false;
		}
	else
		{	
			return true;
		}
}
function isFax (faxNo)
{
	var faxRegex = new RegExp (/^(\+?\d{1,}(\s?|\-?)\d*(\s?|\-?)\(?\d{2,}\)?(\s?|\-?)\d{3,}\s?\d{3,})$/);
	var valid = faxRegex.test(faxNo);
	
	if(!valid)
	{	
		return false;
	}
	else
	{	
		return true;
	}
}
// /^[/-.0-9]*$/

