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
                                            <li class="breadcrumb-item active">Edit Client</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Edit Client</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->
                        
                        <div class="row">
            
                            <div class="col-lg-12">

                                <div class="card-box">
                               		
                                	<div class="col-lg-12">
                                 

                                <form:form id="editForm" action="updateclient" method="post" modelAttribute="client" class="parsley-examples" > 
                                <form:hidden path="traceNo" value="${client.traceNo}" /> 
                                <form:hidden path="userId" id="userid" value="${client.userId}"/> 
                                <form:hidden path="hiddenuserid" value="${client.hiddenuserid}"/>
                                <input type="hidden" id="mailflag" name="mailflag" value="${client.getMailflag()}" />
                                <%-- <form:hidden path="" id="hiddeneventid" value="${client.eventId}"/>  --%> 
                                <div id="derror"> 
                                	<c:if test="${client.getNameflag() == '1'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        						</c:if>	
	                                <c:if test="${client.getMailflag() == '1'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        								<tr><p style="color:red;">Do you wish to go ahead and update this email id?</p></tr>
	        								<tr><button type="button" onclick="setAgree()" id="err" class="btn btn-primary btn-sm"> 
					 							<i class="fa fa-dot-circle-o"></i> Yes</button>&nbsp;
						 							<button type="button" id="err" onclick="enableSubmit()" class="btn btn-primary btn-sm"> 
						 							<i class="fa fa-dot-circle-o"></i> No</button>
					 						</tr>
	        						</c:if>	
	        					</div>
	        					
	        							<%--<c:if test="${client.getPhoneflag() == '1'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        						</c:if>	 
	        						<c:if test="${client.getPhoneflag() == '2'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        						</c:if>	 
	        						<c:if test="${client.getPhoneflag() == '3'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        						</c:if>	        							
	        						<c:if test="${client.getWebsiteflag() == '1'}">
	        								<tr><p style="color:red;"><c:out value="${client.logDescription}" /></p></tr>
	        						</c:if>	    --%>
       								<div class="row row-grid">
       									 
       									<%--  	<div class="col-md-6">
		                                            <label for="traceNo"><b>Client Name</b> &nbsp; &nbsp; <b>:</b></label>
		                                             <label for="clientName"> &nbsp; &nbsp;<b>${client.clientName}</b></label>
                                       		 </div>   --%>
                                       		 <div class="col-md-10">
                                       		 	
		                                            <label for="traceNo"><b>Trace Number</b> &nbsp; &nbsp; <b>: </b></label>
		                                             <label for="traceNo"> &nbsp; &nbsp;<b>${client.traceNo}</b></label>
                                       		 	
                                       		 </div>
                                       		 <div class="col-md-2">
	       									     <c:choose>
		                                   			 <c:when test="${client.getStatusId() != '14' && client.hiddenuserid != client.userId}">
														<div class="form-group"><label>&nbsp;</label></div>
		                                       		 </c:when> 
		                                       		 <c:otherwise>
		                                       		 
		                                       		 <div class="form-group">
		                                       		 	<a href="addnotes?id=${client.traceNo}&&clientName=${client.clientName}"><button type="button"  class="btn btn-success waves-effect waves-light">
																	<i class="fe-file-plus"></i><span> &nbsp; Add Notes</span></button></a>	
													</div>
		                                       		 </c:otherwise>
		                 							
		                                       	</c:choose>
		                                      </div>
	                                    
       								</div>                           
                                     <div class="row">
                                        <div class="col-md-4">
                                    		<div class="form-group">
                                            	<label for="clientName">Client Name<span class="text-danger">*</span> </label>
                                            	<input type="text" name="clientName" parsley-trigger="change" value="${client.clientName}"
                                                   placeholder="Enter Client Name" maxlength="500" class="form-control" id="clientName">
                                        	</div>
                                        	<div class="form-group">
                                            	<label for="address">Address</label>
                                            	<input type="text" name="address" parsley-trigger="change" value="${client.address}"
                                                   placeholder="Enter Address" maxlength="200"  rows="7" class="form-control address" id="address">
                                        	</div>
                                        
                                        	<div class="form-group">					
												<label for="countriesId" class=" form-control-label">Country<span class="text-danger">*</span> </label>
												<form:select path="countriesId"  id="countriesId" class="form-control" >
													<form:option value="" label="--Select Country--"/>
													<form:options items="${client.countrylist}" itemValue="id" itemLabel="name" />	
												</form:select>
											</div>
                                        	
                                        	<div class="form-group">					
												<label for="statesId" class=" form-control-label">State <span class="text-danger">*</span></label>
												<input type="text" name="stateName" parsley-trigger="change" required  value="${client.stateName}"
                                                 	placeholder="Enter State" class="form-control" id="statesId">  
											</div>
                                      
                                       		<div class="form-group">						
												<label for="citiesId" class=" form-control-label">City <span class="text-danger">*</span></label>
												<input type="text" name="cityName" parsley-trigger="change" required  value="${client.cityName}"
                                                 	placeholder="Enter City" class="form-control" id="citiesId"> 	
											</div>
											<div class="form-group">
	                                            <label for="zip">Zip</label>
	                                            <input type="text" name="zip" parsley-trigger="change"  value="${client.zip}"
                                                   placeholder="Enter Zip" class="form-control numericOnly" id="zip">
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
	                                            <label for="email">Email</label>
	                                            <input type=email name="email" placeholder="Enter Email 1"
	                                                  value="${client.email}" maxlength="45"  class="form-control" id="email">
	                                        </div>
	                                        
	                                        <div id="secondemail">
	                                        	<div class="form-group">
	                                            <label for="email2">Email 2</label>
	                                            <input type=email name="email2" placeholder="Enter Email 2"
	                                                  value="${client.email2}" maxlength="45"  class="form-control" id="email2">
	                                       		 </div> 
	                                       	</div> 
	                                       	
	                                       	  <div  id="thirdemail">	 
		                                        <div class="form-group">
		                                            <label for="email3">Email 3</label>
		                                            <input type=email name="email3" placeholder="Enter Email 3"
		                                                  value="${client.email3}" maxlength="45"  class="form-control" id="email3">
		                                       	</div> 
		                                    </div> 
	                                        
	                                        <div>
                                       			<button type="button" id="add" class="btn btn-primary btn-sm"> 
					 							<i class="fa fa-dot-circle-o"></i> Add Email</button>                                      			
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
	                                                    <input type="text" name="designation" placeholder="Enter Designation" class="form-control"
	                                                     maxlength="50" id="designation" value="${client.designation}">
	                                                </div>
                                            	</div>
                                            	
                                            <div class="form-row">                                           
                                              		<div class="form-group col-md-7">
	                                            		<label for="contactPerson">Alternate Contact Person</label>
	                                            		<input type="text" name="contactPersonTwo" parsley-trigger="change"  
	                                            		value="${client.contactPersonTwo}"  placeholder="Enter Alternate Contact Person" maxlength="100" 
	                                            		class="form-control contactPersonTwo" id="contactPerson">
                                      				</div>   
	                                               <div class="form-group col-md-5">
	                                                    <label for="extension">Designation</label>
	                                                    <input type="text" name="designationTwo" placeholder="Enter Designation" class="form-control"
	                                                     maxlength="50" id="designationTwo" value="${client.designationTwo}">
	                                                </div>
                                            	</div>
                                        
	                                        <div class="form-row">
                                                <div class="form-group col-md-7">
                                                    <label for="inputCity">Work Phone Number</label>
                                                    <input type="text" name="phoneNumber" parsley-trigger="change"  class="form-control phoneNo" id="phoneNumber"
	                                            		value="${client.phoneNumber}"  placeholder="Enter Work Phone Number" maxlength="10" >
                                                </div>
                                                
                                                <div class="form-group col-md-5">
                                                    <label for="extension">Extension</label>
                                                    <input type="text" name="extension" placeholder="Enter Extension" class="form-control" id="extension"
                                                    	maxlength="4" value="${client.extension}">
                                                </div>
                                            	</div> 
                                            
                                           		 <div class="form-group">
                                            		<label for="mobileNumber">Mobile Number</label>
                                            		<input type="text" name="mobileNumber" parsley-trigger="change" value="${client.mobileNumber}" 
                                                   	placeholder="Enter Mobile Number" maxlength="20" class="form-control phoneNo" id="mobileNumber">
                                        		</div>
                                        		
                                        		<div class="form-group">
                                            		<label for="mobileNumber">Alternate Mobile Number</label>
                                            		<input type="text" name="alternateMobileNumber" parsley-trigger="change" value="${client.alternateMobileNumber}" 
                                                   	placeholder="Enter Alternate Mobile Number" maxlength="20" class="form-control phoneNo" id="alternateMobileNumber">
                                        		</div>                                    
	                                        
	                                        <%-- <div class="form-group">
                                            		<label for="statusId">Status</label>                                             
                                           			<form:select  type= "select"  path="statusId" class="form-control" id="statusId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.statusidlist}" itemValue="statusId" itemLabel="statusDescription"/>
		        									</form:select>                                                                                                       
                                      			</div>  --%>
                                      			
                                      		<%--  <div class="form-group">
                                            		<label for="userId">User Name</label>                                             
                                           			<form:select  type= "select"  path="userId" class="form-control" id="userId">
			            								<form:option value="" label="--Select--"/>
			            								<form:options items="${client.userlist}" itemValue="userId" itemLabel="firstName"/>
		        									</form:select>                                                                                                       
                                      			</div> --%>
                                      			
                                      		
	                                        
  
                                   		</div>
                                   
                                   		<div class="col-md-4" >
                                   		
                                   			<div class="form-group">
	                                            <label for="fax">Fax</label>
	                                            <input type=text name="fax" placeholder="Enter Fax" parsley-trigger="change"
	                                                   value="${client.fax}" maxlength="20" class="form-control phoneNo" id="fax">
	                                        </div>                                      		 
                                       		 <div class="form-group">                                       
                                              		  <label for="extension">Conference</label>
	                                                   <form:select  type= "select"  path="eventId" class="form-control" id="eventId">
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
	                                            <label for="website">Website</label>
	                                            <input type="text" name="website" parsley-trigger="change"  value="${client.website}"
	                                                   placeholder="Enter Website" maxlength="200" class="form-control" id="website">
	                                        </div>
	                                      
	                                        
	                                        
	                                        <div class="form-group">
	                                            <label for="annualRevenue">Annual Revenue</label>
	                                            <input type=text name="annualRevenue" placeholder="Enter Annual Revenue"
	                                                  value="${client.annualRevenue}" maxlength="14" class="form-control numericOnly" id="annualRevenue">
	                                        </div>
	                                        
	                                        <div class="form-group">
	                                            <label for="description">Description</label>
	                                            <textarea class="form-control" name="description" placeholder="Enter Description" rows="5"
	                                                   maxlength="200"  class="form-control" id="description">${client.description}</textarea>
	                                        </div>  
	                                        
	                                       
	                                       
	                                                                           
	                                      	<%-- <br>
	                                      		<c:choose>
	                                      			<c:when test="${client.existingCustomer == 'Y'}">
	                                      				&nbsp; &nbsp;
	                                      				<form:checkbox path="existingCustomer" id="existingCustomer" value="Y" checked="checked" /> Is Existing Customer
                                                	</c:when>
                                                	<c:when test="${client.existingCustomer == 'N'}">                                                	
                                                		&nbsp; &nbsp;
                                                		<form:checkbox path="existingCustomer" id="existingCustomer" value="Y"/>Is Existing Customer
                                                	</c:when>
                                                	<c:otherwise >                                                		
                                                		&nbsp; &nbsp;
                                                		<form:checkbox path="existingCustomer" id="existingCustomer" value="N"/>Is Existing Customer
                                                	</c:otherwise>
												</c:choose> 
                                           					 --%>		
										</div>	                                         
                                   	</div>                                                                      
                                    
                                  <div class="form-group text-right m-b-0">
                                  <c:if test="${client.getStatusId() != '14'}">
                                      <button id="sbutton" class="btn btn-primary waves-effect waves-light" type="submit" onclick="return Validate()">Submit </button>
                                  </c:if>
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
	
	var countryId=$("#countriesId").val();
	
	/*var userid = $("#userid").val();		
	var hiddenuserid = $("#hiddenuserid").val();
	
	 var addnotesid = document.getElementById("addnotes");
	 var lblid = document.getElementById("lblid");
	
	if(userid != hiddenuserid)
	{
		//$("#addnotes").hide();
		addnotesid.style.display = "none";
		lblid.style.display = "block";
	}
	else
	{
		addnotesid.style.display = "block";
		lblid.style.display = "none";
		//$("#addnotes").show();
	}*/
	
	if(countryId =='231')
	{
		$('input[name="phoneNumber"]').mask('000-000-0000');
		$('input[name="mobileNumber"]').mask('000-000-0000');
		$('input[name="alternateMobileNumber"]').mask('000-000-0000');
		$('input[name="fax"]').mask('000-000-0000');
		$("#zip").attr('maxlength','5');
	}
	else
	{
		$('input[name="phoneNumber"]').mask('0000000000');
		$('input[name="mobileNumber"]').mask('0000000000');
		$('input[name="alternateMobileNumber"]').mask('0000000000');
		$('input[name="fax"]').mask('0000000000');
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
    	if(email2 !="")
    	{
	    	if(counter == 2)
	    	{
	    		$("#secondemail").show();
	    		counter++;
	    	}
    	}
    	else
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
});



