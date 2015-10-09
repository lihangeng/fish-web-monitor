
Ext.define('Eway.view.field.person.UserStateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.userStateFilter',
	
	fieldLabel : Eway.locale.commen.type,
	name : 'userState',
	hiddenName : 'userState',
	msgTarget : 'side',
	store: 'person.user.UserStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	emptyText : Eway.locale.combox.select,
	queryMode : 'local'
		
	
});