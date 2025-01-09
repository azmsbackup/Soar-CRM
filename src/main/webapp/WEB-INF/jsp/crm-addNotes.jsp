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
								<li class="breadcrumb-item active">Add Notes</li>
							</ol>
						</div>
						<h4 class="page-title">Add Notes</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="col-lg-12">
							<form:form action="insertnotes" method="post" modelAttribute="notes" class="parsley-examples">
								<form:hidden path="traceNo" id="hiddenTraceNo"	value="${notes.traceNo}" />
								<form:hidden path="clientName" id="clientName"	value="${notes.clientName}" />
								<form:hidden path="hiddenUserId" id="hiddenUserId"	value="${notes.hiddenUserId}" />
                                <form:hidden path="" id="statusName" value="${notes.statusName}" />
                                  <form:hidden path="frompage" id="frompage" value="${notes.frompage}" />
                                  
<%-- 								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label> <b>Trace Number &nbsp; &nbsp; :</b></label> 
											<label > &nbsp; &nbsp; <b>	${notes.traceNo}</b></label>
										</div>
									</div>
									<div class="col-md-9">
										<div class="form-group">
											<label> <b>Client Name &nbsp; &nbsp; :</b></label>
											 <label> &nbsp; &nbsp; <b>	${notes.clientName}</b></label>
										</div>
									</div>
								</div> --%>
								
								<div class="row">
									<div class="col-md-6">
										<div class="card">
                                    	<div class="card-body">
                                        	<div class="form-group">
												<h3>Client Details</h3>
											</div>
												<div class="form-group">
													<label style="width:150px">Trace Number </label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.traceNo}</label> 
												</div>
												
												<div class="form-group">
													<label style="width:150px">Company Name</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.clientName}</label> 
												</div>
												
												<div class="form-group">
													<label style="width:150px">State</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.stateName}</label>
												</div>
												<div class="form-group">
													<label style="width:150px">Time Zone</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.timezone}</label>
												</div>
												<div class="form-group">
													<label style="width:150px">Contact Person</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.contactPerson}   
													<c:if test="${notes.client.designation != ''}">(${notes.client.designation})</c:if>
													
													</label>
												</div>
												<c:if test="${notes.client.contactPersonTwo!='' && notes.client.designationTwo != null}">
													<div class="form-group">
														<label style="width:150px">Alt. Contact Person</label><span style="width:50px;margin-right:10px">:</span>
														<label style="width:250px">${notes.client.contactPersonTwo}
														(${notes.client.designationTwo})												
														</label>
													</div>	
												</c:if>	
												<%-- <div class="form-group">
													<label style="width:150px">Title</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.designation}</label>
												</div> --%>
												<div class="form-group">
													<label style="width:150px">Telephone Number</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.phoneNumber}</label>
												</div>
												<div class="form-group">
													<label style="width:150px">Mobile Number</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.mobileNumber}</label>
												</div>
												<div class="form-group">
													<label style="width:150px">Alt. Mobile Number</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px">${notes.client.alternateMobileNumber}</label>
												</div>
												<div class="form-group">
													<label style="width:150px">Email</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px"><a href="mailto:${notes.client.email}">${notes.client.email}</a></label>
												</div>
												<div class="form-group">
													<label style="width:150px">Email 2</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px"><a href="mailto:${notes.client.email2}">${notes.client.email2}</a></label>
												</div>
												
												<div class="form-group">
													<label style="width:150px">Email 3</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px"><a href="mailto:${notes.client.email3}">${notes.client.email3}</a></label>
												</div>
												
												<div class="form-group">
													<label style="width:150px">Website</label><span style="width:50px;margin-right:10px">:</span>
													<label style="width:250px"><a href="${notes.client.website}">${notes.client.website}</a></label>
												</div>
											</div>
										</div>																																																										
									</div>
								
									<div class="col-md-6">
										<div class="card">
                                    		<div class="card-body">
                                        	<div class="form-group">
												<h3>Notes Details</h3>
											</div>
												<div class="form-group">
													<label>Status<span class="text-danger">*</span></label>
													<form:select type="select" path="statusId"	class="form-control" id="statusId"	onchange="return myFunction()" 
														required="true">
														<form:option value="" label="--Select--" />
														<form:options items="${notes.statusidlist}"	itemValue="statusId" itemLabel="statusDescription" />
													</form:select>
												</div>
												 <div class="form-group">
													<label>Sub-Status Name<span class="text-danger">*</span></label>
													<form:select type="select" path="bucketId"	class="form-control" id="bucketId" required="true"	> 
														
														<form:option value="" label="--Select--" />
														<form:options items="${notes.bucketList}"	itemValue="bucketId" itemLabel="bucketName" />
													</form:select>
												</div> 
												<div class="form-group" id="followup">
													<label>FollowUp Date</label> 
													<input type="date" name="followUpDate" parsley-trigger="change" placeholder="Enter FollowUp Date" class="form-control"
														id="followUpDate">
												</div>
											
											
												<div class="form-group" id="apptwith">
													<label>Followup With</label> 
													<input type="text"	name="appointmentWith" parsley-trigger="change" 	placeholder="Enter appointment with" 
															maxlength="100"	class="form-control" id="appointmentWith">
												</div>   
												
												<div class="form-group">
													<label>Notes Date<span class="text-danger">*</span></label>
													<input type="text" id="notesDate" name="notesDate" value="${notes.notesDate}" class="form-control"
														placeholder="Notes Date" readonly="true">
												</div>
												<div class="form-group">									
													<label>Notes<span	class="text-danger">*</span></label>
													<textarea class="form-control" name="notes" maxlength="1000"
														placeholder="Enter Notes" id="notesarea" rows="4" required></textarea>
												</div>
							 					
												<div class="form-group text-right m-b-0">
												   <c:if test="${notes.statusName !='Closed'}">  			
													  <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return ValidateNotes()">Submit</button>
						                            </c:if>
						                            <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button>
											     </div>
											</div>
									   </div>
								</div>
									<!-- <div class="form-group">
											<label for="employeeId">Appoinment Status<span
												class="text-danger">*</span></label> <select class="form-control" id="appointmentStatus" name="appointmentStatus" 
												required>
												<option value="" label="--Select --" />
												<option value="Fixed">Fixed</option>
												<option value="Closed">Closed</option>
												
											</select>
										</div> -->
									<!-- end col -->
								
			                 <table id="demo-foo-addrow" class="table table-striped toggle-circle mb-0"
									data-page-size="5" data-limit-navigation="5">
									<thead class="thead-light">
										<tr>

											<th data-field="Notes" data-align="center"
												data-sortable="true">Notes</th>
											<th data-field="NotesDt" data-align="center"
												data-sortable="true">Notes Date</th>
											<th data-field="NotesStatus" data-align="center"
												data-sortable="true">Notes Status</th>
											<th data-field="NotesSubstatus" data-align="center"
												data-sortable="true">Notes Sub-status</th>
											<th data-field="username" data-align="center"
												data-sortable="true">Account Owner</th>	
											<th data-field="view" data-align="center"
												data-sort-ignore="true">View Notes</th>
											
										</tr>
									</thead>

									<tbody>
										<c:forEach var="note" items="${notes.notesList}"
											varStatus="loop">
											<form:hidden path="" id="notesId${loop.index+1}"
												value="${note.notesId}" />
											<tr>
												<td class="more">${note.notes}</td>
												<td>${note.notesDate}</td>
												<td>${note.statusName}</td>
												<td>${note.bucketName}</td>
												<td>${note.userName}</td>
												<td><button type="button"
														class="btn btn-info waves-effect waves-light"
														onclick="return viewNotes(${loop.index+1})">
											         <i class="icon-eye"></i>
													</button></td>
												
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="active">
											<td colspan="8">
												<div class="text-right">
													<ul
														class="pagination pagination-split justify-content-end footable-pagination m-t-10"></ul>
												</div>
											</td>
										</tr>
									</tfoot>
								</table>
								
							</form:form>
						</div>
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
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->


