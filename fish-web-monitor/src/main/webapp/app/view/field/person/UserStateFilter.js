
Ext.define('Eway.view.field.person.UserStateFilter', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field.userStateFilter',
	
	fieldLabel : '状态',
	name : 'userState',
	hiddenName : 'userState',
	msgTarget : 'side',
	store: 'person.user.UserStateFilterDict',
	valueField : 'value',
	displayField : 'display',
	emptyText : '--请选择--',
	queryMode : 'local'
		
	
});