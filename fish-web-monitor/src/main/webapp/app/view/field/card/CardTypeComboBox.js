
Ext.define('Eway.view.field.card.CardTypeComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_CardTypeComboBox',
	
	name : 'cardType',
	fieldLabel : EwayLocale.card.IDType,
	store : 'monitor.card.CardTypeComboBox',
	queryMode: 'local',
	displayField: 'display',
	valueField: 'value',
	msgTarget : 'side'
});