<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
<style>
.morecontent span {
	display: none;
}

.morelink {
	display: block;
}

div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
    }

</style>
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
								<li class="breadcrumb-item active">Lead</li>
							</ol>
						</div>
						<br>
						<c:if test="${lead.roleid !='1'}">
							<button id="demo-delete-row"
								class="btn btn-blue waves-effect waves-light"
								onclick="addLead()">
								<i class="mdi mdi-plus-circle mr-1"></i>Add Lead
							</button>
						</c:if>
						<br>
					</div>
				</div>
			</div>

			<div class="row row-grid">
				<div class="col-sm-12">
					<div class="card-box">
						<form:form class="form-horizontal parsley-examples"
							action="manageclientSubmit" method="post" modelAttribute="lead">
							<form:hidden path="userId" id="userid" value="${lead.userId}" />
							<c:choose>
								<c:when test="${lead.leadList.size() > 0 }">
							
									
										<table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[10, 50, 100, 250, 300, 350, 400, 450, 500]"
                                           data-page-size="10"
                                           data-pagination="true" data-show-pagination-switch="true" class="table table-striped dt-responsive nowrap">
											<thead class="thead-light">
												<tr>
													<th style="text-align: center" data-field="companyName"
														data-sortable="true">Company Name</th>
													<th style="text-align: center" data-field="conFirstName"
														data-sortable="true">Contact First Name</th>
													<th style="text-align: center" data-field="conLastName"
														data-sortable="true">Contact Last Name</th>
													<th data-field="department" data-sortable="true">Phone Number</th>
														<th style="text-align: center" data-field="email"
														data-sortable="true">Email</th>
													<th style="text-align: center" data-field="sourceName"
														data-sortable="true">Source</th>
													<th style="text-align: center" data-field="traceNo"
														data-sortable="true">Trace No</th>
													<th style="text-align: center"
														data-sortable="false">Convert</th>
													<th style="text-align: center"
														data-sortable="false">Edit</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="leadData" items="${lead.leadList}" varStatus="loop">
 												<tr>
													<td>${leadData.companyName}</td>
													<td>${leadData.conFirstName}</td>
													<td>${leadData.conLastName}</td>						
													<td>${leadData.phoneNumber}</td>
													<td>${leadData.email}</td>
													<td>${leadData.sourceName}</td>																			
													<td>${leadData.traceNo}</td>		
													<c:if test="${leadData.statusId != 'I'}"><td  style="text-align: center"><a href="convertLead?id=${leadData.leadId}"><button type="button"
														class="btn btn-success  waves-effect waves-light">
														<i class="icon-arrow-right-circle"></i>
														</button></a></td>
														<td  style="text-align: center"><a href="editLead?id=${leadData.leadId}"><button type="button"
															class="btn btn-warning  waves-effect waves-light">
															<i class="icon-pencil"></i>
														</button></a></td>
													</c:if>	
													<c:if test="${leadData.statusId == 'I'}"><td  style="text-align: center">
													<button type="button"
													class="btn btn-danger waves-light disabled">
													<i class="icon-close"></i></button></td>
													<td  style="text-align: center"><a href="editLead?id=${leadData.leadId}"><button type="button"
														class="btn btn-info  waves-effect waves-light">
														<i class="icon-eye"></i>
													</button></a></td>
													</c:if>						
													
													</tr>
												</c:forEach>
											</tbody>
						
										</table>
									
								</c:when>
								<c:otherwise>
									<c:if test="${leadData.frompage != 'Initial'}">
										<br>
										<div
											class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
											<span class="badge badge-pill badge-danger">No Data</span> No
											data exist!.
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:if>
								</c:otherwise>
							</c:choose>
						</form:form>
					</div>
					<!-- end card-box-->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function addLead(){
		location.href = "addLead";
	}
	function edit(hiddenTraceNo, userId){
		//alert("inside" + loopid);
		//var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		//var userId = document.getElementById("hiddenuserid"+loopid).value;
		alert("hiddenTraceNo "+ hiddenTraceNo);
		alert("userId "+ userId);
		
		location.href = "editclient?id="+hiddenTraceNo+"&userid="+userId;
	}
		
	$(document).ready(function() {
		 $scope.employeeReportList();
	});
			
		
	
</script>

<%@include file="crm-footer.jsp"%>