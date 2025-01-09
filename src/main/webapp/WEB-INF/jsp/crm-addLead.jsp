<%@include file="crm-header.jsp"%>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script>
<script src="resources/assets/js/jquery-1.11.3.min.js"></script>
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>
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
								<li class="breadcrumb-item active">Add Lead</li>
							</ol>
						</div>
						<h4 class="page-title">Add Lead</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card-box">
						<div class="col-lg-10">
							<form:form modelAttribute="lead" action="insertLead"
								method="post" class="parsley-examples">
								<div id="derror">
									<c:if
										test="${lead.getValidFlag() == 'P'||lead.getValidFlag() == 'E'||lead.getValidFlag() == 'C'}">
										<div>
											<p class="text-danger">
												<c:out value="${lead.logDescription}" />
											</p>
										</div>
									</c:if>
								</div>
								<input type="hidden" id="mailflag" name="validFlag"
									value="${lead.getValidFlag()}" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">Company Name<span
												class="text-danger">*</span></label> <input type="text"
												name="companyName" parsley-trigger="change"
												value="${lead.companyName}" placeholder="Enter Company Name"
												maxlength="100" class="form-control" id="companyName">
										</div>
										<div class="form-group">
											<label for="address">Contact First Name<span
												class="text-danger">*</span></label> <input type="text"
												name=conFirstName value="${lead.conFirstName}"
												placeholder="Enter Contact First Name" maxlength="45"
												class="form-control charactersOnly" id="conFirstName">
										</div>
										<div class="form-group">
											<label for="address">Contact Last Name</label> <input
												type="text" name="conLastName" value="${lead.conLastName}"
												placeholder="Enter Contact Last Name" maxlength="45"
												class="form-control charactersOnly" id="conLastName">
										</div>
										<div id="form-group">
											<label for="Comments">Comments</label>
											<textarea rows="3" name="comments"
												placeholder="Enter Comments"
												maxlength="500" class="form-control" id="leadComments">${lead.comments}</textarea>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="countriesId">Source<span
												class="text-danger">*</span></label>
											<form:select type="select" path="sourceId"
												class="form-control" id="sourceId">
												<form:option value="" label="--Select--" />
												<form:options items="${lead.sourceidlist}"
													itemValue="sourceId" itemLabel="sourceDescription" />
											</form:select>
										</div>
										<div class="form-group">
											<label for="mobileNumber">Phone Number<span
												class="text-danger"></span></label> <input type="text"
												name="phoneNumber" data-toggle="input-mask"
												data-mask-format="000-000-0000" value="${lead.phoneNumber}"
												placeholder="Enter Phone Number" maxlength="12"
												class="form-control phoneNo" id="phoneNumber">
										</div>

										<div id="form-group">
											<label for="email">Email<span class="text-danger"></span></label>
											<input type="email" parsley-trigger="change" name="email"
												value="${lead.email}" placeholder="Enter Email"
												maxlength="45" class="form-control" id="email">
										</div>
										
									</div>
								</div>
								<div class="form-group text-right m-b-0">
									<button id="sbutton"
										class="btn btn-primary waves-effect waves-light" type="submit"
										onclick="return leadValidate()">Submit</button>
									<button type="reset" class="btn btn-danger waves-effect m-l-5"
										onclick="return cancel()">Back</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
function cancel(){
	location.href = "manageLead";
}


$(document).ready(function(){	
	if($("#mailflag").val() == 1){
		$("#sbutton").hide();
	}
	
	var countryId =$("#countriesId").val();
	
	if(countryId =='231'){
		$('input[name="phoneNumber"]').mask('000-000-0000');
	}
	else{
		$('input[name="phoneNumber"]').mask('0000000000');
	}

})



</script>
<script src="resources/assets/js/common.js" /></script>
<script src="resources/assets/js/addLeadValidation.js" /></script>
<%@include file="crm-footer.jsp"%>