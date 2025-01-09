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
                                               			<a href="managerOpenDeals"> <i class="fe-layers avatar-title font-22 text-white"></i></a>
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
		                                                <h3 class="text-dark my-1"><a href="managerOpenDeals"><span><%= session.getAttribute("OpenDeals") %></span></a></h3>
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
                                </div> <!-- end card-box-->
                            </div> <!-- end col -->
    
                           <!--  <div class="col-md-2">
                                <div class="card-box bg-pattern">
                                    <div class="row">
                                        
                                    </div>
                                </div> end card-box
                            </div> end col -->
                            
                            <div class="col-md-6 col-xl-3">
                                <div class="card-box">
                                    <div class="row">
                                        <div class="col-md-4 mb-2">
			                              <div class="avatar-sm bg-danger rounded">
                                       			<c:choose>
                                       			 	<c:when test="${sessionScope.HotLeads > '0'}">
			                                                <a href="managerHotLeads"><i class="mdi mdi-fire avatar-title font-22 text-white"></i></a>
			                  					 	</c:when>
                                              	 	<c:otherwise>
                                              	 		<i class="fe-mail avatar-title font-22 text-white"></i>
                                              	 	</c:otherwise>
                                        		 </c:choose>
                                     		</div>
                                        </div>
                                
                                    	   <div class="text-right">
                                            	<c:choose>
	                                       			 <c:when test="${sessionScope.HotLeads > '0'}">
		                                                <h5 class="text-dark my-1"><a href="managerHotLeads"><span><%= session.getAttribute("HotLeads") %></span></a>
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
				                                                <a href="managerDroppedLeads"><i class="fe-arrow-down avatar-title font-22 text-white"></i></a>
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
		                                                <h5 class="text-dark my-1"><a href="managerDroppedLeads"><span><%= session.getAttribute("DroppedLeads") %></span></a>
		                                                Dropped</h5>
													 </c:when>
	                                              	 <c:otherwise>
	                                              	 	<h5 class="text-dark my-1"><span><%= session.getAttribute("DroppedLeads") %></span>
		                                               Dropped</h5>
	                                              	 </c:otherwise>
                                              	 </c:choose>
                                             </div>
                                           </div>
                                   </div>
                                </div> <!-- end card-box-->
                            
                            <div class="col-md-6 col-xl-3">
                                <div class="card-box">
                                    <div class="row">                                     
                                       <div class="col-md-4 mb-2">
				                              <div class="avatar-sm bg-info rounded">
	                                       			<c:choose>
	                                       			 	<c:when test="${sessionScope.FollowUp > '0'}">
				                                                <a href="managerFollowUp"><i class="fe-phone-call avatar-title font-22 text-white"></i></a>
				                  					 	</c:when>
	                                              	 	<c:otherwise>
	                                              	 		<i class="fe-phone-call avatar-title font-22 text-white"></i>
	                                              	 	</c:otherwise>
	                                        		 </c:choose>
	                                     		</div>
                                        	</div>                                  
                                		  <div class="text-right">
                                            	<c:choose>
	                                       			 <c:when test="${sessionScope.FollowUp > '0'}">
		                                                <h5 class="text-dark my-1"><a href="managerFollowUp"><span><%= session.getAttribute("FollowUp") %></span></a>
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
				                                                <a href="managerOverdueFollowup"><i class="fe-phone-off avatar-title font-22 text-white"></i></a>
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
		                                                <h5 class="text-dark my-1"><a href="managerOverdueFollowup"><span><%= session.getAttribute("OverdueFollowup") %></span></a>
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
                                               			<a href="managerClosed"> <i class="mdi mdi-close-box avatar-title font-22 text-white"></i></a>
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
		                                                <h3 class="text-dark my-1"><a href="managerClosed"><span><%= session.getAttribute("ClosedDeals") %></span></a></h3>
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
                       
            <%--                 <div class="row">
                             <div class="col-xl-4 order-xl-2 order-1">
                                <div class="card-box">
                                    <h4 class="header-title mb-3">Status Chart</h4>

                                    <div class="text-center">
                                        <div id="status-chart"></div>

                                        <p class="text-muted font-13 font-family-secondary mb-0 mt-3">
                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle text-success"></i> Closed</span>
                                           <!--  <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle text-info"></i> Cold</span>  -->                                           
                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle text-primary"></i> In-progress</span>
                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle text-danger"></i> Lost</span>
                                        </p>
                                    </div>

                                </div> <!-- end card-box-->
                            </div> <!-- end col -->
                            <div class="col-lg-4">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="card-widgets">
                                            <!-- <a href="javascript: void(0);" data-toggle="reload"><i class="mdi mdi-refresh"></i></a>
                                            <a data-toggle="collapse" href="#cardCollpase2" role="button" aria-expanded="false" aria-controls="cardCollpase2"><i class="mdi mdi-minus"></i></a>
                                            <a href="javascript: void(0);" data-toggle="remove"><i class="mdi mdi-close"></i></a> -->
                                        </div>
                                        <h4 class="header-title mb-0">Department Data</h4>

                                        <div id="cardCollpase2" class="collapse pt-3 show">
                                            <div id="medical" style="height: 350px;" class="morris-chart">
                                            	 <center><p>
	                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle"  style="color:#02c0ce"></i>Open</span>
	                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle" style="color:#CCFFBB"></i>Email Sent</span>
	                                             <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle" style="color:#0acf97"></i>Follow Up</span>
											</p></center> 
											                                           	
                                            </div>

                                        </div>
                                    </div>
                                </div> <!-- end card-->
                            </div> <!-- end col-->
                            <div class="col-lg-4">
                                <div class="card">
                                    <div class="card-body">
                                    	<form:form action="" method="post" modelAttribute="crmstatus" class="parsley-examples" >
                                        <div class="card-widgets">
                                            <!-- <a href="javascript: void(0);" data-toggle="reload"><i class="mdi mdi-refresh"></i></a>
                                            <a data-toggle="collapse" href="#cardCollpase2" role="button" aria-expanded="false" aria-controls="cardCollpase2"><i class="mdi mdi-minus"></i></a>
                                            <a href="javascript: void(0);" data-toggle="remove"><i class="mdi mdi-close"></i></a> -->
                                        </div>
                                        <%@page import="java.util.Date"%>
											<%@page import="java.util.Calendar"%>
											<%@page import="java.util.Locale"%>
											<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>
											<% pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().getDisplayName(java.util.Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)); %>
                                        <h4 class="header-title mb-0">Productivity Totals: Year - <c:out value="${currentYear}" /> & Month - <c:out value="${currentMonth}"/> </h4>
                                         

                                        <div id="cardCollpase2" class="collapse pt-3 show">
                                        
                                        <div class="row">
	                                    <div class="col-md-8">   
	                                    	<div class="form-group">
	                                         	<label for="userId">User Name<span class="text-danger">*</span></label>                                             
	                                        		<form:select  type= "select"  path="userId" class="form-control" id="userid">
			            								<form:option value="" label="All"/>
			            								<form:options items="${crmstatus.userlist}" itemValue="userId" itemLabel="firstName"/>
	       											</form:select>                                                                                                       
	                                      	</div>
	                                     </div>	                                    
										</div>
                                            <div id="productivity" style="height: 310px;" class="morris-chart"></div>                                           
                                            <center><p>
	                                            <span class="mx-2" ><i class="mdi mdi-checkbox-blank-circle" style="color:#FF5733"></i> Year</span>
	                                            <span class="mx-2"><i class="mdi mdi-checkbox-blank-circle"  style="color:#FFBF33"></i> Month</span>
											</p></center>
                                        </div>
                                        </form:form>
                                    </div>
                                </div> <!-- end card-->
                            </div> <!-- end col-->
                          </div> --%>
                         
                          <div class="row">                                            
                          	    <div class="col-lg-6">
                                 <div class="card-box ribbon-box">
                                    <div class="ribbon-two ribbon-two-primary"><span>E-Overdue</span></div>
                                    <c:choose>  
                             		<c:when test="${sessionScope.exceedEmailList.size() > '0' }"> 
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
                                        	<th data-field="username" data-sortable="true">User Name</th>                                       	                                                                       
                                        </tr>
                                        </thead>

                                        <tbody>                                        
											<c:forEach var="client" items="${sessionScope.exceedEmailList}">
											<tr>	
												<td style="text-align:right"><a href="clientNavigation?id=${client.traceNo}&&status=${client.statusId}">
												${client.traceNo} </a></td>	
												<td>${client.clientName}</td>
												<td>${client.username}</td>																																					 
											</tr>
											</c:forEach>
                                       </tbody>
                                    </table>
                                    </c:when>
                               		<c:otherwise>								
									<br>
									<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
	                                	<span class="badge badge-pill badge-danger">No Data</span>
	                                    No Overdue Email Exists!	                                  
	                         		</div>			
								</c:otherwise>
								</c:choose>
                                    </div>                                  
                                  </div> 
                                 <div class="col-lg-6">
                                 <div class="card-box ribbon-box">
                                    <div class="ribbon-two ribbon-two-primary"><span>F-Overdue</span></div>
                                      <c:choose>
                          				<c:when test="${sessionScope.exceedFollowupList.size() > '0' }">   
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
                                        	<th data-field="username" data-sortable="true">User Name</th>                                         	                                                                       
                                        </tr>
                                        </thead>

                                        <tbody>                                        
											<c:forEach var="client" items="${sessionScope.exceedFollowupList}">
											<tr>	
												<td style="text-align:right"><a href="clientNavigation?id=${client.traceNo}&&status=${client.statusId}">
												${client.traceNo} </a></td>	
												<td>${client.clientName}</td>	
												<td>${client.username}</td>																																				 
											</tr>
									</c:forEach>
                                       </tbody>
                                    </table>
                                     </c:when>
	                               		<c:otherwise>								
										<br>
										<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
		                                	<span class="badge badge-pill badge-danger">No Data</span>
		                                    No Overdue Followup Exists!	                                  
		                         		</div>			
									</c:otherwise>
								</c:choose> 
                                    </div>                                  
                                  </div>                         
                        	</div> 
  <%--                       	<c:if test="${sessionScope.RoleId =='1'}"> 
                        	 <div class="row">                                            
                          	    <div class="col-lg-12">
                          	    	 <div class="card-box">
                          	    	  <%@page import="java.util.Date"%>
											<%@page import="java.util.Calendar"%>
											<%@page import="java.util.Locale"%>
											<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>
											<% pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().getDisplayName(java.util.Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)); %>
                                        	<h4 class="header-title mb-0">Productivity for -  <c:out value="${currentMonth}"/> </h4>  
                                        	
                                        	<c:choose>
                                        	     <c:when test="${sessionScope.productivityHashMap.size() > 1 }">                   	    	 	
			                        				<table id="datatable" class="table table-bordered dt-responsive nowrap row-grid">                                   
														<thead class="thead-light">
			                                        	<tr> 
			                                        		<th style="text-align:center">Users</th>
			                                        			<c:forEach var="allstatus" items="${sessionScope.statusmap}">                                        				  	 
							                     				 	<th style="text-align:center">${allstatus.key}</th>				                     				 
							                     				 </c:forEach>                                         			                                                                               
			                                        	</tr>
			                                       		</thead>                                       			 
														<tbody>
								
															<c:forEach var="users" items="${sessionScope.productivityHashMap}">
																<tr>
																	<td style="text-align:center">${users.key}</td>
																	<c:forEach var="status" items="${users.value}">
																		 <td style="text-align:center">${status.value}</td>
																	</c:forEach>													 	  
																</tr>
															</c:forEach>
													
														</tbody>
													</table>
												</c:when>
													<c:otherwise>								
														<c:if test="${client.frompage != 'Initial'}">
														
																<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
							                                	<span class="badge badge-pill badge-danger">No Data</span>
							                                    No data exist!
							                                  	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
							                                    	<span aria-hidden="true">&times;</span>
							                                	</button>
							                         		</div>
							                         		
														</c:if>						
													</c:otherwise>
												</c:choose>
									</div>
                        		</div>
                        	</div> 
                        </c:if> 
                        <c:if test="${sessionScope.RoleId =='3'}"> 
                        	 <div class="row">                                            
                          	    <div class="col-lg-12">
                          	    	 <div class="card-box">
                          	    	  <%@page import="java.util.Date"%>
											<%@page import="java.util.Calendar"%>
											<%@page import="java.util.Locale"%>
											<% pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); %>
											<% pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().getDisplayName(java.util.Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)); %>
                                        	<h4 class="header-title mb-0">Productivity for -  <c:out value="${currentMonth}"/> </h4>                          	    	 	
	                        				<table id="datatable" class="table table-bordered dt-responsive nowrap row-grid">                                   
											<thead class="thead-light">
	                                        	<tr> 
	                                        		<th style="text-align:center">Users</th>
	                                        			<c:forEach var="allstatus" items="${sessionScope.statusmap}">                                        				  	 
					                     				 	<th style="text-align:center">${allstatus.key}</th>				                     				 
					                     				 </c:forEach>                                         			                                                                               
	                                        	</tr>
	                                       	</thead>                                       			 
											<tbody>
												<c:forEach var="users" items="${sessionScope.productivityHashMap}">
													<tr>
														<td style="text-align:center">${users.key}</td>
														<c:forEach var="status" items="${users.value}">
															 <td style="text-align:center">${status.value}</td>
														</c:forEach>													 	  
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
                        		</div>
                        	</div> 
                        </c:if> --%> 
          			</div> <!-- container -->
			</div>
	</div> <!-- content -->


<!-- <script>

$("#userid").change(function()
{
	var userid = $("#userid").val();
	$.ajax({
	       type: "GET",
	       url: "productivitychart",
	       data : 
	       {
	    	   userid   : userid
	        },
	       success: function(result)
	       {
	    	   
	    	   if(result != "")
		       { 
	    		   var response_year = result.response_year;
	    		   var response_month = result.response_month;
	    		   var closed_year = result.closed_year;
	    		   var closed_month = result.closed_month;
	    		
	    		   ShowGrpah(result);

		       }
	        },  
	           
	       	error : function(xhr, status, error) 
	       	{
	       		alert("error is " + xhr.responseText);
	       	}
	   });
	
});

function ShowGrpah(result) {

    new Morris.Bar({
        element: 'e',
        data: a,
        xkey: 'o',
        ykeys: ['r'],
        labels: ['t']
        });

    }



</script> -->

 <script src="resources/assets/js/pages/crm-dashboard2.init.js"></script>
 <script src="resources/assets/js/pages/managerViewDashboard.js"></script>
<%@include file="crm-footer.jsp" %>
