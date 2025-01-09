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
						<h4 class="page-title">Client Search</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->
						<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="clientsearchsubmit" method="post" modelAttribute="client" class="parsley-examples">
								<div class="row row-grid">
									<div class="col-md-2">
										<label for="traceno">Trace No </label> 
										<form:input path="traceNo" data-parsley-type="number" placeholder="Enter trace no" class="form-control" maxlength="11" />									
									</div>
									<div class="col-md-2">  
	                                        <label for="clientName">Company Name</label>
	                                        <form:input path="clientName" placeholder="Enter Company Name" class="form-control" maxlength="100" /> 
	                                 </div>
									<div class="col-md-2">  
	                                        <label for="phoneNumber">Phone Number</label>
	                                        <form:input path="phoneNumber" placeholder="Enter Phone Number" class="form-control" data-toggle="input-mask" data-mask-format="000-000-0000"/> 
	                                 </div>
	                                 <div class="col-md-2">  
	                                        <label for="email">Email Address</label>
	                                        <form:input path="email" placeholder="Enter Email address" class="form-control"  /> 
	                                 </div>
	                                 <div class="col-md-2">  
	                                        <label for="contactPerson">Contact Person </label>
	                                        <form:input path="contactPerson" placeholder="Enter Contact Person" class="form-control"  maxlength="100" /> 
	                                 </div>
	                                 <div class="col-md-2">  
	                                        <label for="website">Website </label>
	                                        <form:input path="website" placeholder="Enter Website" class="form-control"  maxlength="100" /> 
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
									<c:when test="${allclientList.size() > 0 }">
										<div class="row">
											<div class="col-12">
									
													<table class="table-responsive table table-striped">
														
														<thead class="thead-light">
	                                        			<tr>
	                                        				<th  style="width:20px;text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>                                            
	                                             			<th  style="width:20px;text-align:center" data-field="clientName" data-sortable="true">Client Name</th>
	                                             			<th  style="width:20px;text-align:center" data-field="address" data-sortable="true">Address</th>
	                                             			<th  style="width:20px;text-align:center" data-field="state" data-sortable="true">State</th>
	                                             			<th  style="width:20px;text-align:center" data-field="city" data-sortable="true">City</th>
	                                             			<th  style="width:20px;text-align:center" data-field="zip" data-sortable="true">Zip</th>
	                                             			<th  style="width:20px;text-align:center" data-field="departmentName" data-sortable="true">Department</th>
	                                            			<th  style="width:20px;text-align:center" data-field="contactPerson" data-sortable="true">Contact Person</th>
	                                             			<th  style="width:150px;text-align:center" data-field="phoneNumber" data-sortable="true">Phone Number</th>                                             			 
	                                             			<th  style="width:20px;text-align:center" data-field="email" data-sortable="true">Email Address</th> 
	                                           				<th  style="width:20px;text-align:center" data-field="website" data-sortable="true">Website</th> 
	                                           				<th  style="width:20px;text-align:center" data-field="username" data-sortable="true">Account Owner</th>
	                                             			                                     			                                                                               
	                                        			</tr>
	                                       			 </thead>
	                                       			 
													<tbody>
														<c:forEach var="client" items="${allclientList}">
															<tr>
																<td style="text-align:right">${client.traceNo}</td>
																<td style="width:20px;text-align:center">${client.clientName}</td>	
																<td style="width:20px;text-align:center">${client.address}</td>
																<td style="width:20px;text-align:center">${client.stateName}</td>
																<td style="width:20px;text-align:center">${client.cityName}</td>	
																<td style="width:20px;text-align:center">${client.zip}</td>	
																<td style="width:20px;text-align:center">${client.departmentName}</td>	
																<td style="width:20px;text-align:center">${client.contactPerson}</td>															
																<td nowrap style="width:150px;text-align:center" class="phoneNo" id="phoneNumber" data-toggle="input-mask" data-mask-format="000-000-0000">${client.phoneNumber}</td>
																<td style="width:20px;text-align:center">${client.email}</td>
																<td style="width:20px;text-align:center" >${client.website}</td>
																<td style="width:20px;text-align:center">${client.username}</td>
																																												
															</tr>
														</c:forEach>
													</tbody>
												</table>
		
													
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
	var traceNo = document.getElementById("traceNo").value;		
	var clientName = document.getElementById("clientName").value;	
	var phoneNumber = document.getElementById("phoneNumber").value;
	var email = document.getElementById("email").value;
	var contactPerson = document.getElementById("contactPerson").value;
	var website = document.getElementById("website").value;
	
	if(traceNo == "" && clientName == "" && phoneNumber == "" && 
			email == "" && contactPerson == "" && website == "")
	{
		alert("Please atleast one search criteria to proceed.");
		document.getElementById("traceNo").focus();
		return false;
	}
	else if(traceNo != "")
	{
		 if(Isnum(traceNo)==false)
		 {
		   	  alert( "Please Enter only numbers in Trace No!");
		   	  document.getElementById("traceNo").focus();
			  return false;
		 }
	} 
/* 	else if(email != "")
	{
		 if(IsEmail(email)==false)
		 {
		   	  alert( "Please Enter Valid Email Format!");
		   	  document.getElementById("email").focus();
			  return false;
		 }
	}  */
	
	

}
</script>


<%@include file="crm-footer.jsp"%>