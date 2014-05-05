<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<!-- /////////////////// Header has started from here /////////////////// -->
	<%@ include file="../../component/header.jsp"%>
	<!-- /////////////////// Header has ended here /////////////////// -->

	<!-- /////////////////// Page Content Started from here /////////////////// -->
	<form method="POST" name="queryInvoice" id="queryInvoice"
	action="#" enctype="multipart/form-data">
	
	<div class="container asu_cntr inner">
		<div class="row-fluid">
			<div class="promobox">
				<div class="footer_blk_title">
					<h4>MTrack Invoice History</h4>
				</div>
				<div class="promobox_content">

					<div class="span9">
						<p>Please provide at least one search criteria.</p>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<input type="text" name="invoiceSearch" id="invoiceSearch"
								value="${pageAttr.invoiceSearch }"
								style="width: 50%; height: 5%;" placeholder="Invoice Number"
								title="Invoice Number" />
						</div>
					</div>	
					<div class="row-fluid">
						<div class="span9">
							<form:select path="pageAttr.companySelected" id="companySelected" multiple="false" style="width:auto; height: 5%;">
							<option value="">Company</option>
							<form:options items="${pageAttr.companyList}" itemValue="id" itemLabel="name"/>
							</form:select>
					</div>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<form:select path="pageAttr.billingMonth" id="monthSelected" style="width:auto; height: 5%;">
								<option value="">Month</option>
								<option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option>
								<option value="11">November</option>
								<option value="12">December</option>
							</form:select>
						
					 
							<form:select path="pageAttr.billingYear" id="billingYear" style="width:auto; height: 5%;">
								<option value="">Year</option>
							</form:select>
							<script type="text/javascript">							
								var selectBox = document.getElementById('billingYear');							
								var currentYear = new Date().getFullYear();							
								var i = 2012;
								for ( i; i <= currentYear; i++) {							
									var newOption = document.createElement("option");							
									newOption.text = i;
									newOption.value = i;							
									selectBox.options.add(newOption);							
								}							
							</script>
						</div>
					 </div>	
					<div class="row-fluid">
						<div class="span9">
							<%-- <input type="text" name="invoiceSearch" id="invoiceSearch"
								value="${pageAttr.invoiceSearch }"
								style="width: 50%; height: 5%;" placeholder="Invoice Number *"
								title="Invoice Number *" /> --%> <input id="Search" type="button"
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
		</div>
		
		
			

	</div>
	<!-- <div class="container asu_cntr"> -->
	<jsp:include page="../../component/footer.jsp" />
	<jsp:include page="../../component/script.jsp" />
	 
</form>

<script type="text/javascript">
                                         
	$(document).ready(function() {
		$("#result").hide();

	});
	
	var oTable;
	$('#Search')
			.click(
					function() {
						
						if(validateInput()){
						var row = 0;

						$("#result").hide();
						var str = $("#queryInvoice").serialize();
						//alert(str);
						$("#LoadingImageDiv").show();
						$
								.ajax({
									//async: false,
									//contentType: "application/json; charset=utf-8",
									dataType : 'json',
									type : "POST",
									url : "queryInvoice?"
											+ Math
													.floor((Math.random() * 100000) + 1),
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
																		"sTitle" : "Company Name",
																		"mData" : "company",																		
																	},
																	{
																		"sTitle" : "Invoice Number",
																		"mData" : "invoiceNo",
																	},
																	{
																		"sTitle" : "Total",
																		"mData" : "totalInvoice",
																	},
																	{
																		"sTitle" : "Invoice Date",
																		"mData" : "invoiceDate",
																	},																
																	{    
																		"sTitle" : "Invoice",
																		sDefaultContent : "n/a",
												                        "bSearchable": false,
												                        "bSortable": false,
												                        "fnRender": function (oObj)                              
												                        {											                           
												                            return '<a target="_blank" href="openpdf?ftype=invoice&fn='+ oObj.aData.invoiceFilePath+'" id="invoice"><img width="30" height="30" alt="download" src="../assets/img/pdfdl.jpg"/>';
												                        }
												                       },
												                       {    
																			"sTitle" : "ZIP Files",
																			sDefaultContent : "n/a",
													                        "bSearchable": false,
													                        "bSortable": false,
													                        "fnRender": function (oObj)                              
													                        {											                           
													                            return '<a target="_blank" href="getcompressedfiles?invId='+ oObj.aData.invoiceId+'" id="invoiceZip"><img width="30" height="30" alt="download" src="../assets/img/zip.jpg"/>';
													                        }
													                       }  
																	]
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
						} else {
							alert("Please provide at least one search criteria");
						}
						return false;
					});
	
	 
	 function scrollResult(){
//			$('html, body').animate({
//				scrollTop: $("#result").offset().top
//			}, 2000);
			
//			$(document).scrollTop($("#result").offset().top);
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
	 
	 function validateInput(){
			var msisdnSelected = $("#invoiceSearch").val(),
			companySelected = $("#companySelected").val(), 
			monthSelected = $("#monthSelected").val(),
			billingYear = $("#billingYear").val();
			
			if((msisdnSelected!=null && msisdnSelected!="") || (companySelected!=null && companySelected!="") || (monthSelected !=null && monthSelected!="") || (billingYear!=null && billingYear!="")){
				return true;
			} else {
				return false;
			}
		}
</script>
