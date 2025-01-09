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
                                            <li class="breadcrumb-item active">Convert Lead</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Convert Lead</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-12">
                                 

                                   <form:form id="addForm" action="insertLeadClient" method="post" modelAttribute="client" class="parsley-examples" >
                              		<div id="derror">	
                              		 <form:hidden path="leadId" value="${client.leadId}" /> 
                              		  <form:hidden path="flag" id="flag" value="${client.flag}" /> 
                              		  
	                                    <c:if test="${client.getNameflag() == '1'}">
		        								<div><p style="color:red;"><c:out value="${client.logDescription}" /></p></div>		        							
		        						</c:if>	
		                               	<c:if test="${client.getPhoneflag() == '1'}">
		        								<div><p  style="color:red;"><c:out value="${client.phoneNumber} - Work phone Number already exists!" /></p></div>
		        						</c:if>	 
		        						<c:if test="${client.getPhoneflag() == '2'}">
		        								<div><p style="color:red;"><c:out value="${client.mobileNumber} - Mobile Number already exists!" /></p></div>
		        						</c:if>	
		        						<c:if test="${client.getMailflag() == '1'}">
		        								<div><p  style="color:red;"><c:out value="${client.emaillogDescription}" /></p></div>	
		        									<div><p style="color:red;">Do you wish to go ahead and create this client?</p></div>
		        								<div  class="my-md-2"><button type="button" onclick="setAgree()" id="err"  class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> Yes</button>&nbsp;
						 							<button type="button" onclick="enableSubmit()" id="err"  class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> No</button>
						 						</div>	        								
		        						</c:if>	 
		        					 </div>   							
        							<input type="hidden" id="mailflag" name="mailflag" value="${client.getMailflag()}" />
                               			<div class="row">
                                            <div class="col-md-4">                                               
                                            	<div class="form-group">
                                            		<label for="clientName">Client Name<span class="text-danger">*</span></label>
                                            		<input type="text" name="clientName" parsley-trigger="change" value="${client.clientName}"
                                                   	placeholder="Enter Client Name" maxlength="500" class="form-control" id="clientName">
                                             	</div>                                     
                                       			<div class="form-group">					
							 						<label for="countriesId">Country<span class="text-danger">*</span></label> 
													<form:select  type= "select" path="countriesId" class="form-control" id="countriesId">
						               					<form:option value="" label="--Select--"/> 
						              			 		<form:options items="${client.countrylist}" itemValue="id" itemLabel="name" />
				        							</form:select>
				        						</div>				        						
												<div class="form-group">													
                                            		<label for="State">State<span class="text-danger">*</span></label>
                                            		<input type="text" name="stateName" parsley-trigger="change" required  value="${client.stateName}"
                                                 	maxlength="50" placeholder="Enter State" class="form-control charactersOnly" id="statesId">                                       								
							 					</div>				        						
												<div class="form-group">	
													<label for="City">City<span class="text-danger">*</span></label>
                                            		<input type="text" name="cityName" parsley-trigger="change" required  value="${client.cityName}"
                                                 	maxlength="50" placeholder="Enter City" class="form-control charactersOnly" id="citiesId"> 			
							 					</div>
				        						<div class="form-group">
                                            		<label for="zip">Zip<span class="text-danger">*</span></label>
                                            		<input type="text" name="zip" parsley-trigger="change" required  value="${client.zip}"
                                                 	placeholder="Enter Zip" class="form-control numericOnly" id="zip" maxlength="5">
                                        		</div>	
                                        	</div>
                                       		<div class="col-md-4">
                                       			   
                                       			 <div class="form-group">
                                            		<label for="deptId">Department<span class="text-danger">*</span></label>                                             
                                           			<form:select  type= "select"  path="deptId" class="form-control" id="deptId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.departmentidlist}" itemValue="departmentId" itemLabel="departmentName"/>
		        									</form:select>                                                                                                       
                                      			</div>                                        		                                                         
                                              		<div class="form-group ">
	                                            		<label for="contactPerson">Contact Person<span class="text-danger">*</span></label>
	                                            		<input type="text" name="contactPerson" parsley-trigger="change"  
	                                            		value="${client.contactPerson}"  placeholder="Enter Contact Person" maxlength="100" 
	                                            		class="form-control contactPerson" id="contactPerson">
                                      				</div>  
                                            	
                                            	<div class="form-group">
                                            		<label for="mobileNumber">Mobile Number</label>
                                            		<input type="text" name="mobileNumber" data-toggle="input-mask" data-mask-format="000-000-0000" value="${client.mobileNumber}"
                                                   	placeholder="Enter Mobile Number" maxlength="12" class="form-control phoneNo" id="mobileNumber">
                                        		</div>
                                        		<div id="form-group">
                                       				<label for="email">Email </label>
                                           			<input type="email" parsley-trigger="change" name="email"  value="${client.email}"
                                                  	placeholder="Enter Email" maxlength="45" class="form-control" id="email" >													
												</div>	
																		
												
                                        	                     	
                                     		</div>
                                     		<div class="col-md-4">
                                     			
                                      			
                                     			<div class="form-group">
                                            		<label for="deptId">Data Collected User<span class="text-danger">*</span></label>                                             
                                           			<form:select  type= "select"  path="dataCollectedUser" class="form-control" id="dataCollectedUser">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
		        									</form:select>                                                                                                       
                                      			</div>
                                      			
                                      		
                                      			                                 			
                                         		
                                        		 <div class="form-group">
                                            		<label for="sourceId">Source<span class="text-danger">*</span></label>                                             
                                           			<form:select  type= "select"  path="sourceId" class="form-control" id="sourceId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.sourceidlist}" itemValue="sourceId" itemLabel="sourceDescription"/>
		        									</form:select>                                                                                                       
                                      			</div> 
                                        		
                                          	</div>     
                                        </div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group text-right m-b-0">												
	                                            	<button id="sbutton" class="btn btn-primary waves-effect waves-light" type="submit" onclick="return validateClient()">
	                                                	Submit
	                                            	</button>	                                           
		                                            <button type="reset" class="btn btn-danger waves-effect m-l-5" onclick="return cancel()">
		                                                Back
		                                            </button>
	                                        	</div> 
											</div>
										</div>
                                       
                                    </form:form>
                                        </div>
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
            
