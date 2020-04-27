// 往復を選択した場合、帰り日入力欄を表示する
$(document).on('click', '.isRoundTripRadio', function() {
	if ($('#rt_true').prop('checked')) {
		$('#returnDate').show();
	} else {
		$('#returnDate').hide();
	}
})