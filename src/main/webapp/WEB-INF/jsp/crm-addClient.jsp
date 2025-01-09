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
                                            <li class="breadcrumb-item active">Add Client</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Add Client</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-12">
                                 

                                   <form:form id="addForm" action="insertclient" method="post" modelAttribute="client" class="parsley-examples" >
                              		<div id="derror">	
	                                   <c:if test="${client.getNameflag() == '1'}">
		        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
		        						</c:if>	
		        						<c:if test="${client.getMailflag() == '1'}">
		        								<tr><p style="color:red;"><c:out value="${client.emaillogDescription}" /></p></tr>
		        								<tr><p style="color:red;">Do you wish to go ahead and create this client?</p></tr>
		        								<tr><button type="button" onclick="setAgree()" id="err"  class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> Yes</button>&nbsp;
						 							<button type="button" onclick="enableSubmit()" id="err"  class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> No</button>
						 						</tr>
		        								
		        						</c:if>	
		                               	<c:if test="${client.getPhoneflag() == '1'}">
		        								<tr><p style="color:red;"><c:out value="${client.phoneNumber} - Work phone Number already exists!" /></p></tr>
		        						</c:if>	 
		        						<c:if test="${client.getPhoneflag() == '2'}">
		        								<tr><p style="color:red;"><c:out value="${client.mobileNumber} - Mobile Number already exists!" /></p></tr>
		        						</c:if>	 
		        						<c:if test="${client.getPhoneflag() == '3'}">
		        								<tr><p style="color:red;"><c:out value="${client.alternateMobileNumber} - Alternate Mobile Number already exists!" /></p></tr>
		        						</c:if>	        							
		        						<c:if test="${client.getWebsiteflag() == '1'}">
		        								<tr><p style="color:red;"><c:out value="${client.website} - Webiste already exists!" /></p></tr>
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
                                            		<label for="address">Address</label>
                                            		<input type="text" name="address" name="address" parsley-trigger="change"  value="${client.address}"
                                                   	placeholder="Enter Address" maxlength="200"  rows="7" class="form-control address" id="address">
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
                                                 	placeholder="Enter State" class="form-control charactersOnly" id="statesId">                                       								
							 					</div>				        						
												<div class="form-group">	
													<label for="City">City<span class="text-danger">*</span></label>
                                            		<input type="text" name="cityName" parsley-trigger="change" required  value="${client.cityName}"
                                                 	placeholder="Enter City" class="form-control charactersOnly" id="citiesId"> 			
							 					</div>
				        						<div class="form-group">
                                            		<label for="zip">Zip</label>
                                            		<input type="text" name="zip" parsley-trigger="change"   value="${client.zip}"
                                                 	placeholder="Enter Zip" class="form-control numericOnly" id="zip" maxlength="5">
                                        		</div>	
                                        		
										<div class="form-group">
                                            <label >Time Zone </label>
                                            <form:select class="form-control" id="timezone" path="timezone">
                                         <form:option value="" label="--Select --"/>
												<form:option value="CST">CST</form:option>
												<form:option value="EST">EST</form:option>												
												<form:option value="MST">MST</form:option>   
												<form:option value="PST">PST</form:option>   
												<form:option value="HST">HST</form:option>   
												<form:option value="AKST">AKST</form:option>                                                                  
                                             </form:select>
                                        </div>
                                        	<div class="form-group">
                                            		<label for="fax">Fax</label>
                                            		<input type="text" name="fax" parsley-trigger="change"  value="${client.fax}"
                                                   	placeholder="Enter Fax" maxlength="20" class="form-control" id="fax">
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
                                      			<div class="form-group">
                                            		<label for="servicesId">Services</label>                                             
                                           			<form:select  type= "select"  path="servicesId" class="form-control" id="servicesId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.serviceslist}" itemValue="servicesId" itemLabel="servicesDescription"/>
		        									</form:select>                                                                                                       
                                      			</div>                                		
                                        		<div class="form-row">                                           
                                              		<div class="form-group col-md-7">
	                                            		<label for="contactPerson">Contact Person<span class="text-danger">*</span></label>
	                                            		<input type="text" name="contactPerson" parsley-trigger="change"  
	                                            		value="${client.contactPerson}"  placeholder="Enter Contact Person" maxlength="100" 
	                                            		class="form-control contactPerson" id="contactPerson">
                                      				</div>   
	                                               <div class="form-group col-md-5">
	                                                    <label for="extension">Designation</label>
	                                                    <input type="text" name="designation" placeholder="Enter Designation" maxlength="50" class="form-control"
	                                                     id="designation" value="${client.designation}">
	                                                </div>
                                            	</div>
                                            	
                                            	<div class="form-row">                                           
                                              		<div class="form-group col-md-7">
	                                            		<label for="contactPerson">Alternate Contact Person </label>
	                                            		<input type="text" name="contactPersonTwo" parsley-trigger="change"  
	                                            		value="${client.contactPersonTwo}"  placeholder="Enter Alternate Contact Person" maxlength="100" 
	                                            		class="form-control contactPerson" id="contactPerson2">
                                      				</div>   
	                                               <div class="form-group col-md-5">
	                                                    <label for="extension">Designation</label>
	                                                    <input type="text" name="designationTwo" placeholder="Enter Designation" maxlength="50" class="form-control"
	                                                     id="designation2" value="${client.designationTwo}">
	                                                </div>
                                            	</div>
                                        		<div class="form-row">                                           
                                              		<div class="form-group col-md-7">
	                                            		<label for="inputCity">Work Phone Number</label>
	                                            		<input type="text" name="phoneNumber"   
	                                            		value="${client.phoneNumber}"  placeholder="Enter Work Phone Number" maxlength="12" 
	                                            		class="form-control phoneNo" id="phoneNumber" data-toggle="input-mask" data-mask-format="000-000-0000">
                                      				</div>   
	                                               <div class="form-group col-md-5">
	                                                    <label for="extension">Extension</label>
	                                                    <input type="text" name="extension" placeholder="Enter Extension" maxlength="4" class="form-control"
	                                                     id="extension" value="${client.extension}">
	                                                </div>
                                            	</div>
                                            	
                                            	<div class="form-group">
                                            		<label for="mobileNumber">Mobile Number</label>
                                            		<input type="text" name="mobileNumber" data-toggle="input-mask" data-mask-format="000-000-0000" value="${client.mobileNumber}"
                                                   	placeholder="Enter Mobile Number" maxlength="12" class="form-control phoneNo" id="mobileNumber">
                                        		</div>
                                        		
                                        		<div class="form-group">
                                            		<label for="mobileNumber">Alternate Mobile Number</label>
                                            		<input type="text" name="alternateMobileNumber" data-toggle="input-mask" data-mask-format="000-000-0000" value="${client.alternateMobileNumber}"
                                                   	placeholder="Enter Alternate Mobile Number" maxlength="12" class="form-control phoneNo" id="alternateMobileNumber">
                                        		</div>  
                                        		<div id="form-group">
                                       				<label for="email">Email 1</label>
                                           			<input type="email" parsley-trigger="change" name="email"  value="${client.email}"
                                                  	placeholder="Enter Email 1" maxlength="45" class="form-control" id="email" >													
												</div>	
												
												<div id="secondemail" class="row-grid">
												<div id="form-group" >
                                        				<label for="email">Email 2</label>
                                            			<input type="email" parsley-trigger="change" name="email2"  value="${client.email2}"
                                                   	placeholder="Enter Email 2" maxlength="45" class="form-control" id="email2" >													
												</div>												
												</div>
												
												<div  id="thirdemail">	
												<div id="form-group" id="thirdemail" class="row-grid">
                                        				<label for="email">Email 3</label>
                                            			<input type="email" parsley-trigger="change" name="email3"  value="${client.email3}"
                                                   	placeholder="Enter Email 3" maxlength="45" class="form-control" id="email3" >													
												</div>											
												</div>  								
												
                                        		<div class="row-grid">
                                        			<button type="button" id="add" class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> Add Email</button>                                        			
                                        		
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
                                            		<label for="deptId">Assign to User</label>                                             
                                           			<form:select  type= "select"  path="assignToUser" class="form-control" id="assignToUser">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
		        									</form:select>                                                                                                       
                                      			</div> 
                                      			
                                      		 <%--   <div class="form-group">
                                            		<label for="statusId">Status</label>                                             
                                           			<form:select  type= "select"  path="statusId" class="form-control" id="statusId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.statusidlist}" itemValue="statusId" itemLabel="statusDescription"/>
		        									</form:select>                                                                                                       
                                      			</div> 
                                      			
                                      			
                                      			<div class="form-group">
                                            		<label for="bucketId">Bucket Name</label>                                             
                                           			<form:select  type= "select"  path="bucketId" class="form-control" id="bucketId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.bucketList}" itemValue="bucketId" itemLabel="bucketName"/>
		        									</form:select>                                                                                                       
                                      			</div>  --%>
                                      			
                                      			
                                      			<%-- <div class="form-group">
											<label>Bucket Name<span class="text-danger">*</span></label>
											<form:select type="select" path="bucketId"	class="form-control" id="bucketId" required="true"	> 
												
												<form:option value="" label="--Select--" />
												<form:options items="${notes.bucketList}"	itemValue="bucketId" itemLabel="bucketName" />
											</form:select>
												</div> --%>
                                      												
                                      			
                                      			
                                      			 <div class="form-group">                                    
	                                                   <label for="extension">Conference</label>
	                                                   <form:select  type= "select"  path="eventId" class="form-control " id="eventId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.eventList}" itemValue="eventId" itemLabel="eventName"/>
		        									</form:select> 
	                                                </div>
                                            	
                                      			                                 			
                                         		
                                        		 <div class="form-group">
                                            		<label for="sourceId">Source<span class="text-danger">*</span></label>                                             
                                           			<form:select  type= "select"  path="sourceId" class="form-control" id="sourceId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.sourceidlist}" itemValue="sourceId" itemLabel="sourceDescription"/>
		        									</form:select>                                                                                                       
                                      			</div> 
                                        		<div class="form-group">
                                            		<label for="annualRevenue">Annual Revenue</label>
                                            		<input type="text" name="annualRevenue" parsley-trigger="change"  value="${client.annualRevenue}"
                                                   	placeholder="Enter Annual Revenue" maxlength="14" class="form-control numericOnly" id="annualRevenue">
                                        		</div>
                                        		<div class="form-group">
                                            		<label for="description">Description</label>
                                            		<textarea class="form-control" name="description" parsley-trigger="change"  
                                                   	placeholder="Enter Description" class="form-control" maxlength="200" rows="5"  id="description">${client.description}</textarea>
                                        		</div> 
                                        		
                                        		<div class="form-group">
                                            		<label for="website">Website</label>
                                            		<input type="text" name="website" parsley-trigger="change" 	placeholder="Enter Website" maxlength="200" 
                                            		 class="form-control" id="website"  value="${client.website}">
                                        		</div>
                                       		
                                        		
                                         		<!-- <div class="checkbox form-check-inline"> &nbsp; &nbsp;
                                                	<input type="checkbox" name="existingCustomer" id="existingCustomer">
                                                	<label for="inlineCheckbox1"> Is Existing Customer</label>
                                            	</div> -->
                                          	</div>                                       
                                            <!-- end col -->
                                        </div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group text-right m-b-0">
												
	                                            	<button id="sbutton" class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">
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
	function cancel()
	{
		location.href = "manageclient";
	}
