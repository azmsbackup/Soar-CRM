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
                                            <li class="breadcrumb-item active">Calendar</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Calendar</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        <div class="row">
                            <div class="col-12">

                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">                                        

                                            <div class="col-lg-12">
                                                <div id='calendar' style="width: 100%; display: inline-block;"></div>
                                            </div> <!-- end col -->
                                            


                                        </div>  <!-- end row -->
                                    </div> <!-- end card body-->
                                </div> <!-- end card -->

                                <!-- Add New Event MODAL -->

                                <!-- end modal-->
                               <div id="calendarModal" class="modal fade">
                                <form:form id="myForm" action="calendarSave" method="post" modelAttribute="notes" class="parsley-examples" >
                               <input type="hidden" class="form-control" name="notesId" id="hiddenid">
									<div class="modal-dialog">
									    <div class="modal-content">
									        <div class="modal-header border-bottom-0 d-block col-12">
									            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
									            <h4 id="modalTitle" class="modal-title"></h4>
									            <div class="row-grid"></div>
									            <div class="form-group">
										            <label class="control-label"><i class="fas fa-sticky-note"></i> &nbsp; Notes : </label>
								         			<label id="modalBody" class="control-label"></label>
	         	
										        </div>
									             <div class="form-group">
									      			 <label class="control-label"><i class="mdi mdi-format-list-numbers"></i> &nbsp; Trace No : </label>
									      			 <label id="traceNo" class="control-label"></label>									        
											      </div>

											       <div class="form-group">
											        <label class="control-label"><i class="mdi mdi-calendar"></i>&nbsp; Appointment Date : </label>
											        <label id="aptDate" class="control-label"></label>		
													</div>
											        <div class="form-group">
											        <label class="control-label"><i class="ti-timer"></i> &nbsp; Appointment Time : </label>
											        <label id="atime" class="control-label"></label>		

									          		</div>
									          		 <div class="form-group">
											        <label class="control-label"><i class="mdi mdi-account"></i> &nbsp; User Name : </label>
											        <label id="userName" class="control-label"></label>	
									          		</div>
									          		 <div class="form-group">
                                           				 <label class="control-label" for="eventMonth"><i class="fe-check"></i>&nbsp;Appointment Status : </label>
                                            			<form:select class="control-label" id="notesstatus" path="appointmentStatus">
                                             				<form:option value="" label="--Select --"/>
															<form:option value="Reschedule">Reschedule</form:option>
															<form:option value="Cancel">Cancel</form:option>
															<form:option value="Closed">Closed</form:option>																													                                                            
                                              			</form:select>
                                        			</div>
									           
									        <div class="form-group text-right m-b-0">
									        <button id="btnsave" type="button" class="btn btn-success" onclick='document.forms["myForm"].submit();'>Save</button>
									            <!-- <button type="Submit" class="btn btn-success" data-dismiss="modal">Save</button> &nbsp; &nbsp; -->
									             <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button> &nbsp; &nbsp;
									             </div>
									        </div>
									    </div>
									</div>
								</form:form>
									</div>

                                <!-- Modal Add Category -->
                                <!-- <div class="modal fade" id="add-category" tabindex="-1">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header border-bottom-0 d-block">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">Add a category</h4>
                                            </div>
                                            <div class="modal-body p-4">
                                                <form>
                                                    <div class="form-group">
                                                        <label class="control-label">Category Name</label>
                                                        <input class="form-control form-white" placeholder="Enter name" type="text" name="category-name"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Choose Category Color</label>
                                                        <select class="form-control form-white" data-placeholder="Choose a color..." name="category-color">
                                                            <option value="primary">Primary</option>
                                                            <option value="success">Success</option>
                                                            <option value="danger">Danger</option>
                                                            <option value="info">Info</option>
                                                            <option value="warning">Warning</option>
                                                            <option value="dark">Dark</option>
                                                        </select>
                                                    </div>

                                                </form>

                                                <div class="text-right">
                                                    <button type="button" class="btn btn-light " data-dismiss="modal">Close</button>
                                                    <button type="button" class="btn btn-primary ml-1   save-category" data-dismiss="modal">Save</button>
                                                </div>

                                            </div> end modal-body
                                        </div> end modal-content
                                    </div> end modal dialog
                                </div> -->
                                <!-- end modal-->
                            </div>
                            <!-- end col-12 -->
                        </div> <!-- end row -->
                        
                    </div> <!-- container -->

                </div> <!-- content -->

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->

 <%@include file="crm-footer.jsp" %>