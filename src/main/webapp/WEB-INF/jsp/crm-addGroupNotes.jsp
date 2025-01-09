<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
<style>
.morecontent span {
	display: none;
}

.morelink {
	display: block;
}

div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
    }

</style>
<div class="content-page">
	<div class="content">

		<!-- Start Content-->
		<div class="container-fluid">

			<!-- start page title -->
			<%-- <div class="row">
				<div class="col-12">
					<div class="page-title-box">
						<div class="page-title-right">
							<ol class="breadcrumb m-0">
								<li class="breadcrumb-item"><a href="javascript: void(0);">Allzone</a></li>
								<li class="breadcrumb-item"><a href="javascript: void(0);">CRM</a></li>
								<li class="breadcrumb-item active">Add Group Notes</li>
							</ol>
						</div>
						
						<h4 class="page-title">Add Group Notes</h4>
						<br>
						<c:if test="${client.roleid !='4' &&  client.roleid !='1'}">
							<!-- <button id="demo-delete-row"
								class="btn btn-blue waves-effect waves-light"
								onclick="addGroupNotes()">
								<i class="mdi mdi-plus-circle mr-1"></i>Add Group Notes
							</button> -->
							
							<td style="text-align: center"><a href="addnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}"><button
																					type="button"
																					class="btn btn-success waves-effect waves-light">
																					<i class="fe-file-plus"></i>
																				</button></a></td>
						</c:if>	
						<br>
					</div>
				</div>
			</div> --%>
			
			
			<div class="row">
				<div class="col-12">
					<div class="page-title-box">
						<div class="page-title-right">
							<ol class="breadcrumb m-0">
								<li class="breadcrumb-item"><a href="javascript: void(0);">Allzone</a></li>
								<li class="breadcrumb-item"><a href="javascript: void(0);">CRM</a></li>
								<li class="breadcrumb-item active">Add Group Notes</li>
							</ol>
						</div>
						<h4 class="page-title">Add Group Notes</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->
			
		  

			
