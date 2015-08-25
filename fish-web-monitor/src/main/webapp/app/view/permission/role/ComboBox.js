
Ext.define('Eway.view.permission.role.ComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.role.comboBox',
	
	store:'permission.ComboBox',
	queryMode: 'local',
	fieldLabel:'角色类型',
	name:'type',
	 valueField: 'abbr',
	 displayField: 'name',
	msgTarget : 'side',
	emptyText: '--请选择--'
});