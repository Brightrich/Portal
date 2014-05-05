<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en-US">
<head>
	<meta charset="UTF-8" />
<title>Brighten and Richness Technology Portal</title>
    <!-- Bootstrap -->
    <link href="assets/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet" media="screen">    
    <link href="assets/css/custom_global.css" rel="stylesheet" media="screen">
    <link href="assets/css/prettyPhoto.css" rel="stylesheet" media="screen">
    <link href="assets/css/flexslider.css" rel="stylesheet" media="screen">    
  </head>
  <body>
        <!-- /////////////////// Header has started from here /////////////////// -->
    	<%@ include file="../component/header.jsp" %>
        <!-- /////////////////// Header has ended here /////////////////// -->        
        
        <!-- /////////////////// Page Content Started from here /////////////////// -->        
        <div class="container asu_cntr inner">
        	
            <div class="row-fluid">
            	<div class="span8" id="map">
               		<iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Home+Inn+Executive+Residence,+Jalan+Guru+Mughni+No.12,+Indonesia&amp;aq=0&amp;oq=Home+Inn+Executive+Residence&amp;sll=-6.229075,106.821656&amp;sspn=0.006325,0.009645&amp;ie=UTF8&amp;hq=Home+Inn+Executive+Residence,+Jalan+Guru+Mughni+No.12,+Indonesia&amp;hnear=&amp;t=m&amp;z=14&amp;iwloc=A&amp;cid=17745824955517003&amp;ll=-6.230208,106.821632&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=Home+Inn+Executive+Residence,+Jalan+Guru+Mughni+No.12,+Indonesia&amp;aq=0&amp;oq=Home+Inn+Executive+Residence&amp;sll=-6.229075,106.821656&amp;sspn=0.006325,0.009645&amp;ie=UTF8&amp;hq=Home+Inn+Executive+Residence,+Jalan+Guru+Mughni+No.12,+Indonesia&amp;hnear=&amp;t=m&amp;z=14&amp;iwloc=A&amp;cid=17745824955517003&amp;ll=-6.230208,106.821632" style="color:#0000FF;text-align:left">View Larger Map</a></small>
                </div>                
                <div class="span4">
                	<img alt="BRT Portal" src="assets/imgs/common/contact-us-photo.png" >
                </div>
            </div>
             
             <div class="clearfix"></div>
             <div class="as_spacer"></div>
             <a id="contfrmLoc" name="contfrmLoc"></a>
            <div class="row-fluid">
            	<div class="span8">
                	<div class="fancy_header">
                  	<div class="fancy_header_text"><h5>Quick Quotation  |  Question</h5></div>
                </div>
                <p>Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia. Sed quia consequuntur dolores eos. Enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia. Enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia. Enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia.<br><br>

				</p>
                	<div id="contactFrm" class="cmm-itm-form cornice_form">
                    	<div id="msg_success" style="display:none;">
                        	<div class="alert alert-success">
                            	Your email has been received we will get in touch within 24 hrs.
                            </div>
                        </div>
                        <div id="msg_failed" style="display:none;">
                        	<div class="alert alert-error">
                            	Sorry email could not be proceed please check all required fields are filled properly .
                            </div>
                        </div>
                    			<form method="post" action="http://webcornice.com/themes/asure/html/contact_frm/contactfrm.php">
                                	<div class="row-fluid">
                                    	<div class="span4"><input name="Name" type="text" style="width:70%;" placeholder="name *" title="name *"></div>
                                        <div class="span4"><input name="Email" type="text" style="width:70%;" placeholder="email *" title="email *" ></div>
                                        <div class="span4"> <input name="Subject" type="text" style="width:70%;" placeholder="subject *" title="subject"></div>
                                    </div>
                                    <div class="clearfix">
                                		<textarea  name="Message" title="Message" placeholder="message" ></textarea>
                                    </div>
									<div class="clearfix">
                                		<input type="submit" value="Submit" class="btn">
                                        <input type="button" value="cancel" class="btn">
                                    </div>
                                                   
                                </form>
                	</div>
                    
                </div>                
                <div class="span4">
                	<div class="fancy_header">
                    	<div class="fancy_header_text"><h5>Our Office</h5></div>
                    </div>
                    
                    <div class="icon_contact loc">Brighten & Richness Technology, pt.<br>
                    Kuningan Junction, 2nd Floor<br>
					Jl. Guru Mughni #12<br>
					Kuningan Timur - Setiabudi<br>
					Jakarta Selatan 12950, Indonesia
					</div>
                    <div class="icon_contact cell">+62 21 5292 2891</div>
                    <div class="icon_contact cell">+62 21 5292 2893</div>
                    <div class="icon_contact mail"><a href="http://www.brightrich.com">www.brightrich.com</a></div>
                    <div class="icon_contact monitor"><a href="mailto:info@brightrich.com">info@brightrich.com</a></div>
                    
                    <div class="clearfix"></div>
                    <div class="as_spacer"></div>
                    
                     <div class="fancy_header">
                    	<div class="fancy_header_text"><h5>Office Hours</h5></div>
                    </div>
                    
                    <div class="icon_contact clock"><strong>Monday - Frieday</strong>  : 9:00 am - 5:00 pm </div>
                    <div class="icon_contact clock"><strong>Saturday, Sunday</strong>  : Closed</div>
                </div>
            </div>
            
          	<jsp:include page="../component/footer.jsp" />
        </div> <!-- <div class="container asu_cntr"> -->
        
        
  
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.flexslider-min.js"></script>
    <script src="assets/js/jquery.prettyPhoto.js"></script>        
    <script src="assets/js/wc.scripts.js"></script>
    <script src="assets/js/wc.custom.js"></script>
    
  </body>
</html>