
Ext.define('Eway.view.field.case.FaultStatus', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_faultStatus',
	
	fieldLabel : Eway.locale.cases.caseFault.faultState,
	name : 'faultStatus',
	hiddenName : 'faultStatus',
	msgTarget : 'side',
	store: 'case.FaultStatus',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	queryMode : 'local',
	emptyText: Eway.locale.combox.select
	
});