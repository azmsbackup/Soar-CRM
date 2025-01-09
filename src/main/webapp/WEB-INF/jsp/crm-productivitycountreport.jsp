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
								<li class="breadcrumb-item active">Productivity Count Report</li>
							</ol>
						</div>
						<h4 class="page-title">Productivity Count Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">
						<div class="col-lg-12">
							<form:form action="productivitycountsubmit" method="post"	modelAttribute="crmstatus" class="parsley-examples">
							<form:hidden path="roleid" id="roleid" value="${crmstatus.roleid}"/>
							<form:hidden path="frompage" id="frompage" />
															
								<div class="card">
									<div class="card-body">
										<div class="row row-grid">
			                            	<div class="col-md-4">	
			                            		<label for="notesDate">From Date &nbsp;</label> 
												<input type="text"	id="fromdate" name="fromDate" class="form-control flatpickr-input active" 
													value ="${crmstatus.fromDate}" placeholder="From Date" >
			                            	</div>
			                            	<div class="col-md-4">
			                            		<label for="modifiedDate">To Date &nbsp;</label> 
												<input type="text"	id="todate" name="toDate" class="form-control flatpickr-input active" 
												value ="${crmstatus.toDate}" placeholder="To Date">
			                            	</div>
			                            	
			                            </div>
			                            <div class="row row-grid">
											<div class="col-sm-12 col-sm-offset-1">
									            <div class="row">
									                <div class="col-sm-8 col-sm-offset-4 text-center">
									                  <button type="submit" class="btn btn-success waves-effect waves-light"	 
												onclick="return validate()">&nbsp; Search</button> &nbsp;
													
									                </div>
									            </div>
									        </div>										
										</div>

										
	
									<c:choose>
									<c:when test="${crmstatus.crmstatusList.size() > 0 }">
											<div class="row">
												<div class="col-12">
													<div class="card">
														<div class="card-body">
															<table  id="datatable-buttons" class="table table-striped">
															<input type="hidden" id="dt-title" value = "Allzone CRM - Productivity Count Report" />
																<thead class="thead-light">
			                                        			<tr>                                           
			                                             			<th  style="text-align:center" data-field="userName" data-sortable="true">User Name</th>  
			                                             			<th  style="text-align:center" data-field="opendeals" data-sortable="true">Data Collection</th> 
			                                             			<th  style="text-align:center" data-field="partialdata" data-sortable="true">Partial Data</th>  
			                                             			<th  style="text-align:center" data-field="emailsent" data-sortable="true">Email Sent</th>                                             			
			                                             			<th  style="text-align:center" data-field="followup" data-sortable="true">Follow-up(calls)</th>
			                                          			</tr>
			                                       			 </thead>
			                                       			 
															<tbody>
																<c:forEach var="crmstatus" items="${crmstatus.crmstatusList}">
																	<tr>
																		<td style="text-align:left">${crmstatus.userName}</td>
																		<td style="text-align:center">${crmstatus.opendeals}</td>	
																		<td style="text-align:center">${crmstatus.partialdata}</td>
																		<td style="text-align:center">${crmstatus.emailsent}</td>																
																		<td style="text-align:center">${crmstatus.followup}</td>															
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
											<c:if test="${crmstatus.frompage != 'Initial'}">
												<br>
												<div
													class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
													<span class="badge badge-pill badge-danger">No	Data</span> No such data exist!
													<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
													</button>
												</div>
											</c:if>
										</c:otherwise>
									</c:choose>
									</div>

											
											<!-- end card body-->
										</div>
										<!-- end card -->									
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

	
	function validate() {

		var fromDate = document.getElementById("fromdate").value;	
		var toDate = document.getElementById("todate").value;
		
		
		//var fromdt = fromDate.split("/");
		//var todt = toDate.split("/");
	//	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	
		var fromdt = new Date(fromDate);
		var todt = new Date(toDate);
		//var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
		
		
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
			return false;
			
		}

	}
	
	
</script>



<%@include file="crm-footer.jsp"%>