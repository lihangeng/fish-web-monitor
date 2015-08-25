
Ext.define('Eway.view.field.atmType.CashtypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_atmType_cashtypeComboBox',
	
	fieldLabel : '非现金标志',
	name : 'cashtype',
	hiddenName : 'cashtype',
	msgTarget : 'side',
	store: 'machine.atmType.CashtypeComboBox',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: '--请选择--'
});