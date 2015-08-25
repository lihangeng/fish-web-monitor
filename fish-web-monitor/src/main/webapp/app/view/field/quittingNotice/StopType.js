
Ext.define('Eway.view.field.quittingNotice.StopType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_stopType',

	fieldLabel : '停机类型',
	name : 'stopType',
	hiddenName : 'stopType',
	msgTarget : 'side',
	store: 'machine.quittingNotice.StopTypeDict',
	valueField : 'value',
	emptyText : '--请选择--',
	displayField : 'display',
	queryMode : 'local',
	emptyText: '--请选择--'

});