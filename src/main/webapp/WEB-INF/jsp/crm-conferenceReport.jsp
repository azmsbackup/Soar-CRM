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
								<li class="breadcrumb-item active">Conference Report</li>
							</ol>
						</div>
						<h4 class="page-title">Conference Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="conferenceReportSubmit" method="post" modelAttribute="client" class="parsley-examples">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-inline">	
													
											<div class="form-group mx-sm-2">	
												<label for="sourceId">Conference Name<span class="text-danger">*&nbsp;</span></label>                                             
			                                 	<form:select  type= "select"  path="eventId" class="form-control" id="eventId">
				     								<form:option value="" label="--Select--"/>
				     								<form:options items="${client.eventList}" itemValue="eventId" itemLabel="eventCode"/>
												</form:select>  
											</div>
											<div class="form-group mx-sm-2">
												<label for="notesDate">From Date &nbsp;</label> 
												<input type="text"	id="fromdate" name="createdDate" value="${client.createdDate}" class="form-control" placeholder="From Date">
											</div>
											<div class="form-group mx-sm-3">
												<label for="notesDate">To Date &nbsp;</label> 
												<input type="text"	id="todate" name="modifiedDate" value="${client.modifiedDate}" class="form-control" placeholder="To Date">
											</div>
											
									<div class="row">
									<div class="col-sm-12 col-sm-offset-1">
							            <div class="row">
							                <div class="col-sm-12 col-sm-offset-3 text-center">
							                   <button class="btn btn-success waves-effect waves-light" type="submit" onclick="return validate()">Search
													</button>
							                </div>
							            </div>
							        </div>											
								</div>
						
										</div>
									</div>
								</div>
										
							
							
							<c:choose>
							<c:when test="${conferenceList.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons" class="table table-striped">
												<input type="hidden" id="dt-title" value = "Allzone CRM - Conference Report" />
													<thead class="thead-light">
                                        			<tr>
                                        				<th  style="width:20px;text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>                                            
                                             			<th  style="width:20px;text-align:center" data-field="clientName" data-sortable="true">Client Name</th>
                                             			<!-- <th  style="width:20px;text-align:center" data-field="address" data-sortable="true">Address</th> -->
                                             			<th  style="width:20px;text-align:center" data-field="country" data-sortable="true">Country</th>
                                             			<th  style="width:20px;text-align:center" data-field="state" data-sortable="true">State</th>
                                             			<th  style="width:20px;text-align:center" data-field="city" data-sortable="true">City</th>
                                             			<!-- <th  style="width:20px;text-align:center" data-field="zip" data-sortable="true">Zip</th>
                                             			<th  style="width:20px;text-align:center" data-field="event" data-sortable="true">Event</th> -->
                                             			<th  style="width:20px;text-align:center" data-field="contactPerson" data-sortable="true">Contact Person</th>
                                             			<th  style="width:20px;text-align:center" data-field="phoneNumber" data-sortable="true">Phone Number</th>                                             			 
                                             			<th  style="width:20px;text-align:center" data-field="email" data-sortable="true">Email</th> 
                                           				<!-- <th  style="width:20px;text-align:center" data-field="fax" data-sortable="true">Fax</th>  -->
                                           				<th  style="width:20px;text-align:center" data-field="website" data-sortable="true">Website</th>
                                           				<th  style="width:20px;text-align:center" data-field="statusName" data-sortable="true">Status</th>   
                                             			<!-- <th  style="width:20px;text-align:center" data-field="departmentName" data-sortable="true">Department</th>   -->                                   			                                                                               
                                        			</tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="client" items="${conferenceList}">
														<tr>
															<td style="text-align:right">${client.traceNo}</td>
															<td style="width:20px;text-align:center">${client.clientName}</td>	
															<%-- <td style="width:20px;text-align:center">${client.address}</td> --%>
															<td style="width:20px;text-align:center">${client.countryName}</td>
															<td style="width:20px;text-align:center">${client.stateName}</td>
															<td style="width:20px;text-align:center">${client.cityName}</td>	
															<%-- <td style="width:20px;text-align:center">${client.zip}</td>	
															 <td style="width:20px;text-align:center">${client.eventName}</td> --%>	
															<td style="width:20px;text-align:center">${client.contactPerson}</td>															
															<td style="width:20px;text-align:center">${client.phoneNumber}</td>
															<td style="width:20px;text-align:center">${client.email}</td>
															<%-- <td style="width:20px;text-align:center">${client.fax}</td> --%>
															<td style="width:20px;text-align:center" >${client.website}</td>
															<td style="width:20px;text-align:center">${client.statusName}</td>	
															<%-- <td style="width:20px;text-align:center">${client.departmentName}</td>	 --%>																												
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
	                                    No such data exist for this Date!
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
	var event = document.getElementById("eventId").value;	
	var fromDate = document.getElementById("fromdate").value;	
	var toDate = document.getElementById("todate").value;
	//var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	
	//var fromdt = new Date(fromDate);
	//var todt = new Date(toDate);
	//var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
	
	
	if(event == "")
	{
		alert("Please Select Conference!");
		document.getElementById("eventId").focus();
		return false;
	}
	 if(fromDate != "")
	{
		 if(toDate == "")
		 {
				alert("Please Select To Date!");
				document.getElementById("todate").focus();
				return false;
		 }
	}
	 if(toDate != "")
		{
			 if(fromDate == "")
			 {
					alert("Please Select From Date!");
					document.getElementById("fromdate").focus();
					return false;
			 }
		}
	
	/*if(fromdt > todt)
	{
		alert("From Date should be Lesser than To Date!");
		document.getElementById("todate").value="";
		return false;
	}
	if(diffDays > 30)
	{
		alert("Please select date range between one month!");
		return false;
	} */
	
	
}
</script>


<%@include file="crm-footer.jsp"%>