<%@include file="crm-header.jsp" %>

        <!-- Begin page -->
        <div id="wrapper">
            <!-- Left Sidebar End -->

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
                                            <li class="breadcrumb-item active">Inbox</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Inbox</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 


                        <div class="row">
                            <!-- Right Sidebar -->
                            <div class="col-12">
                                <div class="card-box">
                                	
                                    <!-- Left sidebar -->
                                    <div class="inbox-leftbar">
                                    <br>
                                        <a href="composemail" class="btn btn-danger btn-block waves-effect waves-light">Compose</a>

                                         <div class="mail-list mt-4">
                                            <a href="viewemail" class="list-group-item border-0 text-danger font-weight-bold"><i class="mdi mdi-inbox font-18 align-middle mr-2"></i>Inbox<span class="badge badge-danger float-right ml-2 mt-1">8</span></a>
                                             <a href="sentemail" class="list-group-item border-0"><i class="mdi mdi-send font-18 align-middle mr-2"></i>Sent Mail</a>
                                             <a href="unreademail" class="list-group-item border-0"><i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>Unread Mails</a>
                                          </div>
                                            <!--<a href="#" class="list-group-item border-0"><i class="mdi mdi-star font-18 align-middle mr-2"></i>Starred</a>
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
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-light waves-effect"><i class="mdi mdi-archive font-18"></i></button>
                                            <button type="button" class="btn btn-sm btn-light waves-effect"><i class="mdi mdi-alert-octagon font-18"></i></button>
                                            <button type="button" class="btn btn-sm btn-light waves-effect"><i class="mdi mdi-delete-variant font-18"></i></button>
                                        </div>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-light dropdown-toggle waves-effect" data-toggle="dropdown" aria-expanded="false">
                                                <i class="mdi mdi-folder font-18"></i>
                                                <i class="mdi mdi-chevron-down"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <span class="dropdown-header">Move to</span>
                                                <a class="dropdown-item" href="javascript: void(0);">Social</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Promotions</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Updates</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Forums</a>
                                            </div>
                                        </div>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-light dropdown-toggle waves-effect" data-toggle="dropdown" aria-expanded="false">
                                                <i class="mdi mdi-label font-18"></i>
                                                <i class="mdi mdi-chevron-down"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <span class="dropdown-header">Label as:</span>
                                                <a class="dropdown-item" href="javascript: void(0);">Updates</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Social</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Promotions</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Forums</a>
                                            </div>
                                        </div>

                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-light dropdown-toggle waves-effect" data-toggle="dropdown" aria-expanded="false">
                                                <i class="mdi mdi-dots-horizontal font-18"></i> More
                                                <i class="mdi mdi-chevron-down"></i>
                                            </button>
                                            <div class="dropdown-menu">
                                                <span class="dropdown-header">More Option :</span>
                                                <a class="dropdown-item" href="javascript: void(0);">Mark as Unread</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Add to Tasks</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Add Star</a>
                                                <a class="dropdown-item" href="javascript: void(0);">Mute</a>
                                            </div>
                                        </div>

                                        <div class="mt-3">
                                        	
                                            <ul class="message-list">
                                            	<c:forEach var="mail" items="${maillist}">
	                                                <li class="read">
	                                                    <div class="col-mail col-mail-1">
	                                                        <div class="checkbox-wrapper-mail">
	                                                            <input type="checkbox" id="chk1">
	                                                            <label for="chk1" class="toggle"></label>
	                                                        </div>
	                                                        <span class="star-toggle far fa-star text-warning"></span>
	                                                        <a href="emailread?subject=${mail.subject}&&time=${mail.time}&&recipient=${mail.recipient}" 
	                                                        class="title">${mail.from} </a>
	                                                    </div>
	                                                    <div class="col-mail col-mail-2">
	                                                    	<a href="emailread?subject=${mail.subject}&&time=${mail.time}&&recipient=${mail.recipient}" 
	                                                        class="subject"><span class="teaser">${mail.subject} </span>
	                                                        <div class="date"><c:out value="${mail.time}"/></div></a>
	                                                    </div>
	                                                </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <!-- end .mt-4 -->
                                        <div class="row">
                                            <div class="col-7 mt-1">
                                                Showing 1 - 20 of &nbsp; <c:out value=" ${maillist.size()}"/>
                                            </div> <!-- end col-->
                                            <div class="col-5">
                                                <div class="btn-group float-right">
                                                    <button type="button" class="btn btn-light btn-sm"><i class="mdi mdi-chevron-left"></i></button>
                                                    <button type="button" class="btn btn-info btn-sm"><i class="mdi mdi-chevron-right"></i></button>
                                                </div>
                                            </div> <!-- end col-->
                                        </div>
                                        <!-- end row-->
                                    </div> 
                                    <!-- end inbox-rightbar-->

                                    <div class="clearfix"></div>
                                </div> <!-- end card-box -->

                            </div> <!-- end Col -->
                        </div><!-- End row -->
                        
                    </div> <!-- container -->

                </div> <!-- content -->

            </div>

            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->


        </div>
        <!-- END wrapper -->
        
  
<%@include file="crm-footer.jsp" %>