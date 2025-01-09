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
								<li class="breadcrumb-item active">Duplicate Client Report</li>
							</ol>
						</div>
						<h4 class="page-title">Duplicate Client Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="logreportSubmit" method="post" modelAttribute="client" class="parsley-examples">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-inline">	
										<div class="form-group mx-sm-2">
											<label for="notesDate">From Date<span class="text-danger">*&nbsp;</span></label> 
											<input type="text"	id="fromdate" name="createdDate" value="${client.createdDate}" class="form-control" placeholder="From Date">
										</div>
										<div class="form-group mx-sm-2">
											<label for="notesDate">To Date<span class="text-danger">*&nbsp;</span></label> 
											<input type="text"	id="todate" name="modifiedDate" value="${client.modifiedDate}" class="form-control" placeholder="To Date">
										</div>
										
							            <div class="form-group mx-sm-2">
											<button class="btn btn-success waves-effect waves-light" type="submit" onclick="return validate()">Search
											</button>
											
										</div>
							       
									</div>
								</div>
							</div>
							<c:choose>
							<c:when test="${logList.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons" class="table table-striped">
												<input type="hidden" id="dt-title" value = "Allzone CRM - Duplicate Client Report" />
													<thead class="thead-light">
                                        			<tr> 
                                        				<!-- <th  style="text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>
                                        				<th  style="text-align:center "data-field="clientName" data-sortable="true">Client Name</th>  -->  
                                        				                                           
                                             			<th  style="text-align:center" data-field="Description" data-sortable="true">Description</th> 
                                             			<th  style="text-align:center "data-field="userName" data-sortable="true">Uploaded By</th>
                                             			<th style="text-align:center" data-field="Uploaded Date" data-sortable="true">Uploaded Date</th>                                            			 
                                             		</tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="client" items="${logList}">
														<tr>
															<%-- <td style="text-align:right; width:20px;">${client.traceNo}</td>
															<td style="width:200px;text-align:center">${client.clientName}</td>	 --%>
															<td style="width:250px;text-align:justify">${client.description}</td>
															<td style="width:150px;text-align:center">${client.username}</td>
															<td style="width:100px;text-align:center">${client.createdDate}</td>																																																									
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
	else
	{                                                                                                                         
		return true;
	}
	
	
	
}
</script>
<%@include file="crm-footer.jsp"%>