
Ext.define('Eway.view.field.case.DevMod', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_devMod',
	
	fieldLabel : EwayLocale.cases.caseFault.faultState,
	name : 'devMod',
	hiddenName : 'devMod',
	msgTarget : 'side',
	store: 'case.DevMod',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select,
	editable : false
	
});