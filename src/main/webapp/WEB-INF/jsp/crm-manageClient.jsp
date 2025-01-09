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
								<li class="breadcrumb-item active">Client</li>
							</ol>
						</div>
						<br>
						<c:if test="${client.roleid !='4'}">
							<button id="demo-delete-row"
								class="btn btn-blue waves-effect waves-light"
								onclick="addclient()">
								<i class="mdi mdi-plus-circle mr-1"></i>Add Client
							</button>
						</c:if>
						<br>
					</div>
				</div>
			</div>
			<!-- end page title -->
			
	<!-- 		  <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                <a href="insertexisting"><button type="button" class="btn btn-warning waves-effect waves-light">
								<i class="mdi mdi-pencil"></i>Insert Existing Client</button></a>
								 <a href="insertexistingnotes"><button type="button" class="btn btn-warning waves-effect waves-light">
								<i class="mdi mdi-pencil"></i>Insert Existing Notes</button></a>
                             	</div>
                             </div>
              </div> -->   

			<div class="row row-grid">
				<div class="col-sm-12">
					<div class="card-box">
						<form:form class="form-horizontal parsley-examples"
							action="manageclientSubmit" method="post" modelAttribute="client">
							<form:hidden path="userId" id="userid" value="${client.userId}" />
							<div class="row row-grid">
								<div class="col-md-2">
									<label class="text-center" for="statusId">Status</label>
									<form:select type="select" path="status" class="form-control"
										id="statusId" data-search="true">
										<form:option value="" label="All" />
										<form:options items="${client.statusidlist}"
											itemValue="statusId" itemLabel="statusDescription" />
									</form:select>
								</div>
								<div class="col-md-2">
									<label for="followUpDate">From Date</label> <input type="text"
										name="fromDate" parsley-trigger="change"
										placeholder="Select From Date" value="${client.fromDate}"
										class="form-control" id="fromdate">
								</div>
								<div class="col-md-2">
									<label for="followUpDate">To Date</label> <input type="text"
										name="toDate" parsley-trigger="change"
										placeholder="Select From Date" value="${client.toDate}"
										class="form-control" id="todate">
								</div>
								<div class="col-md-2">
									<label class="text-center" for="statusId">Search
										Parameter</label>
									<form:select type="select" path="searchbox"
										class="form-control" id="searchbox" data-search="true">
										<form:option value="" label="--Select--" />
										<form:option value="clientName" label="Client Name" />
										<form:option value="contactPerson" label="Contact Person" />
										<form:option value="email" label="Email" />
										<form:option value="website" label="Website" />
										<form:option value="phoneNumber" label="Phone Number" />
										<form:option value="traceNo" label="Trace No" />
									</form:select>
								</div>
								<div class="col-md-2 ">
									<label for="textbox">Search Value</label> <input type="text"
										name="textbox" parsley-trigger="change"
										placeholder="Enter search parameter value"
										value="${client.textbox}" class="form-control" id="textbox">
								</div>
								<div class="col-md-2 row-grid ">
									<div class="row row-grid">
										<div class="col-sm-12 col-sm-offset-4 text-center">
											<button type="submit"
												class="btn btn-success waves-effect waves-light"
												onclick="return search()">
												<i class="fe-search"></i>&nbsp; Search
											</button>
										</div>
									</div>
								</div>
							</div>


							<c:choose>
								<c:when test="${client.clientList.size() > 0 }">
							
									
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
													<th style="text-align: center" data-field="id"
														data-sortable="true">Trace No</th>
													<th style="text-align: center" data-field="name"
														data-sortable="true">Client Name</th>
													<th style="text-align: center" data-field="timezone"
														data-sortable="true">Time Zone</th>
													<!-- <th style="text-align: center" data-hide="all"></th>
													<th style="text-align: center" data-hide="all"></th>
													<th data-hide="all"></th> -->
													<th data-field="department" data-sortable="true">Department</th>
													
													<!-- <th data-hide="all"></th>
													<th data-hide="all"></th> -->
													<th style="text-align: center" data-field="statusName"
														data-sortable="true">Status</th>
													<th style="text-align: center" data-field="bucketName"
														data-sortable="true">Sub-Status</th>
													
													<th  style="text-align: center"  data-field="modifiedDate" 
													    data-sortable="true">Last Activity Date</th>
													<th  data-field="userName" data-sortable="true">Account Owner</th>
													<%-- <c:if test="${client.roleid !='1'}"> --%>
														<th style="text-align: center" data-sort-ignore="true">Edit</th>
													<%-- </c:if> --%>
													<%-- <c:if test="${client.roleid !='1'}"> --%>
														<th style="text-align: center" data-sort-ignore="true">Add
															Notes</th>
													<%-- </c:if> --%>
													<th style="text-align: center" data-sort-ignore="true">View
														Notes</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="clien" items="${client.clientList}" varStatus="loop">
 												
												
												
												<%-- <form:hidden path="bucketName"id="bucketName${loop.index+1}" value="${clien.bucketName}"/>  --%> 
													<tr>
												
														<td>${clien.manageTraceNo}</td>
														<td style="width: 25%;">${clien.clientName}</td>
														<td>${clien.timezone}</td>
						
														<td>${clien.departmentName}</td>
														<td>${clien.statusName}</td>
