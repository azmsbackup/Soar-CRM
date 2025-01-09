<%@ include file="crm-header.jsp" %>
<!-- Plugins css -->
<link href="resources/assets/libs/dropzone/dropzone.min.css"
	rel="stylesheet" type="text/css" />
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
								<li class="breadcrumb-item active">Upload Client Details</li>
							</ol>
						</div>
						<h4 class="page-title">Upload Client Details</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->
			<div class="row">
				<div class="col-5">
					<div class="card">
						<div class="card-body">
							<form:form action="submitUpload" method="post" name="form" enctype="multipart/form-data" modelAttribute="client">
								<c:choose>
									<c:when test="${sessionScope.savedsuccess == 'success'}">
										<div class="alert alert-success alert-dismissible fade show">
											<span class="badge badge-pill badge-success">Success</span>
											Data saved successfully.
											<button type="button" class="close" data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${sessionScope.savedsuccess != ''}">
											<div class="alert alert-danger alert-dismissible fade show">
												<span class="badge badge-pill badge-danger">Failed</span>
												<c:out value="${sessionScope.savedsuccess}" />
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
								
								<c:if test="${client.clientList.size() > '0'}">
									<ul>
										<li>
											<tr><p style="color: Green;">
												
												<c:forEach var="clien1" items="${client.clientList}">
													<c:out value="Client Name -  ${clien1.clientName} added successfully" />
													<br>
												</c:forEach>
												
											</p></tr>
										</li>
									</ul>
								</c:if>

								<c:if test="${client.logTraceList.size() > '0'}">
									<ul>
										 <%--  <li>
											<tr><p style="color: red;">
												<c:forEach var="clien" items="${client.logTraceList}">
													<c:out
														value="Trace No. ${clien.traceNo} - ${clien.logclientname}, ">
													</c:out>
												</c:forEach>
												<c:out value="Duplicate"></c:out> 
												</p></tr>  --%> 
												<c:forEach var="clien2" items="${client.logTraceList}">
												
													<li>
														<tr><p style="color: red;">
															<c:out value="${clien2.logDescription}" />
														</p></tr>
													</li>
											
												
											</c:forEach>
										<!-- </li> -->
										
									</ul>
								</c:if>

								<div class="form-group mb-3">
									<input type="file" name="file" id="example-fileinput" class="form-control-file">
								</div>

								<div class="clearfix text-left mt-3">
									<button type="submit" class="btn btn-danger" onclick="return validate()">
										<i class="mdi mdi-send mr-1"></i> Submit
									</button>
								</div>
							</form:form>

						</div>
						
						<!-- end card-body-->
					</div>
					
					<div class="card-box">
						<div class="col-lg-12">
							<div class="form-group page-title mb-3">
								<i class="far fa-file-excel font-26"></i>&nbsp;<a href="resources/assets/AllzoneCRMUploadTemplateNew.xls">Download Upload Template</a>&nbsp;
							</div>
							
						</div>
					</div>
								
					<!-- end card-->
				</div>
				
				<!-- end col -->
				<div class="col-7">
					<div class="card">
						<div class="card-body">	
						<h5>Rules for Upload Client Details</h5>							
							<ol>
								<li><span style="color: blue;"><c:out value="Client_Name" /></span><span style="color: red;">*</span></li>
								<li><span style="color: blue;"><c:out value="Address " /></span><c:out value="- No validation" /></li>
								<%-- <li><span style="color: blue;"><c:out value="Country"/></span><span style="color: red;">*</span><c:out value=" - Choose Country from drop down" /></li> --%>
								<li><span style="color: blue;"><c:out value="State" /></span><span style="color: red;">*</span><c:out value=" - Contains Characters Only" /></li>	
								<li><span style="color: blue;"><c:out value="City" /></span><span style="color: red;">*</span><c:out value=" - Contains Characters Only" /></li>	
								<li><span style="color: blue;"><c:out value="Zip" /></span><c:out value="- Contains Numerics Only" /></li>	
								<c:out value="Zip length is 5 digits for country - USA and 6 digits for others" />
								<li><span style="color: blue;"><c:out value="Dept_Name" /></span><span style="color: red;">*</span><c:out value=" - Give Department Code from System" /></li>	
							<%-- 	<li><span style="color: blue;"><c:out value="Service_Name" /></span><span style="color: red;"></span><c:out value=" - Give Service Description from System" /></li>	 --%>	
								<li><span style="color: blue;"><c:out value="Contact_Person" /></span><span style="color: red;">*</span><c:out value="- This field can capture two contact person name along with designation as well, each contact person should be separated by semi colon. " /></li>
								<c:out value=" For example : Adams John(Team Leader); Steve(Assistant Manager). If designation are not added, then only contact person data will be saved. " />
							<%-- 	<li><span style="color: blue;"><c:out value="Designation " /></span><c:out value="- valid two designation" /></li>	
								<c:out value=" Each should be separated by ; without any space " /> --%>
								<li><span style="color: blue;"><c:out value="Phone_No or e_Mail" /></span><span style="color: red;">*</span><c:out value="- Valid three Phone_No and e_Mail." /></li>
								<c:out value=" Each should be separated by ; without any space." />  <!--  Phone number should be in XXX-XXX-XXXX format. -->
								<li><span style="color: blue;"><c:out value="Phone_No " /></span><c:out value="- length 10 digits" /></li>
								<%-- <li><span style="color: blue;"><c:out value="Fax " /></span><c:out value="- Contains Numerics Only" /></li> --%>
								<li><span style="color: blue;"><c:out value="Website " /></span><c:out value="- No validation" /></li>	
							<%-- 	<li><span style="color: blue;"><c:out value="Description " /></span><c:out value="- No validation" /></li>  --%>
								<li><span style="color: blue;"><c:out value="DC_User_Name" /></span><span style="color: red;">*</span><c:out value="- Give Login name from System" /></li>
							<%-- 	<li><span style="color: blue;"><c:out value="Event_Name " /></span><c:out value="- Give Event Code from System" /></li>	 --%>
								<li><span style="color: blue;"><c:out value="Time_Zone " /></span><c:out value=" - Give Timezone from the below list" /></li>	
									<c:out value="i)CST " /><br>
									<c:out value="ii)EST " /><br>
									<c:out value="iii)MST " /><br>
									<c:out value="iv)PST " /><br>	
									<c:out value="v)HST " /><br>	
									<c:out value="vi)AKST " /><br>	
								<li><span style="color: blue;"><c:out value="Source Name " /></span><c:out value=" - Give Source Description from System" /></li>											
								<li><span style="color: red;"><c:out value="Upload process will not proceed further if Client_Name and State is empty" /></span></li>						
							</ol>
							<div class="row row-grid">	
								<span style="color: red;"><c:out value="Note: * - Mandatory Fields" /></span>					
							</div>
								
						</div>
					</div>
				</div>
			</div>
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
		var file = form.file.value;
		var reg = /(.*?)\.(xls|xlsx)$/;

		if (file == "") {
			alert("Please choose file!");
			return false;
		}

		if (!file.match(reg)) {
			alert("Invalid file!");
			return false;
		}
		ShowLoading();
	}
	function ShowLoading(e) {
		 //alert("in");
	        var div = document.createElement('div');
	        var img = document.createElement('img');
	        img.src = 'resources/assets/images/loading_gif.gif';
	       // div.innerHTML = "Loading...<br />";
	        div.style = 'position: fixed; top: 20%; left: 30%; width:33.5%;height:15%; text-align: center; background: #f7f7f2; border: 1px solid #000';
	        //img.style = 'width:60%;height:40%;'
	        div.appendChild(img);
	        document.body.appendChild(div);
	        return true;
	        // These 2 lines cancel form submission, so only use if needed.
	        //window.event.cancelBubble = true;
	        //e.stopPropagation();
	    }
</script>
<%@ include file="crm-footer.jsp" %>