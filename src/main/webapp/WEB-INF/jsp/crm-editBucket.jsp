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
                                            <li class="breadcrumb-item active">Edit Sub-Status</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit Sub-Status</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-3">
                                 

                                    <form:form action="updatebucket" method="post" modelAttribute="crmbucket" class="parsley-examples" >
                                    
                                    <form:hidden path="bucketId" value="${crmbucket.bucketId}" /> 
                                     <c:if test="${crmbucket.getFlag() == '1'}">
        							 <tr><p style="color:red;"><c:out value="Sub-Status Name already exists!!" /></p></tr>
    								</c:if> 
    								<div class="form-group">
                                            <label for="bucketname">Status<span class="text-danger">*</span></label>
                                                        <form:select  type= "select" path="statusId" class="form-control" id="statusId"  >
						               					
						              			 		<form:options    disabled="true"  items="${crmbucket.statusList}" itemValue="statusId" itemLabel="statusDescription" />
				        								</form:select>       
                                        </div> 
    								
                                 
                                    
  										<div class="form-group">
                                            <label for="bucketname">Sub-Status Name<span class="text-danger">*</span></label>
                                            <input type="text" name="bucketName" parsley-trigger="change" value="${crmbucket.bucketName}"
                                                   	placeholder="Enter Bucket Name" maxlength="100" class="form-control charactersOnly" id="bucketName">
                                        </div>                                     
                                                        
                                        <div class="form-group text-right m-b-0">
                                            <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return bucketValidate()">
                                                Submit
                                            </button>
                                            <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">
                                                Back
                                            </button>
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
<script src="resources/assets/js/bucketValidation.js"/></script>
<script src="resources/assets/js/common.js" /></script>
<script>
	function cancel()
	{
		location.href = "managebucket";
	}
	
	

	
</script>
 <%@include file="crm-footer.jsp" %>