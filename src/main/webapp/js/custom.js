$(document).ready(function(){
	$("#Log").click(function(){
    	if ($("#signContent").is(":visible")){
        	$("#signContent").hide();
        }
        $("#logContent").show();
    });
    $("#sign").click(function(){
    	if ($("#logContent").is(":visible")){
        	$("#logContent").hide();
        }
        $("#signContent").show();
    });
});

