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
                                            <li class="breadcrumb-item active">Add status</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Add Status</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-3">
                                 

                                    <form:form action="insertstatus" method="post" modelAttribute="crmstatus" class="parsley-examples" >
                                     <c:if test="${crmstatus.getStatusDescription() != null}">
        							 <tr><p style="color:red;"><c:out value="Status Description already exists!" /></p></tr>
    								</c:if>
                                                                            
                                        <div class="form-group">
                                            <label for="statusDescription">Status Description<span class="text-danger">*</span></label>
                                            <input type="text" name="statusDescription" parsley-trigger="change" required
                                                   placeholder="Enter Status Description" maxlength="100" class="form-control charactersOnly" id="statusDescription">
                                        </div>    
                                        
                                                                           
                                        
                                        <div class="form-group text-right m-b-0">
                                            <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">
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

<script src="resources/assets/js/statusValidation.js"/></script>
<script src="resources/assets/js/common.js"/></script>

<script>
	function cancel()
	{
		location.href = "managestatus";
	}
</script>
 <%@include file="crm-footer.jsp" %>