
// Default ajax settings.

$.ajaxSetup({
	method: "get",
	dataType: "json"
});
$(document).ajaxError(function(jqXHR, textStatus, errorThrown) {
	alert("Something went wrong with the ajax request. See the console for details.");
	console.log(jqXHR, testStatus, errorThrown);
});

	
$(function(){
	
	// Make logout button working.
	$(".confirm").on("click", function(){
		return confirm("Are you sure you want to log out?");
	});
	
});

// Statistics page.
$(function(){
	if ($(".siteContent.statistics").length == 0) {
		return;
	}
	
	$.ajax({
		url: "rest/statistics/foo"
	})
	.done(function(data) {
		if (data.code == 403) {
			return ajaxAuthorizationError();
		}
		if (data.code == 501) {
			return ajaxInternalServerError();
		}
		console.log(data);
		console.log("foo");
	});
});

function ajaxAuthorizationError() {
	alert("You are not logged in, so the request could not be completed.");
}
function ajaxInternalServerError() {
	alert("The server encountered an internal error.");
}