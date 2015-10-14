
Ext.define('Eway.view.field.announcement.EndDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field_endDate',
	
	fieldLabel : Eway.locale.commen.endValidty,
	name : 'endDate',
	format : 'Y-m-d'
	
});