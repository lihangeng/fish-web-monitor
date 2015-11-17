
Ext.define('Eway.view.field.atmBrand.StatusComboBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_atmBrand_statusComboBox',

	
	fieldLabel : EwayLocale.machine.atmBrand.status,
	name : 'status',
	hiddenName : 'status',
	msgTarget : 'side',
	store: 'machine.atmBrand.StatusComboBox',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
	
});