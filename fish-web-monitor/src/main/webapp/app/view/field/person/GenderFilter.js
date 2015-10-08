
Ext.define('Eway.view.field.person.GenderFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.genderFilter',
	
	fieldLabel : Eway.locale.person.bankPer.gender,
	name : 'gender',
	hiddenName : 'gender',
	msgTarget : 'side',
	store: 'person.person.GenderFilterDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});