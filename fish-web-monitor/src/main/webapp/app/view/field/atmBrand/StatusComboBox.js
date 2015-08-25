
Ext.define('Eway.view.field.atmBrand.StatusComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_atmBrand_statusComboBox',

	
	fieldLabel : '生产商状态',
	name : 'status',
	hiddenName : 'status',
	msgTarget : 'side',
	store: 'machine.atmBrand.StatusComboBox',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: '--请选择--'
	
});