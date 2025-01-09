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
								<li class="breadcrumb-item active">Lead Report</li>
							</ol>
						</div>
						<h4 class="page-title">Lead Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="leadReportSubmit" method="post" modelAttribute="lead" class="parsley-examples">
								<div class="row row-grid">
									<div class="col-md-3">											
										<label for="sourceId">Source Name<span class="text-danger">*&nbsp;</span></label>                                             
	                                 	<form:select  type= "select"  path="sourceId" class="form-control" id="sourceId">
		     								<form:option value="" label="--Select--"/>
		     								<form:option value="All" label="All"/>
		     								<form:options items="${lead.sourceidlist}" itemValue="sourceId" itemLabel="sourceDescription"/>
										</form:select> 
									</div> 
									<div class="col-md-3">											
										<label for="notesDate">From Date<span class="text-danger">*&nbsp;</span></label> 
										<input type="text"	id="fromdate" name="createdDate" value="${lead.createdDate}" class="form-control" placeholder="From Date">
									</div>
									<div class="col-md-3">	
										<label for="notesDate">To Date<span class="text-danger">*&nbsp;</span></label> 
										<input type="text"	id="todate" name="modifiedDate" value="${lead.modifiedDate}" class="form-control" placeholder="To Date">
									</div>
									 <div class="col-md-3 ">
									 <label class="mt-lg-4"></label> 
							                  <button type="submit" class="mt-lg-2 btn btn-success waves-effect waves-light" onclick="return validate()" >
												<i class="fe-search"></i>&nbsp; Search</button>	
							         </div>
								</div>											
							<c:choose>
								<c:when test="${lead.leadList.size() > 0 }">
									<div class="row">
										<div class="col-12">
													<table id="datatable-buttons" class="table table-striped">
													<input type="hidden" id="dt-title" value = "Allzone CRM - Lead Report" />
														<thead class="thead-light">
	                                        			<tr>                                           
	                                             			<th  data-field="companyName" data-sortable="true">Company Name</th>
	                                             			<th  data-field="conFirstName" data-sortable="true">Contact Person</th>   
	                                             			<th data-field="phoneNumber" data-sortable="true">Phone Number</th> 
	                                             			<th  data-field="email" data-sortable="true">Email</th>
	                                             			 <th  data-field="traceNo" data-sortable="true">Trace No</th>                                         			                                                                               
	                                        			</tr>
	                                       			 </thead>	                                       			 
													<tbody>
														<c:forEach var="leadData" items="${lead.leadList}">
															<tr>
																<td style="text-left">${leadData.companyName}</td>
																<td style="text-align:left">${leadData.conFirstName} ${leadData.conLastName}</td>															
																<td style="text-align:left">${leadData.phoneNumber}</td>
																<td style="text-align:left">${leadData.email}</td>
																<td style="text-align:left">${leadData.traceNo}</td>																										
															</tr>
														</c:forEach>
													</tbody>
												</table>
										</div>
										<!-- end col-->
									</div>
								</c:when>
								<c:otherwise>								
									<c:if test="${lead.frompage != 'Initial'}">
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


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>

function validate()
{	
	var source = document.getElementById("sourceId").value;	
	var fromDate = document.getElementById("fromdate").value;	
	var toDate = document.getElementById("todate").value;
	var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	
	var fromdt = new Date(fromDate);
	var todt = new Date(toDate);
	var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
	
	
	if(source == "")
	{
		alert("Please Select Source!");
		document.getElementById("sourceId").focus();
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