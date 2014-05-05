 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <!-- Wrap the .navbar in .container to center it within the absolutely positioned parent. -->
                      <div class="navbar navbar-inverse">
                          <div class="navbar-inner">
                            <!-- Responsive Navbar Part 1: Button for triggering responsive navbar (not covered in tutorial). Include responsive CSS to utilize. -->
                            <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button">
                              <span class="icon-bar"></span>
                              <span class="icon-bar"></span>
                              <span class="icon-bar"></span>
                            </button>
                            <!-- Responsive Navbar Part 2: Place all navbar contents you want collapsed withing .navbar-collapse.collapse. -->
                            <div class="nav-collapse collapse">
                              <ul class="nav">
                                <li ${pageAttr.pageId =="home" ? "class='active'" : "" }><a href="${pageContext.request.contextPath}">Home</a></li>
                                <!-- DO NOT REMOVE THIS START-->   
								<%-- <li class='dropdown-submenu ${pageAttr.pageId == "productsAndServices" ? "active" : "" }'>
									<a tabindex="-1" href="${pageContext.request.contextPath}/productsAndServices/product_and_services">Products And Services</a>
									<ul class="dropdown-menu">										
										<li><a href="${pageContext.request.contextPath}/productsAndServices/network_infra">Network Infrastructure</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/consulting">Consulting</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/mixed_delivery">Mixed Delivery</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/service_and_solutions">Service & Solutions</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/it_infra">IT Infrastructure</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/econtact_call">eContact Call Center</a></li>
									</ul>
								</li> --%>
								<!-- DO NOT REMOVE THIS END-->
								
								<li class='dropdown ${pageAttr.pageId == "productsAndServices" ? "active" : "" }'>
                                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">Products & Services</a>
                                  <ul class="dropdown-menu">
										<li><a href="${pageContext.request.contextPath}/productsAndServices/product_and_services">Products & Services</a></li>
										<li class="divider"></li>
										<li><a href="${pageContext.request.contextPath}/productsAndServices/network_infra">Network Infrastructure</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/consulting">Consulting</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/mixed_delivery">Mixed Delivery</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/service_and_solutions">Service & Solutions</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/it_infra">IT Infrastructure</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/productsAndServices/econtact_call">eContact Call Center</a></li>
									</ul>
                                </li>
                            	<li class='dropdown ${pageAttr.pageId == "aboutUs" ? "active" : "" }'>
                                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">About Us</a>
                                  <ul class="dropdown-menu">
										<li><a id="example" href="${pageContext.request.contextPath}/aboutUs/about_us" rel="popover" data-content="with the details of our core values & vision-mission" data-original-title="">About Us</a></li>
										<li class="divider"></li>
										<%-- <li><a href="${pageContext.request.contextPath}/aboutUs/core_values">Core Values</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/aboutUs/vision_mission">Vision & Mission</a></li> --%>
	                                    <li><a href="${pageContext.request.contextPath}/aboutUs/management_team">Management Team</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/aboutUs/strategic_partner">Strategic Partner & Customer</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/aboutUs/anticorruption_directive">Anti-corruption Directive</a></li>
	                                    <li><a href="${pageContext.request.contextPath}/aboutUs/code_of_conduct">Code of Conduct</a></li>
	                                    <%-- <li><a href="${pageContext.request.contextPath}/aboutUs/customer">Customer</a></li> --%>
									</ul>
								</li>
                                <li ${pageAttr.pageId =="contact_us" ? "class='active'" : "" }><a href="${pageContext.request.contextPath}/contact_us">Contact Us</a></li>
                                <li ${pageAttr.pageId =="careers" ? "class='active'" : "" }><a href="${pageContext.request.contextPath}/careers">Careers</a></li>
                                
                                <sec:authorize access="hasAnyRole('ROLE_MTRACK','ROLE_ADMIN')">
                                <li class='dropdown ${pageAttr.pageId == "mTrack" ? "active" : "" }'>
                                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">MTrack</a>
                                  <ul class="dropdown-menu">
                                	<li><a href="${pageContext.request.contextPath}/mTrack/queryMTrack">Create Billing</a></li>
                                	<sec:authorize access="hasAnyRole('ROLE_MTRACK_ADMIN','ROLE_ADMIN')">
                                	<li><a href="${pageContext.request.contextPath}/mTrack/queryBillingHistory">Billing History</a></li>
                                	<li><a href="${pageContext.request.contextPath}/mTrack/queryInvoiceHistory">Invoice History</a></li>
                                	<li><a href="${pageContext.request.contextPath}/mTrack/taxinvno">Create Tax Number</a></li>
                                	</sec:authorize>
                                  </ul>
                                 </li>
                                 </sec:authorize>
                                <!-- li ${pageAttr.pageId =="blog" ? "class='active'" : "" }><a href="${pageContext.request.contextPath}/blog">Blog</a></li-->
                                <%-- <li class='dropdown ${pageAttr.pageId == "application" ? "active" : "" }'>
                                  <a data-toggle="dropdown" class="dropdown-toggle" href="#">Application</a>
                                  <ul class="dropdown-menu">
										<li class='dropdown'>
											<a data-toggle="dropdown" class="dropdown-toggle" href="#">MTrack</a>											
											<ul class="dropdown-menu">
											<li><a href="#">MTrackabc</a></li>
											</ul>
										</li>
										<li class='dropdown'>
											<a data-toggle="dropdown" href="#">MTrack123</a>											
											<ul class='dropdown-menu'>
											<li><a href="#">MTrackabcdef</a></li>
											</ul>
										</li>
								  </ul>
                                </li> --%>
                              </ul>
                            </div><!--/.nav-collapse -->
                            
                            <sec:authorize access="isAnonymous()">
                            <div class="search_cntr">
                            	<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
                            	<form action="${openIDLoginUrl}" method="post">
	  								<input name="openid_identifier" type="hidden" value="https://www.google.com/accounts/o8/id"/>
	  								<input type="image" name="submit" alt="Sign in with openId" style="width: 150px; height: 40px;vertical-align: inherit;" src="${pageContext.request.contextPath}/assets/img/google.png" value="Submit">	  								
								</form>
                            </div>
                            </sec:authorize>
                            
                            
                            <sec:authorize access="isAuthenticated()">
                            <div class="search_cntr">
                            <c:url var="logoutUrl" value="/logout" />
                            <form action="${logoutUrl}" method="post">
                            <input type="image" name="submit" alt="logout" style="vertical-align: inherit;" src="${pageContext.request.contextPath}/assets/img/logout.png" value="Submit">                                         
                            </form>
                            </div>               
                            </sec:authorize>
                            <!-- <div class="search_cntr">
                            	<div class="search_inner">
                            		<div class="tbl">
                                        <div class="tr lh0">
                                             <div class="td">
                                             	<div class="search_box">
                                                	<div class="search_box_inner">
	                                                	<div class="search_inpt"><input type="text"></div>
    	                                                <div class="search_submit"><input class="search_btn" type="submit" value="" ></div>
                                                    </div>
                                                </div>
                                             </div>
                                             <div class="td"><a id="search_open" class="search_btn" href="#"></a> </div>
                                        </div>                            
                                 </div>
                                </div>
                            </div> -->
                            
                          </div><!-- /.navbar-inner -->
                      </div><!-- /.navbar -->

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>                      
<script>
$(function ()
{ $("#example").popover();
});

/* $(document).ready(function(){
    $('.dropdown .dropdown').each(function(){
        var $self = $(this);
        var handle = $self.children('[data-toggle="dropdown"]');
        $(handle).click(function(){
            var submenu = $self.children('.dropdown-menu');
            $(submenu).toggle();
            return false;
        });
    });
});
 */
</script>                      