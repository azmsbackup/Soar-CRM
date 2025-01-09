$(document).ready(function()
{ 
	$.ajax({
	       type: "GET",
	       url: "statuschart",			       
	       success: function(result)
	       {
	    	   if(result != "")
		       {
	    		   var won = result.won;
	    		   var lost = result.lost;
	    		   var inprogress = result.inProgress;
	    		   //var cold = result.cold;
	    		  
	    		   var e,t=function(){$("#status-chart").sparkline([inprogress,lost,won],{type:"pie",width:"300",height:"300",sliceColors:["#6658dd","#f1556c","#1abc9c"]})};t(),$(window).resize(function(i){clearTimeout(e),e=setTimeout(function(){t()},300)})
		       }
	        },  
	           
	       	error : function(xhr, status, error) 
	       	{
	       		alert("error is " + xhr.responseText);
	       	}
	   });

});