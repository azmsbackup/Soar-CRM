<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>  
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script> 
<script src="resources/assets/js/jquery-1.11.3.min.js"></script> 
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->

<div class="content-page">
	<div class="content">

		<!-- Start Content-->
		<div class="container-fluid">

			<!-- start page title -->
			<div class="row">
				<div class="col-12">
					<div class="page-title-box">
						<div class="page-title-right">
							<ol class="breadcrumb m-0">
								<li class="breadcrumb-item"><a href="javascript: void(0);">Allzone</a></li>
								<li class="breadcrumb-item"><a href="javascript: void(0);">CRM</a></li>
								<li class="breadcrumb-item active">Add Target</li>
							</ol>
						</div>
						<h4 class="page-title">Add Target</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="col-lg-8">

							<form:form action="targetSubmit" method="post" modelAttribute="target" class="parsley-examples">
							<form:hidden path="userId" value="${target.userId}"/>							
								<c:choose>
									<c:when test="${sessionScope.savedsuccess == 'success'}">
										<div class="modal-dialog modal-sm" role="document">
											<div class="modal-content">
												<div class="modal-body">
													<p>Target has been Saved Successfully!</p>
												</div>
												<div class="modal-footer">
													<a href="managetarget"><button type="button" class="btn btn-primary">OK</button></a>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${sessionScope.savedsuccess != ''}">
											<div
												class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
												<span class="badge badge-pill badge-danger">Failed</span>
												Invalid Data!
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
								<div class="form-group">
											<label for="userId">User Name :</label>
											<label for="userId">${target.userName}</label>												
								</div>	
							 	<div class="row">
									<div class="col-md-6">	
								 						
										<div class="form-group">
											<label for="text-input">Year<span class="text-danger">*</span> </label>
											<form:select type="select" path="year" id="year"  class="form-control">
												<form:options items="${target.yearlist}" />
											</form:select>
										</div> 								
										<div class="form-group">
                                   			<label for="eventMonth">Month <span class="text-danger">*</span></label>
                                    			 <form:select class="form-control" id="month" path="month">
				                                    <form:option value="" label="--Select --"/>
													<form:option value="1">January</form:option>
													<form:option value="2">February</form:option>
													<form:option value="3">March</form:option>
													<form:option value="4">April</form:option>
													<form:option value="5">May</form:option>
													<form:option value="6">June</form:option>
													<form:option value="7">July</form:option>
													<form:option value="8">August</form:option>
													<form:option value="9">September</form:option>
													<form:option value="10">October</form:option>
													<form:option value="11">November</form:option>
													<form:option value="12">December</form:option>									                                                            
                                     			</form:select>
                                 		 </div> 
                                	</div>                                
									<div class="col-md-6">		 
		                                 <div class="form-group">
											<label for="eventMonth">Target Source<span class="text-danger">*</span></label> 
											<form:select type="select" path="statusId" class="form-control" id="statusId">
												<form:option value="" label="--Select--" />
												<form:options items="${target.statusList}" itemValue="statusId"	itemLabel="statusName" />
											</form:select>
										</div>
										<div class="form-group">
											<label for="dailyTarget">Daily Target
											<span class="text-danger">*</span></label>
											 <input type="text"	name="dailyTarget" parsley-trigger="change" placeholder="Enter Daily Target" maxlength="100"
												class="form-control" id="dailyTarget">
										</div>
									</div>
								</div>							
								<div class="form-group text-right m-b-0">
									<button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return validate()">Submit</button>
									<button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">
                                                Back
                                    </button>
								</div>

							</form:form>
						</div>
					</div>
					<!-- end card-box -->
				</div>
				<!-- end col -->


			</div>
			<!-- end row -->
		</div>
		<!-- container -->

	</div>
	<!-- content -->
</div>
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->



