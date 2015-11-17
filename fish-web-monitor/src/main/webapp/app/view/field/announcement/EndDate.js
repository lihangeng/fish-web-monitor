
Ext.define('Eway.view.field.announcement.EndDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field_endDate',
	
	fieldLabel : EwayLocale.commen.endValidty,
	name : 'endDate',
	format : 'Y-m-d'
	
});