</script>

<script>
$("#countriesId").change(function() 
{
	var countryId=$("#countriesId").val();
	
	if(countryId =='231')
	{
		$('input[name="phoneNumber"]').mask('000-000-0000');
		$('input[name="mobileNumber"]').mask('000-000-0000');
		$('input[name="alternateMobileNumber"]').mask('000-000-0000');
		$('input[name="fax"]').mask('000-000-0000');
		$("#zip").attr('maxlength','5');
	}
	else
	{
		$('input[name="phoneNumber"]').mask('0000000000');
		$('input[name="mobileNumber"]').mask('0000000000');
		$('input[name="alternateMobileNumber"]').mask('0000000000');
		$('input[name="fax"]').mask('0000000000');
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
     success : function(response) {
    	 $('#statesId').empty();
    	 $('#statesId').append("<option value=''>--Select State--</option>");
    	 var branchstate=$('#statesId'), option="";
         //alert("success is " +customer.getStatelist());
    	
         
         for(var i=0; i<response.length; i++)
         { //alert(response[i].name)
             option = option + "<option value='"+response[i].id + "'>"+response[i].name + "</option>";
         }
         branchstate.append(option);
     },
     error : function(xhr, status, error) {
         alert("error is " + xhr.responseText);
     }
 });
 return false;
});


$("#statesId").change(function() 
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
});



/* $("#sourceId").change(function()
		{
			var sourceId = $("#sourceId").val();
			var eventId = $("#hiddeneventid").val();
			
			if(sourceId == '4')
			{
				$("#eventId").prop("disabled", false);
				if(eventId != "")
				{
					$("#eventId").val(eventId);
				}
				else
				{
					$("#eventId").val("");
				}
				
			}
			else
			{
				$("#eventId").prop("disabled", true);
				$("#eventId").val("");
			}
		}); */
</script>
<script src="resources/assets/js/common.js" /></script>
<script src="resources/assets/js/editClientValidation.js" /></script>
 <%@include file="crm-footer.jsp" %>