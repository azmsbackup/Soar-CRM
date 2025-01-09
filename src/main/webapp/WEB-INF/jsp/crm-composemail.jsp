<%@include file="crm-header.jsp" %>
<script src="resources/assets/js/jquery-1.4.2-jquery.min.js"></script>  
<script src="resources/assets/js/jqueryui-1.8.9-jquery-ui.js"></script> 
<script src="resources/assets/js/jquery-1.11.3.min.js"></script> 
<script src="resources/assets/js/jquery-2.1.4.min.js"></script>        

<style>
.morecontent span {
    display: none;
}
.morelink {
    display: block;
}
</style>

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
                                            <li class="breadcrumb-item active">Compose</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Email</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        <!-- Right Sidebar -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card-box">
                                    <!-- Left sidebar -->
                                     <form:form action="emailsent" method="post" modelAttribute="mail" enctype="multipart/form-data" class="parsley-examples" > 
                                     <form:hidden path="traceNo" value="${mail.traceNo}"/>
                                   
                                    <div class="inbox-leftbar">
										<br>
										<a href="composemail?id=${mail.traceNo}&&email=${mail.recipient}" class="btn btn-danger btn-block waves-effect waves-light">Compose</a>
										<br>										
											<c:choose>
											<c:when test="${mail.mailDetailsList.size() > 0}">
											<c:forEach var="email" items="${mail.mailDetailsList}" varStatus="loop" >	
											 <form:hidden path="hiddenFrom" id="hiddenFrom${loop.index+1}" value="${email.from}"/>
		                                     <form:hidden path="hiddenRecipient" id="hiddenRecipient${loop.index+1}" value="${email.recipient}"/>
		                                     <form:hidden path="hiddenSubject" id="hiddenSubject${loop.index+1}" value="${email.subject}"/>
		                                     <form:hidden path="hiddenContent" id="hiddenContent${loop.index+1}" value="${email.content}"/>
		                                      <form:hidden path="hiddenCc" id="hiddenCc${loop.index+1}" value="${email.cc}"/>
												 <ul class="message-list">
                                                <li class="unread">
                                                   <div class="col-mail col-mail-1" onClick="btnclick(${loop.index+1})">
                                                        
                                                            ${loop.index+1}) &nbsp;
                                                            <c:choose>
                                                            <c:when test="${email.subject !='' && email.subject !=null}">
                                                            	<label id="btn${loop.index+1}" class="more">${email.subject}</label>
                                                            </c:when>
                                                            <c:otherwise>
                                                            	<label id="btn${loop.index+1}"  class="more">No subject</label>                                                            
                                                            </c:otherwise>
                                                            </c:choose>                                                          
                                                       
                                                        	
                                                         <%-- <a href="composemail?id=${email.traceNo}" class="title">${email.subject} </a> --%>                                                      
                                                    </div>
                                                </li>
                                               </ul>
                                        </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                        	<br>
											<div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
			                                	<span class="badge badge-pill badge-danger">No Data</span>
			                                    No Email Exist!			                                  		
	                         				</div>
                                        </c:otherwise>
                                        </c:choose>
                                        <!-- <div class="mail-list mt-4">
                                            <a href="viewemail" class="list-group-item border-0 text-danger font-weight-bold"><i class="mdi mdi-inbox font-18 align-middle mr-2"></i>Inbox<span class="badge badge-danger float-right ml-2 mt-1">8</span></a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-star font-18 align-middle mr-2"></i>Starred</a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>Draft<span class="badge badge-info float-right ml-2 mt-1">32</span></a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-send font-18 align-middle mr-2"></i>Sent Mail</a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-delete font-18 align-middle mr-2"></i>Trash</a>
                                        </div>

                                        <h6 class="mt-4">Labels</h6>

                                        <div class="list-group b-0 mail-list">
                                            <a href="#" class="list-group-item border-0"><span class="mdi mdi-circle text-info mr-2"></span>Web App</a>
                                            <a href="#" class="list-group-item border-0"><span class="mdi mdi-circle text-warning mr-2"></span>Recharge</a>
                                            <a href="#" class="list-group-item border-0"><span class="mdi mdi-circle text-dark mr-2"></span>Wallet Balance</a>
                                            <a href="#" class="list-group-item border-0"><span class="mdi mdi-circle text-primary mr-2"></span>Friends</a>
                                            <a href="#" class="list-group-item border-0"><span class="mdi mdi-circle text-success mr-2"></span>Family</a>
                                        </div> -->

                                    </div>
                                    <!-- End Left sidebar -->

                                    <div class="inbox-rightbar">
                                        <div class="mt-4"> 
                                        	                                         
                                                <div class="form-group" >
                                                    <input type="text" class="form-control" value="${mail.recipient}" name="recipient" id="recipient" placeholder="To">
                                                </div>
                                                
                                                 <div class="form-group" >
                                                    <input type="text" class="form-control" value="${mail.cc}" name="cc" id="cc" placeholder="Cc">
                                                </div>
                                                   
                                                <div class="form-group">
                                                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject">
                                                </div>
                                          
                                                <div class="form-group" id="view">
                                     				<textarea class="summernote" id="content"  name="content" rows="18"></textarea>                                     				
                                                </div>
                                            
                                                 
                                                <div class="form-group" id="showcontent" style="display:none;border:1px solid grey;" >                                               
                                     				<!-- <textarea id="showcontent" rows="18" style="width:960px;"></textarea> -->
                                     			 </div>
                                                
                                                <div class="form-group m-b-0">
                                                    <div class="text-right">
                                                        <button type="submit" id="submitbtn" class="btn btn-primary waves-effect waves-light"><span>Send</span> 
                                                        <i class="mdi mdi-send ml-2"></i></button>
                                                    </div>
                                                </div>
        
                                            
                                        </div> <!-- end card-->
            
                                    </div> 
                                    <!-- end inbox-rightbar-->
									</form:form>
									<center> <button type="submit" class="btn btn-danger waves-effect m-l-5" id="cancel">Back</button></center>
                                    <div class="clearfix"></div>
                                </div>

                            </div> <!-- end Col -->

                        </div><!-- End row -->
                        
                    </div> <!-- container -->

                </div> <!-- content -->
               </div>

              


            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->
   <script>
            jQuery(document).ready(function(){
                $('.summernote').summernote({
                    height: 230,                 // set editor height
                    minHeight: null,             // set minimum height of editor
                    maxHeight: null,             // set maximum height of editor
                    focus: false ,                // set focus to editable area after initializing summernote
                });
                    
                var showChar = 20; 
                var ellipsestext = "...";

                    $('.more').each(function() 
                    {
                        var content = $(this).html();                 
                        if(content.length > showChar) 
                        {
                 
                            var c = content.substr(0, showChar);
                            var h = content.substr(showChar, content.length - showChar);
                 
                            var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span>';
                 
                            $(this).html(html);
                        }
                 
                    });
                
                
            });
            $("#submitbtn").click(function()
            {
            		var recipient = $("#recipient").val();            		
            		var subject = $("#subject").val();
            		
            		if(recipient == "")
            		{
            			alert("Please Enter a Recipient's Address!");
            			$("#recipient").focus();
            			return false;
            			
            		}
            		if(subject == "")
            		{
            			if(confirm('Do you want to send without a Subject of this mail ?'))
            			{
            				return true;
            			}
            			else
            			{
            				return false
            			}
            		}
            });
            $("#cancel").click(function()
        	{
        		location.href = "manageclient";
        	});
            
        </script> 
        
 <script>
 
 
 
 function btnclick(loopid)
 {
   	 var from = document.getElementById("hiddenFrom"+loopid).value;
   	 var to = document.getElementById("hiddenRecipient"+loopid).value;
   	 var subject = document.getElementById("hiddenSubject"+loopid).value;
   	 var content = document.getElementById("hiddenContent"+loopid).value;
   	 var cc = document.getElementById("hiddenCc"+loopid).value;
   	
   	document.getElementById("recipient").value = to;
   	document.getElementById("subject").value = subject;
   //	document.getElementById("showcontent").value = content;
   document.getElementById("showcontent").innerHTML = content;
   	document.getElementById("cc").value = cc;
   	
   	//document.getElementById("submitbtn").disabled = true;
   	document.getElementById('submitbtn').style.visibility = 'hidden';
   	
   //	document.getElementById('content').removeClass('summernote');
   	
   	document.getElementById("view").style.display = "none";   	
	document.getElementById("showcontent").style.display = "block";
	
	document.getElementById('recipient').readOnly = true;
	document.getElementById('subject').readOnly = true;
	document.getElementById('showcontent').readOnly = true;
	

	document.getElementById('showcontent').html();
	 
 }
 
 
 </script>         
      
 <%@include file="crm-footer.jsp" %>