
Ext.define('Eway.view.field.atmType.CashtypeComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_atmType_cashtypeComboBox',
	
	fieldLabel : EwayLocale.machine.atmType.cashtype,
	name : 'cashtype',
	hiddenName : 'cashtype',
	msgTarget : 'side',
	store: 'machine.atmType.CashtypeComboBox',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
});