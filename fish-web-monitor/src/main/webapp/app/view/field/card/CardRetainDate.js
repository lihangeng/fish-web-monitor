
Ext.define('Eway.view.field.card.CardRetainDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.card_CardRetainDate',
	
	fieldLabel : Eway.locale.card.eatCardTime,
	name : 'cardRetainTime',
	format : 'Y-m-d H:i:s'
});