
Ext.define('Eway.view.field.person.Type', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.type',
	
	fieldLabel : '类型',
	name : 'type',
	hiddenName : 'type',
	msgTarget : 'side',
	store: 'person.person.PersonTypeDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});