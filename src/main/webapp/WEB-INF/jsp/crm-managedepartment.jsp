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
                                            <li class="breadcrumb-item active">Department</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Department</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                   <!--  <h4 class="header-title">Add/Edit Department</h4> -->
									<%-- <form:form action="managedepartment" method="post" modelAttribute="department" class="parsley-examples"> --%>
                                    <button type="button" id="demo-delete-row" class="btn btn-blue waves-effect waves-light" onclick="adddepartment()"><i class="mdi mdi-plus-circle mr-1"></i>Add Department</button>
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
                                           
                                            <th data-field="id" data-sortable="true">Department Code</th>
                                            <th data-field="name" data-sortable="true">Department Name</th>
                                            <th data-field="inactive" data-align="center">Active / Inactive</th>
                                            <th data-field="status" data-align="center">Edit</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        	<c:forEach var="dept" items="${departmentList}">
                                        	
										<tr>																				
											<td class="stylewidth">${dept.departmentCode}</td>
											<td class="stylewidth">${dept.departmentName}</td>
											<c:choose>
											<c:when test="${dept.status == 'A' }">	
											<td class="stylewidth"><a href="inactivedepartment?id=${dept.departmentId}"><button type="button" onclick="return inactivedept()"
												 class="btn btn-primary waves-effect waves-light"><i class="fe-check-square"></i></button></a></td>										
											<td class="stylewidth"><a href="editdepartment?id=${dept.departmentId}"><button type="button" 
											class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></a></td>
										</c:when>
										<c:otherwise>
											<td class="stylewidth"><a href="inactivedepartment?id=${dept.departmentId}"><button type="button" onclick="return activedept()"
												 class="btn btn-danger waves-effect waves-light"><i class="fe-x-square"></i></button></a></td>										
											<td class="stylewidth"><button type="button" 
											class="btn btn-danger waves-effect waves-light" style="cursor:default"><i class="mdi mdi-pencil"></i></button></td>
										</c:otherwise>
										</c:choose> 
										</tr>
									</c:forEach>
                                        </tbody>
                                    </table>
                                   <%--  </form:form> --%>
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
	function adddepartment()
	{
		location.href = "adddepartment";
	}
	function inactivedept()
	{	
		if(confirm("Do you want to inactive this department?"))
		{
			return true;
		}
		else
		{
			return false
		}
	}
	function activedept()
	{	
		if(confirm("Do you want to active this department?"))
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