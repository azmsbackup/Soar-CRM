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
								<li class="breadcrumb-item active">Status Report</li>
							</ol>
						</div>
						<h4 class="page-title">Status Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="statusReportSubmit" method="post" modelAttribute="client" class="parsley-examples">
							<div class="row row-grid">
								<div class="col-md-3">
									<label for="statusId">Status</label>
									<form:select type="select" path="statusId"	class="form-control" id="statusId" onchange="loadSubstatus()">
										<form:option value="" label="All" />
										<form:options items="${client.statusidlist}" itemValue="statusId" itemLabel="statusDescription" />
									</form:select>									
								</div>
								<div class="col-md-3">
										    <label for="subStatusId">Sub Status</label>
										    <form:select path="bucketId" class="form-control" id="bucketId">
										        <!-- The substatus options will be dynamically loaded here -->
										        <option value=''>Select Substatus</option>
										    </form:select>
										</div>
<%-- 								 <div class="col-md-3">  
                                        <label for="extension">Conference</label>
                                        <form:select  type= "select"  path="eventId" class="form-control" id="eventId">
	          								<form:option value="" label="--Select--"/>
	          								<form:options items="${client.eventList}" itemValue="eventId" itemLabel="eventName"/>
     									</form:select> 
                                  </div> --%>
								<div class="col-md-3">
										<label for="notesDate">From Date</label> 
										<input type="text"	id="fromdate" name="createdDate" class="form-control flatpickr-input active" value ="${client.createdDate}" placeholder="From Date" >
								</div>							
								<div class="col-md-3">										
										<label for="modifiedDate">To Date</label> 
										<input type="text"	id="todate" name="modifiedDate" class="form-control flatpickr-input active" value ="${client.modifiedDate}" placeholder="To Date">
								</div>
							</div>

							<div class="row row-grid">
									<div class="col-sm-12 col-sm-offset-1">
							            <div class="row">
							                <div class="col-sm-12 col-sm-offset-4 text-center">
							                   <button class="btn btn-success waves-effect waves-light" type="submit" onclick="return validate()">Search
											</button>
							                </div>
							            </div>
							        </div>
											
							</div>
							<c:choose>
							<c:when test="${statusReportList.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons" class="table table-striped">
													<input type="hidden" id="dt-title" value = "Allzone CRM - Status Report" />
										
													<thead class="thead-light">
                                        			<tr>
                                        				<th  style="width:20px;text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>                                            
                                             			<th  style="width:20px;text-align:center" data-field="clientName" data-sortable="true">Client Name</th>
                                             			
                                             		
                                             			<c:if test = "${client.selectedStatusFlag != 'Email'}">
													        <th  style="width:20px;text-align:center" data-field="address" data-sortable="true">Address</th>
                                             				<th  style="width:20px;text-align:center" data-field="country" data-sortable="true">Country</th>
													    </c:if>
                                             			
                                             			<th  style="width:20px;text-align:center" data-field="state" data-sortable="true">State</th>
                                             			
                                             			<c:if test = "${client.selectedStatusFlag != 'Email'}">
													       <th  style="width:20px;text-align:center" data-field="city" data-sortable="true">City</th>
                                             			   <th  style="width:20px;text-align:center" data-field="zip" data-sortable="true">Zip</th>
													    </c:if>
                                             			
                                             			<th  style="width:20px;text-align:center" data-field="username" data-sortable="true">Account Owner</th>
                                             			
                                             			<c:if test = "${client.selectedStatusFlag != 'Email'}">
                                             			<th  style="width:20px;text-align:center" data-field="event" data-sortable="true">Conference</th>
                                             			</c:if>
                                             			
                                             			<th  style="width:20px;text-align:center" data-field="contactPerson" data-sortable="true">Contact Person</th>
                                             			<th  style="width:20px;text-align:center" data-field="phoneNumber" data-sortable="true">Phone Number</th>                                             			 
                                             			<th  style="width:20px;text-align:center" data-field="email" data-sortable="true">Email</th> 
                                             			
                                             			<c:if test = "${client.selectedStatusFlag != 'Email'}">
                                           				<th  style="width:20px;text-align:center" data-field="fax" data-sortable="true">Fax</th> 
                                           				</c:if>
                                           				
                                           				<th  style="width:20px;text-align:center" data-field="website" data-sortable="true">Website</th> 
                                           				<th  style="width:20px;text-align:center" data-field="statusName" data-sortable="true">Status</th> 
                                           				<th  style="width:20px;text-align:center" data-field="statusName" data-sortable="true">Sub Status</th>
                                           				<c:if test = "${client.selectedStatusFlag != 'Email'}"> 
                                             			<th  style="width:20px;text-align:center" data-field="departmentName" data-sortable="true">Department</th> 
                                             			</c:if> 
                                             			<c:if test = "${client.selectedStatusFlag == 'Email'}"> 
                                             			<th  style="width:20px; text-align: center"  data-field="modifiedDate" 
													    data-sortable="true">Last Activity Date</th> 
													    </c:if>   
													     <th  style="width:20px;text-align:center" data-field="notes" data-sortable="true">Notes</th>
													    <th  style="width:20px;text-align:center" data-field="notesDate" data-sortable="true">Notes Date</th>                                			                                                                               
                                        			</tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="clients" items="${statusReportList}">
														<tr>
															<td style="text-align:right">${clients.traceNo}</td>
															<td style="width:20px;text-align:center">${clients.clientName}</td>
															
															<c:if test = "${client.selectedStatusFlag != 'Email'}">	
															<td style="width:20px;text-align:center">${clients.address}</td>
															<td style="width:20px;text-align:center">${clients.countryName}</td>
															</c:if>
															
															<td style="width:20px;text-align:center">${clients.stateName}</td>
															<c:if test = "${client.selectedStatusFlag != 'Email'}">
															<td style="width:20px;text-align:center">${clients.cityName}</td>	
															<td style="width:20px;text-align:center">${clients.zip}</td>	
															</c:if>
															<td style="width:20px;text-align:center">${clients.username}</td>
															<td style="width:20px;text-align:center">${clients.eventName}</td>
															
															<c:if test = "${client.selectedStatusFlag != 'Email'}">	
															<td style="width:20px;text-align:center">${clients.contactPerson}</td>	
															</c:if>
																													
															<td style="width:20px;text-align:center">${clients.phoneNumber}</td>
															
															 <c:choose>
														         <c:when test = "${clients.email2 != null}">
														          <td style="width:20px;text-align:center">${clients.email};${clients.email2}</td>
														         </c:when>
														         
														         <c:when test = "${clients.email == '-'}">
														            <td style="width:20px;text-align:center">${clients.email}</td>
														         </c:when>
														         
														         <c:when test = "${(clients.email != '-') and (clients.email2 == null)}">
														            <td style="width:20px;text-align:center">${clients.email}</td>
														         </c:when> 
														         
														         <c:otherwise>
														            <td style="width:20px;text-align:center">${clients.email}</td>
														         </c:otherwise>
													        </c:choose>
														 	
														 	<c:if test = "${client.selectedStatusFlag != 'Email'}">
															<td style="width:20px;text-align:center">${clients.fax}</td>
															</c:if>
															
															<td style="width:20px;text-align:center" >${clients.website}</td>
															<td style="width:20px;text-align:center">${clients.statusName}</td>	
															<td style="width:20px;text-align:center">${clients.bucketName}</td>
															<c:if test = "${client.selectedStatusFlag != 'Email'}">
															<td style="width:20px;text-align:center">${clients.departmentName}</td>	
															</c:if>		
															
															<c:if test = "${client.selectedStatusFlag == 'Email'}">
															<td>${clients.modifiedDate}</td>	
															</c:if>	
															<td style="width:20px;text-align:center">${clients.notes}</td>	
															<td style="width:20px;text-align:center">${clients.notesDate}</td>																							
														</tr>
													</c:forEach>
												</tbody>
											</table>

											</div>
											<!-- end card body-->
										</div>
										<!-- end card -->
									</div>
									<!-- end col-->
								</div>
							</c:when>
							<c:otherwise>								
								<c:if test="${client.frompage != 'Initial'}">
									<br>
									<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
	                                	<span class="badge badge-pill badge-danger">No Data</span>
	                                    No data exist!
	                                  	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                                    	<span aria-hidden="true">&times;</span>
	                                	</button>
	                         		</div>
								</c:if>						
							</c:otherwise>
							</c:choose>
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
</div>
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->



