
// Default ajax settings.

$.ajaxSetup({
	method: "get",
	dataType: "json"
});
$(document).ajaxError(function(jqXHR, textStatus, errorThrown) {
	alert("Something went wrong with the ajax request. See the console for details.");
	console.log(jqXHR, testStatus, errorThrown);
});
$(document).ajaxSuccess(function(jsonData, textStatus, jqXHR) {
	if (jsonData.code == undefined) {
		return;
	}
	if (jsonData.code == 403) {
		return ajaxAuthorizationError();
	}
	if (jsonData.code == 501) {
		return ajaxInternalServerError();
	}
});

	
$(function(){
	
	// Make logout button working.
	$(".confirm").on("click", function(){
		return confirm("Are you sure you want to log out?");
	});
	
});

google.load('visualization', '1.0', {'packages':['corechart']});
google.setOnLoadCallback(function(){

	// Statistics page.
	if ($(".siteContent.statistics").length == 0) {
		return;
	}
	
	var options = {
        //legend: {position: 'none'},
        is3D: true,
        height: 250,
        theme: 'maximized',
        tooltip: {isHtml: true},
        interpolateNulls: true,
        vAxis: {
            viewWindowMode: 'explicit',
            viewWindow: {
                //min: 0
            },
            format: '###'
        },
        hAxis: { 
            format: "EEEE d MMMM yyyy",
            allowContainerBoundaryTextCufoff: true
        }
    };
	
	// Get BMI statistics.
	$.ajax({
		url: "rest/statistics/bmi"
	})
	.done(function(jsonData) {
		if (jsonData.code != 200) {
			return;
		}
		
		if (jsonData.data.length == 0) {
			$("#chart_bmi").html("U heeft uw BMI nog niet berekend.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Datum", type: "date"},
		       {label: "Uw BMI", type: "number"},
		       {label: "Gemiddeld BMI", type: "number"}
	        ],
	        rows: new Array()
		};
		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {
			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString("nl-NL", {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
			                   {v: jsonData.data[i][1], f: "" + (Math.round(jsonData.data[i][1] * 10) / 10)},
			                   {v: jsonData.data[i][2], f: "" + (Math.round(jsonData.data[i][2] * 10) / 10)}]};
		}
		
		// Create chart.
		var dataTable = new google.visualization.DataTable(data);
		var chart = new google.visualization.LineChart($('#chart_bmi')[0]);
		options.title = "BMI";
        chart.draw(dataTable, options);
	});
	

	// Get fat percentage.
	$.ajax({
		url: "rest/statistics/fat"
	})
	.done(function(jsonData) {
		if (jsonData.code != 200) {
			return;
		}
		
		if (jsonData.data.length == 0) {
			$("#chart_fat").html("U heeft uw vetpercentage nog niet berekend.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Datum", type: "date"},
		       {label: "Uw vetpercentage", type: "number"},
		       {label: "Gemiddeld vetpercentage", type: "number"}
	        ],
	        rows: new Array()
		};

		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {

			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString("nl-NL", {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
							   {v: jsonData.data[i][1], f: "" + (Math.round(jsonData.data[i][1] * 10) / 10)},
							   {v: jsonData.data[i][2], f: "" + (Math.round(jsonData.data[i][2] * 10) / 10)}]};
		}
		
		// Create chart.
		var dataTable = new google.visualization.DataTable(data);
		var chart = new google.visualization.LineChart($('#chart_fat')[0]);
		options.title = "Vetpercentage";
        chart.draw(dataTable, options);
	});
});

function ajaxAuthorizationError() {
	alert("You are not logged in, so the request could not be completed.");
}
function ajaxInternalServerError() {
	alert("The server encountered an internal error.");
}