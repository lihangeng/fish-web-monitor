
Ext.define('Eway.view.field.card.CardStatusComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_cardStatusComboBox',
	
	store:'monitor.card.CardStatusComboBox',
	queryMode: 'local',
	fieldLabel:'卡片状态',
	name:'status',
	valueField: 'value',
	displayField: 'display',
	msgTarget : 'side',
	hiddenName : 'status',
	emptyText: '--请选择--'
});