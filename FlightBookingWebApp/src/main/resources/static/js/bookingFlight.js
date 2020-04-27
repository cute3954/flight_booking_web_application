// 予約するチケットを選択した場合、予約するボタンを活性化する
$(document).on('click', '.outbound_check', function() {
	if ($(this).prop('checked')) {
		$('#bookingBtn').prop('disabled', false);
	} else {
		$('#bookingBtn').prop('disabled', true);
	}
})