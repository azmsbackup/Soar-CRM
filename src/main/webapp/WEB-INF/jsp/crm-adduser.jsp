<%@include file="crm-header.jsp"%>

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
								<li class="breadcrumb-item active">Add User</li>
							</ol>
						</div>
						<h4 class="page-title">Add User</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-10">


							<form:form action="insertuser" method="post" modelAttribute="user" class="parsley-examples">

								<c:choose>
									<c:when test="${sessionScope.savedsuccess == 'success'}">
										<div class="modal-dialog modal-sm" role="document">
											<div class="modal-content">
												<div class="modal-body">
													<p style="color:green">User has been Saved Successfully!</p>
												</div>
												<div class="modal-footer">
													<a href="manageuser"><button type="button" class="btn btn-primary">OK</button></a>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:if test="${sessionScope.savedsuccess != ''}">
											<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
												<span class="badge badge-pill badge-danger">Failed</span>
												You are not allowed to having same Login Name. Please use
												some other Login Name.
												<button type="button" class="close" data-dismiss="alert" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
										</c:if>
									</c:otherwise>
								</c:choose>
								<div class="row">
									<div class="col-md-6">									
										<div class="form-group">
											<label for="firstName">First Name<span
												class="text-danger">*</span></label> <input type="text" name="firstName" parsley-trigger="change" required
												placeholder="Enter First Name" maxlength="45" value="${user.firstName}"
												class="form-control charactersOnly" id="firstName">
										</div>

										<div class="form-group">
											<label for="lastName">Last Name</label> <input type="text" name="lastName" parsley-trigger="change"
												placeholder="Enter Last Name" maxlength="45" value="${user.lastName}"
												class="form-control charactersOnly" id="lastName">
										</div>
										
										<%-- <div class="form-group">
											<label for="userType">User Type<span
												class="text-danger">*</span></label>
											<form:select path="userType" items="${user.userTypeList}" id="userType" class="form-control" required="true" />
											<div class="clearfix"></div>
										</div> --%>
										
										<div class="form-group">
                                          <label for="roleId">User Role<span class="text-danger">*</span></label>                                             
                                         	<form:select  type= "select"  path="roleId" class="form-control" id="roleId" onchange="return role()" value="${user.roleId}">
		            							<form:option value="" label="--Select--"/>
		            							<form:options items="${user.rolelist}" itemValue="roleId" itemLabel="roleName"/>
        									</form:select>                                                                                                       
                                      	</div>

										<div class="form-group">
											<label for="employeeId">Employee Id</label> 
											<input type="text" name="employeeId" parsley-trigger="change" placeholder="Enter Employee Id" maxlength="45"
												value="${user.employeeId}" class="form-control" id="employeeId">
											
										</div>
										<div class="form-group">
                                          <label for="roleId">Department<span class="text-danger">*</span></label>  
                                          <select name="departmentId" id="departmentId" class="selectpicker" multiple data-selected-text-format="count" data-style="btn-light">
												<c:forEach var="item" items="${user.departmentList}">
											         <option value="${item.departmentId}">${item.departmentName}</option>
											    </c:forEach>
											</select>                                          
                                        </div>
									</div>									

									<div class="col-md-6">
										<div class="form-group">
											<label for="loginName">Login Name<span
												class="text-danger">*</span></label> <input type="text"	name="loginName" parsley-trigger="change" required
												placeholder="Enter Login Name" maxlength="15" value="${user.loginName}"
												class="form-control Loginname" id="loginName">
										</div>

										<div class="form-group">
											<label for="pass1">Password<span class="text-danger">*</span></label>
											<input id="password" type="password" maxlength="30"	name="userpassword" placeholder="Enter Password" required
												value="${user.userpassword}" class="form-control">
										</div>

										<div class="form-group">
											<label for="passWord2">High security Password </label> 
											<input	data-parsley-equalto="#password" type="password" name="highSecurityPassword" maxlength="30" onclick="return myfunction()"
												value="${user.highSecurityPassword}" placeholder="Enter High Security Password" class="form-control" id="confirmPassword" >
										</div>

										<div class="form-group">
											<label for="email">Email <span class="text-danger">*</span></label>
											<input type="email" id="emailId" class="form-control" name="emailId" data-parsley-trigger="change" maxlength="45"
												value="${user.emailId}" required placeholder="Enter Email">
										</div>

									</div>

								</div>

								<div class="form-group text-right m-b-0">
								<c:if test="${sessionScope.savedsuccess != 'success'}">
									<button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">Submit</button>
								</c:if>
									<button type="reset" class="btn btn-secondary waves-effect m-l-5" onclick="return cancel()">Cancel</button>
								</div>

							</form:form>
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

<script src="resources/assets/js/uservalidation.js" /></script>
<script src="resources/assets/js/common.js" /></script>
<script>
/* window.onload = function () 
{		
	  document.getElementById("confirmPassword").disabled = true;
} */
</script>
<script>
	function myfunction()
	{
		var roleid = document.getElementById("roleId").value;
		
		if(roleid == "")
		{
			alert("Please Select User Role First!");
			document.getElementById("roleId").focus();
			return false;
		}
	}
	function cancel()
	{
		location.href = "manageuser";
	}
	function role()
	{
		var roleid = document.getElementById("roleId").value;
		//alert("roleid "+roleid);
		if(roleid == "1")
		{
			document.getElementById("confirmPassword").disabled = false;
		}
		else
		{
			document.getElementById("confirmPassword").disabled = true;
		}
	}
</script>
<%@include file="crm-footer.jsp"%>