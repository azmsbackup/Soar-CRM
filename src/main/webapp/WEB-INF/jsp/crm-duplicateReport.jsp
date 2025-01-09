<%@include file="crm-header.jsp"%>
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
								<li class="breadcrumb-item active">Duplicate Report</li>
							</ol>
						</div>
						<h4 class="page-title">Duplicate Report</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="duplicatereportsubmit" method="post" modelAttribute="client" id ="form1" class="parsley-examples">
							<div class="row row-grid">
								<div class="col-md-3">
									<label for="statusId">Status</label>
									<form:select  type="select" path="statusflag" name="statusflag"	class="form-control" id="statusflag">
										<form:option value="" label="--Select--" />
										<form:option value="Email" label="Email" />
										<form:option value="Phone" label="PhoneNo" />
										<form:option value="Client" label="Client Name" />
										<form:option value="Website" label="WebSite" />
									</form:select>									
								</div>
								 
							</div>
                         
							
							<c:choose>
							<c:when test="${duplicateList.size() > 0 }">
								<div class="row">
									<div class="col-12">
										<div class="card">
											<div class="card-body">
												<table id="datatable-buttons-d" class="table table-striped">
													<input type="hidden" id="dt-title" value = "Allzone CRM - Duplicate Report" />
													<thead class="thead-light">
                                        			<tr>
                                        				<th  style="width:20px;text-align:center" data-field="traceNo" data-sortable="false">Trace No</th>  
                                        				<th  style="width:20px;text-align:center" data-field="address" data-sortable="false">Name</th>                                          
                                             			<th  style="width:20px;text-align:center" data-field="clientName" data-sortable="false">Client Name</th>
                                             			<th  style="width:20px;text-align:center" data-field="email" data-sortable="false">Email</th> 
                                             			<th  style="width:20px;text-align:center" data-field="mobileNumber" data-sortable="false">Mobile Number </th>
                                             			<th  style="width:20px;text-align:center" data-field="website" data-sortable="false">Website</th> 
                                             			<th  style="width:20px;text-align:center" data-field="phoneNumber" data-sortable="false">Phone Number</th>                                             			 
                                             			
                                             	   </tr>
                                       			 </thead>
                                       			 
												<tbody>
													<c:forEach var="client" items="${duplicateList}">
														<tr>
															<td style="text-align:right">${client.traceNo}</td>
															<td style="text-align:right">${client.name}</td>
															<td style="width:20px;text-align:center">${client.clientName}</td>	
															<td style="width:20px;text-align:center">${client.email}</td>	
															<td style="width:20px;text-align:center">${client.mobileNumber}</td>	
															<td style="width:20px;text-align:center" >${client.website}</td>											
															<td style="width:20px;text-align:center">${client.phoneNumber}</td>
															
																																											
														</tr>
													</c:forEach>
												</tbody>
											</table>

											</div>
											<!-- end card body-->
										</div>
										<!-- end card -->
									</div>
									<!-- end col-->
								</div>
							</c:when>
							<c:otherwise>								
								<c:if test="${client.frompage != 'Initial'}">
									<br>
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
							</form:form>
						</div>
						</div>
					</div>
					<!-- end card-box -->
				</div>
				<!-- end col -->


			</div>
			<!-- end row -->
		</div>
		<!-- container -->

	</div>
	<!-- content -->
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->



<script>
 $('#statusflag').on('change', function() {
		  document.getElementById("form1").submit();   
	     //document.forms[].submit();
});
 $(document).ready(function() { 
	 $('#datatable-buttons-d').dataTable( 
			 {
				 "dom": 'Bfrtip',
				 "order": [],
				 "sScrollY": "400px",
				 "sScrollX": "100%",
				 "sScrollXInner": "100%",
				 "bScrollCollapse": true,
				 "bDestroy": true,
				 "deferRender": true,
				 lengthChange:!1,
				 buttons: [{extend: 'excel', text: 'Download as Excel'}],
				 language:{paginate:{previous:"<i class='mdi mdi-chevron-left'>",next:"<i class='mdi mdi-chevron-right'>"}},
				 drawCallback:function(){$(".dataTables_paginate > .pagination").addClass("pagination-rounded")}
			 });
 } );
