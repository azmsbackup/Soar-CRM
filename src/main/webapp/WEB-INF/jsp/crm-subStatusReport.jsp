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
								<li class="breadcrumb-item active">Sub Status Report</li>
							</ol>
						</div>
						<h4 class="page-title">Sub Status Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="subStatusReportSubmit" method="post" modelAttribute="client" class="parsley-examples">
							<div class="row row-grid">
								<div class="col-md-3">
									<label for="statusId">Status<span class="text-danger">*</span></label>
									<form:select type="select" path="statusId"  class="form-control" id="statusId" onchange="return myFunction()" required="true">
									<%-- 	<form:option value="" label="All" /> --%>
										<form:options items="${client.statusidlist}" itemValue="statusId" itemLabel="statusDescription"  />
									</form:select>									
								</div>
								
								
								<div class="col-md-3">
									<label for="bucketId">Sub-Status Name<span class="text-danger">*</span></label>
									<form:select  path="bucketId" class="form-control" required="true"> 
										
										<form:option value="" label="--Select--" />
										<form:options  items="${client.bucketList}" itemValue="bucketId" itemLabel="bucketName" />
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
										<label for="notesDate">From Date <span class="text-danger">*</span></label> 
										<input type="text"	id="fromdate" name="createdDate" class="form-control flatpickr-input active" value ="${client.createdDate}" placeholder="From Date" >
								</div>							
								<div class="col-md-3">										
										<label for="modifiedDate">To Date<span class="text-danger">*</span></label> 
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
                                           				
                                           				<c:if test = "${client.selectedStatusFlag != 'Email'}"> 
                                             			<th  style="width:20px;text-align:center" data-field="departmentName" data-sortable="true">Department</th> 
                                             			</c:if> 
                                             			<c:if test = "${client.selectedStatusFlag == 'Email'}"> 
                                             			<th  style="width:20px; text-align: center"  data-field="modifiedDate" 
													    data-sortable="true">Last Activity Date</th> 
													    </c:if>                                  			                                                                               
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
															
															<c:if test = "${client.selectedStatusFlag != 'Email'}">
															<td style="width:20px;text-align:center">${clients.departmentName}</td>	
															</c:if>		
															
															<c:if test = "${client.selectedStatusFlag == 'Email'}">
															<td>${clients.modifiedDate}</td>	
															</c:if>																									
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
	var bucket = document.getElementById("bucketId").value;
	
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	var fromdt = new Date(fromDate);
	var todt = new Date(toDate);
	var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
	
	
	if(status == ""){
		alert("Please Select Status!");
		document.getElementById("statusId").focus();
		return false;
	}
	if(bucket == ""){
		alert("Please Select Sub Status!");
		document.getElementById("bucketId").focus();
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
	}
	
	if(fromdt > todt)
	{
		alert("From Date should be Lesser than To Date!");
		document.getElementById("todate").value="";
		return false;
	}
	
	
}


function myFunction() 
{
	 var statusid = document.getElementById("statusId").value;
	
	location.href = "subStatusReportWithSubStatusId?statusId="+statusid;
	
}
</script>


<%@include file="crm-footer.jsp"%>