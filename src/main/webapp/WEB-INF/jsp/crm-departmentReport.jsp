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
								<li class="breadcrumb-item active">Department Report</li>
							</ol>
						</div>
						<h4 class="page-title">Department Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="departmentReportsubmit" method="post" modelAttribute="client" class="parsley-examples">
							<div class="row row-grid">
								<div class="col-md-4">	
                                  	<label for="deptId">Department</label>                                             
                                    	<form:select  type= "select"  path="deptId" class="form-control" id="deptId">
	        								<form:option value="" label="All"/>
	        								<form:options items="${client.departmentidlist}" itemValue="departmentId" itemLabel="departmentName"/>
   									</form:select>                                                                                                       
                            	</div>
                            	<div class="col-md-4">                            							
									<label for="statusId">Status</label>
									<form:select type="select" path="statusId"	class="form-control" id="statusId">
										<form:option value="" label="All" />
										<form:options items="${client.statusidlist}" itemValue="statusId" itemLabel="statusDescription" />
									</form:select>									
								</div>   
								
								<div class="col-md-4">        
                                       <label for="servicesId">Services</label>                                             
                                        <form:select  type= "select"  path="servicesId" class="form-control" id="servicesId">
		            						<form:option value="" label="--Select--"/>
		            						<form:options items="${client.serviceslist}" itemValue="servicesId" itemLabel="servicesDescription"/>
	        							</form:select>                                                                                                       
                                </div>                         								
								
								<div class="col-md-4 row-grid">	
										<label for="notesDate">From Date<span class="text-danger">*&nbsp;</span></label> 
										<input type="text"	id="fromdate" name="fromDate" value="${client.fromDate}" class="form-control" placeholder="From Date">
								</div>								
								<div class="col-md-4 row-grid">
										<label for="notesDate">To Date <span class="text-danger">*&nbsp;</span></label> 
										<input type="text"	id="todate" name="toDate" value="${client.toDate}" class="form-control" placeholder="To Date">
								</div>																
							</div>

							<div class="row row-grid">
									<div class="col-sm-12 col-sm-offset-1">
							            <div class="row">
							                <div class="col-sm-12 col-sm-offset-4 text-center">
							                  <button type="submit" class="btn btn-success waves-effect waves-light" onclick="return validate()" >
												<i class="fe-search"></i>&nbsp; Search</button>	
							                </div>
							            </div>
							        </div>										
							</div>	
							<c:choose>
							<c:when test="${client.departmentList.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons" class="table table-striped">
												<input type="hidden" id="dt-title" value = "Allzone CRM - Department Report" />
													<thead class="thead-light">
                                        			<tr>                                           
                                             			<th  style="width:20px;text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>
                                             			<th  style="text-align:center" data-field="clientName" data-sortable="true">Client Name</th>  
                                             			<th  style="text-align:center" data-field="username" data-sortable="true">Account Owner</th>   
                                             			<th  style="text-align:center" data-field="phoneNumber" data-sortable="true">Phone Number</th>                                             			
                                             			<th  style="text-align:center" data-field="departmentName" data-sortable="true">Department</th>
                                             			<th  style="text-align:center" data-field="servicesName" data-sortable="true">Services</th>                                               			 
                                             			<th  style="text-align:center" data-field="statusName" data-sortable="true">Status</th>   
                                             			                                          			                                                                               
                                        			</tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="client" items="${client.departmentList}">
														<tr>
															<td  style="text-align:right; width:20px;">${client.traceNo}</td>
															<td style="text-align:center">${client.clientName}</td>	
															<td style="text-align:center">${client.username}</td>																
															<td style="text-align:center">${client.phoneNumber}</td>															
															<td style="text-align:center">${client.departmentName}</td>	
															<td style="text-align:center">${client.servicesName}</td>															
															<td style="text-align:center">${client.statusName}</td>																													
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
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>

function validate()
{	
	var department = document.getElementById("deptId").value;
	var status = document.getElementById("statusId").value;
	var fromDate = document.getElementById("fromdate").value; 
	var toDate = document.getElementById("todate").value; 
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	
	var fromdt = new Date(fromDate);
	var todt = new Date(toDate);
	var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
	
	if((fromDate == "") && (toDate == ""))
	{
		alert("Please Select From Date and To Date!");
		return false
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
	if(todate != "")
	{
		if(fromDate == "")
		{
			alert("Please Select From Date!");
			document.getElementById("fromdate").focus();
			return false;			
		}
	}
	if(fromdt > todt)
	{
		alert("From Date should be Lesser than To Date!");
		document.getElementById("todate").value="";
		return false;
	}
	/* if(diffDays > 30)
	{
		alert("Please select date range between one month!");
		return false;
	} */
	
}
</script>


<%@include file="crm-footer.jsp"%>