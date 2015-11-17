
Ext.define('Eway.view.field.person.Birthday', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field.birthday',
	
	fieldLabel : EwayLocale.commen.birthday,
	name : 'birthday',

	format : 'Y-m-d'
	
});