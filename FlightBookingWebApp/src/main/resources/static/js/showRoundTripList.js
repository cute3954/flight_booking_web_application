function showReturnTicketList() {
	// getElementsByClassNameの返り値はHTMLCollectionという名のオブジェクトになるためIdを指定
	// 参照：https://qiita.com/rindarinda5/items/c26dc81fe8cd98992dc1
	// jqueryで処理しようか、、
	document.getElementById('returnTicketList').style.display = 'block';
}