<%@ page import="java.net.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.*" %>  
<%@ page import="java.net.InetAddress" %> 
<%    
    	String systemipaddress = ""; 
        try
        { 
            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
  
            BufferedReader sc = 
            new BufferedReader(new InputStreamReader(url_name.openStream())); 
  
            // reads system IPAddress 
            systemipaddress = sc.readLine().trim(); 
        } 
        catch (Exception e) 
        { 
            //systemipaddress = "Cannot Execute Properly"; 
        }  
        
   
    %>
    
    <%
String ip = request.getHeader("x-forwarded-for");
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
ip = request.getHeader("Proxy-Client-IP");
}
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
ip = request.getHeader("WL-Proxy-Client-IP");
}
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
ip = request.getRemoteAddr();
}

%>

                <!-- Footer Start -->
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row" style="font-size: 10px;">
                            <div class="col-md-8" >
                                2019 &copy; Allzone Management Solutions &nbsp;  
                            </div>
                            <div class="col-md-4">
                            	Developed by &nbsp;<a href="http://www.znifa.com" target="_blank" class="text-blue-50">Znifa Technologies  </a> 
                            </div>

                        </div>
                    </div>
                </footer>
                <!-- end Footer -->

          
            <!-- ============================================================== -->
            <!-- End Page content -->
            <!-- ============================================================== -->


        
        
    
        <!-- Vendor js -->
        <script src="resources/assets/js/vendor.min.js"></script>
        
        
        
                <!-- plugin js -->
        <script src="resources/assets/libs/moment/moment.min.js"></script>
        <script src="resources/assets/libs/jquery-ui/jquery-ui.min.js"></script>
        <script src="resources/assets/libs/fullcalendar/fullcalendar.min.js"></script>
        

        <!-- Calendar init -->
        <script src="resources/assets/js/pages/calendar.init.js"></script>
        

        <!--Morris Chart-->
        <script src="resources/assets/libs/morris-js/morris.min.js"></script>
        
        <script src="resources/assets/libs/raphael/raphael.min.js"></script>
        

        <!-- CRM Dashboard init js-->
      
        
        
        
        
                <!-- third party js -->
        <script src="resources/assets/libs/datatables1/jquery.dataTables.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.bootstrap4.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.responsive.min.js"></script>
        <script src="resources/assets/libs/datatables1/responsive.bootstrap4.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.buttons.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.bootstrap4.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.html5.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.flash.min.js"></script>
        <script src="resources/assets/libs/datatables1/buttons.print.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.keyTable.min.js"></script>
        <script src="resources/assets/libs/datatables1/dataTables.select.min.js"></script>
        
        
        <script src="resources/assets/libs/pdfmake/pdfmake.min.js"></script>
        <script src="resources/assets/libs/pdfmake/vfs_fonts.js"></script>
        <script src="resources/assets/js/pages/jszip.min.js"></script>
        
        <!-- third party js ends -->

        <!-- Datatables init -->
        <script src="resources/assets/js/pages/datatables.init.js"></script>
        
        
        
        
                <!-- Table Editable plugin-->
        <script src="resources/assets/libs/jquery-tabledit/jquery.tabledit.min.js"></script>
        

        <!-- Table editable init-->
        <script src="resources/assets/js/pages/tabledit.init.js"></script>
        
        
                <!-- Bootstrap Tables js -->
        <script src="resources/assets/libs/bootstrap-table/bootstrap-table.min.js"></script>
        

        <!-- Init js -->
        <script src="resources/assets/js/pages/bootstrap-tables.init.js"></script>
        
                <!-- Plugins js -->
        <script src="resources/assets/libs/dropzone/dropzone.min.js"></script>
        <script src="resources/assets/libs/dropify/dropify.min.js"></script>
       

        
         <!-- Common js -->
        <script src="resources/assets/js/common.js" /></script>
        
         <script src="resources/assets/libs/summernote/summernote-bs4.min.js"></script>
         <!-- <script src="resources/assets/libs/summernote/uploadcare.js"></script>
          -->
         
                 <!-- Plugins js-->
        <script src="resources/assets/libs/flatpickr/flatpickr.min.js"></script>
        <script src="resources/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.js"></script>
        <script src="resources/assets/libs/clockpicker/bootstrap-clockpicker.min.js"></script>

        <!-- Init js-->
       
         <script src="resources/assets/js/pages/datepicker.js"></script>
         
        <!-- Plugins js -->
        <script src="resources/assets/libs/jquery-mask-plugin/jquery.mask.min.js"></script>
        <script src="resources/assets/libs/autonumeric/autoNumeric-min.js"></script>

        <!-- Init js-->
        <script src="resources/assets/js/pages/form-masks.init.js"></script>
        
        
        
        <!-- App js -->
        <script src="resources/assets/js/app.min.js"></script>

        
       <!--   <script src="resources/assets/js/pages/form-pickers.init.js"></script> -->
       
        <!-- Modal-Effect -->
        <script src="resources/assets/libs/custombox/custombox.min.js"></script>

        <!-- Sparkline chart js -->
        <script src="resources/assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>

        <!-- Opportunities init js -->
        <script src="resources/assets/js/pages/crm-opportunities.init.js"></script>
        
        <script src="resources/assets/libs/footable/footable.all.min.js"></script>

        <!-- Init js -->
        <script src="resources/assets/js/pages/foo-tables.init.js"></script>
        
        <!-- Init js-->
        <script src="resources/assets/js/pages/form-advanced.init.js"></script>
       
        <script src="resources/assets/libs/jquery-nice-select/jquery.nice-select.min.js"></script>
        <script src="resources/assets/libs/switchery/switchery.min.js"></script>
        <script src="resources/assets/libs/multiselect/jquery.multi-select.js"></script>
        <script src="resources/assets/libs/select2/select2.min.js"></script>
        <script src="resources/assets/libs/jquery-mockjax/jquery.mockjax.min.js"></script>
        <script src="resources/assets/libs/autocomplete/jquery.autocomplete.min.js"></script>
        <script src="resources/assets/libs/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="resources/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"></script>
        <script src="resources/assets/libs/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>


        <!-- Plugin js-->
        <script src="resources/assets/libs/parsleyjs/parsley.min.js"></script>

        <!-- Validation init js-->
        <script src="resources/assets/js/pages/form-validation.init.js"></script>

        
    </body>
</html>