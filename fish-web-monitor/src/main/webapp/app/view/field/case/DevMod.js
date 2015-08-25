
Ext.define('Eway.view.field.case.DevMod', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_devMod',
	
	fieldLabel : '故障状态',
	name : 'devMod',
	hiddenName : 'devMod',
	msgTarget : 'side',
	store: 'case.DevMod',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: '--请选择--',
	editable : false
	
});