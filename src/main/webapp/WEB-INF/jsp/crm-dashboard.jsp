<%@include file="crm-header.jsp" %>

            <!-- ============================================================== -->
            <!-- Start Page Content here -->
            <!-- ============================================================== -->

            <div class="content-page">
                <div class="content">

                    <!-- Start Content-->
                    <div class="container-fluid">
                       
                        <!-- start page title -->
                        
                        <c:if test="${sessionScope.RoleId =='3'}">
                        <div class="row">
                             <div class="col-12">
                                <div class="page-title-box">
                                 <form:form action="crm-dashboard" method="post" modelAttribute="crmstatus" class="parsley-examples" >
                                    <div class="page-title-right">                                 	
                                       <a href="crm-dashboard"><button type="submit" name="userView" value="userView" class="btn btn-primary waves-effect waves-light">User View</button> &nbsp; &nbsp;</a>
                       				 	<a href ="crm-dashboard"><button type="submit" name="managerView" value="managerView" class="btn btn-warning waves-effect waves-light">Manager View</button>  &nbsp; &nbsp;</a>
                       				  </div>
                       			</form:form>
                                    <h4 class="page-title">Dashboard</h4>
                                </div>
                            </div>
                           </div> 
                       </c:if>    
                        <!-- end page title --> 
						
						<br>
                        <div class="row">
                            <div class="col-md-6 col-xl-3">
                            	<div class="card-box">                               		
                                    <div class="row">
                                        <div class="col-6">
                                        	 <c:choose>
			                                       	<c:when test="${sessionScope.OpenDeals > '0'}">
				                                       <div class="avatar-lg bg-blue rounded">
			                                              <a href="openDeals">  <i class="fe-layers avatar-title font-22 text-white"></i> </a>
			                                            </div> 
			                                          </c:when> 
			                                           <c:otherwise> 
			                                           		<div class="avatar-lg bg-blue rounded">
			                                                	<i class="fe-layers avatar-title font-22 text-white"></i>
			                                            	</div>
			                                           </c:otherwise>
		                                         </c:choose>                                  	 
                                        </div>
                                        <div class="col-6">
                                        	<div>  
		                                       <c:choose>
			                                       	<c:when test="${sessionScope.OpenDeals > '0'}">
		                                                <h3 class="text-dark my-1"><a href="openDeals"><span><%= session.getAttribute("OpenDeals") %></span></a></h3>
		                                                <h5 class="text-dark my-1">Open Data</h5>
													 </c:when>
		                                             <c:otherwise>
		                                             	 <h3 class="text-dark my-1"><span><%= session.getAttribute("OpenDeals") %></span></h3>
		                                              	 <h5 class="text-dark my-1">Open Data</h5>
		                                             </c:otherwise>
		                                         </c:choose>
		                                       </div> 
		                                    </div>
                                   </div>
                                    <div class="mt-3">
                                        <h6 class="text-uppercase"> <span class="float-right"></span></h6>
                                        <div>
                                            <div>
                                                
                                            </div>
                                        </div>
                                    </div>                                                
                                </div>
                              </div>					
                            <div class="col-md-6 col-xl-3">
                                <div class="card-box">
                                    <div class="row">
                                        <div class="col-md-4 mb-2">
                                        	<div class="avatar-sm bg-danger rounded">
                                        	 	<c:choose>
                                        			<c:when test="${sessionScope.HotLeads > '0'}">
                                        				<a href="hotleads"><i class="mdi mdi-fire avatar-title font-22 text-white"></i></a>                                        				  
                                            		</c:when>
		                                            <c:otherwise>
		                                            	<i class="mdi mdi-fire avatar-title font-22 text-white"></i>		                                           
		                                            </c:otherwise>
                                            	</c:choose>                                         
                                        	</div> 
                                        </div>                                      
                                        <div class="text-right">
                                             <c:choose>
	                                              <c:when test="${sessionScope.HotLeads > '0'}">
			                                                <h5 class="text-dark my-1"><a href="hotleads"><span><%= session.getAttribute("HotLeads") %></span></a>
			                                                Hot Leads</h5>
												 </c:when>                                             
	                                             <c:otherwise>
	                                             <h5 class="text-dark my-1"><span><%= session.getAttribute("HotLeads") %></span>
			                                                Hot Leads</h5>                                             	
	                                             </c:otherwise>                                             
                                             </c:choose>                   
                                        </div>
                                      </div>
                                      <div class="row">	                                     
                                        <div class="col-md-4 mb-2">
                                         <div class="avatar-sm bg-soft-danger border-danger border rounded">
                                        	<c:choose>
                                        		<c:when test="${sessionScope.DroppedLeads > '0'}">
				                                       <a href="droppedleads"><i class="fe-arrow-down avatar-title font-22 text-white"></i></a>
				                  				</c:when>
	                                            <c:otherwise>
	                                              	 	<i class="fe-arrow-down avatar-title font-22 text-white"></i>
	                                            </c:otherwise>                                        	
                                             </c:choose>                                      	                                          
                                        </div>
                                      </div>
                                        <div class="text-right">
                                         <c:choose>
                                         	<c:when test="${sessionScope.DroppedLeads > '0'}">
		                                        <h5 class="text-dark my-1"><a href="droppedleads"><span><%= session.getAttribute("DroppedLeads") %></span></a>
		                                               Dropped</h5>
											 </c:when>                                            
                                             <c:otherwise>
                                              	<h5 class="text-dark my-1"><span><%= session.getAttribute("DroppedLeads") %></span>
		                                               Dropped</h5>
                                              </c:otherwise>
                                              </c:choose>
                                        </div>
                                    </div>
                                </div> <!-- end card-box-->
                            </div> <!-- end col -->
                           
                            <div class="col-md-6 col-xl-3">
                                <div class="card-box">
                                    <div class="row">
                                        <div class="col-md-4 mb-2">
                                        	<div class="avatar-sm bg-info rounded">
                                        		<c:choose>
                                        			<c:when test="${sessionScope.FollowUp > '0'}">
				                                                <a href="followUp"><i class="fe-phone-call avatar-title font-22 text-white"></i></a>
				                  					 </c:when>                                             
			                                    	<c:otherwise>
			                                    		<i class="fe-arrow-down avatar-title font-22 text-white"></i>
			                                    </c:otherwise>
			                                 </c:choose>                                           
                                        </div>
                                      </div>
                                         <div class="text-right">
                                       		 <c:choose>
	                                        	 <c:when test="${sessionScope.FollowUp > '0'}">
		                                                <h5 class="text-dark my-1"><a href="followUp"><span><%= session.getAttribute("FollowUp") %></span></a>
		                                                Follow-up Calls</h5>
												</c:when>                                             
	                                           <c:otherwise>
	                                           		<h5 class="text-dark my-1"><span><%= session.getAttribute("FollowUp") %></span>
			                                                Follow-up Calls</h5>
	                                           </c:otherwise>
                                          	 </c:choose>   
                                          </div>
                                       </div>
                                       <div class="row">                                       
                                        <div class="col-md-4 mb-2">
                                        	<div class="avatar-sm bg-soft-danger border-danger border rounded">
	                                        	<c:choose>
	                                        		<c:when test="${sessionScope.OverdueFollowup > '0'}">
					                                             <a href="overdueFollowup"><i class="fe-phone-off avatar-title font-22 text-white"></i></a>
					                  				</c:when>                                            
	                                                <c:otherwise>
	                                                	<i class="fe-phone-off avatar-title font-22 text-white"></i>
	                                                </c:otherwise>
	                                            </c:choose>                                                                               
                                        	</div>
                                         </div>
                                       <div class="text-right">                                        
                                          <c:choose>
                                           		<c:when test="${sessionScope.OverdueFollowup > '0'}">
		                                                <h5 class="text-dark my-1"><a href="overdueFollowup"><span><%= session.getAttribute("OverdueFollowup") %></span></a>
		                                                Calls Overdue</h5>
												</c:when>                                             
                                              	<c:otherwise>
                                              		<h5 class="text-dark my-1"><span><%= session.getAttribute("OverdueFollowup") %></span>
		                                               Calls Overdue</h5>
                                              </c:otherwise>
                                              </c:choose>
                                        </div>
                                    </div>
                                </div> <!-- end card-box-->
                            </div> <!-- end col -->
                            <div class="col-md-6 col-xl-3">
                                <div class="card-box">
                                    <div class="row">
                                        <div class="col-6">
                                        	<c:choose>
                                        	 	<c:when test="${sessionScope.ClosedDeals > '0'}">
                                            		<div class="avatar-lg bg-success rounded">
                                               			<a href="closedDeals"> <i class="mdi mdi-close-box avatar-title font-22 text-white"></i></a>
                                            		</div>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<div class="avatar-lg bg-success rounded">
                                               			 <i class="mdi mdi-close-box avatar-title font-22 text-white"></i>
                                            		</div>
                                            	</c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-6">
                                            <div>
                                            	<c:choose>
	                                       			 <c:when test="${sessionScope.ClosedDeals > '0'}">
		                                                <h3 class="text-dark my-1"><a href="closedDeals"><span><%= session.getAttribute("ClosedDeals") %></span></a></h3>
		                                                <h5 class="text-dark my-1">Closed Data</h5>
													 </c:when>
	                                              	 <c:otherwise>
	                                              	 	<h3 class="text-dark my-1"><span><%= session.getAttribute("ClosedDeals") %></span></h3>
		                                                <h5 class="text-dark my-1">Closed Data</h5>
	                                              	 </c:otherwise>
                                              	 </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-3">
                                        <h6 class="text-uppercase"> <span class="float-right"></span></h6>
                                        <div>
                                            <div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- end card-box-->
                            </div> <!-- end col -->
                         </div>
                           
                        <!-- end row-->
						
						<!--   <div class="row">
						 	 <div class="col-12">
						 	 	<div class="card">
                                    <div class="card-body">
                                    <h4 class="header-title">Today's Appointment</h4>
                                    <c:choose>
                                    <c:when test="${sessionScope.notesList.size() > '0' }">
                                    	<table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[5, 10, 20]"
                                           data-page-size="5"
                                           data-pagination="true" data-show-pagination-switch="true" class="table-borderless">
                                        <thead class="thead-light">
                                        <tr>
                                        	<th data-field="traceNo" data-sortable="true">Trace No</th> 
                                        	<th data-field="clientName" data-sortable="true">Client Name</th> 
                                        	 <th data-field="appointmenttime" data-sortable="true">Appointment Time</th> 
                                             <th data-field="appointmentwith" data-sortable="true">Appointment With</th> 
                                  
                                             <th data-field="view" >View</th>                                                                                
                                        </tr>
                                        </thead>

                                        <tbody>                                        
											<c:forEach var="notes" items="${sessionScope.notesList}">
											<tr>	
												<td style="text-align:right"><a href="clientNavigation?id=${notes.traceNo}">
												${notes.traceNo} </a></td>		
												<td>${notes.clientName}</td>									
												<td>${notes.appointmentTime}</td>
												<td>${notes.appointmentWith}</td>
											
												<td><a href="viewnotes?id=${notes.traceNo}&&clientName=${notes.clientName}"><button type="button" class="btn btn-success waves-effect waves-light">
														<i class="icon-eye"></i></button></a></td>																																 
											</tr>
									</c:forEach> 
                                        
                                        </tbody>
                                    </table>
                                    </c:when>                                   
                                   <c:otherwise>								
									<br>
									<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
	                                	<span class="badge badge-pill badge-danger">No Data</span>
	                                    No Appointment Exists Today!	                                  
	                         		</div>			
							</c:otherwise>
                             </c:choose>       
                                    
                                    </div>
						 		</div>
						 	</div>
						 </div> -->
						 
						 <div class="row">
						 	 <div class="col-12">
						 	 	<div class="card">
                                    <div class="card-body">
                                    <h4 class="header-title">Today's Calls</h4>
                                    <c:choose>
                                    <c:when test="${sessionScope.followupdatelist.size() > '0' }">
                                    	<table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[5, 10, 20]"
                                           data-page-size="5"
                                           data-pagination="true" data-show-pagination-switch="true" class="table-borderless">
                                        <thead class="thead-light">
                                        <tr>
                                        	<th data-field="traceNo" data-sortable="true">Trace No</th> 
                                        	<th data-field="clientName" data-sortable="true">Client Name</th> 
                                        	<th  data-field="userName" data-sortable="true">Account Owner</th>
                                        	<th  data-field="timezone" data-sortable="true">Time Zone</th>
                                        	<th  data-field="modifiedDate" data-sortable="true">Last Activity Date</th>
                                        	 <!-- <th data-field="followupDate" data-sortable="true">Followup Date</th>  -->
                                              <!-- <th data-field="appointmentwith" data-sortable="true">Appointment With</th> 
                                            <th data-field="appointmentstatus" data-sortable="true">Appointment Status</th> -->
                                             <th  data-sort-ignore="true">Add Notes</th>
                                             <th data-sort-ignore="true" >View Notes</th>                                                                                
                                        </tr>
                                        </thead>

                                        <tbody>                                        
											<c:forEach var="notes" items="${sessionScope.followupdatelist}" varStatus="loop">
												<input type="hidden" name="hiddenuserid" id="hiddenuserid${loop.index+1}" value="${notes.userId}"/>	
												<input type="hidden" name="traceNo" id="traceNo${loop.index+1}" value="${notes.traceNo}"/>
                                   				<input type="hidden" name="clientName" id="clientName${loop.index+1}" value="${notes.clientName}"/>													
											<tr>	
												<td style="text-align:right"><a href="clientNavigation?id=${notes.traceNo}">
												${notes.traceNo} </a></td>															
												<td>${notes.clientName}</td>		
												<td>${notes.userName}</td> 
                                       			<td>${notes.timezone}</td>	
                                       			<td>${notes.modifiedDate}</td>						
												<%-- <td>${notes.followUpDate}</td> --%>
												<%-- <td>${notes.appointmentWith}</td>
												 <td>${notes.appointmentStatus}</td> --%>
												 <td><button type="button" onclick="return notes(${loop.index+1})" class="btn btn-success waves-effect waves-light">
														<i class="fe-file-plus"></i></button></td>	
												<td><a href="viewnotes?id=${notes.traceNo}&&clientName=${notes.clientName}&&frompage=crmdashboard"><button type="button" class="btn btn-success waves-effect waves-light">
														<i class="icon-eye"></i></button></a></td>																																 
											</tr>
									</c:forEach>
                                       </tbody>
                                    </table>  
                                </c:when>     
                                   <c:otherwise>								
									<br>
									<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
	                                	<span class="badge badge-pill badge-danger">No Data</span>
	                                    No Calls Exists Today!	                                  	
	                         		</div>			
							</c:otherwise>
                             </c:choose>       
                                    
                                    </div>
						 		</div>
						 	</div>
						 </div>				
                    </div>
               </div>
            </div>
   <script>
	function notes(loopid)
	{
		var hiddenTraceNo = document.getElementById("traceNo"+loopid).value;
		var userid = document.getElementById("userid").value;		
		var hiddenuserid = document.getElementById("hiddenuserid"+loopid).value;
		var clientName = document.getElementById("clientName"+loopid).value;		
		
		/* if(userid != hiddenuserid)
		{
			alert("You are not allowed to add notes for this client!");
			return false;
		}
		else
		{ */
			location.href ="addnotes?id="+hiddenTraceNo+"&&clientName="+clientName+"&&userid="+hiddenuserid;
			return true;
		//}
	}
   </script>                     
              
<%@include file="crm-footer.jsp" %>
<script src="resources/assets/js/pages/crm-dashboard.init.js"></script>