
Ext.define('Eway.view.permission.role.ComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.role.comboBox',
	
	store:'permission.ComboBox',
	queryMode: 'local',
	fieldLabel:Eway.locale.permission.role.type,
	name:'type',
	 valueField: 'abbr',
	 displayField: 'name',
	msgTarget : 'side',
	emptyText: Eway.locale.combox.select
});