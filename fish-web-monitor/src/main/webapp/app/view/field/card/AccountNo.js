
Ext.define('Eway.view.field.card.AccountNo', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.card_AccountNo',

	fieldLabel : '卡号',
	name : 'accountNo',
	regex: /^\d{13,19}$/,
	regexText: '只能输入数字,13-19位',
	msgTarget : 'side',
	labelAlign : 'right'

});