<script>


function validate()
{
	var status = document.getElementById("statusId").value;		
	var fromDate = document.getElementById("fromdate").value;	
	var toDate = document.getElementById("todate").value;
	
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	var fromdt = new Date(fromDate);
	var todt = new Date(toDate);
	var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
	
	
	if(fromDate == "")
	{
		alert("Please Select From Date!");
		document.getElementById("fromdate").focus();
		return false;
	}
	if(toDate == "")
	{
		alert("Please Select To Date!");
		document.getElementById("todate").focus();
		return false;
	}
	/* if((country == "") && (state == "") && (city == "") && (status == "") && (department == ""))
	{
		alert("Please Select Anyone to Search!");
		return false;
	} */
	/* if((fromDate !="") && (toDate == "") )
	{
		alert("Please Select To Date!");
		document.getElementById("todate").focus();
		return false;
	}
	if((toDate != "") && (fromDate ==""))
	{
		alert("Please Select From Date!");
		document.getElementById("fromdate").focus();
		return false;
	} */
	if(fromdt > todt)
	{
		alert("From Date should be Lesser than To Date!");
		document.getElementById("todate").value="";
		return false;
	}
	/*if(diffDays > 30)
	{
		alert("Please select date range between one month!");
		return false;
	}
	 if(state == "")
	{
		alert("Please Select State!");
		document.getElementById("statesId").focus();
		return false;
	}
	if(city == "")
	{
		alert("Please Select City!");
		document.getElementById("citiesId").focus();
		return false;
	}
	if(status == "")
	{
		alert("Please Select Status!");
		document.getElementById("statusId").focus();
		return false;
	}
	if(department == "")
	{
		alert("Please Select Department!");
		document.getElementById("deptId").focus();
		return false;
	}
	if(fromDate == "")
	{
		alert("Please Select From Date!");
		document.getElementById("fromdate").focus();
		return false;
	}
	if(toDate == "")
	{
		alert("Please Select To Date!");
		document.getElementById("todate").focus();
		return false;
	} */
	
	
}