</script>
<script>

$(document).ready(function()
{	
	if($("#mailflag").val() == 1)
	{
		$("#sbutton").hide();
	}
	
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

	
    var counter = 2;
    
    var email = $("#email").val();
    var email2 = $("#email2").val();
	var email3 = $("#email3").val();
    
   	if(email2 == "")
   	{
   		$("#secondemail").hide();
   	}
   	if(email3 == "")
   	{
   		$("#thirdemail").hide();
   	}
	
    $("#add").click(function () 
    {
    	email = $("#email").val();
    	email2 = $("#email2").val();
    	email3 = $("#email3").val();
    	
    	if(email == "")
    	{
    		alert("Please Enter Email 1!");
    		$("#email").focus();
			return false;
    	}
    	if(counter == 2)
    	{
    		$("#secondemail").show();
    	}
    	if(counter == 3)
    	{
    		if(email2 == "")
        	{
        		alert("Please Enter Email 2!");
        		$("#email2").focus();
    			return false;
        	}
    		else
    		{
    			$("#thirdemail").show();
    		}
    	}
    	
		if(counter > 3)
		{
	            alert("Only 3 Email Ids allowed!");
	            return false;
		}   	
					
		counter++;
     }); 
    
    var status = $("#statusId").val();
	var followup = document.getElementById("followup");
	
	if(status == 3)
	{
		
		followup.style.display = 'block';
	}
	else
	{
		followup.style.display = 'none';
	}
});

