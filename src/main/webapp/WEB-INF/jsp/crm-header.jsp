<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>SoarGobalInfotech CRM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="resources/assets/images/favicon.ico">
        
       <link rel="stylesheet" type="text/css" href="resources/assets/libs/datatables1/jquery.dataTables.min.css">
       <link rel="stylesheet" type="text/css" href="resources/assets/libs/datatables1/buttons.dataTables.min.css">
        
         <!-- Bootstrap Tables css -->
        <link href="resources/assets/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
        <!-- Plugin css -->
        <link href="resources/assets/libs/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />

                <!-- Footable css -->
        <link href="resources/assets/libs/footable/footable.core.min.css" rel="stylesheet" type="text/css" />
        
        <!-- Plugins css -->
        <link href="resources/assets/libs/dropzone/dropzone.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/dropify/dropify.min.css" rel="stylesheet" type="text/css" />
        
          

       
        <!-- App css -->
        <link href="resources/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/css/app.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/css/style.css" rel="stylesheet" type="text/css" />
        
         <!-- Summernote css -->
        <link href="resources/assets/libs/summernote/summernote-bs4.css" rel="stylesheet" />
        
        <link href="resources/assets/libs/flatpickr/flatpickr.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/clockpicker/bootstrap-clockpicker.min.css" rel="stylesheet" type="text/css" />
        
           <!-- Plugins css -->
      
        <!-- Plugins css -->
        <link href="resources/assets/libs/jquery-nice-select/nice-select.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/switchery/switchery.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/multiselect/multi-select.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/select2/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.css" rel="stylesheet" type="text/css" />

    </head>
    <script>

document.onreadystatechange = function () {
	  var state = document.readyState
	  if (state == 'complete') {
	      setTimeout(function(){
	          document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	      },100);
	  }
	}

</script>
			
<style>

#load{
    width:100%;
    height:100%;
    position:fixed;
    z-index:9999;
    background:url("resources/assets/images/loader7.gif") no-repeat center center rgba(0,0,0,0.25)
}


       </style>
        <body>

        <!-- Begin page -->
        <div id="wrapper">
    
            <!-- Topbar Start -->
            <div class="navbar-custom">
                       <ul class="list-unstyled topnav-menu float-right mb-0">
                       <li class="dropdown notification-list">
                        <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light" data-toggle="dropdown" href="crm-dashboard" role="button" aria-haspopup="false" aria-expanded="false">
                            <img src="resources/assets/images/LL-7.jpg" alt="user-image" class="rounded-circle">
                         </a>
                       </li>
                     </ul>
                     
                 <!-- LOGO -->
                <div class="logo-box">                	 
                   	 	<a href="crm-dashboard" class="logo text-center">                   	
                        <span class="logo-lg">
                            <img src="resources/assets/images/logoheader.jpg" alt="" height="70" width="240">
                            <!-- <span class="logo-lg-text-light">UBold</span> -->
                        </span>
                        <span class="logo-sm">
                            <!-- <span class="logo-sm-text-dark">U</span> -->
                            <img src="resources/assets/images/LL-7.jpg" alt="" height="60" width="50">
                        </span>
                    </a>                    
                </div>

                <ul class="list-unstyled topnav-menu topnav-menu-left m-0">
                    <li>
                        <button class="button-menu-mobile waves-effect waves-light">
                            <i class="fe-menu"></i>
                        </button>
                    </li>
                    <li>
                    	<h4 style="color:white;padding-top:15px">Welcome ${sessionScope.loggedInUserName}</h4>
                    </li>
                    
             
               
                  </ul>
                </div>
            <!-- end Topbar -->
<%@include file="crm-leftbar.jsp" %>
<div id="load"></div>