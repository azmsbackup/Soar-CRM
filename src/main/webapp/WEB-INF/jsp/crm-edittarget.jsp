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
                                            <li class="breadcrumb-item active">Edit Target</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit Target</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->                        
                        <div class="row">            
                            <div class="col-lg-12">
                                <div class="card-box">                               		
                                	<div class="col-lg-8">                                
										<form:form action="updatetarget" method="post" modelAttribute="target" class="parsley-examples" >
                                    	<form:hidden path="targetsId" value="${target.targetsId}" />  
                                    	<form:hidden path="userId" value="${target.userId}"/>	                                
                                     	
                                     	<div class="form-group">
											<label for="userId">User Name :</label>
											<label for="userId">${target.userName}</label>												
										</div>
                                    	 <div class="row">
											<div class="col-md-6">	                                	   													
												<div class="form-group">
													<label for="text-input">Year <span class="text-danger">*</span></label>&nbsp;&nbsp;
														<form:select type="select" path="year" id="year"  class="form-control" disabled="true">
															<form:options items="${target.yearlist}" />
														</form:select>
												</div> 								
												<div class="form-group">
                                   					<label for="eventMonth">Month <span class="text-danger">*</span></label>
                                    				 <form:select class="form-control" id="month" path="month" required="true" disabled="true">
					                                    <form:option value="" label="--Select --"/>
														<form:option value="1">January</form:option>
														<form:option value="2">February</form:option>
														<form:option value="3">March</form:option>
														<form:option value="4">April</form:option>
														<form:option value="5">May</form:option>
														<form:option value="6">June</form:option>
														<form:option value="7">July</form:option>
														<form:option value="8">August</form:option>
														<form:option value="9">September</form:option>
														<form:option value="10">October</form:option>
														<form:option value="11">November</form:option>
														<form:option value="12">December</form:option>									                                                            
				                                     </form:select>
                                  				</div> 	
                                  			</div>	
                                  			<div class="col-md-6">									
												<div class="form-group">
													<label for="eventMonth">Target Source<span class="text-danger">*</span></label>										 
													<form:select type="select" path="statusId" class="form-control" id="statusId" disabled="true">
														<form:option value="" label="--Select--" />
														<form:options items="${target.statusList}" itemValue="statusId"	itemLabel="statusName" />
													</form:select>
												</div>									
												<div class="form-group">
													<label for="dailyTarget">Daily Target <span class="text-danger">*</span></label>
													 <input type="text"	name="dailyTarget" parsley-trigger="change" placeholder="Enter Daily Target" maxlength="100"
														value ="${target.dailyTarget}" class="form-control" id="dailyTarget">
												</div>
											</div>
										</div>				
									 <div class="form-group text-center m-b-0">
                                      <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return validate()">Submit </button>
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
 <script>
	
	/* function userName()
	{
		alert("User Name Cann't be Change!");
		return false;
	} */
	function validate()
	{
		var statusId = document.getElementById("statusId").value;
		var dailyTarget = document.getElementById("dailyTarget").value;
		
		 if(statusId == "")
		{
				alert("Please Select Target Source!");
				document.getElementById("statusId").focus();
				return false;
		}
		else if(dailyTarget == "")
		{
				alert("Please Enter Target Value!");
				document.getElementById("dailyTarget").focus();
				return false;
		}
		else
		{
			return true;
		}
	}
	
	document.getElementById("dailyTarget").addEventListener("keydown", function(event) {
	    // Allow only numeric values and control keys like backspace, tab, etc.
	    if (event.key === "Backspace" || event.key === "Tab" || event.key === "ArrowLeft" || event.key === "ArrowRight") {
	        return;  // Allow these keys
	    }

	    // Prevent non-numeric input
	    if (!/^\d$/.test(event.key)) {
	        event.preventDefault();
	    }
	});
	function cancel()
	{
		location.href = "managetarget";
	}
</script>
 <%@include file="crm-footer.jsp" %>