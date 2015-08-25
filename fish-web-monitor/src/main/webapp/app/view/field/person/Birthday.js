
Ext.define('Eway.view.field.person.Birthday', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field.birthday',
	
	fieldLabel : '生日',
	name : 'birthday',

	format : 'Y-m-d'
	
});