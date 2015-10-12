
Ext.define('Eway.view.field.atmMove.Date', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field_date',
	
	fieldLabel : Eway.locale.machine.atmMove.recoverDate,
	format: 'Y-m-d',
	name : 'date',
	id:'dateId'
});