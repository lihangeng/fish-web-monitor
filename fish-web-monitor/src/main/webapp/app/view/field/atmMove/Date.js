
Ext.define('Eway.view.field.atmMove.Date', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field_date',
	
	fieldLabel : EwayLocale.machine.atmMove.recoverDate,
	format: 'Y-m-d',
	name : 'date',
	id:'dateId'
});