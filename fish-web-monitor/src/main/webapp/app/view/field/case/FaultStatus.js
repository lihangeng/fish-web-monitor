
Ext.define('Eway.view.field.case.FaultStatus', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_faultStatus',
	
	fieldLabel : '故障状态',
	name : 'faultStatus',
	hiddenName : 'faultStatus',
	msgTarget : 'side',
	store: 'case.FaultStatus',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	queryMode : 'local',
	emptyText: '--请选择--'
	
});