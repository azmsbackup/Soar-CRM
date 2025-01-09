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
								<li class="breadcrumb-item active">Overdue Deals</li>
							</ol>
						</div>
						<h4 class="page-title">Overdue Deals</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="" method="post" modelAttribute="client" class="parsley-examples">
							
							
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons" class="table table-striped dt-responsive nowrap">
													<thead class="thead-light">
                                        			<tr>                                           
                                             			<th style="color:#000000" data-field="traceNo" data-sortable="true">Trace No</th>
                                             			<th style="color:#000000" data-field="clientName" data-sortable="true">Client Name</th>  
                                             			<th style="color:#000000" data-field="phoneNumber" data-sortable="true">Phone Number</th> 
                                             			<th style="color:#000000" data-field="email" data-sortable="true">Email</th> 
                                             			<th style="color:#000000" data-field="departmentName" data-sortable="true">Dapartment</th>
                                             			<th style="color:#000000" data-field="sourceName" data-sortable="true">Source</th>  
                                             			<th style="color:#000000" data-field="statusName" data-sortable="true">Status</th> 
                                             			<th style="color:#000000" data-field="notes" data-align="center">Add Notes</th>                                            			                                                                               
                                        			</tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="client" items="${overdueDealstList}">
														<tr>
															
															<td style="text-align:right"><a href="editclient?id=${client.traceNo}&&clientName=${client.clientName}"> 
															${client.traceNo} </a></td>	
															<td>${client.clientName}</td>														
															<td>${client.phoneNumber}</td>
															<td>${client.email}</td>
															<td>${client.departmentName}</td>
															<td>${client.sourceName}</td>
															<td>${client.statusName}</td>
															<td><a href="addnotes?id=${client.traceNo}&&clientName=${client.clientName}"><button type="button" class="btn btn-success waves-effect waves-light">
															<i class="fe-file-plus"></i></button></a></td> 																														
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
							
							
							</form:form>
							<center> <button type="submit" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button></center>
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
	function cancel()
	{
		location.href = "crm-dashboard";
	}
</script>

<%@include file="crm-footer.jsp"%>