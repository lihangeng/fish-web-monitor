
Ext.define('Eway.view.operatingPlan.PlanState', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_planState',
	
	fieldLabel : Eway.locale.cases.informWay,
	name : 'planState',
	hiddenName : 'planState',
	msgTarget : 'side',
	store: 'operatingPlan.PlanState',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
		
	trigger1Cls : Ext.baseCSSPrefix + "form-clear-trigger",
	trigger2Cls : Ext.baseCSSPrefix + "form-arrow-trigger",
	onTrigger1Click : function() {
		this.setValue('');
	}
	
});