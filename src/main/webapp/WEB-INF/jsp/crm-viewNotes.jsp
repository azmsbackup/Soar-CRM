<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>

<style>
.morecontent span {
	display: none;
}

.morelink {
	display: block;
}
</style>
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
								<li class="breadcrumb-item active">Manage Notes</li>
							</ol>
						</div>
						<h4 class="page-title">Manage Notes</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->


			<div class="row">
				<div class="col-sm-12">
					<div class="card-box">
						<form:form action="" method="post" modelAttribute="notes"
							class="parsley-examples">
								
							<form:hidden path="userId" id="userId" value="${notes.userId}" />
							<form:hidden path="traceNo" id="traceNo" value="${notes.traceNo}" />
							<form:hidden path="clientName" id="clientName"
								value="${notes.clientName}" />
							<form:hidden path="hiddenUserId" id="hiddenUserId"
								value="${notes.hiddenUserId}" />
							<form:hidden path="" id="statusName" value="${notes.statusName}" />
						    <form:hidden path="frompage" id="frompage" value="${notes.frompage}" />
							<form:hidden path="roleId" value="${sessionScope.RoleId}"	/>
							<label for="traceNo"><b>Trace Number</b> &nbsp; &nbsp; <b>:</b></label>
							<label for="clientName"> &nbsp; &nbsp;<b>${notes.traceNo}</b></label> &nbsp; &nbsp; &nbsp; &nbsp;
                                   
                                    <label for="traceNo"><b>Client
									Name</b> &nbsp; &nbsp; <b>:</b></label>
							<label for="clientName"> &nbsp; &nbsp;<b>${notes.clientName}</b></label>
							<div class="table-responsive row-grid">
								<table id="demo-foo-addrow"
									class="table table-striped toggle-circle mb-0"
									data-page-size="5" data-limit-navigation="5">
									<thead class="thead-light">
										<tr>

											<th data-field="Notes" data-align="center"
												data-sortable="true">Notes</th>
											<th data-field="NotesDt" data-align="center"
												data-sortable="true">Notes Date</th>
											<th data-field="NotesStatus" data-align="center"
												data-sortable="true">Notes Status</th>
											<th data-field="NotesSubstatus" data-align="center"
												data-sortable="true">Notes Sub-status</th>
											<th data-field="username" data-align="center"
												data-sortable="true">Account Owner</th>	
											<c:choose>
												<c:when test="${sessionScope.RoleId != '1'}">
													<th data-field="view" data-align="center"
														data-sort-ignore="true">Edit Notes</th>
												</c:when>
												<c:otherwise>
													<th data-field="view" data-align="center"
														data-sort-ignore="true">View Notes</th>
												</c:otherwise>
											</c:choose>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="note" items="${notes.notesList}"
											varStatus="loop">
											<form:hidden path="notesId" id="notesId${loop.index+1}"
												value="${note.notesId}" />
											<tr>
												<td class="more">${note.notes}</td>
												<td>${note.notesDate}</td>
												<td>${note.statusName}</td>
												<td>${note.bucketName}</td>
												<td>${note.userName}</td>
												<%-- <td>${note.followUpDate}</td>	
												<td>${note.appointmentDate}</td>	
												<td>${note.appointmentTime}</td>
												<td>${note.appointmentStatus}</td> --%>
												
												<c:choose>
													<c:when test="${sessionScope.RoleId != '1'}">
														<td><button type="button" class="btn btn-warning waves-effect waves-light"
																onclick="return editNotes(${loop.index+1})">
													<i class="icon-pencil"></i>
															</button></td>
													</c:when>
													<c:otherwise>
															<td><button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="return viewNotes(${loop.index+1})">
													<i class="icon-eye"></i>
															</button></td>
													</c:otherwise>
												</c:choose>
												
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr class="active">
											<td colspan="8">
												<div class="text-right">
													<ul
														class="pagination pagination-split justify-content-end footable-pagination m-t-10"></ul>
												</div>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>

						<c:choose>
							<c:when test="${notes.hiddenUserId == sessionScope.UserId}">
									
									<c:choose>
										<c:when
											test="${sessionScope.RoleId != '1' && notes.statusName != 'Closed'}">
											<div class="row" style="margin-left: 80%;">
												<div id="addnotes" style="display: none;padding-right:10px;">
													<button type="button" id="addbtn"
														class="btn btn-success waves-effect waves-light"
														onclick="return addnotes()">
														<i class="fe-file-plus">Add Notes  &nbsp; </i>
													</button>
												</div>
												<div class="form-group">
													<button type="button" class="btn btn-danger waves-effect m-l-5"
														onclick="return cancel()">Back</button>
													&nbsp;
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="row" style="margin-left: 90%;">
												<div class="form-group">
												<button type="button" class="btn btn-danger waves-effect m-l-5"
													onclick="return cancel()">Back</button>
												&nbsp;
												</div>
											</div>
										</c:otherwise>
										
									</c:choose>
								</c:when>
								<c:otherwise>
									<div class="row" style="margin-left: 90%;">
										<div class="form-group">
											<button type="button" class="btn btn-danger waves-effect m-l-5"
												onclick="return cancel()">Back</button>
											&nbsp;
										</div>
									</div>
								</c:otherwise>
						</c:choose>

						</form:form>
					</div>
					<!-- end card-box-->
				</div>
				<!-- end col-->

			</div>
			<!-- end row-->



		</div>
		<!-- container -->

	</div>
	<!-- content -->
