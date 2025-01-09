            <!-- ========== Left Sidebar Start ========== -->
      <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <div class="left-side-menu">

                <div class="slimscroll-menu">

                    <!--- Sidemenu -->
                    <div id="sidebar-menu">

                        <ul class="metismenu" id="side-menu">
   						  	<c:forEach var="mainmenu" items="${sessionScope.mainMenuList}">

										<input type="hidden" name="mainMenuId" value="${user.mainMenuId}" />
										<li>
																									
											<a href="${mainmenu.mainMenuhref}">					
											
											<c:if test="${mainmenu.subMenuList.size() > 0 }">
											 <span class="menu-arrow"></span>
											</c:if>	
											<c:choose>
												<c:when test="${mainmenu.mainMenuName != 'Logout'}">
													<i class="${mainmenu.mainMenuClassName}"></i>
													<span> ${mainmenu.mainMenuName} </span> <br>												
												</c:when>
												<c:otherwise>
													<i class="${mainmenu.mainMenuClassName}" style="color:red"></i>
													<span style="color:red"> ${mainmenu.mainMenuName} </span> <br>	
												</c:otherwise>											
											</c:choose>																	 	
											</a>
											<c:if test="${mainmenu.subMenuList.size() > 0 }">
											<ul class="nav-second-level collapse" aria-expanded="true">
																						
												<c:forEach var="submenu" items="${mainmenu.subMenuList}">											
													<li>
														 <a href="${submenu.subMenuhref}">
														 <span> ${submenu.subMenuName} </span> <br>
														 </a>
													 </li>
												 </c:forEach>											
											</ul>
											</c:if>											
										
										</li>

										
								</c:forEach>
                            
					
                        </ul>

                    </div>
                    <!-- End Sidebar -->

                    <div class="clearfix"></div>

                </div>
                <!-- Sidebar -left -->

            </div>
            <!-- Left Sidebar End -->