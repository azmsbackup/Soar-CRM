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
                                            <li class="breadcrumb-item active">Swap Client</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Swap Client</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    <!-- <h4 class="header-title">Manage Client Assignment</h4> -->
                                    <table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[5, 10, 20]"
                                           data-page-size="5"
                                           data-pagination="true" data-show-pagination-switch="true" class="table table-striped dt-responsive nowrap">
                                        <thead class="thead-light">
                                        <tr>    
                                        	<th  data-field="traceNo" data-sortable="true">Trace No</th>                                        
                                             <th  data-field="clientName" data-sortable="true">Client Name</th>
                                          	 <th  data-field="user" data-align="center" data-sortable="true">From User</th>
                                            <th  data-field="notes" data-align="center">Swap</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="client" items="${clientAssignemntList}">
											<tr>	
												<td>${client.traceNo}</td>										
												<td>${client.clientName}</td>
												<td>${client.username}</td>											
												<td><a href="editclientassignment?id=${client.id}&&traceNo=${client.traceNo}&&hiddenuserid=${client.userId}"><button type="button" class="btn btn-success waves-effect waves-light">
														<i class="mdi mdi-pencil"></i></button></a></td>
											</tr>
										</c:forEach>    
                                        </tbody>
                                    </table>
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
	function addevent()
	{
		location.href = "addEvent";
	}
</script>


 <%@include file="crm-footer.jsp" %>