
Ext.define('Eway.view.field.InstallDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field.installDate',
	
	fieldLabel : Eway.locale.commen.installDate,
	format: 'Y-m-d',
	name : 'installDate'
	
});