$(function () {
		
    $('#fileupload123').fileupload({
        dataType: 'json',
        
        done: function (e, data) {
        	
        },                
        
        progressall: function (e, data) {
        	alert("LOADED = " + data.loaded);
        	alert("TOTAL = " + data.total);
        	
	        var progress = parseInt(data.loaded / data.total * 100, 10);	        
	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );	    
	        
	        alert("PROGRESS = " + progress);
	        if(progress >= 100){
	        	getInfo(e);
	        }
	        
   		},
   		
		dropZone: $('#dropzone')
    });
    
    
});


function getInfo(e){		
	
	$
	.ajax({
		dataType : 'json',		
		type : "POST",
		url : "getinfo?"
				+ Math
						.floor((Math.random() * 100000) + 1),
		error : function(xhr, msg, thrownError) {

			alert(xhr.status);
			alert(msg);
			
		},
		success : function(data) {
			alert(data);
			
		}
	});
	
	
	
	
}