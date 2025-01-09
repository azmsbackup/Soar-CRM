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
                                            <li class="breadcrumb-item active">Services</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Services</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    <!-- <h4 class="header-title">Add/Edit Status</h4> -->
							<%-- 	<form:form class="form-horizontal parsley-examples" action="managestatus" method="post" modelAttribute="crmstatus"> --%>
                                    <button id="demo-delete-row" type="button" class="btn btn-blue waves-effect waves-light" onclick="addServices()">
                                    	<i class="mdi mdi-plus-circle mr-1"></i>Add Services</button>
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
                                            <th data-field="id"  data-sortable="true">Description</th>                                       
                                            <th data-field="status" data-align="center">Edit</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="services" items="${servicesList}">
												<tr>																					
													<td class="stylewidth">${services.servicesDescription}</td>											
													<td class="stylewidth"><a href="editServices?id=${services.servicesId}"><button type="button"
													 	class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></a></td> 
												</tr>
											</c:forEach> 
                                        </tbody>
                                    </table>
                                    <%-- </form:form> --%>
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
	function addServices()
	{
		location.href = "addservices";
	}
</script>


 <%@include file="crm-footer.jsp" %>