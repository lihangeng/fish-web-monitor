
Ext.define('Eway.view.field.card.ActionStatus',{
	extend:'Ext.form.field.ComboBox',

	alias:'widget.card_ActionStatus',

	store:'monitor.card.ActionStatus',
	queryMode: 'local',
	fieldLabel:Eway.locale.card.cardStatus,
	name:'status',
	valueField: 'value',
	displayField: 'display',
	msgTarget : 'side',
	emptyText: Eway.locale.combox.select
});