<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<!-- /////////////////// Header has started from here /////////////////// -->
	<%@ include file="../../component/header.jsp"%>
	<!-- /////////////////// Header has ended here /////////////////// -->

	<!-- /////////////////// Page Content Started from here /////////////////// -->
	<form method="POST" name="queryMTrackHistory" id="queryMTrackHistory"
	action="generateInvoiceData" target="_blank">
	
	<div class="container asu_cntr inner">
		<div class="row-fluid">
			<div class="promobox">
				<div class="footer_blk_title">
					<h4>MTrack Billing History & Generate Invoice</h4>
				</div>
				<div class="promobox_content">

					<div class="span9">
						<p>Please provide at least one search criteria.</p>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<input type="text" name="msisdnSearch" id="msisdnSearch"
								value="${pageAttr.msisdnSearch }"
								style="width: 50%; height: 5%;" placeholder="MSISDN"
								title="MSISDN" />
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
							<input id="Search" type="button" value="Search" class="btn">
							
							</div>
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
							<table id="resultMTrackSearch" align="left"
								class="features-table">
								<tfoot>
								<tr>
									<td colspan="5" width="80%">Billing Summary & Call Details Compilation</td>
									<td style="width: 10%;text-align: center;"><a target="_blank" href="getcompiledpdf?ftype=billSum" id="billSumCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/></td>
									<td style="width: 10%;text-align: center;"><a target="_blank" href="getcompiledpdf?ftype=callDetail" id="callDetailCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/></td>
								</tr>
								</tfoot>
							</table>														                           
						</div>												
							
					</div>					

						<!-- <div id="compilationDiv" class="row-fluid">
							
							<table id="compilation" align="left" class="features-table" style="width: 100%;">
								
							</table>
							
						</div> -->

					</div>		
								
					<div class="as_spacer"></div>
					<div id="resultUnbilled" class="row-fluid">
						
							<div class="span9">
							<div class="fancy_header">
	                        <div class="fancy_header_text"><h5>Missing Billing</h5></div>
	                     	</div>
                     		
                     		<label for="msisdnUnbilled">There is no billing inserted for the following MSISDN :</label>
							<div class="clearfix">
                                <textarea  id="msisdnUnbilled" readonly="true" style="width: 80%;height: 15%"></textarea>
                            </div>
                            </div>	
                        </div>
					
					<div class="as_spacer"></div>
					<%-- <div id="viewcompilation" class="row-fluid">
					<div class="span9">
						<div class="fancy_header">
                        <div class="fancy_header_text"><h5>Billing Summary & Call Details Compilation</h5></div>
                     	</div>
						<label for="billSumCompilation">Billing Summary</label>
						<a target="_blank" href="getcompiledpdf?ftype=billSum" id="billSumCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/>						
						
						<label for="calLDetailCompilation">Call Detail</label>
						<a target="_blank" href="getcompiledpdf?ftype=callDetail" id="callDetailCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/>
					</div>
					</div> --%>	
					
					<div id="generateInvoice" class="row-fluid">
						<div class="span6">
						<div class="fancy_header">
                        <div class="fancy_header_text"><h5>Generate Invoice</h5></div>
                     	</div>
                     		
                     		<label for="invoiceDateVal">Invoice Date</label>
							<input type="text" name="invoiceDateVal" id="invoiceDateVal" class="text ui-widget-content ui-corner-all" style="width: 50%; height: 5%;" />
							<label for="invoiceNumberVal">Invoice Number</label>
							<input type="text" name="invoiceNumberVal" id="invoiceNumberVal" class="text ui-widget-content ui-corner-all" style="width: 50%; height: 5%;"/>
							<input id="generateInvoiceBtn" type="submit" value="Generate Monthly Invoice" class="btn">
						</div>	
					</div>
					<div id="viewInvoice" class="row-fluid">
					<div class="span9">
						<div class="fancy_header">
                        <div class="fancy_header_text"><h5>View Generated Invoice</h5></div>
                     	</div>
						<label for="invoiceNo">Invoice No</label>
						<input type="text" name="invoiceNo" id="invoiceNo" disabled="disabled" class="text ui-widget-content ui-corner-all" style="width: 50%; height: 5%;" />
						<a target="_blank" href="openpdf?ftype=invoice&fn=####" id="billSummary"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/>						
					</div>
					</div>					 
				</div>




			</div>
			<input type="hidden" id="fleetNum"/>
			<!-- <div class="container asu_cntr"> -->
			<jsp:include page="../../component/footer.jsp" />
			<jsp:include page="../../component/script.jsp" />
