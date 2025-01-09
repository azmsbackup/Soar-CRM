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
								<li class="breadcrumb-item active">ClosedData</li>
							</ol>
						</div>
						<h4 class="page-title">Closed Data</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">
							
							<form:form action="" method="post" modelAttribute="client" class="parsley-examples">
							<%--  <form:hidden path="userId" id="userid" value="${client.userId}"/>    --%>
							<form:hidden path="frompage" value="dashboardclosed"	/>
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
		                                           	<!-- <th  data-hide="all">Location</th>  -->
		                                           	<th  data-field="department" data-sortable="true">Department</th>                                              	
		                                           <!-- 	<th  data-sort-ignore="true">Add Notes</th>		    -->    
		                                           
		                                           <th  data-field="sourceName" data-sortable="true">Source</th>  
		                                           <th  data-field="modifiedDate" data-sortable="true">Last Activity Date</th>
		                                            <th  data-field="userName" data-sortable="true">Account Owner</th>
		                                           <th  data-sort-ignore="true">View Notes</th>                                 	                                            
		                                        </tr>
                                       			 </thead>
                                       			 <tbody>
												<c:forEach var="clien" items="${client.closedList}" varStatus="loop">	
												<form:hidden path="hiddenuserid" id="hiddenuserid${loop.index+1}" value="${clien.userId}" />
												<form:hidden path="hiddenTraceNo" id="traceNo${loop.index+1}" value="${clien.traceNo}" />
												<form:hidden path="hiddenClientName" id="clientName${loop.index+1}" value="${clien.clientName}" />												
												<tr>                                                                  
			                            			<td>${clien.traceNo}</td>
			                            			<td>${clien.clientName}</td>
			                                        <%-- <td>
                                            			<p class="mb-1">
                                           				<i class="ti-location-pin"></i> &nbsp; State :  ${clien.stateName} </p> 
                                            			<p> &nbsp;  City :   ${clien.cityName}</p>                                             	
                                             			<p class="mb-1 mt-3 mt-sm-0"><c:if test="${clien.email !='' && clien.email !=null}">
                                             			<i class="mdi mdi-email mr-1"></i></c:if>${clien.email}</p>
														<p class="mb-0"><c:if test="${clien.phoneNumber !='' && clien.phoneNumber != null}">
														<i class="mdi mdi-phone-classic mr-1"></i></c:if> ${clien.phoneNumber}</p>
                                      				 </td> --%>
                                       				<td>${clien.departmentName}</td>
                                       				
                                       				<td>${clien.sourceName}</td>
                                       				<td>${clien.modifiedDate}</td>
                                       				<td>${clien.username}</td>  
                                       				<td style="text-align: center"><a href="viewnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}&&frompage=dashboardclosed">
                                       				<button type="button"
																class="btn btn-info waves-effect waves-light">
																<i class="icon-eye"></i>
															</button></a></td>                                   
                                					<%-- <td><a href="addnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}"><button type="button" class="btn btn-success waves-effect waves-light">
													<i class="fe-file-plus"></i></button></a></td>	 --%>									
												 </tr>
													</c:forEach>
												</tbody>
												 <tfoot>
				                                 <tr class="active">
				                                       <td colspan="11">
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
	/* function notes(loopid)
	{
		var userid = document.getElementById("userid").value;		
		var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
		
		if(userid != hiddenuserid)
		{
			alert("You are not allowed to add notes for this client!");
			return false;
		}
		else
		{
			return true;
		}
	} */
	
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

<!-- <script>

$(document).ready(function() {
    $('#datatable-buttons').DataTable( {
        "scrollX": true
    } );
} );


</script>
 -->

<%@include file="crm-footer.jsp"%>