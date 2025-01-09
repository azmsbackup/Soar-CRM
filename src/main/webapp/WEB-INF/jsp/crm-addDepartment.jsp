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
                                            <li class="breadcrumb-item active">Add Department</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Add Department</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-6">                         
	                                    <form:form action="insertdepartment" method="post" modelAttribute="department" class="parsley-examples" >
	                                    <c:if test="${department.getDepartmentName() != null}">
	        								<tr><p style="color:red;"><c:out value="Department Code and Name already exists!" /></p></tr>
	        							</c:if>
        							</div>
                                      <div class="col-lg-3">                                         
                                        <div class="form-group">
                                            <label for="departmentCode">Department Code<span class="text-danger">*</span></label>
                                            <input type="text" name="departmentCode" parsley-trigger="change" required value="${department.departmentCode}"
                                                   placeholder="Enter Department Code" maxlength="15" class="form-control" id="departmentCode">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label for="departmentName">Department Name<span class="text-danger">*</span></label>
                                            <input type="text" name="departmentName" parsley-trigger="change" required value="${department.departmentName}"
                                                   placeholder="Enter Department Name" maxlength="100" class="form-control" id="departmentName">
                                        </div>
                            
                                        <div class="form-group text-right m-b-0">
                                            <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">
                                                Submit
                                            </button>
                                            <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">
                                                Back
                                            </button>
                                        </div>
								 </div>
                                    </form:form>
                                   
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
            
<script src="resources/assets/js/departmentValidation.js" /></script>
<script src="resources/assets/js/common.js" /></script>       
<script>
	function cancel()
	{
		location.href = "managedepartment";
	}
</script>
 <%@include file="crm-footer.jsp" %>