$("#zip").click(function () 
{
	var countryId =$("#countriesId").val();
	
	if(countryId == '')
	{
		alert("Please Select Country First!");
		$("#countriesId").focus();
		return false;
	}
});

$("#statusId").change(function ()
{	
	var status = $("#statusId").val();
	var followup = document.getElementById("followup");
	
	if(status == 3)
	{
		
		followup.style.display = 'block';
	}
	else
	{
		followup.style.display = 'none';
	}
	
	
});






$("#statusId").change(function() 
		{
	
		var bucketId=$("#bucketId").val();
		var statusId=$("#statusId").val();
			if(statusId == "")
			{
				alert("Please Select statusId");
				$("#statusId").focus();
				$("#bucketId").val("");
				return false;
			}

		 
		 $.ajax({
		     url : "brbucket",
		     data :  {statusId:statusId},
		     type : "GET",
		     dataType: "json",
		     success : function(response) { //alert(response);
		    	 var branchbucket=$('#bucketId'), option="";
		    	 
		    	 for(var i=0; i<response.length; i++)
		         {
		             option = option + "<option value='"+response[i].bucketId + "'>"+response[i].bucketName + "</option>";
		         }
		    	 
		    	// alert(option);
		    	//$('#bucketId').append(option);
		    	 branchbucket.html(option);
		         
		     },
		     error : function(xhr, status, error) {
		         alert("error is " + xhr.responseText);
		     }
		 });
		 return false;
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



/* $("#statesId").change(function() 
{
var stateId=$("#statesId").val();
var countryId=$("#countriesId").val();
	if(countryId == "")
	{
		alert("Please Select Country First");
		$("#countriesId").focus();
		$("#statesId").val("");
		return false;
	}
if(stateId == "")
	{
	alert("Please Select State")  
	 $('#citiesId').empty();
	 var branchcity=$('#citiesId'), option="";
	 option =  "<option value=''>--Select  City-- </option>";
	 branchcity.append(option); 
	 return false;
	}
 
 $.ajax({
     url : "brcity",
     data :  {stateId:stateId},
     type : "GET",
     dataType: "json",
     success : function(response) { //alert(response);
    	$('#citiesId').empty();
    	 $('#citiesId').append("<option value=''>--Select City--</option>");
    	 var branchcity=$('#citiesId'), option="";
    	 
    	 for(var i=0; i<response.length; i++)
         {
             option = option + "<option value='"+response[i].id + "'>"+response[i].name + "</option>";
         }
    	
    	 branchcity.append(option);
         
     },
     error : function(xhr, status, error) {
         alert("error is " + xhr.responseText);
     }
 });
 return false;
}); */

/* $("#sourceId").change(function()
{
	var sourceId = $("#sourceId").val();
	
	if(sourceId == '4')
	{
		$("#eventId").prop("disabled", false);
	}
	else
	{
		$("#eventId").prop("disabled", true);
	}
}); */
</script>
<script src="resources/assets/js/common.js" /></script>
<script src="resources/assets/js/clientValidation.js" /></script>
<%@include file="crm-footer.jsp" %>