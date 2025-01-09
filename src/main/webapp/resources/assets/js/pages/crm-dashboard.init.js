$(document).ready(function()
{ 
	$.ajax({
	       type: "GET",
	       url: "UserDashboardData",			       
	       success: function(result)
	       {
	    	   if(result != "")
		       {
	    		   var dailyopendeals = result.opendealsdaily;
	    		   var dailyfollowup = result.followupdaily;
	    		   var dailyemail = result.emailsentdaily;
	    		   var dailyresponse = result.responsedaily;
	    		   var dailyclose =  result.closedaily;
	    		   
	    		   var weeklyopendeals = result.opendealsweekly;
	    		   var weeklyfollowup = result.followupweekly;
	    		   var weeklyemail = result.emailsentweekly;
	    		   var weeklyresponse = result.responseweekly;
	    		   var weeklyclose = result.closeweekly;
	    		   
	    		   var monthlyopendeals = result.opendealsmonthly;
	    		   var monthlyfollowup = result.followupmonthly;
	    		   var monthlyemail = result.emailsentmonthly;
	    		   var monthlyresponse= result.reponsemonthly;
	    		   var monthlyclose= result.closemonthly;
	    		   
	    		   var targetdailyopendeals = result.targetopendaily;
	    		   var targetdailyfollowup = result.targetfollowupdaily;
	    		   var targetdailyemail = result.targetemaildaily;
	    		   var targetdailyresponse= result.targetresponsedaily;
	    		   var targetdailyclose= result.targetcloseedaily;
	    		   
	    		   var targetweeklyopendeals = result.targetopenweekly;
	    		   var targetweeklyfollowup = result.targetfollowupweekly;
	    		   var targetweeklyemail = result.targetemailweekly;
	    		   var targetweeklyresponse= result.targetresponseweekly;
	    		   var targetweeklyclose= result.targetcloseeweekly;
	    		   
	    		   var targetmonthlyopendeals = result.targetopenmonthly;
	    		   var targetmonthlyfollowup = result.targetfollowupmonthly;
	    		   var targetmonthlyemail = result.targetemailmonthly;
	    		   var targetmonthlyresponse = result.targetresponsemonthly;
	    		   var targetmonthlyclose = result.targetcloseemonthly;
	    	       
	    		   !function(e){"use strict";var a=function(){};a.prototype.createBarChart=function(e,a,o,r,t,i){Morris.Bar({element:e,data:a,xkey:o,ykeys:r,labels:t,hideHover:"auto",resize:!0,gridLineColor:"#eeeeee",barSizeRatio:.4,barColors:i})},a.prototype.createLineChart=function(e,a,o,r,t,i,n,l,b){Morris.Line({element:e,data:a,xkey:o,ykeys:r,labels:t,fillOpacity:i,pointFillColors:n,pointStrokeColors:l,behaveLikeLine:!0,gridLineColor:"#eef0f2",hideHover:"auto",lineWidth:"3px",pointSize:0,resize:!0,lineColors:b})},a.prototype.createDonutChart=function(e,a,o){Morris.Donut({element:e,data:a,barSize:.2,resize:!0,colors:o})},a.prototype.init=function(){this.createBarChart("morris-bar-example",[{y:"Today",a:dailyopendeals,b:targetdailyopendeals},{y:"Weekly",a:weeklyopendeals,b:targetweeklyopendeals},{y:"Monthly",a:monthlyopendeals,b:targetmonthlyopendeals}],"y",["a","b"],["Actual","Target"],["#02c0ce","#0acf97"]);this.createBarChart("morris-bar-example1",[{y:"Today",a:dailyemail,b:targetdailyemail},{y:"Weekly",a:weeklyemail,b:targetweeklyemail},{y:"Monthly",a:monthlyemail,b:targetmonthlyemail}],"y",["a","b"],["Actual","Target"],["#FF5733","#FFBF33"]);this.createBarChart("morris-bar-example2",[{y:"Today",a:dailyfollowup,b:targetdailyfollowup},{y:"Weekly",a:weeklyfollowup ,b:targetweeklyfollowup},{y:"Monthly",a:monthlyfollowup ,b:targetmonthlyfollowup}],"y",["a","b"],["Actual","Target"],["#cc0099","#4d4dff"]);this.createBarChart("morris-bar-example3",[{y:"Today",a:dailyresponse,b:targetdailyresponse},{y:"Weekly",a:weeklyresponse ,b:targetweeklyresponse},{y:"Monthly",a:monthlyresponse ,b:targetmonthlyresponse}],"y",["a","b"],["Actual","Target"],["#1ac6ff","#cc00cc"]);this.createBarChart("morris-bar-example4",[{y:"Today",a:dailyclose,b:targetdailyclose},{y:"Weekly",a:weeklyclose ,b:targetweeklyclose},{y:"Monthly",a:monthlyclose ,b:targetmonthlyclose}],"y",["a","b"],["Actual","Target"],["#a05195","#d45087"]);this.createLineChart("deals-analytics",[{y:"2010",a:50,b:0},{y:"2011",a:75,b:50},{y:"2012",a:30,b:80},{y:"2013",a:50,b:50},{y:"2014",a:75,b:10},{y:"2015",a:50,b:40},{y:"2016",a:75,b:50},{y:"2017",a:100,b:70}],"y",["a","b"],["Won Deal","Lost Deal"],["0.1"],["#ffffff"],["#999999"],["#1abc9c","#f1556c"]);this.createDonutChart("morris-donut-example",[{label:"Group 1",value:12},{label:"Group 2",value:30},{label:"Group 3",value:20}],["#4fc6e1","#6658dd","#ebeff2"])},e.CRMDashboard=new a,e.CRMDashboard.Constructor=a}(window.jQuery),function(e){"use strict";e.CRMDashboard.init()}(window.jQuery);

		       }
	        },  
	           
	       	error : function(xhr, status, error) 
	       	{
	       		alert("error is " + xhr.responseText);
	       	}
	   });

});

