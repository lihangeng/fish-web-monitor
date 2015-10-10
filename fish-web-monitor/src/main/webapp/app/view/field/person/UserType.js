
Ext.define('Eway.view.field.person.UserType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_userType',

	fieldLabel : Eway.locale.person.user.userType,
	name : 'userType',
	hiddenName : 'userType',
	msgTarget : 'side',
	store: 'person.user.UserTypeDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	value:1,
	autoLoadOnValue:true,
	emptyText: Eway.locale.combox.select
});