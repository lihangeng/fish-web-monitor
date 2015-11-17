
Ext.define('Eway.view.field.case.FaultStatus', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_faultStatus',
	
	fieldLabel : EwayLocale.cases.caseFault.faultState,
	name : 'faultStatus',
	hiddenName : 'faultStatus',
	msgTarget : 'side',
	store: 'case.FaultStatus',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
	
});