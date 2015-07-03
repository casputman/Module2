
// Default ajax settings.
$.ajaxSetup({
	method: "get",
	dataType: "json"
});
$(document).ajaxError(function(jqXHR, textStatus, errorThrown) {
	alert("Something went wrong with the ajax request. See the console for details.");
	console.log(jqXHR, textStatus, errorThrown);
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

// Settings.
var locale = "en-EN";

	
$(function(){
	
	// Make logout button working.
	$(".confirm").on("click", function(){
		return confirm("Are you sure you want to log out?");
	});
	
});

google.load('visualization', '1.0', {'packages':['corechart'], 'language': locale});
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
			$("#chart_bmi").html("No BMI data available yet.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Date", type: "date"},
		       {label: "Your BMI", type: "number"},
		       {label: "Average BMI of all users", type: "number"}
	        ],
	        rows: new Array()
		};
		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {
			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString(locale, {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
			                   {v: jsonData.data[i][1], f: "" + rnd(jsonData.data[i][1])},
			                   {v: jsonData.data[i][2], f: "" + rnd(jsonData.data[i][2])}]};
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
			$("#chart_fat").html("No fat percentage data available yet.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Date", type: "date"},
		       {label: "Your fat percentage", type: "number"},
		       {label: "Average fat percentage of all users", type: "number"}
	        ],
	        rows: new Array()
		};

		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {

			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString(locale, {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
							   {v: jsonData.data[i][1], f: "" + rnd(jsonData.data[i][1])},
							   {v: jsonData.data[i][2], f: "" + rnd(jsonData.data[i][2])}]};
		}
		
		// Create chart.
		var dataTable = new google.visualization.DataTable(data);
		var chart = new google.visualization.LineChart($('#chart_fat')[0]);
		options.title = "Fat percentage";
        chart.draw(dataTable, options);
	});
	

	
	// Get calorie statistics.
	$.ajax({
		url: "rest/statistics/calorie"
	})
	.done(function(jsonData) {
		if (jsonData.code != 200) {
			return;
		}
		if (jsonData.data.length == 0) {
			$("#chart_calorie").html("No calorie data available yet.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Date", type: "date"},
		       {label: "Calorie intake", type: "number"},
		       {label: "Calorie usage", type: "number"}
	        ],
	        rows: new Array()
		};
		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {
			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString(locale, {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
			                   {v: jsonData.data[i][1], f: "" + rnd(jsonData.data[i][1])},
			                   {v: jsonData.data[i][2], f: "" + rnd(jsonData.data[i][2])}]};
		}
		
		// Create chart.
		var dataTable = new google.visualization.DataTable(data);
		var chart = new google.visualization.LineChart($('#chart_calorie')[0]);
		options.title = "Your calorie intake and usage";
        chart.draw(dataTable, options);
	});

	
	// Get calorie statistics.
	$.ajax({
		url: "rest/statistics/sleep"
	})
	.done(function(jsonData) {
		if (jsonData.code != 200) {
			return;
		}
		if (jsonData.data.length == 0) {
			$("#chart_sleep").html("No sleep data is available yet.");
			return;
		}
		
		// Define columns.
		var data = {
			cols: [
		       {label: "Date", type: "date"},
		       {label: "Your hours of sleep", type: "number"},
		       {label: "Average hours of sleep of all users", type: "number"}
	        ],
	        rows: new Array()
		};
		// Put received data in the right format.
		for (var i = 0; i < jsonData.data.length; i++) {
			data.rows[i] = {c:[{v: new Date(jsonData.data[i][0]),
								f: new Date(jsonData.data[i][0]).toLocaleString(locale, {month: "long", weekday: "long", year: "numeric", day: "numeric" })},
			                   {v: jsonData.data[i][1], f: "" + rnd(jsonData.data[i][1])},
			                   {v: jsonData.data[i][2], f: "" + rnd(jsonData.data[i][2])}]};
		}
		
		// Create chart.
		var dataTable = new google.visualization.DataTable(data);
		var chart = new google.visualization.LineChart($('#chart_sleep')[0]);
		options.title = "Hours of sleep";
        chart.draw(dataTable, options);
	});
});

function ajaxAuthorizationError() {
	alert("You are not logged in, so the request could not be completed.");
}
function ajaxInternalServerError() {
	alert("The server encountered an internal error.");
}
function rnd(iNumber, iDecimals) {
	iDecimals = iDecimals||1;
	var aux = Math.pow(10, iDecimals);
	return Math.round(iNumber * aux) / aux;
}