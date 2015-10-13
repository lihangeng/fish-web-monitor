Ext.define('Eway.view.field.case.NotifyWay', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_notifyWay',

	fieldLabel : Eway.locale.cases.caseFault.informWay,
	name : 'notifyWay',
	hiddenName : 'notifyWay',
	msgTarget : 'side',
	store : 'case.NotifyWay',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText : Eway.locale.combox.select,
	editable : false
});