<!-- Modal-Effect -->
<script>
function cancel(){
	location.href = "manageLead";
}

$(document).ready(function(){	
	if($("#mailflag").val() == 1){
		$("#sbutton").hide();
	}
	var countryId =$("#countriesId").val();
	
	if(countryId =='231'){
		$('input[name="phoneNumber"]').mask('000-000-0000');
		$('input[name="mobileNumber"]').mask('000-000-0000');
		$('input[name="alternateMobileNumber"]').mask('000-000-0000');
		$("#zip").attr('maxlength','5');
	}
	else{
		$('input[name="phoneNumber"]').mask('0000000000');
		$('input[name="mobileNumber"]').mask('0000000000');
		$('input[name="alternateMobileNumber"]').mask('0000000000');
		$("#zip").attr('maxlength','6');
	}

	
    var counter = 2;
    
    var email = $("#email").val();
    
    var status = $("#statusId").val();
	var followup = document.getElementById("followup");
	
	if(status == 3){		
		followup.style.display = 'block';
	}
	else{
		followup.style.display = 'none';
	}
});

$("#zip").click(function (){
	var countryId =$("#countriesId").val();
	
	if(countryId == '')
	{
		alert("Please Select Country First!");
		$("#countriesId").focus();
		return false;
	}
});






$("#countriesId").change(function() 
{
	var countryId =$("#countriesId").val();
	
	if(countryId =='231')
	{
		$('input[name="phoneNumber"]').mask('000-000-0000');
		$('input[name="mobileNumber"]').mask('000-000-0000');
		$('input[name="alternateMobileNumber"]').mask('000-000-0000');
		$("#zip").attr('maxlength','5');
	}
	else
	{
		$('input[name="phoneNumber"]').mask('0000000000');
		$('input[name="mobileNumber"]').mask('0000000000');
		$('input[name="alternateMobileNumber"]').mask('0000000000');
		$("#zip").attr('maxlength','6');
	}

if(countryId == "")
{
alert("Please Select Country")  
 $('#statesId').empty();
 var branchstate=$('#statesId'), option="";
 option =  "<option value=''>--Select State--</option>";
 branchstate.append(option); 
 return false;
  }
 $.ajax({
     url : "brstate",
     data :  {countryId:countryId},
     type : "GET",
     success : function(response)
     {
    	 
    	 $('#statesId').empty();
    	 $('#statesId').append("<option value=''>--Select State--</option>");
    	 var branchstate=$('#statesId'), option="";
         //alert("success is " +customer.getStatelist());
    	
         
         for(var i=0; i<response.length; i++)
         { //alert(response[i].name)
        	 var phonecode = response[i].phoneCode;
             option = option + "<option value='"+response[i].id + "'>"+response[i].name + "</option>";
         }
         branchstate.append(option);
         //$("#phoneNumber").val(phonecode);
     },
     error : function(xhr, status, error) {
         alert("error is " + xhr.responseText);
     }
 });
 return false;
});


</script>
<script src="resources/assets/js/common.js" /></script>
<script src="resources/assets/js/convertLeadValidation.js" /></script>
<%@include file="crm-footer.jsp" %>