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
                                            <li class="breadcrumb-item active">Target</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Target</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                   <!--  <h4 class="header-title">Add/Edit Target</h4> -->
                                    <form:form class="form-horizontal parsley-examples" action="managetargetsubmit" method="post" modelAttribute="target">                                    
                                    <div class="col-md-12">
			                            <div class="col-lg-12">
												<div class="form-inline">	
													<div class="col-md-2"></div>
													<div class="input-group m-b-2">	
														<label for="userId">User Name<span class="text-danger">*&nbsp;</span></label>
														<form:select  type= "select"  path="userId" class="form-control" id="userId">
		            								<form:option value="" label="--Select--"/>
		            								<form:options items="${target.userlist}" itemValue="userId" itemLabel="firstName"/>
	        									</form:select> 
													</div>
													<div class="input-group-append">
			                                           <button type="submit" class="btn btn-success waves-effect waves-light" onclick="return validate()">
			                                           		<i class="fe-search"></i>
			                                           	</button>
		                                           	</div>
													<div class="form-group mx-sm-2">

				                                    <c:if test="${target.frompage != 'Initial' && target.userStatus != 'I'}">
				                                    <center>
				                                    <button type="button" name="add" value="addbtn"  class="btn btn-blue waves-effect waves-light"  style="margin-left:100px"
				                                    onclick="return validate()">
				                                    <i class="mdi mdi-plus-circle mr-1"></i>Add Target</button>
				                                    </center>
				                                    </c:if>
                                    			</div>
												</div>
											</div>
                                    	</div>
                                   <div class="row row-grid"> 
                                  	 <div class="col-md-6">	                                  	                                  	 
                                   	 <c:choose>                             
										<c:when test="${target.openTargetlist.size() > '0' }">
										 <div class="card-box">
										<h5>Data Collection</h5>								
											<table class="table table-bordered mb-0">
		                                        <thead class="thead-light">
		                                        <tr>
		                                        	<!-- <th data-field="userId" data-sortable="true">User Name</th>   -->
		                                        	<th data-field="openmonth" style="text-align:center">Month</th>
		                                        	<th data-field="openyear" style="text-align:center">Year</th>                                        
		                                           	<th data-field="opendailyTarget" style="text-align:center">Daily Target</th>
		                                            <th data-field="openstatus" style="text-align:center">Edit</th> 
		                                        </tr>
		                                        </thead>
		                                        <tbody>
		                                       		<c:forEach var="crmtarget" items="${target.openTargetlist}" varStatus="loop">
		                                       		<form:hidden path="targetsId" id="targetsId${loop.index+1}" value="${crmtarget.targetsId}"/>
		                                       		<form:hidden path="name" id="name${loop.index+1}" value="${crmtarget.name}"/>
													<tr>											
														<%-- <td>${crmtarget.name}</td> --%>
														<td style="text-align:center">${crmtarget.monthName}</td>
														<td style="text-align:center">${crmtarget.year}</td>											
														<td style="text-align:center">${crmtarget.dailyTarget}</td>																							
														<c:choose>
														<c:when test ="${crmtarget.flag == '1' }"> 
														<td><button type="button" class="btn btn-success waves-effect waves-light" onclick="return openEdit(${loop.index+1})">
														<i class="mdi mdi-pencil" ></i></button></td> 
														 </c:when>
														<c:otherwise>
															<td><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default;">
														<i class="fe-delete"></i></button></td>
														</c:otherwise>
														</c:choose> 
													</tr>
												</c:forEach> 
		                                      </tbody>
		                                    </table>
		                                    </div>		                                    
		                                    </c:when>
		                                    <c:otherwise>		                                    	
		                                    	<c:if test="${target.frompage != 'Initial'}">
		                                    	<div class="card-box">	
		                                    	<h5>Data Collection</h5>
		                                    		<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
			                                			<span class="badge badge-pill badge-danger">No Data</span>
			                                   				 No  data exist!
			                                  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			                                    			<span aria-hidden="true">&times;</span>
			                                			</button>
			                         				</div>
			                         			</div>
			                         			</c:if>
		                                    </c:otherwise>
		                                  </c:choose>
		                               </div>		                              		                                
		                              <div class="col-md-6">		                               	                                 
		                                	 <c:choose>                             
												<c:when test="${target.emailSentTargetlist.size() > '0' }">
												<div class="card-box">	
												  <h5>Email</h5>
				                                    <table class="table table-bordered mb-0">
				                                        <thead class="thead-light">
				                                        <tr>
				                                        	<!-- <th data-field="userId" data-sortable="true">User Name</th>   -->
				                                        	<th data-field="followupmonth" style="text-align:center">Month</th>
				                                        	<th data-field="followupyear" style="text-align:center">Year</th>                                        
				                                           	<th data-field="followupdailyTarget" style="text-align:center">Daily Target</th>
				                                            <th data-field="followupstatus" style="text-align:center">Edit</th> 
				                                        </tr>
				                                        </thead>
				                                        <tbody>
				                                       		<c:forEach var="crmtarget" items="${target.emailSentTargetlist}" varStatus="loop">
				                                       		<form:hidden path="emailTargetId" id="emailTargetId${loop.index+1}" value="${crmtarget.targetsId}"/>
		                                       				<form:hidden path="name" id="emailName${loop.index+1}" value="${crmtarget.name}"/>
																<tr>										
																	<td style="text-align:center">${crmtarget.monthName}</td>
																	<td style="text-align:center">${crmtarget.year}</td>												
																	<td style="text-align:center">${crmtarget.dailyTarget}</td>
																											
																	<c:choose>
																	<c:when test ="${crmtarget.flag == '1' }"> 
																	<td><button type="button" onclick="return emailEdit(${loop.index+1})" 
																	class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></td> 
																	 </c:when>
																	<c:otherwise>
																		<td><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default;">
																	<i class="fe-delete"></i></button></td>
																	</c:otherwise>
																	</c:choose> 
																</tr>
															</c:forEach> 
				                                        </tbody>
				                                    </table>
				                                  </div>
		                                    </c:when>
		                                    <c:otherwise>		                                    
		                                    	<c:if test="${target.frompage != 'Initial'}">
		                                    	<div class="card-box">	
		                                    	<h5>Email</h5>
		                                    		<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
			                                			<span class="badge badge-pill badge-danger">No Data</span>
			                                   				 No  data exist!
			                                  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			                                    			<span aria-hidden="true">&times;</span>
			                                			</button>
			                         				</div>
			                         			</div>
			                         			</c:if>
		                                    </c:otherwise>
		                                  </c:choose>
		                                 </div>
		                               </div> 
		                            		                               
		                            <div class="row row-grid">
										<div class="col-md-6">																					
												<c:choose>                             
												<c:when test="${target.followUpTargetlist.size() > '0' }">
												<div class="card-box">		
												 <h5>Follow-up(calls)</h5>
				                                    <table class="table table-bordered mb-0">
				                                        <thead class="thead-light">
				                                        <tr>
				                                        	<!-- <th data-field="userId" data-sortable="true">User Name</th>   -->
				                                        	<th data-field="month" style="text-align:center">Month</th>
				                                        	<th data-field="year" style="text-align:center">Year</th>                                        
				                                           <th data-field="dailyTarget" style="text-align:center">Daily Target</th>
				                                            <th data-field="status" style="text-align:center">Edit</th> 
				                                        </tr>
				                                        </thead>
				                                        <tbody>
				                                       		<c:forEach var="crmtarget" items="${target.followUpTargetlist}" varStatus="loop">
				                                       		<form:hidden path="followupTargetId" id="followupTargetId${loop.index+1}" value="${crmtarget.targetsId}"/>
		                                       				<form:hidden path="name" id="followupName${loop.index+1}" value="${crmtarget.name}"/>
															<tr>										
																<td style="text-align:center">${crmtarget.monthName}</td>
																<td style="text-align:center">${crmtarget.year}</td>													
																<td style="text-align:center">${crmtarget.dailyTarget}</td>
																										
																<c:choose>
																<c:when test ="${crmtarget.flag == '1' }"> 
																<td><button type="button" onclick="return followupEdit(${loop.index+1})"
																class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></td> 
																 </c:when>
																<c:otherwise>
																	<td><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default;">
																<i class="fe-delete"></i></button></td>
																</c:otherwise>
																</c:choose> 
															</tr>
														</c:forEach> 
				                                       </tbody>
				                                    </table>
				                                    </div>
			                                     </c:when>
		                                    <c:otherwise>		                                    
		                                    	<c:if test="${target.frompage != 'Initial'}">
		                                    	<div class="card-box">	
		                                    	 <h5>Follow-up(calls)</h5>
		                                    		<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
			                                			<span class="badge badge-pill badge-danger">No Data</span>
			                                   				 No  data exist!
			                                  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			                                    			<span aria-hidden="true">&times;</span>
			                                			</button>
			                         				</div>
			                         				</div>
			                         			</c:if>
		                                    </c:otherwise>
		                                  </c:choose>
		                                  </div>
			                          
		                                <div class="col-md-6">		                                 
		                                 <c:choose>                             
											<c:when test="${target.responseTargetlist.size() > '0' }">
											 <div class="card-box">	
												<h5>New Leads(Response)</h5>											
			                                    <table class="table table-bordered mb-0">			                                   
			                                        <thead class="thead-light">
			                                        <tr>			                                        	
			                                        	<th data-field="month" style="text-align:center">Month</th>
			                                        	<th data-field="year" style="text-align:center">Year</th>                                        
			                                           	<th data-field="dailyTarget" style="text-align:center">Daily Target</th>
			                                            <th data-field="status" style="text-align:center">Edit</th> 
			                                        </tr>
			                                        </thead>
			                                        <tbody>
			                                       		<c:forEach var="crmtarget" items="${target.responseTargetlist}" varStatus="loop">
			                                       		<form:hidden path="responseTargetId" id="responseTargetId${loop.index+1}" value="${crmtarget.targetsId}"/>
		                                       			<form:hidden path="name" id="responseName${loop.index+1}" value="${crmtarget.name}"/>
														<tr>										
															<td style="text-align:center">${crmtarget.monthName}</td>
															<td style="text-align:center">${crmtarget.year}</td>												
															<td style="text-align:center">${crmtarget.dailyTarget}</td>																								
															<c:choose>
															<c:when test ="${crmtarget.flag == '1' }"> 
															<td><button type="button" onclick="return responseEdit(${loop.index+1})"
															class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></td> 
															 </c:when>
															<c:otherwise>
																<td><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default;">
															<i class="fe-delete"></i></button></td>
															</c:otherwise>
															</c:choose> 
														</tr>
														</c:forEach> 
			                                        </tbody>
			                                    </table>
			                                    </div>
			                                   </c:when>
			                                    <c:otherwise>			                                    
			                                    	<c:if test="${target.frompage != 'Initial'}">
			                                    	<div class="card-box">	
			                                    	 <h5>New Leads(Response)</h5>	
			                                    		<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
				                                			<span class="badge badge-pill badge-danger">No Data</span>
				                                   				 No  data exist!
				                                  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				                                    			<span aria-hidden="true">&times;</span>
				                                			</button>
				                         				</div>
				                         				</div>
				                         			</c:if>
			                                    </c:otherwise>
		                                  		</c:choose>
			                                 </div>
			                            </div>
			                            <div class="row row-grid">
			                            	<div class="col-md-6">		                                 
		                                 <c:choose>                             
											<c:when test="${target.closeTargetlist.size() > '0' }">
											 <div class="card-box">	
												<h5>Closed</h5>											
			                                    <table class="table table-bordered mb-0">			                                   
			                                        <thead class="thead-light">
			                                        <tr>			                                        	
			                                        	<th data-field="month" style="text-align:center">Month</th>
			                                        	<th data-field="year" style="text-align:center">Year</th>                                        
			                                           	<th data-field="dailyTarget" style="text-align:center">Daily Target</th>
			                                            <th data-field="status" style="text-align:center">Edit</th> 
			                                        </tr>
			                                        </thead>
			                                        <tbody>
			                                       		<c:forEach var="crmtarget" items="${target.closeTargetlist}" varStatus="loop">
			                                       		<form:hidden path="closeTargetId" id="closeTargetId${loop.index+1}" value="${crmtarget.targetsId}"/>
		                                       			<form:hidden path="name" id="closeName${loop.index+1}" value="${crmtarget.name}"/>
														<tr>										
															<td style="text-align:center">${crmtarget.monthName}</td>
															<td style="text-align:center">${crmtarget.year}</td>												
															<td style="text-align:center">${crmtarget.dailyTarget}</td>																								
															<c:choose>
															<c:when test ="${crmtarget.flag == '1' }"> 
															<td><button type="button" onclick="return closeEdit(${loop.index+1})"
															class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></td> 
															 </c:when>
															<c:otherwise>
																<td><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default;">
															<i class="fe-delete"></i></button></td>
															</c:otherwise>
															</c:choose> 
														</tr>
														</c:forEach> 
			                                        </tbody>
			                                    </table>
			                                    </div>
			                                   </c:when>
			                                    <c:otherwise>			                                    
			                                    	<c:if test="${target.frompage != 'Initial'}">
			                                    	<div class="card-box">	
			                                    	 <h5>Closed</h5>	
			                                    		<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
				                                			<span class="badge badge-pill badge-danger">No Data</span>
				                                   				 No  data exist!
				                                  			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				                                    			<span aria-hidden="true">&times;</span>
				                                			</button>
				                         				</div>
				                         				</div>
				                         			</c:if>
			                                    </c:otherwise>
		                                  		</c:choose>
			                                 </div>
			                            </div>
			                       
                                    
                                 </form:form>
                                </div> <!-- end card-box-->
                            </div> <!-- end col-->
                        </div>
                        <!-- end row-->
                    </div> <!-- container -->

                </div> <!-- content -->
		</div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
