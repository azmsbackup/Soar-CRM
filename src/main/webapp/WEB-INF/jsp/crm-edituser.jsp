<%@include file="crm-header.jsp" %>

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
                                            <li class="breadcrumb-item active">Edit User</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit User</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-6">
                                 

                                    <form:form action="updateuser" method="post" modelAttribute="user" class="parsley-examples" >
                                    <form:hidden path="userId" value="${user.userId}" />
                                    <form:hidden path="hiddenroleid" value="${user.roleId}" />
                                     <div class="row">
                                        <div class="col-md-6">
                                        	<div class="form-group">
                                            	<label for="firstName">First Name<span class="text-danger">*</span></label>
                                            	<input type="text" name="firstName" parsley-trigger="change" required value="${user.firstName}"
                                                   placeholder="Enter First Name" maxlength="45" class="form-control charactersOnly" id="firstName">
                                        	</div>
                                        
                                        	<div class="form-group">
                                            	<label for="lastName">Last Name</label>
                                            	<input type="text" name="lastName" parsley-trigger="change" value="${user.lastName}"
                                                   placeholder="Enter Last Name" maxlength="45" class="form-control charactersOnly" id="lastName">
                                        	</div>
                                    		<%-- <div class="form-group">
                                            	<label for="userType">User Type<span class="text-danger">*</span></label>                                            
                                              	<form:select  path="userType" items="${user.userTypeList}" id="userType" class="form-control" required="true" />                                                                                                          
                                        	</div> --%>
                                        	
                                        	<div class="form-group">
                                          		<label for="roleId">User Role<span class="text-danger">*</span></label>                                             
                                         			<form:select  type= "select"  path="roleId" class="form-control" id="roleId" onclick="return role()">
		            									<form:option value="" label="--Select--"/>
		            									<form:options items="${user.rolelist}" itemValue="roleId" itemLabel="roleName"/>
        											</form:select>                                                                                                       
                                      		</div>
                                        
                                         	<div class="form-group">
                                            	<label for="employeeId">Employee Id</label>   
                                            	<input type="text" name="employeeId" parsley-trigger="change" value="${user.employeeId}"
                                                   placeholder="Enter Employee Id" maxlength="45" class="form-control" id="employeeId">                                       
                                        	</div>
                                        
                                        	
                                     </div>
                                    <div class="col-md-6">                                        
                                        <div class="form-group">
                                            <label for="loginName">Login Name<span class="text-danger">*</span></label>
                                            <input type="text" name="loginName" parsley-trigger="change" required value="${user.loginName}" 
                                                   placeholder="Enter Login Name" maxlength="15" class="form-control Loginname" id="loginName">
                                        </div>
                                      
                                        <div class="form-group">
                                            <label for="pass1">Password<span class="text-danger">*</span></label>
                                            <input id="password" type="password" name="userpassword" placeholder="Enter Password" required
                                                   value="${user.userpassword}" maxlength="30" class="form-control">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="passWord2">High security Password </label>
                                            <input data-parsley-equalto="#password" type="password" value="${user.highSecurityPassword}"   name="highSecurityPassword" 
                                            maxlength="30"  placeholder="Enter High Security Password" class="form-control" id="confirmPassword">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="emailAddress">Email ID<span class="text-danger">*</span></label>
                                            <input id="emailId" type=email name="emailId" placeholder="Enter Email ID" required
                                                  value="${user.emailId}"  maxlength="45"  class="form-control">
                                        </div> 
                                   </div> 
                                 </div>                                            
                                    
                                  <div class="form-group text-right m-b-0">
                                      <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">Submit </button>
                                       <button type="reset" class="btn btn-secondary waves-effect m-l-5" onclick="return cancel()">Cancel</button>
                                  </div>                                 
                           </form:form>
                         </div>
                      </div> <!-- end card-box -->
                   </div>
                            <!-- end col -->   
               </div>
                        <!-- end row -->
            </div> <!-- container -->

         </div> <!-- content -->
      </div> <!-- content-page -->


            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->        
<script>
window.onload = function () 
{	
	var roleid = document.getElementById("roleId").value;
	if(roleid != "1")
	{
	  document.getElementById("confirmPassword").disabled = true;
	}
	else
	{
		document.getElementById("confirmPassword").disabled = false;
	}
}
</script>
 <script>
	function cancel()
	{
		location.href = "manageuser";
	}
	function role()
	{
		var roleid = document.getElementById("roleId").value;
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
 <script src="resources/assets/js/edituserValidation.js" /></script>
 <%@include file="crm-footer.jsp" %>