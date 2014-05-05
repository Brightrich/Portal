<title>Brighten and Richness Technology Portal</title>
    	<% String type = request.getParameter("type");
			if (type!=null && type.equals("slideshow")) { %>
				<div class="container canvas">
			    	<div class="header">			
				<%@ include file="slideshow.jsp" %>
		<% } else { %>
				<div class="container canvas-mod">
		    	<div class="header">		
				<div class="banner inner"><img alt="BRT Portal" src="${pageContext.request.contextPath}/assets/imgs/bgs/inner-page-header-bg.jpg"></div>     
		<% } %>
    	
    	
        	       
            <div class="topheader-wrapper ">
            	<div class="asu_cntr">
                    <div class="logobar-wrapper">

                        <div class="tbl w100prs">
                        	<div class="tr">
                            	 <div class="td">
                                 	<div class="tbl">
                                        <div class="tr">
                                             <div class="td logo"><a href="${pageContext.request.contextPath}"><img alt="BRT Portal" src="${pageContext.request.contextPath}/assets/imgs/common/Logo1.png" >&nbsp&nbsp&nbsp</a></div>
						                     <div class="td tagline"><span>Modern, Professional Multi-purpose</span></div>
                                        </div>                            
                                    </div>
                                 </div>
                                 <div class="td alr">
                                    <a class="sc twitter" data-original-title="Follow us on Twitter" href="https://twitter.com/brightrich" target="blank"></a>
                                    <a class="sc facebook" data-original-title="Follow us on facebook" href="http://www.facebook.com/brightrich" target="blank"></a>
                                    <a class="sc linkedin" data-original-title="View our Linked in Profile" href="http://www.linkedin.com/company/1766099?trk=prof-exp-company-name" target="blank"></a>                                    
                                 </div>
                            </div>                            
                        </div>
                        
                    </div>
                    
            
                    <%@ include file="menu.jsp" %>
                    <% if (type==null || !type.equals("slideshow")) { %>  
                    
                    <div class="page_title">
                    	<div class="fancy_header">
                            <div class="fancy_header_text"><h1>${pageAttr.title}</h1></div>
                        </div>
                    </div>
                	<% }%>                		
                     
    			</div>
            
        </div>
        
        <%
        if (type!=null && type.equals("slideshow")) { %>
        </div>
        <% } else { %>
        </div>
        <% } %>