</form>


<script type="text/javascript">
$(document).ready(function() {
	//$("#viewcompilation").hide();
	$("#result").hide();
	$("#generateInvoice").hide();
	$("#viewInvoice").hide();
	$("#resultUnbilled").hide();
});

$(function(){
	  $("#generateInvoiceBtn").click(function(evt) {
	    evt.preventDefault();
	   
		var num = $("#fleetNum").val();
		if(num=="-1"){
			var confirmation = confirm("The number of billing created is different from the number of fleet for this company. Are you sure want to continue generating invoice?");
			if (confirmation== true){
				
				generateInvoiceAjax();
		     
			}else{		
			     return false;
			 }			
		} else {
			generateInvoiceAjax();
		}
	 });
	});

$(function() {
	 $( "#invoiceDateVal" ).datepicker();
	 });	

var oTable;
$('#Search')
		.click(
				function() { // catch the form's submit event
					
					if(validateInput()){
					var row = 0;
					$("#resultUnbilled").hide();
					$("#result").hide();
					var newflag = false;
					var iNo = "";
					var iPath="";
					var iDate = "";
					var str = $("#queryMTrackHistory").serialize();
					var companySelected = $("#companySelected").val(), 
					monthSelected = $("#monthSelected").val(),
					billingYear = $("#billingYear").val();
										
					
					//alert(str);
					$("#LoadingImageDiv").show();
					$("#generateInvoice").hide();
					$("#viewInvoice").hide();
					//$("#viewcompilation").hide();
					$
							.ajax({
								//async: false,
								//contentType: "application/json; charset=utf-8",
								dataType : 'json',
								type : "POST",
								url : "queryMTrackHistory?"
										+ Math
												.floor((Math.random() * 100000) + 1),
								data : str,
								error : function(xhr, msg, thrownError) {

									//alert(xhr.status);

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
									if(companySelected!=null && companySelected!="" && monthSelected!=null && monthSelected!="" && billingYear!=null && billingYear!=""){
									$("#generateInvoice").show();
									}
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
																	"sTitle" : "MSISDN",
																	"mData" : "msisdn",																	
																},
																{
																	"sTitle" : "Company",
																	"mData" : "companyName",
																},
																{
																	"sTitle" : "Door Number",
																	"mData" : "doorNumber",
																},																
																{
																	"sTitle" : "Billing Date",
																	"mData" : "billingDate",
																},
																{
																	"sTitle" : "Total",
																	"mData" : "totalBilling",
																},																
																{    
																	"sTitle" : "Billing Summary",
																	sDefaultContent : "n/a",
											                        "bSearchable": false,
											                        "bSortable": false,
											                        "fnRender": function (oObj)                              
											                        {
											                        	$("#fleetNum").val(oObj.aData.fleetCount);
											                        	if(oObj.aData.invoiceNo == "null"){
											                        		newflag = true;
											                        	} else {
											                        		iPath = oObj.aData.invoiceFilePath;
											                        		iNo = oObj.aData.invoiceNo;
											                        		iDate = oObj.aData.invoiceDate;
											                        	}
											                            return '<a target="_blank" href="openpdf?ftype=BillingSummary&fn='+ oObj.aData.billSummaryPath+'" id="billSummary"><img width="30" height="30" alt="download" src="../assets/img/pdfdl.jpg"/>';
											                        }
											                       },
											                       {    
																		"sTitle" : "Call Details",
																		sDefaultContent : "n/a",
												                        "bSearchable": false,
												                        "bSortable": false,
												                        "fnRender": function (oObj)                              
												                        {								
												                        	if(oObj.aData.callDetailPath!="N/A"){
												                        	return '<a target="_blank" href="openpdf?ftype=CallDetail&fn='+ oObj.aData.callDetailPath+'" id="callDetails"><img width="30" height="30" alt="download" src="../assets/img/pdfdl.jpg"/></a>';
												                        	} else {
												                        		return "N/A";
												                        	}
												                        }
												                       }
																]
													}).css('width', '100%');
									$("#result").show();
									//$("#viewcompilation").show();
									scrollResult();
									$(function() {
										var oSettings = oTable.fnSettings();
										if (oSettings.fnRecordsTotal() <= 50) {
											$(
													"#resultMTrackSearch_paginate")
													.hide();						
											
											
											
											if (oSettings.fnRecordsTotal() == 0) {
												$("#generateInvoice").hide();
												$("#viewInvoice").hide();
												//$("#compilationDiv").hide();
											} else {
												//$("#compilationDiv").show();
											}
											
											if(companySelected!=null && companySelected!="" && monthSelected!=null && monthSelected!="" && billingYear!=null && billingYear!=""){
												if(oSettings.fnRecordsTotal() > 0 && !newflag){
													$("#invoiceNo").val (iNo);
													$('a[href*="openpdf?ftype=invoice&fn=####"]').attr('href', function(i,href) {													       
													    return href.replace('openpdf?ftype=invoice&fn=####', 'openpdf?ftype=invoice&fn='+iPath);
													});
													$("#viewInvoice").show();
													$("#generateInvoice").hide();
												}	else if(oSettings.fnRecordsTotal() > 0 && newflag){
													$("#generateInvoice").show();
													$("#invoiceNumberVal").val (iNo);
													$("#invoiceDateVal").val (iDate);
												}
												
												var num = $("#fleetNum").val();												
												
												if(num < 0) {
													getUnbilledNumberAjax(monthSelected,billingYear,companySelected);
												} else {
													$("#resultUnbilled").hide();
												}
												
											}
											
										}
									});

								}
							});
					
						//calling for resultUnbilled
					
					} else {
						alert("Please provide at least one search criteria");
					}
					return false;
					//$('#resultMTrackSearch > tbody:last').after('<tr><td colspan="6">Billing Summary & Call Details Compilation</td><td><a target="_blank" href="getcompiledpdf?ftype=billSum" id="billSumCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/></td><td><a target="_blank" href="getcompiledpdf?ftype=callDetail" id="callDetailCompilation"><img width="30" height="30" alt="download" src="${pageContext.request.contextPath}/assets/img/pdfdl.jpg"/></td></tr>');
				});

