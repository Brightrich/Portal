<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${pageAttr.message !=null }">
<div id="ModalMessage" title="Add Result">
${pageAttr.message }
</div>
</c:if> 
	
	<!-- /////////////////// Header has started from here /////////////////// -->
	<%@ include file="../../component/header.jsp"%>
	<!-- /////////////////// Header has ended here /////////////////// -->

	<!-- /////////////////// Page Content Started from here /////////////////// -->
	
	<div class="container asu_cntr inner">
		<div class="row-fluid">
			
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#search">Search Tax Invoice</a></li>
					<li><a href="#add">Add Tax Invoice</a></li>
				</ul>
		
			<div class="tab-content">
			<div class="tab-pane active" id="search">
			
				<div class="fancy_box">
            	<div class="fancy_header">
                	<div class="fancy_header_text"><h5>Search Tax Invoice Number</h5></div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
					<form method="POST" name="queryTaxInvData" id="queryTaxInvData" action="#" enctype="multipart/form-data">                	
					<div class="span9">
						<p>Please provide the customer Tax number.</p>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<input type="text" name="taxno" id="taxno"
								<%-- value="${pageAttr.msisdnSearch }" --%>
								style="width: 50%; height: 6%;" placeholder="TAX NUMBER *"
								title="TAX NUMBER *" /> <input id="Search" type="button"
								value="Search" class="btn">
						</div>
					</div>
					
					<div id="LoadingImageDiv" class="span12"
						style="display: none; text-align: center;">
						<img id="loading-image"
							src="${pageContext.request.contextPath}/assets/img/ajax-loader.gif" />
					</div>
					
			<div id="result">	
				<div class="row-fluid">
					
						<div class="as_spacer"></div>
						<table id="resultMTrackSearch" align="left" class="features-table">
						</table>
				</div>
				
				<div class="row-fluid">		
				<div class="as_spacer"></div>
										
					</div>
				
			</div>
					
					
				</form>		
			</div>	
			</div>
			</div>
				<div class="tab-pane" id="add">
					<div class="fancy_box">
	            	<div class="fancy_header">
	                	<div class="fancy_header_text"><h5>Add Tax Invoice Number</h5></div>
	                </div>
					<div class="row-fluid">
						<div class="span9">
					<form method="POST" name="insertTaxInvData" id="insertTaxInvData" action="insertTaxInvData">
			
							<fieldset>
							<div class="span9">
								<p>Insert Tax number.</p>
							</div>
							<input type="text" name="taxnoadd" id="taxnoadd"
								style="width: 50%; height: 6%;" placeholder="TAX NUMBER *"
								title="TAX NUMBER *" /> <input id="Add" type="submit"
								value="Add" class="btn">
							</fieldset>
					</form>		
					</div>
					</div> 
					
					
		</div>
		
		
	</div>
	
	</div>
	</div>
	
	</div>
	</div>
	
	
	<!-- <div class="container asu_cntr"> -->
	<jsp:include page="../../component/footer.jsp" />
	<jsp:include page="../../component/script.jsp" />
	 

<script type="text/javascript">


                                         
$(document).ready(function() {                         
 $("#ModalMessage").dialog({
	 modal: true,
	 hide: {effect: 'fold', duration: 1000},
     show: {effect: 'highlight', duration: 500},
     position: [($(window).width() / 2) - (400 / 2), 200],     
	 height: 200,
	 width: 300,
	 });  
});

	$(document).ready(function() {
		$("#result").hide();
		$("#LoadingImageDiv2").hide();
	});
	
	 /* $(function() {
		 $( "#dateSelectedVal" ).datepicker();
		 }); */		 
	 
		 
	var oTable;
	$('#Search')
			.click(
					function() {
						var row = 0;
						var searchText = $("#taxno").val();
						if(searchText != null && searchText != ""){
						$("#result").hide();
						var str = $("#queryTaxInvData").serialize();
						//alert(str);
						$("#LoadingImageDiv").show();
						$
								.ajax({
									//async: false,
									//contentType: "application/json; charset=utf-8",
									dataType : 'json',
									type : "POST",
									url : "queryTaxInvData?",
									data : str,
									error : function(xhr, msg, thrownError) {																			

										if (xhr.status == "200") {
											alert("Your session time has expired. You will now be redirected to login page.")
											window.location = "../login?error=invalidsession";
										} else {
											window.location = "../deniedpage?error="
													+ thrownError;
										}
									},
									success : function(msg) {
										//leavePage();
										$("#LoadingImageDiv").hide();

										oTable = $('#resultMTrackSearch')
												.dataTable(
														{
															"sPaginationType" : "full_numbers",
															"iDisplayLength" : 50,
															"aaData" : msg,
															"bFilter" : false,
															"bLengthChange" : false,
															"bInfo" : false,
															"bDestroy" : true,
															"aoColumns" : [
																	{
																		"sTitle" : "Tax No",
																		"mData" : "taxno",
																		sDefaultContent : "n/a"
																	},
																	{
																		"sTitle" : "Invoice Id",
																		"mData" : "invid",
																		sDefaultContent : "n/a"
																	},
																	{
																		"sTitle" : "Added By",
																		"mData" : "addedby",
																		sDefaultContent : "n/a"
																	},
																	{
																		"sTitle" : "Added Date",
																		"mData" : "addeddate",
																		sDefaultContent : "n/a"
																	}]
														}).css('width', '100%');
										$("#result").show();
										scrollResult();
										$(function() {
											var oSettings = oTable.fnSettings();
											if (oSettings.fnRecordsTotal() <= 50) {
												$(
														"#resultMTrackSearch_paginate")
														.hide();												
											}
										});

									}
								});
						return false;
						} else {
							alert("Tax Invoice No cannot be empty.");
						}
					});
	
function scrollResult(){
//		$('html, body').animate({
//			scrollTop: $("#result").offset().top
//		}, 2000);
		
//		$(document).scrollTop($("#result").offset().top);
		var res = document.getElementById("result");
		var left = getOffset(res).left;
		var top = getOffset(res).top;
		window.scroll(left,top);
	}
function getOffset( el ) {
	    var _x = $(el).offset().left;
	    var _y = $(el).offset().top;
	    return { top: _y, left: _x };
	}
	
</script>
