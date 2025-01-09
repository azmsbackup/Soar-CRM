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
                                            <li class="breadcrumb-item active">Status</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Status</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                  <!--   <h4 class="header-title">Add/Edit Status</h4> --> 
							<!--  	<form class="form-horizontal parsley-examples" action="managestatus" method="post" modelAttribute="crmstatus"> --%>-->
                                    <button id="demo-delete-row" type="button" class="btn btn-blue waves-effect waves-light" onclick="addstatus()"><i class="mdi mdi-plus-circle mr-1"></i>Add Status</button>
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
                                            <th data-field="inactive" data-align="center">Active / Inactive</th>                                              
                                            <th data-field="status" data-align="center">Edit</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        	<c:forEach var="cstatus" items="${statusList}">
										<tr>
																					
											<td class="stylewidth">${cstatus.statusDescription}</td>	
											<c:choose>
											<c:when test="${cstatus.crmstatus == 'A' }">
												<td class="stylewidth"><a href="inactivestatus?id=${cstatus.statusId}"><button type="button" onclick="return inactivestatus()"
													 class="btn btn-primary waves-effect waves-light"><i class="fe-check-square"></i></button></a></td>
												<td class="stylewidth"><a href="editstatus?id=${cstatus.statusId}"><button type="button" class="btn btn-success waves-effect waves-light">
												<i class="mdi mdi-pencil"></i></button></a></td> 
											</c:when>
											<c:otherwise>
												<td class="stylewidth"><a href="inactivestatus?id=${cstatus.statusId}"><button type="button" onclick="return activestatus()"
												 class="btn btn-danger waves-effect waves-light"><i class="fe-x-square"></i></button></a></td>
												<td class="stylewidth"><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default">
												<i class="mdi mdi-pencil"></i></button></td> 
											</c:otherwise>
											</c:choose>																														
											
											
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
                </div>


            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
<script>
	function addstatus()
	{
		location.href = "addstatus";
	}
	function inactivestatus()
	{
		if(confirm('Do you want to inactive this status?'))
		{
			return true;
		}
		else
		{
			return false
		}
		
	}
	function activestatus()
	{
		if(confirm('Do you want to active this status?'))
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