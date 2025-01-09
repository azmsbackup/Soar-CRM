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
								<li class="breadcrumb-item active">Productivity Report</li>
							</ol>
						</div>
						<h4 class="page-title">Productivity Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">
						<div class="col-lg-12">
							<form:form action="productivityTotalSubmit" method="post"	modelAttribute="crmstatus" class="parsley-examples">
							<form:hidden path="roleid" id="roleid" value="${crmstatus.roleid}"/>
							<form:hidden path="frompage" id="frompage" />
							<c:if test="${crmstatus.roleid =='4' }"><label for="userId">User Name : ${crmstatus.userName}</label></c:if>								
								<div class="card">
									<div class="card-body">
									<c:choose>
									<c:when test="${crmstatus.roleid !='4' }">
										<div class="row row-grid">
											<div class="col-md-4">	
			                                  	<label for="userId">User Name &nbsp;</label>
												<form:select type="select" path="userid" class="form-control" id="username">
													<form:option value="" label="--Select--" />
													<form:options items="${crmstatus.userlist}"	itemValue="userId" itemLabel="firstName" />
												</form:select>                                                                                                     
			                            	</div>
			                            	<div class="col-md-4">	
			                            		<label for="notesDate">From Date &nbsp;</label> 
												<input type="text"	id="fromdate" name="fromDate" class="form-control flatpickr-input active" 
													value ="${crmstatus.fromDate}" placeholder="From Date" >
			                            	</div>
			                            	<div class="col-md-4">
			                            		<label for="modifiedDate">To Date &nbsp;</label> 
												<input type="text"	id="todate" name="toDate" class="form-control flatpickr-input active" value ="${crmstatus.toDate}" placeholder="To Date">
			                            	</div>
			                            </div>
			                            <div class="row row-grid">
											<div class="col-sm-12 col-sm-offset-1">
									            <div class="row">
									                <div class="col-sm-12 col-sm-offset-4 text-center">
									                  <button type="submit" class="btn btn-success waves-effect waves-light"	 
												onclick="return validate('One')">&nbsp; View One User</button> &nbsp;
													<button type="submit" class="btn btn-success waves-effect waves-light" onclick="return viewallvalidate('All')">&nbsp; View All Users	</button>
									                </div>
									            </div>
									        </div>										
										</div>
									</c:when>
									<c:otherwise>
											<div class="row">
												<div class="col-lg-12">
													<div class="form-inline">	
														
														<div class="form-group mx-sm-2">
															<label for="notesDate">From Date &nbsp;</label> 
															<input type="text"	id="fromdate" name="fromDate" class="form-control flatpickr-input active" 
																value ="${crmstatus.fromDate}" placeholder="From Date" >
														</div>
														<div class="form-group mx-sm-2">
															<label for="modifiedDate">To Date &nbsp;</label> 
															<input type="text"	id="todate" name="toDate" class="form-control flatpickr-input active" value ="${crmstatus.toDate}" placeholder="To Date">
														</div>
														<div class="form-group mx-sm-2">
															<button class="btn btn-success waves-effect waves-light" type="submit" onclick="return uservalidate()">Search
															</button>
											
														</div>
													</div>
												</div>
											</div>
											
	
									</c:otherwise>									
									</c:choose>
										
	
							<c:choose>
								<c:when test="${crmstatus.allusermap.size() > 0}">
									<c:forEach var="userlist" items="${crmstatus.allusermap}">
									<div class="card">
                                    <div class="card-body">
                                        <h2><c:out value="${userlist.key}" /></h2>	
											<div class="row-grid rtotal" id = "total">	
											<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<th class="text-center" colspan="2">Today</th>
														<th class="text-center" colspan="2">Weekly</th>
														<th class="text-center" colspan="2">Monthly</th>														
														<th class="text-center" colspan="2">Yearly</th>
													</tr>
													<tr>
														<c:forEach var="i" begin="0" end="3">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${userlist.value}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity" items="${status.value}">                                                      	                                                        	 
																<td style="text-align:center;">${productivity.targetopendaily}</td>															
																<td style="text-align:center;">${productivity.opendealsdaily}</td>															
																<td style="text-align:center;">${productivity.targetopenweekly}</td>															
																<td style="text-align:center;">${productivity.opendealsweekly}</td>															
																<td style="text-align:center;">${productivity.targetopenmonthly}</td>															
																<td style="text-align:center;">${productivity.opendealsmonthly}</td>																														
																<td style="text-align:center;">${productivity.targetYearlyDeals}</td>															
																<td style="text-align:center;">${productivity.yearlyDeals}</td>														
													</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>																				
											</table>
											<%@page import="java.util.Date"%>
											<%@page import="java.util.Calendar"%>
											<%@page import="java.util.Locale"%>
											<% pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)); %>	
											<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<c:if test="${currentMonth + 1 >= '1' }">
															<th class="text-center" colspan="2">Quarter I</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '3' }">
															<th class="text-center" colspan="2">Quarter II</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '6' }">
															<th class="text-center" colspan="2">Quarter III</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '9' }">
															<th class="text-center" colspan="2">Quarter IV</th>
														</c:if>
														<c:if test="${currentMonth + 1 >= '1' }">
															<th class="text-center" colspan="2">Half-yearly I</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '6' }">
														<th class="text-center" colspan="2">Half-yearly II</th>	
														</c:if>													
													</tr>
													<tr>
													<c:if test="${currentMonth + 1 >= '1' &&  currentMonth + 1 < '3'}">
														<c:forEach var="i" begin="0" end="1">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>
													<c:if test="${currentMonth + 1 > '3' &&  currentMonth + 1 < '6'}">
														<c:forEach var="i" begin="0" end="2">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>
													<c:if test="${currentMonth + 1 > '6' &&  currentMonth + 1 < '9'}">
														<c:forEach var="i" begin="0" end="4">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>	
													<c:if test="${currentMonth + 1 > '9' &&  currentMonth + 1 < '12'}">
														<c:forEach var="i" begin="0" end="5">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>													
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${userlist.value}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity1" items="${status.value}">
                                                       		 <c:if test="${currentMonth + 1 >= '1' }">
                                                        		<td style="text-align:center;">${productivity1.targetFirstQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.firstQuarterlyDeals}</td>
															</c:if>	
															<c:if test="${currentMonth + 1 > '3' }">														
																<td style="text-align:center;">${productivity1.targetSecondQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.secondQuarterlyDeals}</td>
															</c:if>
															<c:if test="${currentMonth + 1 > '6' }">
																<td style="text-align:center;">${productivity1.targetThirdQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.thirdQuarterlyDeals}</td>	
															</c:if>
															<c:if test="${currentMonth + 1 > '9' }">														
																<td style="text-align:center;">${productivity1.targetFourthQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.fourthQuarterlyDeals}</td>	
															</c:if>
															<c:if test="${currentMonth + 1 >= '1' }">															                                                      	                                                        	 
																<td style="text-align:center;">${productivity1.targetFirstHalfYearlyDeals}</td>															
																<td style="text-align:center;">${productivity1.firsthalfYearlyDeals}</td>
															</c:if>
															<c:if test="${currentMonth + 1 > '6' }">															
																<td style="text-align:center;">${productivity1.targetSecondHalfYearlyDeals}</td>															
																<td style="text-align:center;">${productivity1.secondhalfYearlyDeals}</td>	
															</c:if>													
														</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>
											</table>
											</div>
											
											<div class="row-grid rdate" id="date">
												<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<th class="text-center" colspan="2">${crmstatus.fromDate}  -   ${crmstatus.toDate}</th>														
													</tr>
													<tr>
														
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${userlist.value}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity" items="${status.value}">                                                      	                                                        	 
																<td style="text-align:center;">${productivity.targetDateuser}</td>															
																<td style="text-align:center;">${productivity.actualDateuser}</td>														
														</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>																				
											</table>									
									</div>
										</div>
									</div>	
									
									</c:forEach>
								</c:when>
										
										<c:when test="${crmstatus.statushashmap.size() > 0}">
										<div class="row-grid rtotal" id = "total">	
											<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<th class="text-center" colspan="2">Today</th>
														<th class="text-center" colspan="2">Weekly</th>
														<th class="text-center" colspan="2">Monthly</th>														
														<th class="text-center" colspan="2">Yearly</th>
													</tr>
													<tr>
														<c:forEach var="i" begin="0" end="3">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${crmstatus.statushashmap}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity" items="${status.value}">                                                      	                                                        	 
																<td style="text-align:center;">${productivity.targetopendaily}</td>															
																<td style="text-align:center;">${productivity.opendealsdaily}</td>															
																<td style="text-align:center;">${productivity.targetopenweekly}</td>															
																<td style="text-align:center;">${productivity.opendealsweekly}</td>															
																<td style="text-align:center;">${productivity.targetopenmonthly}</td>															
																<td style="text-align:center;">${productivity.opendealsmonthly}</td>																														
																<td style="text-align:center;">${productivity.targetYearlyDeals}</td>															
																<td style="text-align:center;">${productivity.yearlyDeals}</td>														
													</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>																				
											</table>
											<%@page import="java.util.Date"%>
											<%@page import="java.util.Calendar"%>
											<%@page import="java.util.Locale"%>
											<% pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)); %>	
											<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<c:if test="${currentMonth + 1 >= '1' }">
															<th class="text-center" colspan="2">Quarter I</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '3' }">
															<th class="text-center" colspan="2">Quarter II</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '6' }">
															<th class="text-center" colspan="2">Quarter III</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '9' }">
															<th class="text-center" colspan="2">Quarter IV</th>
														</c:if>
														<c:if test="${currentMonth + 1 >= '1' }">
															<th class="text-center" colspan="2">Half-yearly I</th>
														</c:if>
														<c:if test="${currentMonth + 1 > '6' }">
														<th class="text-center" colspan="2">Half-yearly II</th>	
														</c:if>													
													</tr>
													<tr>
													<c:if test="${currentMonth + 1 >= '1' &&  currentMonth + 1 < '3'}">
														<c:forEach var="i" begin="0" end="1">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>
													<c:if test="${currentMonth + 1 > '3' &&  currentMonth + 1 < '6'}">
														<c:forEach var="i" begin="0" end="2">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>
													<c:if test="${currentMonth + 1 > '6' &&  currentMonth + 1 < '9'}">
														<c:forEach var="i" begin="0" end="4">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>	
													<c:if test="${currentMonth + 1 > '9' &&  currentMonth + 1 < '12'}">
														<c:forEach var="i" begin="0" end="5">
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														</c:forEach>
													</c:if>													
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${crmstatus.statushashmap}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity1" items="${status.value}">
                                                       		 <c:if test="${currentMonth + 1 >= '1' }">
                                                        		<td style="text-align:center;">${productivity1.targetFirstQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.firstQuarterlyDeals}</td>
															</c:if>	
															<c:if test="${currentMonth + 1 > '3' }">														
																<td style="text-align:center;">${productivity1.targetSecondQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.secondQuarterlyDeals}</td>
															</c:if>
															<c:if test="${currentMonth + 1 > '6' }">
																<td style="text-align:center;">${productivity1.targetThirdQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.thirdQuarterlyDeals}</td>	
															</c:if>
															<c:if test="${currentMonth + 1 > '9' }">														
																<td style="text-align:center;">${productivity1.targetFourthQuarterlyDeals}</td>															
																<td style="text-align:center;">${productivity1.fourthQuarterlyDeals}</td>	
															</c:if>
															<c:if test="${currentMonth + 1 >= '1' }">															                                                      	                                                        	 
																<td style="text-align:center;">${productivity1.targetFirstHalfYearlyDeals}</td>															
																<td style="text-align:center;">${productivity1.firsthalfYearlyDeals}</td>
															</c:if>
															<c:if test="${currentMonth + 1 > '6' }">															
																<td style="text-align:center;">${productivity1.targetSecondHalfYearlyDeals}</td>															
																<td style="text-align:center;">${productivity1.secondhalfYearlyDeals}</td>	
															</c:if>													
														</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>
											</table>
											</div>
											<div class="row-grid rdate" id="date">
												<table id="datatable-buttons" class="table table-bordered dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<th rowspan="3">Status</th>
														<th class="text-center" colspan="2">${crmstatus.fromDate}  -   ${crmstatus.toDate}</th>														
													</tr>
													<tr>
														
															<th class="text-center" colspan="1">Target</th>
															<th class="text-center" colspan="1">Actual</th>
														
													</tr>
												</thead>
												<tbody>
													<c:forEach var="status" items="${crmstatus.statushashmap}">														
                                                       <tr><td>${status.key}</td>
                                                        <c:forEach var="productivity" items="${status.value}">                                                      	                                                        	 
																<td style="text-align:center;">${productivity.targetDateuser}</td>															
																<td style="text-align:center;">${productivity.actualDateuser}</td>														
														</c:forEach>
													</tr>
												</c:forEach>													
												</tbody>																				
											</table>									
									</div>
										</c:when>
										
										<c:otherwise>
											<c:if test="${crmstatus.frompage != 'Initial'}">
												<br>
												<div
													class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
													<span class="badge badge-pill badge-danger">No	Data</span> No such data exist!
													<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
													</button>
												</div>
											</c:if>
										</c:otherwise>
									</c:choose>
									</div>

											
											<!-- end card body-->
										</div>
										<!-- end card -->									
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
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->
<script>

	function setFrompage(input)
	{
		//alert("input "+ input);
		document.getElementById("frompage").value = input;
		
	}
	
	function viewallvalidate(input) {
		setFrompage(input);
		return datevalidate();
		
	}
	
	function validate(input) {
		setFrompage(input);
		var username = document.getElementById("username").value;
		//var fromDate = document.getElementById("fromdate").value;	
		//var toDate = document.getElementById("todate").value;
		//var status = document.getElementById("statuscrm").value;

		if (username == "") {
			alert("Please Select User Name!");
			document.getElementById("username").focus();
			return false;
		}
		
		return datevalidate();

	}
	
	function uservalidate()
	{
		setFrompage("One");
		return datevalidate();
		
	}
	
	function datevalidate()
	{
		var fromDate = document.getElementById("fromdate").value;	
		var toDate = document.getElementById("todate").value;
		
		
		//var fromdt = fromDate.split("/");
		//var todt = toDate.split("/");
			//var oneDay = 24*60*60*1000; // hours*minutes*seconds*milliseconds
	
	var fromdt = new Date(fromDate);
	var todt = new Date(toDate);
	//var diffDays = Math.round(Math.abs((fromdt.getTime() - todt.getTime())/(oneDay)));
		
		
		if(fromDate == "" && toDate != "")
		{
			alert("Please Select From Date!");
			document.getElementById("fromdate").focus();
			return false;
		}
		if(fromDate != "" &&  toDate == "")
		{
			alert("Please Select To Date!");
			document.getElementById("todate").focus();
			return false;
		}
		if(fromdt > todt)
		{
			alert("From Date should be Lesser than To Date!");
			return false;
			
		}
		else
		{
			document.getElementById('load').style.visibility="visible";
		}
		
	}
	
	
	
</script>

<script>

$(document).ready(function()
{	
	var roleid = $("#roleid").val();
	
	//if(roleid == 4)
	//{
		var fromdate = $("#fromdate").val();
		var todate = $("#todate").val();
		
		var fromdt = new Date(fromdate);
		var todt = new Date(todate);
		
		if((fromdate == "") && (todate == ""))
		{
			//$("#date").hide();
			//$("#total").show();
			$(".rdate").hide();
			$(".rtotal").show();
			
		}
		else if((fromdate != "") && (todate != ""))
		{
			//$("#total").hide();
			//$("#date").show();
			$(".rdate").show();
			$(".rtotal").hide();
		}
		else if(fromdt > todt)
		{
			alert("From Date should be Lesser than To Date!");
			$("#todate").val("");
			//$("#date").hide();
			//$("#total").show();
			$('.rdate').hide();
			$('.rtotal').show();
			return false;
		}
		else
		{
			//$("#date").hide();
			$(".rdate").hide();
			$(".rtotal").show();
		}
	//}
	//else
	//{
	//	$("#date").hide();
	//}
	
	
	
});

</script>

<%@include file="crm-footer.jsp"%>