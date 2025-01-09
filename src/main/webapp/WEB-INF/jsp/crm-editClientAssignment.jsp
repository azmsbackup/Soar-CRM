<%@include file="crm-header.jsp" %>

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
                                            <li class="breadcrumb-item active">Edit Swap Client</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit Swap Client</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-12">
                                 

                                <form:form action="updateclientassignment" method="post" modelAttribute="client" class="parsley-examples" > 
                                <form:hidden path="traceNo" value="${client.traceNo}" />
                                  <input type = "hidden" name="hiddenuserid" value="${client.userId}" />                                  
                                     <div class="row">  
                                     	<div class="col-md-4"> 
                                     		<div class="form-group">
		                                            <label for="clientName"><b>Client Name &nbsp; &nbsp; :</b></label>
		                                             <label for="clientName"> &nbsp; &nbsp;<b> ${client.clientName} </b></label>
                                       		 </div>
                                       		  <div class="form-group">
                                            		<label for="deptId">From User</label>                                             
                                           			<form:select  type= "select"  path="userId" class="form-control" id="userId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
		        									</form:select>                                                                                                       
                                      			</div>                                        		                                                                          
	                                      </div>
	                                    <div class="col-md-4">
	                                    	<div class="form-group">
		                                            <label for="traceNo"><b>Trace Number &nbsp; &nbsp; :</b></label>
		                                             <label for="traceNo"> &nbsp; &nbsp;<b> ${client.traceNo} </b></label>
                                       		 </div>
                                       		
                                      			<div class="form-group">
                                            		<label for="deptId">Swap to<span class="text-danger">*</span></label>                                             
                                           			<form:select  type= "select"  path="username" class="form-control" id="username">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
		        									</form:select>                                                                                                       
                                      			</div>   
	                                    </div>
	                                                                           
                                   	</div>                                                                      
                                    
                                  <div class="form-group text-right m-b-0">
                                      <button class="btn btn-primary waves-effect waves-light" type="submit" onclick="return validate()">Submit </button>
                                       <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button>
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
	  document.getElementById("userId").disabled = true;
}
</script>
 <script>
	function cancel()
	{
		location.href = "manageclientassignment";
	}
	function validate()
	{
		var userid = document.getElementById("username").value;		
		if(userid == "")
		{
			alert("Please Select Swap to!");
			document.getElementById("username").focus();
			return false;
		}
	}
</script>
 <%@include file="crm-footer.jsp" %>