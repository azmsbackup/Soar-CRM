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
                                            <li class="breadcrumb-item active">State</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage State</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                    <!-- <h4 class="header-title">Manage State</h4> --> 
                                    <form:form action="" method="post" modelAttribute="state" class="parsley-examples" >  
                                   <b> <c:out value="Country Id : ${state.countryId}"/></b> 
                                   &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; 
                                   <b> <c:out value="Country Name : ${state.countryName}"/></b>                               
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
                                        	 <th data-field="id" data-sortable="true">State Id</th>                                              
                                             <th data-field="name" data-sortable="true">State Name</th>                                                                                   
                                            <th data-field="status" data-align="center">View City</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="states" items="${state.statelist}">
											<tr>
												<td>${states.id}</td>												
												<td>${states.name}</td>																																
												<td><a href="managecity?id=${states.id}&&countryid=${state.countryId}&&stateName=${states.name}&&countryName=${state.countryName}"><button type="button" class="btn btn-success waves-effect waves-light"><i class="icon-eye"></i></button></a></td> 
											</tr>
									</c:forEach> 
                                        </tbody>
                                    </table>
                                     <center><button type="button" class="btn btn-danger width-xl waves-effect waves-light" onclick="return back()">
                                                Back
                                            </button></center>
                                    </form:form>
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
	function back()
	{
		location.href = "managecountry";
	}
</script>
 <%@include file="crm-footer.jsp" %>