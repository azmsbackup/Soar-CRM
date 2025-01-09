<%@include file="crm-header.jsp" %>
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
                                            <li class="breadcrumb-item active">Read mail</li>
                                        </ol>
                                    </div>
                                    <h4 class="page-title">Email Read</h4>
                                </div>
                            </div>
                        </div>     
                        <!-- end page title --> 

                        <!-- Right Sidebar -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card-box">
                                <form:form action="emailreadsent" method="post" modelAttribute="mail" class="parsley-examples" > 
                                    <!-- Left sidebar -->
                                    <div class="inbox-leftbar">

                                        <a href="composemail" class="btn btn-danger btn-block waves-effect waves-light">Compose</a>
										<br>
                                        <div class="mail-list mt-4">
                                            <a href="viewemail" class="list-group-item border-0 text-danger font-weight-bold"><i class="mdi mdi-inbox font-18 align-middle mr-2"></i>Inbox<span class="badge badge-danger float-right ml-2 mt-1">8</span></a>
                                            <a href="sentemail" class="list-group-item border-0"><i class="mdi mdi-send font-18 align-middle mr-2"></i>Sent Mail</a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>Unread Mails</a>
                                          <!--   <a href="#" class="list-group-item border-0"><i class="mdi mdi-star font-18 align-middle mr-2"></i>Starred</a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-file-document-box font-18 align-middle mr-2"></i>Draft<span class="badge badge-info float-right ml-2 mt-1">32</span></a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-send font-18 align-middle mr-2"></i>Sent Mail</a>
                                            <a href="#" class="list-group-item border-0"><i class="mdi mdi-delete font-18 align-middle mr-2"></i>Trash</a> -->
                                        </div>

                                        <!-- <h6 class="mt-4">Labels</h6>

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

                                        <div class="mt-4">
                                            <h5 class="font-18">${mail.subject}</h5>

                                            <hr/>

                                            <div class="media mb-4 mt-1">
                                                <!-- <img class="d-flex mr-2 rounded-circle avatar-sm" src="assets/images/users/user-2.jpg" alt="Generic placeholder image"> -->
                                                <div class="media-body">
                                                    <span class="float-right">${mail.time}</span>
                                                    
                                                    <h5 class="font-15">From: ${mail.from}</h5>
                                                </div>
                                            </div>

                                           
                                            <hr/>

                                            <!-- <h6> <i class="fa fa-paperclip mb-2"></i> Attachments <span>(3)</span> </h6>

                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <a href="#"> <img src="assets/images/attached-files/img-1.jpg" alt="attachment" class="img-thumbnail img-responsive"> </a>
                                                </div>
                                                <div class="col-sm-2 mt-3 mt-sm-0">
                                                    <a href="#"> <img src="assets/images/attached-files/img-2.jpg" alt="attachment" class="img-thumbnail img-responsive"> </a>
                                                </div>
                                                <div class="col-sm-2 mt-3 mt-sm-0">
                                                    <a href="#"> <img src="assets/images/attached-files/img-3.jpg" alt="attachment" class="img-thumbnail img-responsive"> </a>
                                                </div>
                                            </div> -->

                                        </div> <!-- card-box -->

                                        <!-- <div class="media mb-0 mt-5">
                                            <img class="d-flex mr-3 rounded-circle avatar-sm" src="assets/images/users/user-7.jpg" alt="Generic placeholder image">
                                            <div class="media-body">
                                                <div class="mb-2"> -->
                                                     <div class="form-group">
                                     				<textarea class="summernote" id="content" name="content" rows="18"></textarea>
                                                </div>
                                                <!-- </div> end reply-box
                                            </div> end media-body
                                        </div> end medi -->

                                        <div class="text-right">
                                            <button type="submit" class="btn btn-primary btn-rounded width-sm">Send</button>
                                        </div>

                                    </div> 
                                    <!-- end inbox-rightbar-->

                                    <div class="clearfix"></div>
                                    </form:form>
                                </div>
                               

                            </div> <!-- end Col -->

                        </div><!-- End row -->

                        
                    </div> <!-- container -->

                </div> <!-- content -->

                <!-- Footer Start -->
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                2015 - 2018 &copy; UBold theme by <a href="">Coderthemes</a> 
                            </div>
                            <div class="col-md-6">
                                <div class="text-md-right footer-links d-none d-sm-block">
                                    <a href="javascript:void(0);">About Us</a>
                                    <a href="javascript:void(0);">Help</a>
                                    <a href="javascript:void(0);">Contact Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </footer>
                <!-- end Footer -->

          </div>

        <script>
            jQuery(document).ready(function(){
                $('.summernote').summernote({
                    height: 230,                 // set editor height
                    minHeight: null,             // set minimum height of editor
                    maxHeight: null,             // set maximum height of editor
                    focus: false                 // set focus to editable area after initializing summernote
                });
            });
        </script>
 <%@include file="crm-footer.jsp" %>