<script>
	function cancel()
	{
		location.href = "managetarget";
	}
	/* function userName()
	{
		alert("User Name Cann't be Change!");
		return false;
	} */
	function validate()
	{
		var statusId = document.getElementById("statusId").value;		
		var month = document.getElementById("month").value;
		var year = document.getElementById("year").value;
		var dailyTarget = document.getElementById("dailyTarget").value;
		
		var currentYear = new Date().getFullYear();
		var currentMonth = new Date().getMonth();
		currentMonth = currentMonth+1;
		
		if(month == "")
		{
			alert("Please Select Month!");
			document.getElementById("month").focus();
			return false;
		}
		if (currentYear > year )
		{
			alert("Please Choose Correct Year!");
			document.getElementById("year").vaule="";
			return false;
		}
		if(currentMonth > month)
		{
			alert("Please Choose Correct Month!");
			return false;
		}
		if(dailyTarget == "")
		{
			alert("Please Enter Target Value!");
			document.getElementById("dailyTarget").focus();
			return false;
		}
		if(statusId == "")
		{
			alert("Please Select Target Source!");
			document.getElementById("statusId").focus();
			return false;
		}
		else
		{
			return true;
		}
	}
	
</script>
<script>

$("#statusId").change(function()
{
	var month = document.getElementById("month").value;
	var year =  document.getElementById("year").value;
	var userId = document.getElementById("userId").value;
	var statusId = document.getElementById("statusId").value

	var months = [
	    'January', 'February', 'March', 'April', 'May',
	    'June', 'July', 'August', 'September',
	    'October', 'November', 'December'
	    ];
	var monthname = months[month - 1]
	
	var status = "";
	
	if(statusId == '6')
	{
		status = "Data Collection"
	}
	if(statusId == '4')
	{
		status = "Email"
	}
	if(statusId == '3')
	{
		status = "Follow-up(calls)"
	}
	if(statusId == '9')
	{
		status = "New Leads(Response)"
	}

	
	jQuery.ajax({
        type: "GET",
        url: "checkTarget",
        data: 
        {
        	month : month,
        	year : year,
        	userId : userId,
        	statusId : statusId
        },
        success: function(result)
        {	
        	if(result != "")
		    {
        		alert("For " +monthname+" - " +year+ " Target source " +status+ " already Exist!");
        		$("#month").val("");
        		$("#statusId").val("");
        		return false;
		    }
        },
        error : function(xhr, status, error) {
            alert("error is " + xhr.responseText);
        }
    });
	
	
});

$("#month").change(function()
		{
			var month = document.getElementById("month").value;
			var year =  document.getElementById("year").value;
			var userId = document.getElementById("userId").value;
			var statusId = document.getElementById("statusId").value

			var months = [
			    'January', 'February', 'March', 'April', 'May',
			    'June', 'July', 'August', 'September',
			    'October', 'November', 'December'
			    ];
			var monthname = months[month - 1]
			
			var status = "";
			
			if(statusId == '6')
			{
				status = "Data Collection"
			}
			if(statusId == '4')
			{
				status = "Email"
			}
			if(statusId == '3')
			{
				status = "Follow-up(calls)"
			}
			if(statusId == '9')
			{
				status = "New Leads(Response)"
			}

			
			jQuery.ajax({
		        type: "GET",
		        url: "checkTarget",
		        data: 
		        {
		        	month : month,
		        	year : year,
		        	userId : userId,
		        	statusId : statusId
		        },
		        success: function(result)
		        {	
		        	if(result != "")
				    {
		        		alert("For " +monthname+" - " +year+ " Target source " +status+ " already Exist!");
		        		$("#month").val("");
		        		$("#statusId").val("");
		        		return false;
				    }
		        },
		        error : function(xhr, status, error) {
		            alert("error is " + xhr.responseText);
		        }
		    });
			
			
		});


document.getElementById("dailyTarget").addEventListener("keydown", function(event) {
    // Allow only numeric values and control keys like backspace, tab, etc.
    if (event.key === "Backspace" || event.key === "Tab" || event.key === "ArrowLeft" || event.key === "ArrowRight") {
        return;  // Allow these keys
    }

    // Prevent non-numeric input
    if (!/^\d$/.test(event.key)) {
        event.preventDefault();
    }
});

</script>
<%@include file="crm-footer.jsp"%>