<div class="row row-grid">
	<div class="col-sm-12">
		<div class="card-box">
		<div class="row">
			<div class="col-sm-6">
			
			
				<form:form class="form-horizontal parsley-examples"
					action="clientSearchByTraceNo" method="post" modelAttribute="notes">
				 <form:hidden path="userId" id="userid" value="${notes.userId}" /> 
					
					
					<!-- <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                <a href="insertexisting"><button type="button" class="btn btn-warning waves-effect waves-light">
								<i class="mdi mdi-pencil"></i>Insert Existing Client</button></a>
								<div class="col-sm-3">
								<input path="traceNo" data-parsley-type="number" placeholder="Enter trace no" class="form-control" maxlength="11" />	
								</div>
								 <a href="insertexistingnotes"><button type="button" class="btn btn-success waves-effect waves-light">
								<i class="mdi mdi-search"></i>Search</button></a>
                             	</div>
                             </div>
            		  </div>  -->
            		   <div class="row">
            			<%-- <c:if test="${notes.alreadyExistsMsg != null}">
								<br>
								<div
									class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
									<span class="badge badge-pill badge-danger">X</span> ${notes.alreadyExistsMsg}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							</c:if> --%>
							
							 <c:if test="${notes.alreadyExistsMsg != null}">
							<div id="error-alert" class="alert alert-danger alert-dismissible fade show">
								<span class="badge badge-pill badge-danger">Failed</span>
								${notes.alreadyExistsMsg}
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
						</c:if>
						<c:forEach var="message" items="${notes.traceMessages}">
						    <div id="error-alert" class="alert alert-danger alert-dismissible fade show">
						        <span class="badge badge-pill badge-danger">Failed</span>
						        ${message}
						        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						            <span aria-hidden="true">&times;</span>
						        </button>
						    </div>
						</c:forEach>
							</div>	
            		  <div class="row row-grid">
            		  <div class="col-md-4">
									<!-- 	<label for="traceno">Trace No </label>  -->
								<!-- 	data-parsley-type="number" -->
										<form:input path="traceNo" id="traceNo" value="" data-parsley-type="text" placeholder="Enter trace no, separated by commas"
										 class="form-control"  />
											<form:hidden path="traceNos" id="hiddenTraceNoOnSearch" value="${traceNos}" /> 
											
																
									</div>
									
									<div class="col-md-4">  
									 <a><button type="submit" onclick="return searchValidate()" class="btn btn-success waves-effect waves-light m-t-4">
								Add Trace No</button></a>	</div>
									
							
								</div>
				

					<c:choose>
					
						<c:when test="${notes.clientList.size() > 0 }">
							
							
							<div  style="height:600px;width:100%;overflow:auto;">
								<table data-toggle="table"
                                           data-page-size="5"
                                           data-buttons-class="xs btn-light"
                                           data-pagination="true" class="table-bordered " >
									<thead class="thead-light">
										<tr>
												
											<th data-field="id"
												data-sortable="false" data-align="left">Trace No</th>
											<th  data-field="name"
												data-sortable="false">Client Name</th>
												
										
											<th style="text-align: center" data-align="center" data-field="statusName"
												data-sortable="false">Status</th>
											
												
											<th  data-field="userName" data-align="center" data-sortable="false">Account Owner</th>
											
											<th  data-field="remove" data-align="center" data-sortable="false">Remove</th>
										
										</tr>
									</thead>
									<tbody>
										<c:forEach var="clien" items="${notes.clientList}" varStatus="loop">
										<form:hidden path="clientListSize" id="clientListSize" value="${notes.clientList.size()}" /> 
										
									
												
												<td style = "text_align:left">
													
	                                     
	                                                     ${clien.manageTraceNo}
	                                           
												</td>
													
												<td style="width: 25%;">${clien.clientName}</td>
				
												<td>${clien.statusName}</td>
			
												
												<td>${clien.username}</td>
								
												<td><a href="removeClient?id=${clien.traceNo}" class="btn btn-xs btn-danger"><i class="mdi mdi-minus" title="Remove"></i></a>
												
												</td>
												
										


											</tr>
										</c:forEach>
									</tbody>
				
								</table>
							</div>
							
						</c:when>
						<c:otherwise>
							<c:if test="${client.frompage != 'Initial'}">
								<!-- <br>
								<div
									class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
									<span class="badge badge-pill badge-danger">No Data</span> No
									data exist!.
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div> -->
								
						<div  style="height:600px;width:100%;overflow:auto;">
								<table  data-toggle="table"
                                           data-page-size="5"
                                           data-buttons-class="xs btn-light"
                                           data-pagination="true" class="table-bordered " >
									<thead class="thead-light">
										<th  data-field="id"
												data-sortable="false">Trace No</th>
											<th style="text-align: center" data-field="name"
												data-sortable="false">Client Name</th>
											
											<th style="text-align: center" data-field="statusName"
												data-sortable="false">Status</th>
												
											
											<th  data-field="userName" data-sortable="false">Account Owner</th>
											
											<th  data-field="remove" data-sortable="false">Remove</th>
											
										</tr>
									</thead>
									
								</table>
							</div>
										
							</c:if>
						</c:otherwise>
					</c:choose>
					
				
					
				</form:form>
			
			</div>
			
			<div class="col-sm-6">
			
				 <form:form action="insertnotesOnGroup" method="post" modelAttribute="notes" class="parsley-examples">
					<form:hidden path="traceNo" id="hiddenTraceNo"	value="${notes.traceNo}" />
					<form:hidden path="clientName" id="clientName"	value="${notes.clientName}" />
					<form:hidden path="hiddenUserId" id="hiddenUserId"	value="${notes.hiddenUserId}" />
					<form:hidden path="" id="statusName" value="${notes.statusName}" />
					<form:hidden path="frompage" id="frompage" value="${notes.frompage}" />
				<%-- 	<form:hidden path="traceNo" id="hiddenTraceNo"	value="${notes.traceNo}" />  --%>
				 	<form:hidden path="traceNos" id="hiddenTraceNoOnSearch"	value="${traceNos}" /> 
					<!-- selectedObjectsList -->

					

					
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
							 <%--   <c:if test="${notes.statusName !='Closed'}">  			
								  <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return ValidateNotes()">Submit</button>
								</c:if> --%>
								
								 	 <c:if test="${notes.clientListSize > 0}"> 		
								  <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return ValidateNotes()">Submit</button>
								
								<button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button>
								</c:if>
							</div>
					
					
			   </form:form> 
			</div>
		</div>
				
			
		</div>
	</div>
	<!-- end card-box-->
