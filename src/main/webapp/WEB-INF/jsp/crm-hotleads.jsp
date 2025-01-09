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
								<li class="breadcrumb-item active">Hot Leads</li>
							</ol>
						</div>
						<h4 class="page-title">Hot Leads</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="" method="post" modelAttribute="client" class="parsley-examples">
							 <form:hidden path="userId" id="userid" value="${client.userId}"/>
							
								<div class="row">
									<div class="col-12">
								
						
											<table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[10, 50, 100, 250, 300, 350, 400, 450, 500]"
                                           data-page-size="10"
                                           data-pagination="true" data-show-pagination-switch="true" class="table table-striped toggle-circle mb-0"> 
												<thead class="thead-light">
                                        		<tr>
		                                            <th  data-field="id" data-sortable="true" data-toggle="true">Trace No</th>
		                                           	<th  data-field="name" data-sortable="true">Client Name</th>
		                                           	<th  data-field="timezone" data-sortable="true">Time Zone</th>
		                                           <!--  	<th  data-hide="all">Location</th> -->
		                                           	<th  data-field="department" data-sortable="true">Department</th>
		                                           	<th  data-field="statusName" data-sortable="true">Status</th>
		                                           	<th  data-field="bucketName" data-sortable="true">Sub-Status</th>
		                                           	<th  data-field="modifiedDate" data-sortable="true">Last Activity Date</th>
		                                           	<th  data-field="userName" data-sortable="true">Account Owner</th>
		                                           	<c:if test="${sessionScope.RoleId != '1'}">                                               	
		                                           		<th  data-sort-ignore="true">Add Notes</th>
		                                           		<th  data-sort-ignore="true">View Notes</th> 
		                                           	</c:if>		  
		                                           	                                        	                                            
		                                        </tr>
                                       			 </thead>
                                       			 
                                       			 
                                       			  <form:hidden path="frompage" value="dashboardhotleads"	/>
                                       			  <form:hidden path="roleid" value="${sessionScope.RoleId}"	/>	
                                       			 <tbody>
												<c:forEach var="clien" items="${client.hotleadsList}" varStatus="loop">
												<form:hidden path="hiddenuserid" id="hiddenuserid${loop.index+1}" value="${clien.userId}" />
												<form:hidden path="hiddenTraceNo" id="traceNo${loop.index+1}" value="${clien.traceNo}" />
												<form:hidden path="hiddenClientName" id="clientName${loop.index+1}" value="${clien.clientName}" />
																		
												<tr>                                                                  
			                            			<td>${clien.traceNo}</td>
                                                     <td>${clien.clientName}</td>
                                                     <td>${clien.timezone}</td>
			                                     <!--   <td>
                                            		<p class="mb-1">
																<i class="ti-location-pin"></i>&nbsp;State :
																${clien.stateName} , City : ${clien.cityName}
															</p>
															
															<p class="mb-1 mt-3 mt-sm-0">
																<c:if test="${clien.email !='' && clien.email !=null}">
																	<i class="mdi mdi-email mr-1"></i>
																</c:if>${clien.email}</p>
															<p class="mb-1">
																<c:if
																	test="${clien.phoneNumber !='' && clien.phoneNumber != null}">
																	<i class="mdi mdi-phone-classic mr-1"></i>
																</c:if>
																${clien.phoneNumber}
															</p>
															
															<p class="mb-1 mt-3 mt-sm-0">
																<c:if test="${clien.email !='' && clien.email !=null}">
																	<i class="mdi mdi-email mr-1"></i>
																</c:if>${clien.email}</p>
															<p class="mb-1">
																<c:if
																	test="${clien.phoneNumber !='' && clien.phoneNumber != null}">
																	<i class="mdi mdi-phone-classic mr-1"></i>
																</c:if>
																${clien.phoneNumber}
															</p>
															
                                      				 </td>-->  
                                      				 
                                      				 
                                       				<td>${clien.departmentName}</td>
                                       				
													
													<td>${clien.statusName}</td>
													<td>${clien.bucketName}</td>
													<td>${clien.modifiedDate}</td>
													<td>${clien.username}</td>
													<c:if test="${sessionScope.RoleId != '1'}">                                      
	                                					<%-- <td><a href="addnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}">
	                                					<button type="button" onclick="return notes(${loop.index+1})" class="btn btn-success waves-effect waves-light">
														<i class="fe-file-plus"></i></button></a></td>	 --%>
														
														<c:choose>
															<c:when test="${clien.userId == client.userId}">
																<td style="text-align: center"><a href="addnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}"><button
																		type="button"
																		class="btn btn-success waves-effect waves-light">
																		<i class="fe-file-plus"></i>
																	</button></a></td>
															</c:when>
															<c:otherwise>
																<td style="text-align: center"><button
																		type="button"
																		class="btn btn-primary waves-effect waves-light" style="cursor: default;">
																		<i class="fe-file-plus"></i>
																	</button></td>
															</c:otherwise>
														</c:choose>
														<td style="text-align: center"><a href="viewnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}&&frompage=dashboardhotleads"><button type="button"
																class="btn btn-info waves-effect waves-light">
																<i class="icon-eye"></i>
															</button></a></td>
														
														
													</c:if>									
												 </tr>
													</c:forEach>
												</tbody>
												 <tfoot>
				                                 <tr class="active">
				                                       <td colspan="12">
				                                           <div class="text-right">
				                                               <ul class="pagination pagination-split justify-content-end footable-pagination m-t-10"></ul>
				                                           </div>
				                                        </td>
				                                </tr>
				                                </tfoot>
											</table>	
							
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
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>
	function cancel()
	{
		location.href = "crm-dashboard";
	}
	function notes(loopid)
	{
		var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		var userid = document.getElementById("userid").value;		
		var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
		var clientName = document.getElementById("clientName"+loopid).value;		
		
		/* if(userid != hiddenuserid)
		{
			alert("You are not allowed to add notes for this client!");
			return false;
		}
		else
		{ */
			location.href ="addnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid;
			return true;
		//}
	}

	function viewNotes(loopid)
	{
	
			var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;		
			var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
			var clientName = document.getElementById("clientName"+loopid).value;
			var frompage = document.getElementById("frompage").value;
			
			//var statusName = document.getElementById("statusName"+loopid).value;
			//var bucketName = document.getElementById("bucketName"+loopid).value;
			
			location.href ="viewnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid+"&&frompage="+frompage;
	}
</script>
<%@include file="crm-footer.jsp"%>