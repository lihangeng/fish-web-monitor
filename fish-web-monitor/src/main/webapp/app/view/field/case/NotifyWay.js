Ext.define('Eway.view.field.case.NotifyWay', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_notifyWay',

	fieldLabel : EwayLocale.cases.caseFault.informWay,
	name : 'notifyWay',
	hiddenName : 'notifyWay',
	msgTarget : 'side',
	store : 'case.NotifyWay',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText : EwayLocale.combox.select,
	editable : false
});