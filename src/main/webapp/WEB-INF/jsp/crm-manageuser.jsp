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
                                            <li class="breadcrumb-item active">User</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage User</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    
									<%-- <form:form action="manageuser" method="post" modelAttribute="user" class="parsley-examples"> --%>
                                    <button id="demo-delete-row" type="button" class="btn btn-blue waves-effect waves-light" onclick="adduser()"><i class="mdi mdi-plus-circle mr-1"></i>Add User</button>
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
                                            <th data-field="id" data-sortable="true" >First Name</th>
                                            <th data-field="name" data-sortable="true">Last Name</th>
                                            <th data-field="date" data-sortable="true" >Login Name</th>
                                            <th data-field="amount" data-sortable="true" data-sorter="priceSorter">User Role</th>
                                            <th data-field="inactive" data-align="center">Active / Inactive</th>
                                            <th data-field="status" data-align="center">Edit</th>                                           
                                            <th data-field="addepartment" data-align="center">Add Department</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                       		<c:forEach var="crmuser" items="${userlist}" varStatus="loop">                                       		
										<tr>											
											<td class="stylewidth">${crmuser.firstName}</td>
											<td class="stylewidth">${crmuser.lastName}</td>
											<td class="stylewidth">${crmuser.loginName}</td>
											<c:if test="${crmuser.roleId =='1' }">
												<td class="stylewidth">Super User</td>
											</c:if>
											<c:if test="${crmuser.roleId =='2' }">
												<td class="stylewidth">Admin</td>
											</c:if>
											<c:if test="${crmuser.roleId =='3' }">
												<td class="stylewidth">Manager</td>
											</c:if>
											<c:if test="${crmuser.roleId =='4' }">
												<td class="stylewidth">Team</td>
											</c:if>
											
											<c:choose>
											<c:when test="${crmuser.userStatus == 'A' }">
												<td class="stylewidth"><a href="inactiveuser?id=${crmuser.userId}"><button type="button" onclick="return inactiveuser()"
												 class="btn btn-primary waves-effect waves-light"><i class="fe-check-square"></i></button></a></td>
												<td class="stylewidth"><a href="edituser?id=${crmuser.userId}&&hiddenroleid=${crmuser.roleId}"><button type="button" 
												class="btn btn-success waves-effect waves-light"><i class="mdi mdi-pencil"></i></button></td>
												<td class="stylewidth"><a href="userdeptMapping?id=${crmuser.userId}&&firstname=${crmuser.firstName}"><button type="button" 
												class="btn btn-warning waves-effect waves-light"><i class="fe-plus"></i></button></a></td>
											</c:when>
											<c:otherwise>
												<td class="stylewidth"><a href="inactiveuser?id=${crmuser.userId}"><button type="button" onclick="return activeuser()"
												class="btn btn-danger waves-effect waves-light"><i class="fe-x-square"></i></button></a></td>
												<td class="stylewidth"><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default"><i class="mdi mdi-pencil"></i></button></td>
												<td class="stylewidth"><button type="button" class="btn btn-danger waves-effect waves-light" style="cursor:default"><i class="fe-plus"></i></button></td>
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
			</div>   <!-- content-page -->

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
     
<script>
	function adduser()
	{
		location.href = "adduser";
	}
	function inactiveuser()
	{		
		if(confirm('Do you want to inactive this user?'))
		{
			return true;
		}
		else
		{
			return false
		}
		
	}
	function activeuser()
	{		
		if(confirm('Do you want to active this user?'))
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