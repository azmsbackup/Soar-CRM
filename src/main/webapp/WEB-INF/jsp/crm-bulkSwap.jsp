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
                                            <li class="breadcrumb-item active">Bulk Swap Client</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Bulk Swap Client</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 
				 
                   
                   <div class="row">
                   
              
                            <div class="col-sm-12">
                                <div class="card-box">
                                
                                <div class="row">
                                
                                   
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-12">
                                 
                                   <div class="row">
							
							 <c:if test="${client.alreadyExistsMsg != null}">
							<div id="error-alert" class="alert alert-danger alert-dismissible fade show">
								<span class="badge badge-pill badge-danger">Failed</span>
								${client.alreadyExistsMsg}
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
						</c:if>
						<c:forEach var="message" items="${client.traceMessages}">
						    <div id="error-alert" class="alert alert-danger alert-dismissible fade show">
						        <span class="badge badge-pill badge-danger">Failed</span>
						        ${message}
						        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						            <span aria-hidden="true">&times;</span>
						        </button>
						    </div>
						</c:forEach>
							</div>	

                                <form:form action="clientSearchByTraceNoOnBulkswap" method="post" modelAttribute="client" class="parsley-examples" > 
                                <form:hidden path="traceNo" value="${client.traceNo}" />
                               <%--  <form:hidden path="traceNos" id="hiddenTraceNos" value="${traceNos}" />  --%>
                                <form:hidden path="fromUserIdstr" id="hiddenfromUserIds" value="${fromUserIdstr}" /> 
                                
                                  <input type = "hidden" name="hiddenuserid" value="${client.userId}" />   
                                  
                                  <div class="row row-grid">
            		  
									<div class="col-md-3">
									    <label for="traceno">Trace No <span class="text-danger">*</span></label>
									    <form:input path="traceNoNew" id="traceNoOnSearch" value="" data-parsley-type="text" placeholder="Enter trace no, separated by commas"
									        class="form-control"  />
									    <form:hidden path="traceNos" id="hiddenTraceNoOnSearch" value="${traceNos}" />
									</div>
									<div class="col-md-2"> 
									 <label for=""><span class="" style="color:white;">.</span></label>   
									  <div class="form-group m-t-4""><a><button type="submit" onclick="return searchValidate()" class="btn btn-success waves-effect waves-light m-t-4">
								Add Trace No</button></a>	</div></div>
								
								   <div class="col-md-3">
                                			
                                      <label for="deptId">Swap to<span class="text-danger">*</span></label>                                             
                                   		<form:select  type= "select"  path="username" class="form-control" id="username">
	          								<form:option value="" label="--Select--"/>
	          								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
   										</form:select>                                                                                                  
                                			
                                  </div>
		                                    
		                                       <c:if test="${client.clientListSize > 0}">   
		                                    <div class="col-md-2"> 
		                                      <label for=""><span class="" style="color:white;">.</span></label>  
				                                  <div class="form-group m-t-4"">
				                                      <button class="btn btn-primary waves-effect waves-light" type="button" onclick="return validate()">Submit </button>
				                                       <!-- <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">Back</button> -->
				                                  </div>   
				                            </div> 
				                             	</c:if>   
									
							
								</div>
								
								
                                     
                                                            
                                                                                                  
                                                           
                           </form:form>
                         </div>
                      </div> <!-- end card-box -->
                   </div>
                            <!-- end col -->   
               </div>    
                                    <!-- <h4 class="header-title">Manage Client Assignment</h4> -->
                              <!--  <div  style="height:600px;width:100%;overflow:auto;">
                                    <table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="false"
                                          data-show-refresh="false"
								  			 data-show-columns="false"
                                           data-sort-name="id"
                                           data-page-list="[5, 10, 20]"
                                           data-page-size="5"
                                           data-pagination="false" data-show-pagination-switch="false" class="table table-striped dt-responsive nowrap"> -->
                                           
                                           			<div  style="height:600px;width:100%;overflow:auto;">
								<table data-toggle="table"
                                           data-page-size="5"
                                           data-buttons-class="xs btn-light"
                                           data-pagination="true" class="table-bordered " >
                                        <thead class="thead-light">
                                        <tr>    
                                        	<th  data-align="left" data-field="traceNo" data-sortable="false">Trace No</th>                                        
                                             <th   data-align="center" data-field="clientName" data-sortable="false">Client Name</th>
                                          	 <th  data-field="user" data-align="center" data-sortable="false">From User</th>
                                          	 <th  data-field="remove" data-align="center" data-sortable="false">Remove</th>
                                           <!--  <th  data-field="notes" data-align="center">Swap</th> -->
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="client" items="${client.clientList}" varStatus="loop">
											<tr>	
												
												
												<td style = "text_align:left">
													${client.traceNo}
												</td>
																					
												<td style="width: 25%;">${client.clientName}</td>
												<td>${client.username}</td>		
												<td><a href="removeClientOnBulkSwap?id=${client.traceNo}" class="btn btn-xs btn-danger"><i class="mdi mdi-minus" title="Remove"></i></a></td>									
												
											</tr>
										</c:forEach>    
                                        </tbody>
                                    </table>
                                   </div>
                                </div> <!-- end card-box-->
                            </div> <!-- end col-->
                        </div>
                        <!-- end row-->
                       

                        
                    </div> <!-- container -->

                </div> <!-- content -->


            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
