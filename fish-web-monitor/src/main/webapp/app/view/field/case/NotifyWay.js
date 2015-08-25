Ext.define('Eway.view.field.case.NotifyWay', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_notifyWay',

	fieldLabel : '通知方式',
	name : 'notifyWay',
	hiddenName : 'notifyWay',
	msgTarget : 'side',
	store : 'case.NotifyWay',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText : '--请选择--',
	editable : false
});