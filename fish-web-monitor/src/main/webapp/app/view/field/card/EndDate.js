
Ext.define('Eway.view.field.card.EndDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.card_EndDate',
	
	fieldLabel : Eway.locale.card.endData,
	name : 'endData',
	format : 'Y-m-d',
//	editable : false,
	msgTarget : 'side',
	format : 'Y-m-d H:i:s'
});