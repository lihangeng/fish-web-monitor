
Ext.define('Eway.view.field.card.ActionStatus',{
	extend:'Ext.form.field.ComboBox',

	alias:'widget.card_ActionStatus',

	store:'monitor.card.ActionStatus',
	queryMode: 'local',
	fieldLabel:'卡片状态',
	name:'status',
	valueField: 'value',
	displayField: 'display',
	msgTarget : 'side',
	emptyText: '--请选择--'
});