<%-- 														<c:choose>
															<c:when test="${clien.statusName == 'Open'}">
																<td><span class="badge label-table badge-open">${clien.statusName}</span>
															</c:when>
															<c:when test="${clien.statusName == 'Email'}">
																<td><span class="badge label-table badge-emailsent">${clien.statusName}</span></td>
															</c:when>
															<c:when test="${clien.statusName == 'Follow-up(calls)'}">
																<td><span class="badge label-table badge-followup">${clien.statusName}</span>
																</td>
															</c:when>
															<c:when
																test="${clien.statusName == 'New Leads(Response)'}">
																<td><span class="badge label-table badge-leads">${clien.statusName}</span>
																</td>
															</c:when>
															<c:when test="${clien.statusName == 'Not Reachable'}">
																<td><span
																	class="badge label-table badge-notreachable">${clien.statusName}</span>
															</c:when>
															<c:when test="${clien.statusName == 'Disqualified'}">
																<td><span
																	class="badge label-table badge-disqualified">${clien.statusName}</span>
															</c:when>
															<c:when test="${clien.statusName == 'Closed'}">
																<td><span class="badge label-table badge-closed">${clien.statusName}</span></td>
															</c:when>
															
															<c:when test="${clien.statusName == 'Client_Live'}">
																<td><span
																	class="badge label-table badge-clientlive">${clien.statusName}</span>
															</c:when>

															<c:otherwise>
																<td><span class="badge badge-warning"
																	style="width: 120px; height: 20px; padding: 5px 5px 5px 5px; cursor: auto">${clien.statusName}</span></td>
															</c:otherwise>

														</c:choose> --%>
														
														<td>${clien.bucketName}</td>
														
														<td>${clien.modifiedDate}</td>
														<td>${clien.username}</td>
												<form:hidden path="hiddenuserid" id="hiddenuserid${loop.index+1}" value="${clien.userId}" />
												<form:hidden path="hiddenTraceNo" id="traceNo${loop.index+1}" value="${clien.traceNo}" />
												<form:hidden path="hiddenClientName" id="clientName${loop.index+1}" value="${clien.clientName}" />	
												<form:hidden path="statusName" id="statusName${loop.index+1}" value="${clien.statusName}" /> 
														<c:choose>
															<c:when test="${clien.statusName != 'Closed'}">
																
																	<td style="text-align: center"><a href="editclient?id=${clien.traceNo}&userid=${clien.userId}"><button
																			type="button" 
																			class="btn btn-warning waves-effect waves-light">
																			<i class="mdi mdi-pencil"></i>
																		</button></a></td>
															

																
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
																
															</c:when>
															<c:otherwise>
																<c:if
																	test="${client.roleid !='4'}">
																	<td style="text-align: center"><button
																			type="button"
																			class="btn btn-danger waves-effect waves-light"
																			style="cursor: default;">
																			<i class="fe-delete"></i>
																		</button></td>
															
																</c:if>
																<%-- <c:if test="${client.roleid !='1'}">
																	<td style="text-align: center"><button
																			type="button"
																			class="btn btn-danger waves-effect waves-light"
																			style="cursor: default;">
																			<i class="fe-delete"></i>
																		</button></td>
																</c:if> --%>
															</c:otherwise>
														</c:choose>
														<td style="text-align: center"><a href="viewnotes?id=${clien.traceNo}&&clientName=${clien.clientName}&&userid=${clien.userId}&&status=${clien.statusName}"><button type="button"
																class="btn btn-info waves-effect waves-light">
																<i class="icon-eye"></i>
															</button></a></td>


													</tr>
												</c:forEach>
											</tbody>
						
										</table>
									
								</c:when>
								<c:otherwise>
									<c:if test="${client.frompage != 'Initial'}">
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
				<!-- end col-->

				<!-- end row-->



			</div>
			<!-- container -->

		</div>
		<!-- content -->
	</div>
