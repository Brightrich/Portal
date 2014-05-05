<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${pageAttr.message !=null }">
	<div id="ModalMessage" title="Upload Result" style="display: none;">
		${pageAttr.message }</div>
</c:if>

<!-- /////////////////// Header has started from here /////////////////// -->
<%@ include file="../../component/header.jsp"%>
<!-- /////////////////// Header has ended here /////////////////// -->

<!-- /////////////////// Page Content Started from here /////////////////// -->


	<div class="container asu_cntr inner">
		<div class="row-fluid">
			<!-- <div class="promobox">
				
				<div class="promobox_content"> -->
				
	<div class="tabbable">
		<ul class="nav nav-tabs" id="myTab">			
			<li><a href="#batch">Batch Process</a></li>
			<li><a href="#single">Single Process</a></li>
		</ul>
				
	<div class="tab-content">
	
		<!-- <div class="tab-pane active" id="dragdrop">
				<div class="fancy_box">
            	<div class="fancy_header">
                	<div class="fancy_header_text"><h5>MTrack Batch Files Landing Zone</h5></div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
                	<div class="span9">
                	
					<input id="fileupload123" type="file" name="files[]" data-url="uploadDD" multiple>
	
					<div id="dropzone" class="fade well">Drop files here</div>
	
					<div id="progress" class="progress">
				    	<div class="bar" style="width: 0%;"></div>
					</div>

					</div>
                </div>
            	</div>				
		</div> -->
	
		<div class="tab-pane" id="batch">
				<div class="fancy_box">
            	<div class="fancy_header">
                	<div class="fancy_header_text"><h5>MTrack Batch Files</h5></div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
                	<div class="span9">
                	<form method="POST" name="batchMtrackBilling" id="batchMtrackBilling" action="batchMtrackBilling" enctype="multipart/form-data">
						<div class="span9">
							<p>Please provide the batch file to proceed billing extraction.</p>
						</div>
						<div class="row-fluid">
							<div class="span9">
							
								<label for="batchFile">Batch File</label> 
								<input type="file" name="batchFile" id="batchFile" class="text ui-widget-content ui-corner-all" multiple/>
								<input id="uploadBatch" type="submit" value="Upload" class="btn">
							</div>
	
						</div>
					</form>

					</div>
                </div>
            	</div>				
		</div>		
		
		<div class="tab-pane" id="single">		
				<div class="fancy_box">
            	<div class="fancy_header">
                	<div class="fancy_header_text"><h5>Create Single MTrack Billing</h5></div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
                	<div class="span9">
				<form method="POST" name="queryMTrackData" id="queryMTrackData" action="#" enctype="multipart/form-data">
				
					<div class="span9">
						<p>Please provide the customer MSISDN number to proceed.</p>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<input type="text" name="msisdnSearch" id="msisdnSearch"
								value="${pageAttr.msisdnSearch }"
								style="width: 50%; height: 5%;" placeholder="MSISDN *"
								title="MSISDN *" /> <input id="Search" type="button"
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
							<table id="resultMTrackSearch" align="left"
								class="features-table">
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
	</div>
	</div>



			</div>
			<!-- </div>
			</div>-->
			</div>
			</div> 
			<!-- <div class="container asu_cntr"> -->
			<jsp:include page="../../component/footer.jsp" />
			<jsp:include page="../../component/script.jsp" />


<div id="dialog-form" title="Create new billing"
	style="width: 610; height: 500">
	<p class="validateTips">All form fields are required.</p>

	<form method="POST" name="insertMTrackData" id="insertMTrackData"
		action="insertMtrackBilling" enctype="multipart/form-data">

		<fieldset>
			<!-- <label for="name">Billing Date</label>
			<input type="text" name="dateSelectedVal" id="dateSelectedVal" class="text ui-widget-content ui-corner-all" style="width: 50%; height: 5%;" />
			<label for="totalBilling">Total Billing</label>
			<input type="text" name="totalBillingVal" id="totalBillingVal" class="text ui-widget-content ui-corner-all" style="width: 50%; height: 5%;"/>
			 -->
			<label for="billingSummary">Billing Summary</label> 
			<input type="file" name="files" id="files1" class="text ui-widget-content ui-corner-all" /> 
			<label for="callDetail">Call Detail</label> <input type="file" name="files" id="files2" class="text ui-widget-content ui-corner-all" /> 
			<input type="hidden" name="fleetIdSelectedVal" id="fleetIdSelectedVal" class="text ui-widget-content ui-corner-all" />
		</fieldset>
	</form>
</div>