</div>

<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>
	function cancel()
	{
		var roleid = document.getElementById("roleId").value;
		if(document.getElementById("frompage").value == "dashboardfollowup")
		{
			if(roleid == 4)
			{
				location.href = "followUp";
			}
			else
			{
				location.href = "managerFollowUp";
			}
			
		}
		else if(document.getElementById("frompage").value == "dashboardoverduefollowup")
		{
			if(roleid == 4)
			{
				location.href = "overdueFollowup";
			}
			else
			{
				location.href = "managerOverdueFollowup";
			}
		}
		else if(document.getElementById("frompage").value == "dashboardemail")
		{
			if(roleid == 4)
			{
				location.href = "emailSent";
			}
			else
			{
				location.href = "managerEmailSent";
			}
		}
		else if(document.getElementById("frompage").value == "dashboardoverdueemail")
		{
			if(roleid == 4)
			{
				location.href = "overdueEmail";
			}
			else
			{
				location.href = "managerOverdueEmail";
			}
			
			
		}
		else if(document.getElementById("frompage").value == "dashboardclosed")
		{
			if(roleid == 4)
			{
				location.href = "closedDeals";
			}
			else
			{
				location.href = "managerClosed";
			}
			
			
		}
		else if(document.getElementById("frompage").value == "dashboardhotleads")
		{
			if(roleid == 4)
			{
				location.href = "hotleads";
			}
			else
			{
				location.href = "managerHotLeads";
			}
			
			
		}
		else if(document.getElementById("frompage").value == "dashboarddropped")
		{
			if(roleid == 4)
			{
				location.href = "droppedleads";
			}
			else
			{
				location.href = "managerDroppedLeads";
			}
			
			
		}
		else if(document.getElementById("frompage").value == "crmdashboard")
		{
			location.href = "crm-dashboard";
		}
		else
		{
			location.href = "manageclient";
		}
		//window.history.back();
		//
	}
	function viewNotes(loopid)
	{
		var hiddenuserId = document.getElementById("hiddenUserId").value;
		var notesId = document.getElementById("notesId"+loopid).value;
		var statusName = document.getElementById("statusName").value;
		//var bucketName= document.getElementById("bucketName").value;
		var frompage = document.getElementById("frompage").value;
		
		location.href = "viewnotessubmit?id="+notesId+"&&hiddenuserid="+hiddenuserId+"&&status="+statusName+"&&frompage="+frompage;
		
	}
	
	function editNotes(loopid)
	{
		var hiddenuserId = document.getElementById("hiddenUserId").value;
		var notesId = document.getElementById("notesId"+loopid).value;
		var statusName = document.getElementById("statusName").value;
		//var bucketName= document.getElementById("bucketName").value;
		var frompage = document.getElementById("frompage").value;
		
		location.href = "editnotes?id="+notesId+"&&hiddenuserid="+hiddenuserId+"&&status="+statusName+"&&frompage="+frompage;
		
	}
	
	function addnotes()
	{
		var traceNo = document.getElementById("traceNo").value;
		var clientName = document.getElementById("clientName").value;
		var userid = document.getElementById("hiddenUserId").value;
		
	
		location.href = "addnotes?id="+traceNo+"&&clientName="+clientName+"&&userid="+userid;
		
	}
</script>

<script>
$(document).ready(function() 
{ 
	var userid= document.getElementById("userId").value;
	var hiddenuserid = document.getElementById("hiddenUserId").value;

	 var addnotesid = document.getElementById("addnotes");
		
		/* if(userid != hiddenuserid)
		{
			//$("#addnotes").hide();
			addnotesid.style.display = "none";
		}
		else
		{ */
			addnotesid.style.display = "block";
			//$("#addnotes").show();
		//}
	
    var showChar = 30; 
    var ellipsestext = "...";

    $('.more').each(function() {
        var content = $(this).html();
 
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar, content.length - showChar);
 
            var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span>';
 
            $(this).html(html);
        }
 
    });
});
</script>

<%@include file="crm-footer.jsp"%>