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
								<li class="breadcrumb-item active">Edit Notes</li>
							</ol>
						</div>
						<h4 class="page-title">Edit Notes</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="col-lg-12">
							<form:form action="updatenotes" method="post" modelAttribute="notes"
								class="parsley-examples">
								<form:hidden path="notesId" id="notesId" value="${notes.notesId}" />
								<form:hidden path="traceNo" id="hiddenTraceNo" value="${notes.traceNo}" />
								<form:hidden path="clientName" id="clientName" value="${notes.clientName}" />
								<form:hidden path="userId" id="userId" value="${notes.userId}"/>
                                <form:hidden path="hiddenUserId" id="hiddenUserId" value="${notes.hiddenUserId}"/>
                                  <form:hidden path="" id="statusName" value="${notes.statusName}"/>
                                  <form:hidden path="frompage" id="frompage" value="${notes.frompage}" />
                               <%--    <form:hidden path="" id="bucketName" value="${notes.bucketName}"/> --%>

								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label for="traceNo"> <b>Trace Number &nbsp; &nbsp; :</b></label> <label for="traceNo"> &nbsp; &nbsp; <b>
											${notes.traceNo}</b></label>
										</div>
									</div>
									<div class="col-md-9">
										<div class="form-group">
											<label for="traceNo"> <b>Client Name &nbsp;	&nbsp; :</b></label> <label for="traceNo"> &nbsp; &nbsp; <b>
													${notes.clientName}</b></label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">										
										<div class="form-group">
											<label for="notesDate">Notes Date<span	class="text-danger">*</span></label> 
																			
											<input type="text"	id="fromdate" name="notesDate" class="form-control flatpickr-input active" value ="${notes.notesDate}" placeholder="Notes Date" >
										</div>
										<div class="form-group">
											<label for="statusId">Status<span	class="text-danger">*</span></label>
											<form:select type="select" path="statusId"	class="form-control" onchange="return myFunction()" 
														required="true">
												<form:option value="" label="--Select--" />
												<form:options items="${notes.statusidlist}"	itemValue="statusId" itemLabel="statusDescription" />
											</form:select>
										</div>
										
										<div class="form-group">
											<label for="bucketId">Sub-Status Name<span class="text-danger">*</span></label>
											<form:select  path="bucketId" class="form-control" required="true"> 
												
												<form:option value="" label="--Select--" />
												<form:options  items="${notes.bucketList}" itemValue="bucketId" itemLabel="bucketName" />
											</form:select>
										</div> 
										<div class="form-group">
											<label for="followUpDate">FollowUp Date</label> 

											<input type="text"	id="followUpDate" name="followUpDate" class="form-control flatpickr-input active" 
												value ="${notes.followUpDate}" placeholder="FollowUp Date" >
										</div>
									</div>
									
                                   <div class="col-md-6">	
										<div class="form-group">
											<label for="appointmentWith">Followup With</label> 
											<input type="text"	name="appointmentWith" parsley-trigger="change" value="${notes.appointmentWith}"
												 maxlength="100" class="form-control" id="appointmentWith" >
										</div>
										<div class="form-group">
											<label for="notes">Notes<span class="text-danger">*</span></label>
											<textarea class="form-control" name="notes" maxlength="1000"	placeholder="Enter Notes" id="notesarea" rows="6"
												>${notes.notes}</textarea>
										</div>
									</div>
								</div>							
								<div class="form-group text-right m-b-0">
									 <button class="btn btn-primary waves-effect waves-light"
										type="submit">Submit</button> 
									<button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button>
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
	//$('#statusId').attr("disabled", true);
	//$('#appointmentStatus').attr("disabled", true);
	//$('#timezone').attr("disabled", true);
	function cancel() 
	{	
		var hiddenTraceNo = document.getElementById("hiddenTraceNo").value;
		var clientName = document.getElementById("clientName").value;
		var hiddenuserid = document.getElementById("hiddenUserId").value;
		var statusName = document.getElementById("statusName").value;
		var frompage = document.getElementById("frompage").value;
		if(document.getElementById("frompage").value == "addNotes"){
			location.href = "addnotes?id="+hiddenTraceNo+"&clientName="+clientName+"&userid="+hiddenuserid+"&&status="+statusName;
		}else{
		location.href = "viewnotes?id="+hiddenTraceNo+"&clientName="+clientName+"&userid="+hiddenuserid+"&&status="+statusName+"&&frompage="+frompage;
			}
	}
	
	
	function myFunction() 
	{
		 var statusid = document.getElementById("statusId").value;
		  var followup = document.getElementById("followup");
		 var appointmentTime = document.getElementById("apptwith");
		  if(statusid=='3')
		  {
			  document.getElementById("followUpDate").disabled = false;
			 document.getElementById("appointmentWith").disabled = false;
			  
			  
			  document.getElementById("appointmentWith").value = "";
		  }
		  else
		  {
			  document.getElementById("followUpDate").disabled = true;
			  document.getElementById("appointmentWith").disabled = true;
			  
			  document.getElementById("followUpDate").value = "";
			  document.getElementById("appointmentWith").value = "";
		  }
		  $("#appointmentTime").val("");
		  
		  var hiddenTraceNo = document.getElementById("hiddenTraceNo").value;
			var clientName = document.getElementById("clientName").value;
			var hiddenuserid = document.getElementById("hiddenUserId").value;
			var statusName = document.getElementById("statusName").value;
			var frompage = document.getElementById("frompage").value;
			var notesId = document.getElementById("notesId").value;
			
			 
			location.href = "getSubStatus?id="+notesId+"&&hiddenuserid="+hiddenuserid+"&&status="+statusName+"&&frompage="+frompage+"&&statusId="+statusid;
		 // location.href = "getSubStatus?statusId="+statusid;
		
	}
</script>
<script src="resources/assets/js/common.js" /></script>
<script src="resources/assets/js/notesValidation.js" /></script>
<%@include file="crm-footer.jsp"%>