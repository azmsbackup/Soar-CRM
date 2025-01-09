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
                                            <li class="breadcrumb-item active">City</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage City</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                   <!--  <h4 class="header-title">Manage City</h4> -->
                                  
                                    <form:form action="" method="post" modelAttribute="city" class="parsley-examples" >
                                     <b><c:out value="State Id : ${city.stateId}"></c:out></b>
                                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                                     <b><c:out value="State Name : ${city.stateName}"></c:out></b>
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
                                           	<th style="color:#000000" data-field="id" data-sortable="true">City Id</th>      
                                             <th style="color:#000000" data-field="name" data-sortable="true">City Name</th>                                                                                
                                        </tr>
                                        </thead>

                                        <tbody>                                        
											<c:forEach var="cities" items="${city.citylist}">
											<tr>	
												<td>${cities.id}</td>											
												<td>${cities.name}</td>																																 
											</tr>
									</c:forEach> 
                                        
                                        </tbody>
                                    </table>
                                   <center><a href="managestate?id=${city.countryId}&&name=${city.countryName}"><button type="button" class="btn btn-danger width-xl waves-effect waves-light" >Back</button></a></center>
                                   </form:form>
                                </div> <!-- end card-box-->
                            </div> <!-- end col-->
                        </div>
                        <!-- end row-->
                       

                        
                    </div> <!-- container -->

                </div> <!-- content -->
			</div> <!-- content-page  -->

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
<!-- <script>
	function back()
	{
		location.href = "managestate";
	}
</script> -->
 <%@include file="crm-footer.jsp" %>