
Ext.define('Eway.view.field.person.GenderFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.genderFilter',
	
	fieldLabel : '性别',
	name : 'gender',
	hiddenName : 'gender',
	msgTarget : 'side',
	store: 'person.person.GenderFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});