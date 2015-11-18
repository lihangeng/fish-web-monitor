
Ext.define('Eway.view.field.card.StartDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.card_StartDate',
	
	fieldLabel : EwayLocale.card.startData,
	name : 'startData',
	format : 'Y-m-d',
//	editable : false,
	msgTarget : 'side',
	format : 'Y-m-d H:i:s'
	
});