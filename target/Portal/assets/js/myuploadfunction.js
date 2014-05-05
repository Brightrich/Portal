$(function () {
	
	var filet=null;
	var idx=null;
    $('#fileupload').fileupload({
        dataType: 'json',
        
        done: function (e, data) {
        	$("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
            	
            	filet = file;
            	idx = index;
                /*$("#uploaded-files").append(
                		$('<tr/>')
                		.append($('<td/>').text(file.fileName))
                		.append($('<td/>').text(file.fileSize))
                		.append($('<td/>').text(file.fileType))
                		.append($('<td/>').html("<a href='rest/controller/get/"+index+"'>Click</a>"))
                		)*///end $("#uploaded-files").append()
            }); 
        },                
        
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);	        
	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );	    
	        
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