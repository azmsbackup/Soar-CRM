<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>
<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->

<style>
.morecontent span {
	display: none;
}

.morelink {
	display: block;
}

div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
    }
.wrapword {
    white-space: -moz-pre-wrap !important;  /* Mozilla, since 1999 */
    white-space: -webkit-pre-wrap;          /* Chrome & Safari */ 
    white-space: -pre-wrap;                 /* Opera 4-6 */
    white-space: -o-pre-wrap;               /* Opera 7 */
    white-space: pre-wrap;                  /* CSS3 */
    word-wrap: break-word;                  /* Internet Explorer 5.5+ */
    word-break: break-all;
    white-space: normal;
}
</style>

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
								<li class="breadcrumb-item active">Remove Duplicates</li>
							</ol>
						</div>
						<h4 class="page-title">Remove Duplicates</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->
						<div class="row">

				<div class="col-lg-12">

					<div class="card-box">

						<div class="col-lg-12">

							<form:form action="searchKeySubmit" method="post" modelAttribute="client" class="parsley-examples">
								<div class="row row-grid">
									<div class="col-md-4">
										<div class="form-group">
                                            <label >Find Duplicates By: </label>
                                            <form:select class="form-control" id="searchKey" path="searchKey">
                                         		<form:option value="" label="--Select --"/>
												<form:option value="Client_Name">Client Name</form:option>
												<form:option value="phone_no">Phone Number</form:option>												
												<form:option value="e_mail">Email Address</form:option>   
												<form:option value="website">WebSite</form:option>                                                        
                                             </form:select>
                                        </div>									
									</div>
									
									<div class="col-md-2"> 
									 <label for=""><span class="" style="color:white;">.</span></label>   
									  <div class="form-group m-t-4""><a><button type="submit" onclick="return validate()" class="btn btn-success waves-effect waves-light m-t-4">
								Search</button></a>	</div></div>
									
									
								</div>
	
								<c:choose>
									<c:when test="${allclientList.size() > 0 }">
										<div class="row">
											<div class="col-12">
									
													<table id="demo-custom-toolbar"  data-toggle="table"
                                           data-toolbar="#demo-delete-row"
                                           data-search="true"
                                           data-show-refresh="true"
                                           data-show-columns="true"
                                           data-sort-name="id"
                                           data-page-list="[10, 50, 100, 250, 300, 350, 400, 450, 500]"
                                           data-page-size="10"
                                           data-pagination="true" data-show-pagination-switch="true" class="table table-striped dt-responsive nowrap">
											
														
														<thead class="thead-light">
	                                        			<tr>
	                                        				
	                                        				<th  style="text-align:center" data-field="traceNo" data-sortable="true">Trace No</th>                                            
	                                             			<th  style="text-align:center" data-field="clientName" data-sortable="true">Client Name</th>
	                                             	        <th  style="text-align:center" data-field="phoneNumber" data-sortable="true">Phone Number</th>                                             			 
	                                             			<th  style="width:20px;text-align:center" data-field="email" data-sortable="true">Email Address</th> 
	                                           				<th  style="width:20px;text-align:center" data-field="website" data-sortable="true">Website</th> 
	                                           				<th  style="text-align:center" data-field="delete" data-sortable="false">Delete</th>     
	                                           				<!-- <th  style="width:20px;text-align:center" data-field="username" data-sortable="true">Account Owner</th> -->
	                                             			                                     			                                                                               
	                                        			</tr>
	                                       			 </thead>
	                                       			 
													<tbody>
														<c:forEach var="client" items="${allclientList}">
															<tr>
																
																<td style="text-align:right">${client.traceNo}</td>
																<td style="text-align:center">${client.clientName}</td>	
												
																<td nowrap style="text-align:center" class="phoneNo" id="phoneNumber" data-toggle="input-mask" data-mask-format="000-000-0000">${client.phoneNumber}</td>
																<td class="wrapword" style="width:20px;text-align:center">${client.email}</td>
																<td class="wrapword" style="width:20px;text-align:center" >${client.website}</td>
																<td> <a onclick="return deleteDuplicate(${client.traceNo})"  class="btn btn-xs btn-danger"><i class="mdi mdi-trash-can-outline" title="Remove" style="color:white"></i></a></td>	
																<%-- <td style="width:20px;text-align:center">${client.username}</td> --%>
																																												
															</tr>
														</c:forEach>
													</tbody>
												</table>
		
													
											</div>
											<!-- end col-->
										</div>
									</c:when>
									<c:otherwise>								
										<c:if test="${client.frompage != 'Initial'}">
											<br>
											<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
			                                	<span class="badge badge-pill badge-danger">No Data</span>
			                                    No data exist!
			                                  	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			                                    	<span aria-hidden="true">&times;</span>
			                                	</button>
			                         		</div>
										</c:if>						
									</c:otherwise>
								</c:choose>
							</form:form>
						</div>
						</div>
					</div>
					<!-- end card-box -->
				</div>
				<!-- end col -->


			</div>
			<!-- end row -->
		</div>
		<!-- container -->

	</div>
	<!-- content -->
</div>
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->



<script>
function validate()
{
	var searchKey = document.getElementById("searchKey").value;		
	
	
	if(searchKey == "")
	{
		alert("Please atleast select one search criteria to proceed.");
		document.getElementById("searchKey").focus();
		return false;
	}
	
	
}


function deleteDuplicate(traceNo)
{
	//alert("traceNo :"+traceNo);
	var searchKey =  document.getElementById("searchKey").value;		
	location.href = "deleteDuplicate?id="+traceNo+"&&key="+searchKey;
}
</script>


<%@include file="crm-footer.jsp"%>