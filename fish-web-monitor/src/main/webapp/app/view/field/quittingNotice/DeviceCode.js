
Ext.define('Eway.view.field.quittingNotice.DeviceCode', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_deviceCode',

	fieldLabel : Eway.locale.machine.atmGroup.terminalId,
	name : 'deviceCode',
	regex:/^[a-zA-Z0-9-\.]{1,20}$/,
	hiddenName : 'deviceCode',
	msgTarget : 'side',
	minChars: 1,
	store: 'machine.quittingNotice.DeviceCode',
	queryMode: 'remote',
	triggerAction: 'query',
	valueField : 'terminalId',
    pageSize: 25,
	displayField : 'terminalId',
    hideTrigger:true

});