document.addEventListener('DOMContentLoaded', function () {
    // Call this function when the page is loaded
    loadSubstatus();
});

// Function to load substatus options and set the selected value
function loadSubstatus() {
    var statusId = document.getElementById("statusId").value;  // Get selected statusId

    // Get the substatus dropdown element
    var subStatusSelect = document.getElementById("bucketId");

    // Clear the existing options
    subStatusSelect.innerHTML = "<option value=''>Select Substatus</option>";

    // If a status is selected, make an AJAX request to get the substatus
    if (statusId) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/AllzoneCRM/getSubstatusByStatusId?statusId=" + statusId, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var substatusOptions = JSON.parse(xhr.responseText);  // Assuming response is JSON

                // Check if there's a selected bucketId stored in sessionStorage
                var selectedBucketId = sessionStorage.getItem("bucketId");

                // Populate the substatus dropdown with the new options
                for (var i = 0; i < substatusOptions.length; i++) {
                    var option = document.createElement("option");
                    option.value = substatusOptions[i].bucketId;  // Assuming bucketId is the value
                    option.textContent = substatusOptions[i].bucketName;  // Assuming bucketName is displayed
                    
                    // Ensure types match, compare strings or numbers as needed
                    if (String(substatusOptions[i].bucketId) === String(selectedBucketId)) {
                        option.selected = true;  // Mark the option as selected if it matches
                    }

                    subStatusSelect.appendChild(option);
                }
            }
        };

        xhr.onerror = function() {
            alert("An error occurred while fetching substatus options.");
        };

        xhr.send();
    }
}

// Function to store selected bucketId in sessionStorage when the selection changes
document.getElementById("bucketId").addEventListener('change', function() {
    var selectedBucketId = this.value;
    // Store the selected bucketId in sessionStorage
    sessionStorage.setItem("bucketId", selectedBucketId);
});
</script>


<%@include file="crm-footer.jsp"%>