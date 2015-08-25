Ext.define('Eway.view.field.report.DeviceComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.report_DevicComboBox',

	fieldLabel : '按设备',
	name : 'isDevice',
	hiddenName : 'isDevice',
	msgTarget : 'side',
	store : 'monitor.report.DeviceComboBox',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	editable : false,
	emptyText : '--请选择--'
});