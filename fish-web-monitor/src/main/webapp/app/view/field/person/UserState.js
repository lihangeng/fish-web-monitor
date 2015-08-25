
Ext.define('Eway.view.field.person.UserState', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.userState',
	
	fieldLabel : '状态',
	name : 'userState',
	hiddenName : 'userState',
	msgTarget : 'side',
	store: 'person.user.UserStateDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'
	
});