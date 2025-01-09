<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Soar Global Infotech CRM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="resources/assets/images/favicon.ico">

        <!-- App css -->
        <link href="resources/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/assets/css/app.min.css" rel="stylesheet" type="text/css" />
  <script>
  
  function setTimezone()
  {
  	var date = new Date();
  	var offset = date.getTimezoneOffset();
  	document.getElementById("tzinput").value = offset;
  }
  </script>
    </head>

    <body class="authentication-bg authentication-bg-pattern">
     <form:form id="loginForm" method="post" action="loginsubmit" modelAttribute="crmUser" > 
        <div class="account-pages mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 col-lg-6 col-xl-5">
                        <div class="card bg-pattern">

                            <div class="card-body p-4">
                                
                                <div class="text-center w-75 m-auto">

                                        <span><img src="resources/assets/images/L-3.svg" alt="" height="175"></span>
                                 
                                    
                                </div>
                                <br>
                                        <input type="hidden" id="tzinput" name="timezoneoffset" />
                                 <c:if test="${sessionScope.message != '' && sessionScope.message == 'Invalid credentials!!'}">
                  					<tr><h4><p style="color:Red"><b><c:out value="Invalid Credential!" /></b></p></h4></tr><br>
    								</c:if>    								
    							 <c:if test="${sessionScope.message == '' && sessionScope.message != 'Invalid credentials!!' &&
    							  sessionScope.flag == '0'}">
                  					<tr><h4><p style="color:Red"><b><c:out value="You are not authorised to access!" /></b></p></h4></tr><br>
    								</c:if>
    							 <c:if test="${sessionScope.flag == '1' && sessionScope.Frompage != ''}">
                                 <div class="form-group mb-3">
                                 	<tr><h5><p style="color:Red"><b><c:out value="Welcome SuperUser! Please Enter your High security Password!" /></b></p></h5></tr><br>
                                 </div>
                                </c:if>
                                
                                <div class="form-group mb-3">
                                    <label for="loginName">Login Name</label>
                                       <input class="form-control" type="text" id="loginName" name="loginName" required value="${crmUser.loginName}"
                                       placeholder="Enter your Login Name" autofocus>
                                 </div>

                                 <div class="form-group mb-3">
                                     <label for="password">Password</label>
                                     <input class="form-control" type="password" required id="password" value="${crmUser.password}"
                                      name="password" placeholder="Enter your password">
                                 </div>
                                 
                                 <c:if test="${sessionScope.flag == '1'}">
                                 <div class="form-group mb-3">                                 	
                                     <label for="password">High security Password</label>
                                     <input class="form-control" type="password" required id="password" name="password2" placeholder="Enter your password">
                                 </div>
                                 </c:if>

                              <!--    <div class="form-group mb-3">
                                     <div class="custom-control custom-checkbox">
                                         <input type="checkbox" class="custom-control-input" id="checkbox-signin" checked>
                                         <label class="custom-control-label" for="checkbox-signin">Remember me</label>
                                     </div>
                                 </div>  -->
                                 
                                  <div class="form-group mb-0 text-center">
                                      <button class="btn btn-primary btn-block" type="submit" onclick="setTimezone();"> Log In </button>
                                  </div>
                            </div> <!-- end card-body -->
                        </div>
                        <!-- end card -->

                       <!--  <div class="row mt-3">
                            <div class="col-12 text-center">
                                <p> <a href="pages-recoverpw.html" class="text-blue-50 ml-1">Forgot your password?</a></p>
                               
                            </div>
                        </div> -->
                        <!-- end row -->

                    </div> <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- end container -->
        </div>

        </form:form>
        <!-- end page -->
        
        <footer class="footer footer-alt">
       			 <div class="row">
                       <div class="col-md-6">
                           2019 &copy; Allzone Management Solutions &nbsp;  
                       </div>
                       <div class="col-md-6">
                       Developed by<a href="www.znifa.com" class="text-white-50">Znifa Technologies</a> 
                       </div>
            	</div>
            	
        </footer>

        <!-- Vendor js -->
        <script src="resources/assets/js/vendor.min.js"></script>

        <!-- App js -->
        <script src="resources/assets/js/app.min.js"></script>
        
    </body>
</html>