<script>

	window.onload = function () 
	{		
		  document.getElementById("userId").disabled = true;
		  document.getElementById("traceNoOnSearch").value = "";
	}
	
	var selectedObjectsList = new Array();
	var selectedFromUserlist = new Array(); 
	function clientSelectedCheckBox(obj, traceNo, fromUser)
	{	
		
		var value=obj.value;
	
		if(obj.checked)
		{
			 selectedObjectsList.push(traceNo);
			 selectedFromUserlist.push(fromUser);
		}
		else
		{
			var isExists = selectedObjectsList.includes(traceNo, 0);
			
			for(var i = 0;i < selectedObjectsList.length; i++)
			{
				
				if(selectedObjectsList[i] == traceNo)
				{
					selectedObjectsList.splice(i, 1);
					selectedFromUserlist.splice(i, 1);
					break;
				}
				
			}
			
		}
		
	}
	
	function validate()
	{	
		/* document.getElementById('hiddenTraceNos').value = selectedObjectsList;
		document.getElementById('hiddenfromUserIds').value = selectedFromUserlist; */
		
		/* if(selectedObjectsList.length == 0)
		{
			alert("Please Select Atleast One Client!");
			return false;
		}
		 */
		var userid = document.getElementById("username").value;		
		if(userid == "")
		{
			alert("Please Select Swap to!");
			document.getElementById("username").focus();
			return false;
		}
		else
		{
			location.href = "updateBulkclientassignment?id="+userid;
		}
	}
	
	function searchValidate() {
	    var traceNoOnSearch = document.getElementById("traceNoOnSearch").value.trim();  // Get input value and trim leading/trailing spaces
	    
	    if (traceNoOnSearch != "") {
	        document.getElementById('hiddenTraceNoOnSearch').value = traceNoOnSearch;  // Set hidden field with trace numbers
	    }

	    // Check if input is empty
	    if (traceNoOnSearch == "") {
	        alert("Please enter trace no");
	        document.getElementById("traceNoOnSearch").focus();
	        return false;
	    }

	    // Check if trace numbers are separated by commas and if all are valid numbers
	    var traceNumbers = traceNoOnSearch.split(",");  // Split the string by commas
	    for (var i = 0; i < traceNumbers.length; i++) {
	        var traceNo = traceNumbers[i].trim();  // Remove extra spaces around each trace number
	        if (traceNo == "") {
	            continue;  // Skip empty trace numbers (in case of extra commas)
	        }

	        if (isNum(traceNo) == false) {
	            alert("Please enter only numbers in Trace No!");
	            document.getElementById("traceNoOnSearch").focus();
	            return false;
	        }
	    }

	    // Optionally, you can submit the form or redirect if the validation passes
	    // location.href = "clientSearchByTraceNo";  // Uncomment this if you want to perform the redirect
	    return true;  // Validation passes, allow form submission or other actions
	}

	// Helper function to check if a string is a number
	function isNum(value) {
	    var regex = /^[0-9]+$/;  // Only digits are allowed
	    return regex.test(value);
	}

	
	function addevent()
	{
		location.href = "addEvent";
	}
	
	/* $("#error-alert").fadeTo(3000, 2000).slideUp(2000, function(){
	    $("#error-alert").slideUp(2000);
	}); */
</script>


 <%@include file="crm-footer.jsp" %>