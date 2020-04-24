$(document).on('click', '#fb_flightdate', function() {
	$('#ddayYear').val(0);
	$('#ddayMonth').val(0);
	var ddayOption = 'default';
	var ddayYear = $('#ddayYear').val();
	var ddayMonth = $('#ddayMonth').val();
	showCalendar(ddayYear, ddayMonth, ddayOption);
	$('#calendar_outbound').modal('show');
})

$(document).on('click', '.monthChange', function() {
	var ddayOption = $(this).attr('ddayOption');
	var ddayYear = $('#ddayYear').val();
	var ddayMonth = $('#ddayMonth').val();
	showCalendar(ddayYear, ddayMonth, ddayOption);
})

function showCalendar(ddayYear, ddayMonth, ddayOption) {
	$.ajax({
		url: "calendar",
		type: "POST",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data: {ddayYear: ddayYear, ddayMonth: ddayMonth, ddayOption: ddayOption},
		dataType: "json",
		success: function(data) {
			var html = "";
			var calendarLabel = data.ddayYear + "." + (data.ddayMonth + 1);
			$("#calendarLabel").html(calendarLabel);
			var calendarList = data.calendarList;
			html = appendCalendar(calendarList);
			$("#calendarDate").html(html);
			$("#ddayYear").val(data.ddayYear);
			$("#ddayMonth").val(data.ddayMonth);
		},
		error: function() {
			alert("error");
		}
	})
}

function appendCalendar(calendarList) {
	var html = "";
	var notThisMonthColor = "#BDBDBD";
	var sundayColor = "#FF0000";
	var weekdayColor = "#000000";
	var saturdayColor = "#0000FF";
	$.each(calendarList, function(key, item) {
		if (key % 7 == 0) {
			html += "<tr>";
		}
		html += "<td><div><div style='color: ";
		if (item.day > (key + 1) || (key - item.day) > 27) {
			html += notThisMonthColor;
		} else {
			if (key % 7 == 0) {
				html += sundayColor;
			} else if ((key % 7) > 0 && (key % 7) < 6) {
				html += weekdayColor;
			} else if (key % 7 == 6) {
				html += saturdayColor;
			}
		}
		html += "; font-weight: bold;'>" + item.day + "</div>";
		if (!(item.day > (key + 1) || (key - item.day) > 27)) {
			html += "<a class='selectDate' href='#' value='" + item.date + "'>指定</a>";
		}
		html += "</td>";
		
		if (key % 7 == 6) {
			html += "</tr>";
		}
	})
	return html;
}

$(document).on('click', '.selectDate', function() {
	var selectedDate = $(this).attr('value');
	$('#calendar_outbound').modal('hide');
	$('#fb_flightdate').val(selectedDate);
})
