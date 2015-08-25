
Ext.define('Eway.view.field.monitor.BlackList', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_blackList',
	
	fieldLabel : '黑名单卡',
	name : 'blacklist',
	hiddenName : 'blacklist',
	msgTarget : 'side',
	store: 'monitor.transaction.BlacklistDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
		emptyText: '--请选择--',
		editable : false
});