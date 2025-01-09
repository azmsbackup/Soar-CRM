<%@include file="crm-header.jsp" %>
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
                                            <li class="breadcrumb-item active">Mapping</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">User - Department Mapping</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        
                   <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box">                                   
                                    <form:form class="form-horizontal parsley-examples" action="userdeptMappingsubmit" method="post" modelAttribute="department">
                                    <form:hidden path="userId" value="${department.userId}" />
                                    <form:hidden path="size" id="size" value="${department.usermappingList.size()}" /> 
                                    <div class="row">
                                        <div class="col-md-6">
                                        	<div class="form-group">
		                                            <label for="traceNo"><b>User Name</b> &nbsp; &nbsp; <b>:</b></label>
		                                             <label for="userName"> &nbsp; &nbsp;<b>${department.userName}</b></label>	                                             
		                               </div> 		                              
		                              <%--  <div class="form-group">            
		                                   <c:forEach var="dept" items="${department.departmentList}">		                                             
		                                      <div class="row">
		                                      	<input type="text" name="checkbox" value="${dept.departmentName}" id="checkbox" class="form-check-input testdept">
		                                     </div>
		                                    </c:forEach>
                                       	</div>  --%>
                                       	
                             <table class="table table-striped table-bordered"  >
                    			<thead>
                     				  <tr>
										<th>Select All&nbsp;&nbsp;
										<input type="checkbox" id="checkAll"/><br></th>	
										<th>Department Name</th>											        								
                     				 </tr>
                    			</thead>
								<tbody>									
									<c:forEach var ="dept" items="${department.usermappingList}">
									<tr><td><c:choose>									
							  			<c:when test="${dept.getChecked() == 'true'}" >
										 	<input type="checkbox" checked="checked" name="departmentId"  class="deptchecked" value="${dept.departmentId}"/>
										 </c:when>
										 <c:otherwise>
										 	<input type="checkbox" name=departmentId  value="${dept.departmentId}" class="testdept"/>
										 </c:otherwise>										 
									</c:choose></td>
										<td>${dept.departmentName}</td></tr>
									</c:forEach>									
					 			</tbody>
                    
                  			</table>              			
                  			               
                                     
                  			<center> <button type="submit" class="btn btn-success btn-sm"  value="apply" name="apply">
                  			<i class="fa fa-dot-circle-o"></i>Apply</button>
                  			<button type="button" class="btn btn-danger btn-sm" onclick="return back()" >Back</button>   </center>            			
                  			
                                      </div> 
                                       	</div>                                                        
									
                                 </form:form>
                                  </div>
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
/* $("#checkAll").click(function () {
    $('.testdept').not(this).prop('checked', this.checked);
}); */

function back()
{
	location.href = "manageuser";	
}

$(document).ready(function()
{
	  var listSize = $("#size").val();
	  if($('.deptchecked:checked').length == listSize)
	  {
          $('#checkAll').prop('checked',true);
      }
      else
      {
          $('#checkAll').prop('checked',false);
      }
	  
    $('#checkAll').on('click',function()
    {
        if(this.checked)
        {
            $('.testdept').each(function()
            {
                this.checked = true;
            });
            $('.deptchecked').each(function()
            {
                this.checked = true;
            });
        }
        else
        {
            $('.testdept').each(function()
            {
                this.checked = false;
            });
            $('.deptchecked').each(function()
            {
                 this.checked = false;
             });
        }
    });
    
    $('.testdept').on('click',function()
    {
        if($('.testdept:checked').length == $('.testdept').length)
        {
            $('#checkAll').prop('checked',true);
        }
        else
        {
            $('#checkAll').prop('checked',false);
        }
    });
    $('.deptchecked').on('click',function()
    {
        if($('.deptchecked:checked').length == $('.deptchecked').length)
        {
            $('#checkAll').prop('checked',true);
        }else
        {
            $('#checkAll').prop('checked',false);
        }
    });
});


</script>
 <%@include file="crm-footer.jsp" %>