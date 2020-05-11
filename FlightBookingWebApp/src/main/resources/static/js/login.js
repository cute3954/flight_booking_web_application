// JQueryでinputのtypeを変更する方法
// https://flipclap.co.jp/473/
// パスワード表示
$(".fa-eye-slash").on({
    'click': function() {
        $('.userpwd').get(0).type = 'text';
    }
});
// パスワード非表示
$(".fa-eye").on({
    'click': function() {
        $('.userpwd').get(0).type = 'password';
    }
});