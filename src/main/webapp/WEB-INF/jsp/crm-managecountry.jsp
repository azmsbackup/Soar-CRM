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
                                            <li class="breadcrumb-item active">Country</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Manage Country</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">
                                   <!--  <h4 class="header-title">Manage Country</h4> -->

                                   
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
                                           
                                             <th style="color:#000000" data-field="id" data-sortable="true">Country Id</th>
                                            <th style="color:#000000" data-field="countryname" data-sortable="true">Country Name</th>
                                            <th style="color:#000000" data-field="sortname" data-sortable="true">Sort Name</th> 
                                            <th style="color:#000000" data-field="name" data-sortable="true">Phone code</th>                                         
                                            <th style="color:#000000" data-field="status" data-align="center">View State</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        	<c:forEach var="country" items="${countryList}">
											<tr>	
												<td>${country.id}</td>										
												<td>${country.name}</td>
												<td>${country.sortname}</td>
												<td>${country.phonecode}</td>																					
												<td><a href="managestate?id=${country.id}&&name=${country.name}"><button type="button" class="btn btn-success waves-effect waves-light"><i class="icon-eye"></i></button></a></td> 
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
 <%@include file="crm-footer.jsp" %>