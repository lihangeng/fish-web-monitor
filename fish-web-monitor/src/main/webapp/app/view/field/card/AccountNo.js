
Ext.define('Eway.view.field.card.AccountNo', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.card_AccountNo',

	fieldLabel : EwayLocale.card.cardNum,
	name : 'accountNo',
	regex: /^\d{13,19}$/,
	regexText: EwayLocale.card.onlyNumber,
	msgTarget : 'side',
	labelAlign : 'right'

});