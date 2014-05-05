<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en-US">
<head>
	<meta charset="UTF-8" />
<title>Brighten and Richness Technology Portal</title>
   
  </head>
  <body>
    
    	
        <!-- /////////////////// Header has started from here /////////////////// -->
    	<%@ include file="../component/header.jsp" %>
        <!-- /////////////////// Header has ended here /////////////////// -->
        
        
        <!-- /////////////////// Page Content Started from here /////////////////// -->       
        <div class="container asu_cntr inner">
        	
			 <div class="as_spacer"></div>
			 
			 <div class="row-fluid">      	
            	<div class="span6">
            		<%-- <div id="login-error">${error} because of ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div> --%>
						<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
						<c:url var="googleLogoUrl" value="/resources/google-logo.png" />
						<img src="${googleLogoUrl}"></img>
						<form action="${openIDLoginUrl}" method="post">
							   For Google users:
							  <input name="openid_identifier" type="hidden" value="https://www.google.com/accounts/o8/id"/>
							  <input type="submit" value="Sign with Google"/>
						</form>
                </div>
            </div>
            
            
             <jsp:include page="../component/footer.jsp" />
             <jsp:include page="../component/script.jsp" />
        </div> <!-- <div class="container asu_cntr"> -->
        
        
  </body>
</html>