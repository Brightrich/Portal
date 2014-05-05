	// JavaScript Document
	jQuery(document).ready(function($){		
	
		wc_searchbtn();
		wc_services_anm();
		wc_slidersInit();
		wc_test_client_tabs();
		wc_bs_accordian();
		wc_filterable();             //  for filter portfolio		
		wc_contacyus_message();             //  for filter portfolio		
		

		// this is customization of Bootstrap components
		jQuery('.accordion').on('shown', function () {
		 jQuery(this).find('.in').parent().find('.accordion-heading .accordion-toggle').css('background-position','right -129px');
		 jQuery(this).find('.in').parent().find('.accordion-heading .accordion-toggle').css('background-color','#f6f6f6');		 
		})

		jQuery('.accordion').on('hidden', function () {
			 jQuery(this).find('.accordion-heading .accordion-toggle').css('background-position','right -18px');
			 jQuery(this).find('.accordion-heading .accordion-toggle').css('background-color','#ffffff');
		})
		// enable tabs
		jQuery('.nav-tabs a').click(function (e) {
		    e.preventDefault();
    		jQuery(this).tab('show');
    	})
		// enable tooltips
		jQuery('.sc').tooltip();
		
		// have to change this script in wordpress version
		jQuery("a[data-gal^='prettyPhoto']").prettyPhoto({
			animation_speed:'fast',
			slideshow:false, 
			hideflash: true,
			show_title: false,
		});		
	});//jQuery(document).ready(function($){

	jQuery(window).load(function(){
		if (jQuery('.clientslider4home').length>0) { jQuery('.clientslider4home').hide(); }
	});//jQuery(window).load(function(){

	
	//===========================================================================================	
	function wc_contacyus_message(){
		if (jQuery('#contactFrm').length>0) {		
			var me = getUrlVars()["message"];
			if(me == 'yes'){
				jQuery('#msg_success').show();
			} 
			if(me == 'no'){				
				jQuery('#msg_failed').show();
			}		
		}
	
	}
	
	//===========================================================================================	
	function getUrlVars()
		{
			var vars = [], hash;
			var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
			for(var i = 0; i < hashes.length; i++)
			{
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
			return vars;
		}
		


	//===========================================================================================
	
	function wc_filterable(){		
		jQuery('#filters li a').click(function(){			
			jQuery('#filters li').removeClass('active');			
			jQuery(this).parent().addClass('active');			
			filterfor = jQuery(this).attr('class');			
			jQuery('.ourHolder .prt_item').animate({ opacity: 1.0 }, 100);			
			if ( filterfor != 'all' ){
				jQuery('.ourHolder .prt_item').animate({ opacity: 0.1 }, 100);//.animate({
				jQuery('.ourHolder .prt_item').each(function(){									
					//alert(jQuery(this).attr('class'));		
					if( jQuery(this).hasClass(filterfor) ) {
						//alert(filterfor);
						jQuery(this).animate({ opacity: 1.0 }, 400);//.animate({
						//jQuery(this).css('filter', 'alpha(opacity=10)' );
						//jQuery(this).css('opacity', '0.1');						 					     
					}//if( jQuery(this).attr('data-type') == filterfor ) {													 
				});				
			}//if ( jQuery(this).attr('data-type') != 'all' ){
		});				
	}//function wc_filterable
	
	
	//===========================================================================================
	function wc_bs_accordian(){
		jQuery('#test_client a').click(function(){
			currentTab = jQuery(this).attr('data-gal');
			jQuery('.cstm_tab a').removeClass('enabled');
			jQuery(this).addClass('enabled');
			jQuery('#test_slider, #client_slider, #test_slider_nav, #client_slider_nav').hide();
			jQuery('#'+currentTab).show();
			jQuery('#'+currentTab+'_nav').show();			
		});
			
	} //function wc_bs_accordian(){
		
	//===========================================================================================
	function wc_test_client_tabs(){
		jQuery('#test_client a').click(function(){
			currentTab = jQuery(this).attr('data-gal');
			jQuery('.cstm_tab a').removeClass('enabled');
			jQuery(this).addClass('enabled');
			jQuery('#test_slider, #client_slider, #test_slider_nav, #client_slider_nav').hide();
			jQuery('#'+currentTab).show();
			jQuery('#'+currentTab+'_nav').show();			
		});
			
	} //function wc_test_client_tabs(){

	//===========================================================================================
	function wc_slidersInit(){
		
		if (jQuery('.flexslider').length>0) {
			jQuery('.flexslider').flexslider({									
				animation: "slide",
				animationLoop: true,
				animation: "fade",
				pauseOnHover: true,
				slideshow: false,
				directionNav: false,			
			});//jQuery('.flexslider').flexslider({			
		}//if (jQuery('.flexslider').length>0) {
		
		if (jQuery('#latest_proj_slider').length>0) {
			jQuery('#latest_proj_slider').flexslider({									
				controlsContainer: "#latest_proj_nav",
				animation: "slide",
				animationLoop: true,
				controlNav: false,
				slideshow: false,
				prevText: "",
				nextText: "", 
				start: function(slider){ jQuery('#latest_proj_slider').removeClass('loading'); }	
			});//jQuery('.flexslider').flexslider({			
		}//if (jQuery('.ContentSlider').length>0) {


		if (jQuery('#latest_blog_slider').length>0) {
			jQuery('#latest_blog_slider').flexslider({									
				animation: "slide",
				animationLoop: true,
				controlNav: false,
				slideshow: false,
				controlsContainer: "#latest_blog_nav",
				prevText: "",
				nextText: "", 
				start: function(slider){ jQuery('#latest_blog_slider').removeClass('loading'); }	
			});//jQuery('.flexslider').flexslider({			
		}//if (jQuery('.ContentSlider').length>0) {

		if (jQuery('#test_slider').length>0) {
			jQuery('#test_slider').flexslider({									
				animation: "slide",
				animationLoop: true,
				controlNav: false,
				slideshow: false,
				controlsContainer: "#test_slider_nav",
				prevText: "",
				nextText: "", 
				start: function(slider){ jQuery('#test_slider').removeClass('loading'); }	
			});//jQuery('.flexslider').flexslider({			
		}//if (jQuery('.ContentSlider').length>0) {


		if (jQuery('#client_slider').length>0) {
			jQuery('#client_slider').flexslider({									
				animation: "slide",
				animationLoop: true,
				controlNav: false,
				slideshow: false,
				controlsContainer: "#client_slider_nav",
				prevText: "",
				nextText: "", 
				//start: function(slider){ jQuery('#client_slider').removeClass('loading'); }			
			});//jQuery('.flexslider').flexslider({			
		}//if (jQuery('.ContentSlider').length>0) {
			
			
				
	}//function wc_slidersInit(){
	


	//===========================================================================================
	function wc_services_anm () {
		
		
		var config = {    
			 sensitivity: 1, // number = sensitivity threshold (must be 1 or higher)    
			 interval: 10,  // number = milliseconds for onMouseOver polling interval    
			 over: servicesOpen,   // function = onMouseOver callback (REQUIRED)    
			 timeout: 10,   // number = milliseconds delay before onMouseOut    
			 out: servicesClose    // function = onMouseOut callback (REQUIRED)    
		};
		jQuery(".src_cntr").hoverIntent(config);	
		
		function servicesOpen() {							
			jQuery(this).find('.src_icon').animate({ marginTop: '-20px'}, 300);
			jQuery(this).find('.src_ring').animate({ marginTop: '-20px'}, 300);
			jQuery(this).find('.src_mask').animate({ opacity: 0.0, top: '135px' }, 500);
			jQuery(this).find('.src_txt_cntr').animate({ 'padding-top': '140px' }, 200);
		}	 
		function servicesClose() {			
			jQuery(this).find('.src_icon').animate({ marginTop: '0px'}, 300);
			jQuery(this).find('.src_ring').animate({ marginTop: '0px'}, 300);
			jQuery(this).find('.src_mask').animate({ opacity: 1, top: '125px' }, 200);
			jQuery(this).find('.src_txt_cntr').animate({ 'padding-top': '128px' }, 200);
		}	
		
	}



	//===========================================================================================
	function wc_searchbtn () {

		TriggerClick = 0;
		jQuery('#search_open').click(function(){
		if(TriggerClick==0){
			 TriggerClick=1;			 
			 
			 jQuery('.search_box').animate({
				width:'300px'
				}, 500, function() {
				jQuery('#search_open').css('background-position','-592px -226px');
				jQuery('.search_box_inner ').show();
			 });
			 
		}else{
			 TriggerClick=0;
			 jQuery('.search_box').animate({
				width:'0px'
				}, 500, function() {
				jQuery('#search_open').css('background-position','-591px -156px');
				jQuery('.search_box_inner ').hide();				
			 });
			};
	  	});
		
	}//	function wc_searchbtn () {