<script src="resources/assets/js/common.js" /></script>
<%@include file="crm-footer.jsp"%>

<script>

function ValidateNotes()
{

	var notes = document.getElementById("notesarea").value;
	var notesDate = document.getElementById("notesDate").value;
	var statusId = document.getElementById("statusId").value;
	var followUpDate = document.getElementById("followUpDate").value;
	var appointmentWith = document.getElementById("appointmentWith").value;
	var bucketId = document.getElementById("bucketId").value;
	
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

$("#statusId").change(function() 
		{
	//alert(111);
		var bucketId=$("#bucketId").val();
		var statusId=$("#statusId").val();
			if(statusId == "")
			{
				alert("Please Select statusId");
				 $('#bucketId').empty();
				 option =  "<option value=''>Please Select</option>";
				 bucketId.append(option); 
				$("#statusId").focus();
				$("#bucketId").val("");
				return false;
			}
			
			//alert("gg"+statusId);
		 $.ajax({
		     url : "brbucket",
		     data :  {statusId:statusId},
		     type : "GET",
		     dataType: "json",
		     success : function(response) { 
		    	 
		    	 $('#bucketId').empty();
	        	
		    	var branchbucket=$('#bucketId'), option="";
		    	
		    	option="<option value=''>Please Select</option>";
		    	//alert(11);
		     for(var i=0; i<response.length; i++)
		         {
		    		// alert("response.length"+response.length);
		             option = option + "<option value='"+response[i].bucketId + "'>"+response[i].bucketName + "</option>";
		         }
		     
		    	 
		    	// alert(option);
		    	//$('#bucketId').append(option);
		    	 branchbucket.html(option); 
		    	
		    	
		     },
		     error : function(xhr, status, error) {
		         alert("error is " + xhr.responseText);
		     }
		 });
		 return false;
		});
		
		
		
		
	
	$("#followUpDate").click(function()
			{
				var status = document.getElementById("statusId").value;
				
				if(status == "")
				{
					alert("Please Select Status First!");
					$("#statusId").focus();
					$("#appointmentTime").val("");
					return false;			
				}
			});
			
			
			
			
			
			
			$("#appointmentWith").click(function()
			{
				var status = document.getElementById("statusId").value;
				
				if(status == "")
				{
					alert("Please Select Status First!");
					$("#statusId").focus();
					$("#appointmentTime").val("");
					return false;			
				}
			});
	
	function cancel()
	{
		/*var hiddenTraceNo = document.getElementById("hiddenTraceNo").value;
		var clientName = document.getElementById("clientName").value;
		var hiddenuserid = document.getElementById("hiddenUserId").value;
		
		location.href = "viewnotes?id="+hiddenTraceNo+"&clientName="+clientName+"&userid="+hiddenuserid;*/
		//window.history.back();
		location.href = "manageclient";
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
		
		}
	
	function viewNotes(loopid)
	{
		var hiddenuserId = document.getElementById("hiddenUserId").value;
		var notesId = document.getElementById("notesId"+loopid).value;
		var statusName = document.getElementById("statusName").value;
		
		if(statusName=="Closed"){
			location.href = "manageclient";
		}else{
		//var bucketName= document.getElementById("bucketName").value;
		var frompage = "addNotes";
		location.href = "viewnotessubmit?id="+notesId+"&&hiddenuserid="+hiddenuserId+"&&status="+statusName+"&&frompage="+frompage;
		}
	}
	
</script>


