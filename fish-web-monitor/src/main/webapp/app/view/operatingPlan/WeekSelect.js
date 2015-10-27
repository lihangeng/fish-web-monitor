
Ext.define('Eway.view.operatingPlan.WeekSelect', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_weekSelect',
	
	fieldLabel : '通知方式',
	name : 'week',
	hiddenName : 'week',
	msgTarget : 'side',
	store: 'operatingPlan.WeekSelect',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
		
	trigger1Cls : Ext.baseCSSPrefix + "form-clear-trigger",
	trigger2Cls : Ext.baseCSSPrefix + "form-arrow-trigger",
	onTrigger1Click : function() {
		this.setValue('');
	}
	
});