</div>


								
								
			                 <%-- <table id="demo-foo-addrow" class="table table-striped toggle-circle mb-0"
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
								</table> --%>
	
				<!-- end row-->



			</div>
			<!-- container -->

		</div>
		<!-- content -->
	</div>
</div>


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>
	var selectedObjectsList = new Array();
	function clientSelectedCheckBox(obj, traceNo)
	{	
		var value=obj.value;
	
		if(obj.checked)
		{
			 selectedObjectsList.push(traceNo);
		}
		else
		{
			var isExists = selectedObjectsList.includes(traceNo, 0);
			
			for(var i = 0;i < selectedObjectsList.length; i++)
			{
				
				if(selectedObjectsList[i] == traceNo)
				{
					selectedObjectsList.splice(i, 1);
					break;
				}
				
			}
			
		}
		
	}
	
	function addGroupNotes()
	{
		location.href = "addgroupnotes";
	}
	
	
	function searchValidate() {
	    var traceNoOnSearch = document.getElementById("traceNoOnSearch").value.trim();  // Get input value and trim leading/trailing spaces
	    
	    if (traceNoOnSearch != "") {
	        document.getElementById('hiddenTraceNoOnSearch').value = traceNoOnSearch;  // Set hidden field with trace numbers
	    }

	    // Check if input is empty
	    if (traceNoOnSearch == "") {
	        alert("Please enter trace no");
	        document.getElementById("traceNoOnSearch").focus();
	        return false;
	    }

	    // Check if trace numbers are separated by commas and if all are valid numbers
	    var traceNumbers = traceNoOnSearch.split(",");  // Split the string by commas
	    for (var i = 0; i < traceNumbers.length; i++) {
	        var traceNo = traceNumbers[i].trim();  // Remove extra spaces around each trace number
	        if (traceNo == "") {
	            continue;  // Skip empty trace numbers (in case of extra commas)
	        }

	        if (isNum(traceNo) == false) {
	            alert("Please enter only numbers in Trace No!");
	            document.getElementById("traceNoOnSearch").focus();
	            return false;
	        }
	    }

	    // Optionally, you can submit the form or redirect if the validation passes
	    // location.href = "clientSearchByTraceNo";  // Uncomment this if you want to perform the redirect
	    return true;  // Validation passes, allow form submission or other actions
	}

	// Helper function to check if a string is a number
	function isNum(value) {
	    var regex = /^[0-9]+$/;  // Only digits are allowed
	    return regex.test(value);
	}
	
	
	function edit(hiddenTraceNo, userId)
	{
		//alert("inside" + loopid);
		//var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		//var userId = document.getElementById("hiddenuserid"+loopid).value;
		alert("hiddenTraceNo "+ hiddenTraceNo);
		alert("userId "+ userId);
		
		location.href = "editclient?id="+hiddenTraceNo+"&userid="+userId;
	}	
	function notes(loopid)
	{
		var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		var userid = document.getElementById("userid").value;		
		var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
		var clientName = document.getElementById("clientName"+loopid).value;
		
		/* if(userid != hiddenuserid)
		{
			alert("You are not allowed to add notes for this client!");
			return false;
		}
		else
		{ */
			location.href ="addnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid;
			return true;
		//}
	}
	function viewNotes(loopid)
	{
		var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;		
		var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
		var clientName = document.getElementById("clientName"+loopid).value;
		var statusName = document.getElementById("statusName"+loopid).value;
		//var bucketName = document.getElementById("bucketName"+loopid).value;
		
		location.href ="viewnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid+"&&status="+statusName;//"&&bucket="+bucketName;
	}
	function mail()
	{
		alert("This function will be added in future!")
		return false;
	}
	
	function search()
	{
		var status = document.getElementById("statusId").value;
		//var traceno = document.getElementById("traceNo").value;
		//var clientname = document.getElementById("name").value;
		var fromdate = document.getElementById("fromdate").value;
		var todate = document.getElementById("todate").value;
		//var contactPerson = document.getElementById("contactPerson").value;
		//var phoneNumber = document.getElementById("phoneNumber").value;
		var searchbox = document.getElementById("searchbox").value;
		var textbox = document.getElementById("textbox").value;
		
		var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds		
		var fromdt = new Date(fromdate);
		var todt = new Date(todate);
		var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
		
		if(searchbox == "" && textbox =="" && fromdate=="" && todate=="" && status =="")
		{
			alert("Please Select Anyone to Search!");
			return false;
		}
		
		if(searchbox != "")
		{
			if(textbox == "")
			{
				alert("Please Enter Search Value!");
				document.getElementById("textbox").focus();
				return false;
			}
		}
		if(textbox != "")
		{
			if(searchbox == "")
			{
				alert("Please Select searchbox!");
				document.getElementById("textbox").focus();
				return false;
			}
		}
		
		/* if((searchbox == "") && (textbox == ""))
		{
			if(fromdate == "")
			{
				alert("Please Select From Date!");
				document.getElementById("fromdate").focus();
				return false;
			}
			if(todate == "")
			{
				alert("Please Select To Date!");
				document.getElementById("todate").focus();
				return false;
			}
		} */
		
		if((fromdate != "") && (todate != ""))
		{
			if(fromdt > todt)
			{
				alert("From Date should be Lesser than To Date!");
				document.getElementById("todate").value="";
				return false;
			}
		}		
		
			
		
		/* if(diffDays > 30)
		{
			alert("Please select date range between one month!");
			return false;
		} */
		 if(fromdate != "")
		{
			if(todate == "")
			{
				alert("Please Select To Date!");
				document.getElementById("todate").focus();
				return false;
			}
			
		}
		if(todate != "")
		{
			if(fromdate == "")
			{
				alert("Please Select From Date!");
				document.getElementById("fromdate").focus();
				return false;
			}
			
		} 
		
		else
		{
			return true;
		}
	}
	
	

	function ValidateNotes()
	{
		//alert("selectedObjectsList :"+selectedObjectsList.length); 
		
		var notes = document.getElementById("notesarea").value;
		var notesDate = document.getElementById("notesDate").value;
		var statusId = document.getElementById("statusId").value;
		var followUpDate = document.getElementById("followUpDate").value;
		var appointmentWith = document.getElementById("appointmentWith").value;
		var bucketId = document.getElementById("bucketId").value;
		
		alert("clientSize : "+document.getElementById("clientListSize").value);
		var clientSize = document.getElementById("clientListSize").value;	
		alert("clientSize : "+clientSize);
		if(clientSize == 0)
		{
		
			alert("Please enter trace no!");
			return false;
		
		}
		else if(notesDate == "")
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
					 var branchbucket=$('#bucketId'), option="";
				    	
				     option="<option value=''>--Select--</option>";
				     branchbucket.html(option); 
					$("#statusId").focus();
					//$("#bucketId").val("");
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
			    	
			    	option="<option value=''>--Select--</option>";
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
			  
			 // alert("statusid "+ statusid);
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
		
	/* 	$("#error-alert").fadeTo(3000, 2000).slideUp(2000, function(){
		    $("#error-alert").slideUp(2000);
		}); */
</script>

<%@include file="crm-footer.jsp"%>