</div>


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>
	function addclient()
	{
		location.href = "addclient";
	}
	function edit(hiddenTraceNo, userId)
	{
		//alert("inside" + loopid);
		//var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		//var userId = document.getElementById("hiddenuserid"+loopid).value;
		alert("hiddenTraceNo "+ hiddenTraceNo);
		alert("userId "+ userId);
		
		location.href = "editclient?id="+hiddenTraceNo+"&userid="+userId;
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
		var statusName = document.getElementById("statusName"+loopid).value;
		//var bucketName = document.getElementById("bucketName"+loopid).value;
		
		location.href ="viewnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid+"&&status="+statusName;//"&&bucket="+bucketName;
	}
	function mail()
	{
		alert("This function will be added in future!")
		return false;
	}
	
	function search()
	{
		var status = document.getElementById("statusId").value;
		//var traceno = document.getElementById("traceNo").value;
		//var clientname = document.getElementById("name").value;
		var fromdate = document.getElementById("fromdate").value;
		var todate = document.getElementById("todate").value;
		//var contactPerson = document.getElementById("contactPerson").value;
		//var phoneNumber = document.getElementById("phoneNumber").value;
		var searchbox = document.getElementById("searchbox").value;
		var textbox = document.getElementById("textbox").value;
		
		var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds		
		var fromdt = new Date(fromdate);
		var todt = new Date(todate);
		var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
		
		if(searchbox == "" && textbox =="" && fromdate=="" && todate=="" && status =="")
		{
			alert("Please Select Anyone to Search!");
			return false;
		}
		
		if(searchbox != "")
		{
			if(textbox == "")
			{
				alert("Please Enter Search Value!");
				document.getElementById("textbox").focus();
				return false;
			}
		}
		if(textbox != "")
		{
			if(searchbox == "")
			{
				alert("Please Select searchbox!");
				document.getElementById("textbox").focus();
				return false;
			}
		}
		
		/* if((searchbox == "") && (textbox == ""))
		{
			if(fromdate == "")
			{
				alert("Please Select From Date!");
				document.getElementById("fromdate").focus();
				return false;
			}
			if(todate == "")
			{
				alert("Please Select To Date!");
				document.getElementById("todate").focus();
				return false;
			}
		} */
		
		if((fromdate != "") && (todate != ""))
		{
			if(fromdt > todt)
			{
				alert("From Date should be Lesser than To Date!");
				document.getElementById("todate").value="";
				return false;
			}
		}		
		
			
		
		/* if(diffDays > 30)
		{
			alert("Please select date range between one month!");
			return false;
		} */
		 if(fromdate != "")
		{
			if(todate == "")
			{
				alert("Please Select To Date!");
				document.getElementById("todate").focus();
				return false;
			}
			
		}
		if(todate != "")
		{
			if(fromdate == "")
			{
				alert("Please Select From Date!");
				document.getElementById("fromdate").focus();
				return false;
			}
			
		} 
		
		else
		{
			return true;
		}
	}
</script>

<%@include file="crm-footer.jsp"%>