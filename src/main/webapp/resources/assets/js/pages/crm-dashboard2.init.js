$(document).ready(function()
{ 	
	$.ajax({
	       type: "GET",
	       url: "DashboardData",			       
	       success: function(result)
	       {
	    	   if(result != "")
		       {    		   
	    		   var billing_open = result.billing_open;
	    		   var billing_email = result.billing_email;
	    		   var billing_followup = result.billing_followup;
	    		   
	    		   var coding_open = result.coding_open;
	    		   var coding_email = result.coding_email;
	    		   var coding_followup = result.coding_followup;
	    		   
	    		   var response_year = result.response_year;
	    		   var response_month = result.response_month;
	    		   var closed_year = result.closed_year;
	    		   var closed_month = result.closed_month
	    	       
	    		   !function(e){"use strict";var a=function(){};a.prototype.createBarChart=function(e,a,o,r,t,i){Morris.Bar({element:e,data:a,xkey:o,ykeys:r,labels:t,hideHover:"auto",resize:!0,gridLineColor:"#eeeeee",barSizeRatio:.4,barColors:i})},a.prototype.createLineChart=function(e,a,o,r,t,i,n,l,b){Morris.Line({element:e,data:a,xkey:o,ykeys:r,labels:t,fillOpacity:i,pointFillColors:n,pointStrokeColors:l,behaveLikeLine:!0,gridLineColor:"#eef0f2",hideHover:"auto",lineWidth:"3px",pointSize:0,resize:!0,lineColors:b})},a.prototype.createDonutChart=function(e,a,o){Morris.Donut({element:e,data:a,barSize:.2,resize:!0,colors:o})},a.prototype.init=function(){this.createBarChart("medical",[{y:"Medical Billing",a:billing_open,b:billing_email,c:billing_followup},{y:"Medical Coding",a:coding_open,b:coding_email,c:coding_followup}],"y",["a","b","c"],["Open","Email Sent","Follow Up"],["#02c0ce","#CCFFBB","#0acf97"]);this.createBarChart("productivity",[{y:"Response",a:response_year,b:response_month},{y:"Closed",a:closed_year,b:closed_month}],"y",["a","b"],["year","month"],["#FF5733","#FFBF33"]);this.createLineChart("deals-analytics",[{y:"2010",a:50,b:0},{y:"2011",a:75,b:50},{y:"2012",a:30,b:80},{y:"2013",a:50,b:50},{y:"2014",a:75,b:10},{y:"2015",a:50,b:40},{y:"2016",a:75,b:50},{y:"2017",a:100,b:70}],"y",["a","b"],["Won Deal","Lost Deal"],["0.1"],["#ffffff"],["#999999"],["#1abc9c","#f1556c"]);this.createDonutChart("morris-donut-example",[{label:"Group 1",value:12},{label:"Group 2",value:30},{label:"Group 3",value:20}],["#4fc6e1","#6658dd","#ebeff2"])},e.CRMDashboard=new a,e.CRMDashboard.Constructor=a}(window.jQuery),function(e){"use strict";e.CRMDashboard.init()}(window.jQuery);

		       }
	        },  
	           
	       	error : function(xhr, status, error) 
	       	{
	       		alert("error is " + xhr.responseText);
	       	}
	   });

});

