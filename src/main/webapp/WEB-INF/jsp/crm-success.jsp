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
                               		
                               		<c:if test="${sessionScope.isSuccess != ''}" >
									<div class="col col-md-3"></div>
		        	 					<center><i style="color:green; font-size:31px">Existing clients inserted successfully! </i></center>
		        	 	<br>
		        	 	<left><a href="manageclient">
            			<div class="col col-md-12"></div>
            			<br>            
            			<div class="col col-md-5"></div>            
                  			<center><button type="button" class="btn btn-primary btn-sm">                   					
				 					<i class="fa fa-dot-circle-o"></i> OK</button></center></a> </left>
	                </c:if>
                                
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

<script src="resources/assets/js/common.js" /></script>
 <%@include file="crm-footer.jsp" %>