<script type="text/javascript">
$(function(){
	  $("#uploadBatch").click(function(evt) {
	   
		  var file = $("#batchFile").val();
			if (file == null || file == ""){
				alert("Batch files required!");
				return false;				
			} else {
				return true;
			}  
		
	 });
	});


	$(document).ready(function() {
		$("#ModalMessage").dialog({
			modal : true,
			hide : {
				effect : 'fold',
				duration : 1000
			},
			show : {
				effect : 'highlight',
				duration : 500
			},
			position : [ ($(window).width() / 2) - (400 / 2), 200 ],
			height : 300,
			width : 400,
		});
	});

	$(document).ready(function() {
		$("#result").hide();

	});

	/* $(function() {
	 $( "#dateSelectedVal" ).datepicker();
	 }); */

	var oTable;
	$('#Search')
			.click(
					function() {
						var row = 0;

						var searchText = $("#msisdnSearch").val();
						if (searchText != null && searchText != "") {

							$("#result").hide();
							var str = $("#queryMTrackData").serialize();
							//alert(str);
							$("#LoadingImageDiv").show();
							$
									.ajax({
										//async: false,
										//contentType: "application/json; charset=utf-8",
										dataType : 'json',
										type : "POST",
										url : "queryMTrackData?"
												+ Math
														.floor((Math.random() * 100000) + 1),
										data : str,
										/* statusCode : {
											302 : function() {
												alert("INVALID");
												window.location = "../login?error=invalidsession";
											}
										}, */
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
																			"mData" : "destroyed",
																			sDefaultContent : "n/a"
																		},
																		{
																			"sTitle" : "Company",
																			"mData" : "company",
																		},
																		{
																			"sTitle" : "Door Number",
																			"mData" : "doorNumber",
																			sDefaultContent : "n/a"
																		} ],
																"aoColumnDefs" : [ {
																	"fnRender" : function(
																			oObj,
																			val) {

																		return '<a href="'+ oObj.aData.fleetId+'" id="create-billing">'
																				+ oObj.aData.msisdn
																				+ '</a>';
																	},
																	"aTargets" : [ 0 ]
																}

																]
															}).css('width',
															'100%');
											$("#result").show();
											scrollResult();
											$(function() {
												var oSettings = oTable
														.fnSettings();
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
							alert("MSISDN cannot be empty");
						}
					});

	$(function() {
		/*  dateSelectedVal = $( "#dateSelectedVal" ),
		totalBillingVal = $( "#totalBillingVal" ), */
		var files1 = $("#files1"), 
		//files2 = $("#files2"),
		//callDetail = $( "#callDetail" ),

		allFields = $([]).add(files1)
		//.add(files2)
		, tips = $(".validateTips");
		function updateTips(t) {
			tips.text(t).addClass("ui-state-highlight");
			setTimeout(function() {
				tips.removeClass("ui-state-highlight", 1500);
			}, 500);
		}
		function checkLength(o, n, min, max) {

			if (o.val().length > max || o.val().length < min) {
				o.addClass("ui-state-error");
				updateTips("Field " + n + " cannot be empty.");
				return false;
			} else {
				return true;
			}
		}
		function checkRegexp(o, regexp, n) {
			if (!(regexp.test(o.val()))) {
				o.addClass("ui-state-error");
				updateTips(n);
				return false;
			} else {
				return true;
			}
		}
		$("#dialog-form").dialog(
				{
					autoOpen : false,
					hide : {
						effect : 'puff',
						duration : 1500
					},
					show : {
						effect : 'explode',
						duration : 1000
					},
					position : [ ($(window).width() / 2) - (610 / 2), 100 ],
					height : 500,
					width : 610,
					modal : true,
					buttons : {
						"Create new billing" : function() {
							var bValid = true;
							allFields.removeClass("ui-state-error");
							/* bValid = bValid && checkLength( dateSelectedVal, "Billing Date", 1, 20 );
							bValid = bValid && checkLength( totalBillingVal, "Total Billing", 1, 80 );
							 */
							bValid = bValid
									&& checkLength(files1, "Billing Summary",
											1, 200);
							//bValid = bValid && checkLength(files2, "Call Detail", 1, //200);

							//bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
							// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
							//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
							//bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
							if (bValid) {
								/*  $("#dateSelectedVal").val ($("#dateSelected").val());
								 $("#totalBillingVal").val ($("#totalBilling").val());
								 $("#billingSummaryVal").val ($("#billingSummary").val());
								 $("#callDetailVal").val ($("#callDetail").val());
								 alert("before submit");
								alert($("#dateSelectedVal").val());
								alert($("#totalBillingVal").val());
								alert($("#billingSummaryVal").val());
								alert($("#callDetailVal").val()); */

								$("#insertMTrackData").submit();
								$(this).dialog("close");
								/* var str = $("#insertMTrackData").serialize();
								alert(str);
								//var  password = $( '#password' );
								$.ajax({
								            type: "POST",
								            url: "insertMtrackBilling",
								            data: str,
								            success: function(){
								                $( this ).dialog( "close" );
								            }}) */

							}
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					},
					close : function() {
						//allFields.val( "" ).removeClass( "ui-state-error" );
					}
				});
		$("#create-billing").live({
			click : function() {
				$("#fleetIdSelectedVal").val($(this).attr("href"));
				$("#dialog-form").dialog("open");
				return false;
			}
		});
	});
	function scrollResult() {
		//			$('html, body').animate({
		//				scrollTop: $("#result").offset().top
		//			}, 2000);

		//			$(document).scrollTop($("#result").offset().top);
		var res = document.getElementById("result");
		var left = getOffset(res).left;
		var top = getOffset(res).top;
		window.scroll(left, top);
	}
	function getOffset(el) {
		var _x = $(el).offset().left;
		var _y = $(el).offset().top;
		return {
			top : _y,
			left : _x
		};
	}
</script>
