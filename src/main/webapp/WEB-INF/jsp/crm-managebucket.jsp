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
                                            <li class="breadcrumb-item active">Sub-Status</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Sub-Status</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                  
                                    <button id="demo-delete-row" type="button" class="btn btn-blue waves-effect waves-light" onclick="addbucket()"><i class="mdi mdi-plus-circle mr-1"></i>Add Sub-Status</button>
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
                                        	<th data-field="statusname"  data-sortable="true">Status Name</th>                                         
                                            <th data-field="id"  data-sortable="true">Sub-Status Name</th> 
                                            <th data-field="inactive" data-align="center">Active / Inactive</th>                                              
                                            <th data-field="status" data-align="center">Edit</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        	<c:forEach var="cbucket" items="${bucketList}">
										<tr>
											<td class="stylewidth">${cbucket.statusDescription}</td>										
											<td class="stylewidth">${cbucket.bucketName}</td>	
											<c:choose>
											<c:when test="${cbucket.bucketType == 'A' }">
												<td class="stylewidth"><a href="inactivebucket?id=${cbucket.bucketId}"><button type="button" onclick="return inactivebucket()"
													 class="btn btn-primary waves-effect waves-light"><i class="fe-check-square"></i></button></a></td>
												<td class="stylewidth"><a href="editbucket?id=${cbucket.bucketId}"><button type="button" class="btn btn-success waves-effect waves-light">
												<i class="mdi mdi-pencil"></i></button></a></td> 
											</c:when>
											<c:otherwise>
												<td class="stylewidth"><a href="inactivebucket?id=${cbucket.bucketId}"><button type="button" onclick="return activebucket()"
												 class="btn btn-danger waves-effect waves-light"><i class="fe-x-square"></i></button></a></td>
												<td class="stylewidth"><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default">
												<i class="mdi mdi-pencil"></i></button></td> 
											</c:otherwise>
											</c:choose>																														
											
											
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
                </div>


            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
<script>
	function addbucket()
	{
		location.href = "addbucket";
	}
	function inactivebucket()
	{
		if(confirm('Do you want to inactive this bucket?'))
		{
			return true;
		}
		else
		{
			return false
		}
		
	}
	function activebucket()
	{
		if(confirm('Do you want to active this bucket?'))
		{
			return true;
		}
		else
		{
			return false
		}
		
	}
</script>


 <%@include file="crm-footer.jsp" %>