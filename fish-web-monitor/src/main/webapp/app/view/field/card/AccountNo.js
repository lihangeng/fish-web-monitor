
Ext.define('Eway.view.field.card.AccountNo', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.card_AccountNo',

	fieldLabel : Eway.locale.card.cardNum,
	name : 'accountNo',
	regex: /^\d{13,19}$/,
	regexText: Eway.locale.card.onlyNumber,
	msgTarget : 'side',
	labelAlign : 'right'

});