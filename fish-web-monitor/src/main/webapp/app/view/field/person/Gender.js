
Ext.define('Eway.view.field.person.Gender', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.gender',
	
	fieldLabel : EwayLocale.commen.gender,
	name : 'gender',
	hiddenName : 'gender',
	msgTarget : 'side',
	store: 'GenderDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});