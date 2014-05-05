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
    	<%@ include file="../../component/header.jsp" %>
        <!-- /////////////////// Header has ended here /////////////////// -->
        
        
        <!-- /////////////////// Page Content Started from here /////////////////// -->       
        <div class="container asu_cntr inner">
        	
			 <div class="as_spacer"></div>
			 
			 <div class="row-fluid">      	
            	<h1>Admin Page</h1>
				<p>Only admins have access to this page.</p>
				<p>Curabitur quis libero elit, dapibus iaculis nisl. Nullam quis velit eget odio 
				adipiscing tristique non sed ligula. In auctor diam eget nisl condimentum laoreet..</p>
            </div>
            
            
             <jsp:include page="../../component/footer.jsp" />
             <jsp:include page="../../component/script.jsp" />
        </div> <!-- <div class="container asu_cntr"> -->
        
        
  </body>
</html>