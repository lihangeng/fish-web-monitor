Ext.define('Eway.view.field.OpenPlan.PlanStateType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_openPlan_planStateType',
	fieldLabel : '方案状态',
	name : 'planStateType',
	hiddenName : 'planStateType',
	store: 'operatingPlan.PlanStateType',
	valueField : 'value',
	displayField : 'display',
	editable : false,
	emptyText: Eway.locale.combox.select,
	listeners: {
		beforerender: function(){
			this.store.load();
		}
	}
});