<script>
	function addtarget()
	{
		location.href = "addtarget";
	}
	
	function validate()
	{
		var username = document.getElementById("userId").value;
		if(username == "")
		{
			alert("Please Select User Name!");
			document.getElementById("userId").focus();
			return false;
			
		}
		else
		{
			location.href="addtarget?userId="+username;
		}
	}
	function openEdit(loopid)
	{
		var userid = document.getElementById("userId").value;
		var targetsId = document.getElementById("targetsId"+loopid).value;
		var name = document.getElementById("name"+loopid).value;

		location.href="edittarget?id="+targetsId+"&&userId="+userid+"&&userName="+name;
	}
	function emailEdit(loopid)
	{
		var userid = document.getElementById("userId").value;
		var targetsId = document.getElementById("emailTargetId"+loopid).value;
		var name = document.getElementById("emailName"+loopid).value;
		
		location.href="edittarget?id="+targetsId+"&&userId="+userid+"&&userName="+name;
	}
	function followupEdit(loopid)
	{
		var userid = document.getElementById("userId").value;
		var targetsId = document.getElementById("followupTargetId"+loopid).value;
		var name = document.getElementById("followupName"+loopid).value;
		
		location.href="edittarget?id="+targetsId+"&&userId="+userid+"&&userName="+name;
	}
	function responseEdit(loopid)
	{
		var userid = document.getElementById("userId").value;
		var targetsId = document.getElementById("responseTargetId"+loopid).value;
		var name = document.getElementById("responseName"+loopid).value;
		
		location.href="edittarget?id="+targetsId+"&&userId="+userid+"&&userName="+name;
	}
	function closeEdit(loopid)
	{
		var userid = document.getElementById("userId").value;
		var targetsId = document.getElementById("closeTargetId"+loopid).value;
		var name = document.getElementById("closeName"+loopid).value;
		
		location.href="edittarget?id="+targetsId+"&&userId="+userid+"&&userName="+name;
	}
</script>


 <%@include file="crm-footer.jsp" %>