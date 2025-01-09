<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>

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
								<li class="breadcrumb-item active">Response and Leads</li>
							</ol>
						</div>
						<h4 class="page-title">Response and Leads</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<div class="row">

				<div class="col-lg-12">

					<div class="card-box">
						<div class="col-lg-12">
							<div class="row">
								<div class="col-12">
									<div class="card">
										<div class="card-body">
											<table id="datatable-buttons" class="table table-striped dt-responsive nowrap">
												<thead class="thead-light">
													<tr>
														<%@page import="java.util.Date"%>
														<%@page import="java.util.Calendar"%>
														<%@page import="java.util.Locale"%>
														<%pageContext.setAttribute("currentYear", java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));%>
														<%pageContext.setAttribute("currentMonth", java.util.Calendar.getInstance().getDisplayName(java.util.Calendar.
																MONTH, Calendar.LONG, Locale.ENGLISH));%>
														<th data-field="traceNo" data-sortable="true">User Name</th>
														<th data-field="clientName" data-sortable="true">Respone Year - <c:out value="${currentYear}" /></th>
														<th data-field="phoneNumber" data-sortable="true">Response Month - <c:out value="${currentMonth}" /></th>
														<th data-field="email" data-sortable="true">Closed Year - <c:out value="${currentYear}" /></th>
														<th data-field="departmentName" data-sortable="true">Closed	Month - <c:out value="${currentMonth}" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="response" items="${responseandLeads}">
														<tr>
															<td>${response.key}</td>
															<c:forEach var="responseclose" items="${response.value}">
																<td>${responseclose.response_year}</td>
																<td>${responseclose.response_month}</td>
																<td>${responseclose.closed_year}</td>
																<td>${responseclose.closed_month}</td>
															</c:forEach>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
										<!-- end card body-->
									</div>
									<!-- end card -->
								</div>
								<!-- end col-->
							</div>
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
<!-- content-page -->


<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->


<%@include file="crm-footer.jsp"%>