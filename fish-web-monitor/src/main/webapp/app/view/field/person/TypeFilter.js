
Ext.define('Eway.view.field.person.TypeFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.typeFilter',
	
	fieldLabel : '类型',
	name : 'type',
	hiddenName : 'type',
	msgTarget : 'side',
	store: 'person.person.PersonTypeFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});