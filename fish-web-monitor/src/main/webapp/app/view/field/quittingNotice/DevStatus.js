
Ext.define('Eway.view.field.quittingNotice.DevStatus', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_devStatus',

	fieldLabel : Eway.locale.commen.gender,
	name : 'devStatus',
	hiddenName : 'devStatus',
	msgTarget : 'side',
	store: 'machine.quittingNotice.DevStatusDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local'

});