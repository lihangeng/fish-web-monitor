
Ext.define('Eway.view.field.person.UserStateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.userStateFilter',
	
	fieldLabel : EwayLocale.commen.type,
	name : 'userState',
	hiddenName : 'userState',
	msgTarget : 'side',
	store: 'person.user.UserStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	emptyText : EwayLocale.combox.select,
	queryMode : 'local'
		
	
});