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
                                            <li class="breadcrumb-item active">Edit Conference</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit Conference</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">            
                            <div class="col-lg-12">
                                <div class="card-box">                               		
                                	<div class="col-lg-8">                                 
									<form:form action="updateEvent" method="post" modelAttribute="conference" class="parsley-examples" > 
                                    <form:hidden path="eventId" value="${conference.eventId}" />  
                                    <div class="row">
										<div class="col-md-6">                                  
		                                      <div class="form-group">
		                                            <label for="eventCode">Conference Code<span class="text-danger">*</span></label>
		                                            <input type="text" name="eventCode" parsley-trigger="change" required value ="${conference.eventCode}"
		                                                   placeholder="Enter Conference Code" class="form-control" maxlength="15" id="eventCode">
		                                       </div>                                        
		                                        <div class="form-group">
		                                            <label for="eventName">Conference Name<span class="text-danger">*</span></label>
		                                            <input type="text" name="eventName" parsley-trigger="change" required value ="${conference.eventName}"
		                                                   placeholder="Enter Conference Name" class="form-control" maxlength="100" id="eventName">
		                                        </div>                                        
                                        		<div class="form-group">
                                            		<label for="eventMonth">Conference Month<span class="text-danger">*</span></label>
                                           			<form:select class="form-control" id="eventMonth" path="eventMonth" required="true" value="${conference.eventMonth}" >
                                            			<form:option value="" label="--Select --"/>
														<form:option value="January">January</form:option>
														<form:option value="February">February</form:option>
														<form:option value="March">March</form:option>
														<form:option value="April">April</form:option>
														<form:option value="May">May</form:option>
														<form:option value="June">June</form:option>
														<form:option value="July">July</form:option>
														<form:option value="August">August</form:option>
														<form:option value="September">September</form:option>
														<form:option value="October">October</form:option>
														<form:option value="November">November</form:option>
														<form:option value="December">December</form:option>															                                                            
                                             		</form:select>   
                                        		</div> 
                                        	</div> 
                                        	<div class="col-md-6">                                       
		                                        <div class="form-group">
		                                        	<label for="description">Department Name<span class="text-danger">*</span></label>
				                                     <form:select  type= "select"  path="deptId" class="form-control" id="deptId">
						            				<form:option value="" label="--Select--"/>
						            				<form:options items="${conference.departmentidlist}" itemValue="departmentId" itemLabel="departmentName"/>
						        					</form:select>
						        				</div>                                        
		                                        <div class="form-group">
		                                            <label for="description">Description<span class="text-danger">*</span></label>
		                                            <input type="text" name="description" parsley-trigger="change" required value = "${conference.description}"
		                                                   placeholder="Enter Description" class="form-control" maxlength="250"  id="description">
		                                        </div>
		                                     </div>
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
<script src="resources/assets/js/eventValidation.js" /></script>
<script src="resources/assets/js/common.js" /></script>
<script>
	function cancel()
	{
		location.href = "manageevent";
	}
</script>
 <%@include file="crm-footer.jsp" %>