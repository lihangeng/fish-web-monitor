
Ext.define('Eway.view.field.quittingNotice.StopType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_stopType',

	fieldLabel : Eway.locale.machine.quittingNotice.closeType,
	name : 'stopType',
	hiddenName : 'stopType',
	msgTarget : 'side',
	store: 'machine.quittingNotice.StopTypeDict',
	valueField : 'value',
	emptyText : Eway.locale.combox.select,
	displayField : 'display',
	queryMode : 'local',
	emptyText: Eway.locale.combox.select

});