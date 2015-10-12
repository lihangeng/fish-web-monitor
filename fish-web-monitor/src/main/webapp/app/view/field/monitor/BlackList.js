
Ext.define('Eway.view.field.monitor.BlackList', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_blackList',
	
	fieldLabel : Eway.locale.monitor.business.blackList.black,
	name : 'blacklist',
	hiddenName : 'blacklist',
	msgTarget : 'side',
	store: 'monitor.transaction.BlacklistDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
		emptyText: Eway.locale.combox.select,
		editable : false
});