</script>

        <!-- Vendor js -->
        <script src="resources/assets/js/vendor.min.js"></script>
        
                 <!-- plugin js -->
        <script src="resources/assets/libs/moment/moment.min.js"></script>
        <script src="resources/assets/libs/jquery-ui/jquery-ui.min.js"></script>
        <script src="resources/assets/libs/fullcalendar/fullcalendar.min.js"></script>
        

        <!-- Calendar init -->
        <script src="resources/assets/js/pages/calendar.init.js"></script>
        

        <!--Morris Chart-->
        <script src="resources/assets/libs/morris-js/morris.min.js"></script>
        
        <script src="resources/assets/libs/raphael/raphael.min.js"></script>
        

        <!-- CRM Dashboard init js-->
        
        
                <!-- third party js -->
        <script src="resources/assets/libs/datatables1/jquery.dataTables.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.bootstrap4.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.responsive.min.js"></script>
        <script src="resources/assets/libs/datatables1/responsive.bootstrap4.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.buttons.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.bootstrap4.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.html5.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.flash.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.print.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.keyTable.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.select.min.js"></script>
        
        
        <script src="resources/assets/libs/pdfmake/pdfmake.min.js"></script>
        <script src="resources/assets/libs/pdfmake/vfs_fonts.js"></script>
        <script src="resources/assets/js/pages/jszip.min.js"></script>
        
        <!-- third party js ends -->

        
        
                <!-- Table Editable plugin-->
        <script src="resources/assets/libs/jquery-tabledit/jquery.tabledit.min.js"></script>
        

        <!-- Table editable init-->
        <script src="resources/assets/js/pages/tabledit.init.js"></script>
        
        
                <!-- Bootstrap Tables js -->
        <script src="resources/assets/libs/bootstrap-table/bootstrap-table.min.js"></script>
        

        <!-- Init js -->
        <script src="resources/assets/js/pages/bootstrap-tables.init.js"></script>
        
                <!-- Plugins js -->
        <script src="resources/assets/libs/dropzone/dropzone.min.js"></script>
        <script src="resources/assets/libs/dropify/dropify.min.js"></script>
       

        
         <!-- Common js -->
        <script src="resources/assets/js/common.js" /></script>
        
         <script src="resources/assets/libs/summernote/summernote-bs4.min.js"></script>
         <!-- <script src="resources/assets/libs/summernote/uploadcare.js"></script>
          -->
         
                 <!-- Plugins js-->
        <script src="resources/assets/libs/flatpickr/flatpickr.min.js"></script>
        <script src="resources/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.js"></script>
        <script src="resources/assets/libs/clockpicker/bootstrap-clockpicker.min.js"></script>

        <!-- Init js-->
       
         <script src="resources/assets/js/pages/datepicker.js"></script>
         
        <!-- Plugins js -->
        <script src="resources/assets/libs/jquery-mask-plugin/jquery.mask.min.js"></script>
        <script src="resources/assets/libs/autonumeric/autoNumeric-min.js"></script>

        <!-- Init js-->
        <script src="resources/assets/js/pages/form-masks.init.js"></script>
        
        
        
        <!-- App js -->
        <script src="resources/assets/js/app.min.js"></script>

        
       <!--   <script src="resources/assets/js/pages/form-pickers.init.js"></script> -->
       
        <!-- Modal-Effect -->
        <script src="resources/assets/libs/custombox/custombox.min.js"></script>

        <!-- Sparkline chart js -->
        <script src="resources/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

        <!-- Opportunities init js -->
        <script src="resources/assets/js/pages/crm-opportunities.init.js"></script>
        
        <script src="resources/assets/libs/footable/footable.all.min.js"></script>

        <!-- Init js -->
        <script src="resources/assets/js/pages/foo-tables.init.js"></script>
        
        <!-- Init js-->
        <script src="resources/assets/js/pages/form-advanced.init.js"></script>
       
        <script src="resources/assets/libs/jquery-nice-select/jquery.nice-select.min.js"></script>
        <script src="resources/assets/libs/switchery/switchery.min.js"></script>
        <script src="resources/assets/libs/multiselect/jquery.multi-select.js"></script>
        <script src="resources/assets/libs/select2/select2.min.js"></script>
        <script src="resources/assets/libs/jquery-mockjax/jquery.mockjax.min.js"></script>
        <script src="resources/assets/libs/autocomplete/jquery.autocomplete.min.js"></script>
        <script src="resources/assets/libs/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="resources/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"></script>
        <script src="resources/assets/libs/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>


        <!-- Plugin js-->
        <script src="resources/assets/libs/parsleyjs/parsley.min.js"></script>

        <!-- Validation init js-->
        <script src="resources/assets/js/pages/form-validation.init.js"></script>
