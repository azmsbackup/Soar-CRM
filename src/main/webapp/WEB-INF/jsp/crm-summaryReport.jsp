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
								<li class="breadcrumb-item active">Summary Report</li>
							</ol>
						</div>
						<h4 class="page-title">Summary Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">
						<div class="col-lg-12">

							<form:form action="summaryReportSubmit" method="post" modelAttribute="crmstatus" class="parsley-examples">
                              <div class="row">
								<div class="col-lg-12">
									<div class="form-inline">	
										<div class="form-group mx-sm-2">
											<label for="notesDate">From Date<span class="text-danger">*&nbsp;</span></label> 
											<input type="text"	id="fromdate" name="createdDate" value="${crmstatus.createdDate}" class="form-control" placeholder="From Date">
										</div>
										<div class="form-group mx-sm-2">
											<label for="notesDate">To Date<span class="text-danger">*&nbsp;</span></label> 
											<input type="text"	id="todate" name="modifiedDate" value="${crmstatus.modifiedDate}" class="form-control" placeholder="To Date">
										</div>										
							            <div class="form-group mx-sm-2">
											<button class="btn btn-success waves-effect waves-light" type="submit" onclick="return validate()">Search
											</button>
											
										</div>
							       
									</div>
								</div>
							</div>
							<c:choose>
							<c:when test="${crmstatus.summaryhashmap.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body table-responsive">											
												<table class="table mb-0">
		                                           
													<thead class="thead-light">
                                        			<tr> 
                                        				<th style="text-align:center">User Name</th>
                                        				  <c:forEach var="allstatus" items="${crmstatus.statusallhashmap}">
                                        				  	 
				                     				 		<th style="text-align:center">${allstatus.key}</th>
				                     				 
				                     				 	</c:forEach>                                         			                                                                               
                                        			</tr>
                                       			 </thead>                                       			 
												<tbody>
													<c:forEach var="services" items="${crmstatus.summaryhashmap}">
														<tr><td style="text-align:center">${services.key}</td>
														<c:forEach var="status" items="${services.value}">
														  <td style="text-align:center">${status.value}</td>
														</c:forEach>
													 	  
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
									<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
	                                	<span class="badge badge-pill badge-danger">No Data</span>
	                                    No data exist!
	                                  	<button type="button" class="close" data-dismiss="alert">
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