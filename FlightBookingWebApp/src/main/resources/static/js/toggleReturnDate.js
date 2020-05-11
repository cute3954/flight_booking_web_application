// 往復を選択した場合、帰り日入力欄を表示する
$(document).on('click', '.roundtrip', function() {
	$('.roundtrip').children('input').prop('checked', 'true');
	$('#returnDate').show();
})
$(document).on('click', '.one-way', function() {
	$('.one-way').children('input').prop('checked', 'true');
	$('#returnDate').hide();
})