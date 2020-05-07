// 予約するチケットを選択した場合、予約するボタンを活性化する
$(document).on('click', '.outbound_check', function() {
	if ($(this).prop('checked')) {
		$('#bookingBtn').prop('disabled', false);
	} else {
		$('#bookingBtn').prop('disabled', true);
	}
})

// 指定した条件によって、決済金額と予約画面のurlを変える
$(document).on('change', '.outbound_check', function() {
	var ticketNo = $(this).val();
	var isRoundTrip = $(this).closest('.req').find('.isroundtrip').val();
	
	// 決済する金額を設定
	var ticketPrice = $(this).parents('.flightno_' + ticketNo).find('._price').text();
	$('#jpyPrice').val(ticketPrice);
	
	// 決済完了後、予約画面に遷移するためのurl作り
	var originalUrl = "http://localhost:8080/flight/booking-success?";
	var returnUrl = originalUrl + 'fb_flightno=' + ticketNo + "&isroundtrip=" + isRoundTrip;
	$('.returnUrl').val(returnUrl);
})