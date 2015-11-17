
Ext.define('Eway.view.field.quittingNotice.StopType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_stopType',

	fieldLabel : EwayLocale.machine.quittingNotice.closeType,
	name : 'stopType',
	hiddenName : 'stopType',
	msgTarget : 'side',
	store: 'machine.quittingNotice.StopTypeDict',
	valueField : 'value',
	emptyText : EwayLocale.combox.select,
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select

});