function validateInput(){
	var msisdnSelected = $("#msisdnSearch").val(),
	companySelected = $("#companySelected").val(), 
	monthSelected = $("#monthSelected").val(),
	billingYear = $("#billingYear").val();
	
	if((msisdnSelected!=null && msisdnSelected!="") || (companySelected!=null && companySelected!="") || (monthSelected !=null && monthSelected!="") || (billingYear!=null && billingYear!="")){
		return true;
	} else {
		return false;
	}
}

function scrollResult(){
//	$('html, body').animate({
//		scrollTop: $("#result").offset().top
//	}, 2000);
	
//	$(document).scrollTop($("#result").offset().top);
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

function generateInvoiceAjax(){
	
	 var str = $("#queryMTrackHistory").serialize();
	
	$.ajax({
		dataType : 'json', type : "POST", url : "generateInvoiceData?" + Math.floor((Math.random() * 100000) + 1),
		data : str,
		error : function(xhr, msg, thrownError) {
			if (xhr.status == "200") {
			alert("Your session time has expired. You will now be redirected to login page.")						
			window.location = "../login?error=invalidsession";
		} else {
			window.location = "../deniedpage?error=" + thrownError;
		}
		},	
		success : function(obj) {
			
			//alert(msg);
			//var obj = $.parseJSON(msg);
			
			if(obj.state=="SUCCESS"){
				
			$("#invoiceNo").val (obj.invoiceNum);
			$('a[href*="openpdf?ftype=invoice&fn=####"]').attr('href', function(i,href) {													       
			    return href.replace('openpdf?ftype=invoice&fn=####', 'openpdf?ftype=invoice&fn='+obj.invfileName);
			});
			$("#viewInvoice").show();
			$("#generateInvoice").hide();
			} else {
				alert(obj.errorMsg);
				return false;
			}
		}
	});
}


function getUnbilledNumberAjax(month,year,comp){		
	
	var unbilled="";
	
	$
	.ajax({
		dataType : 'json',
		type : "POST",
		url : "queryMTrackHistoryUnbilled?"
				+ Math
						.floor((Math.random() * 100000) + 1),
		error : function(xhr, msg, thrownError) {

			//alert(xhr.status);

			if (xhr.status == "200") {
				alert("Your session time has expired. You will now be redirected to login page.")
				window.location = "../login?error=invalidsession";
			} else {
				window.location = "../deniedpage?error="
						+ thrownError;
			}
		},
		success : function(msg) {
			unbilled = msg;		
			$("#msisdnUnbilled").val(msg);
			if(unbilled!=null && unbilled!=""){
				$("#resultUnbilled").show();
			} else {
				$("#resultUnbilled").hide();
			}
		}
	});
	
	
	
	
}

</script>
