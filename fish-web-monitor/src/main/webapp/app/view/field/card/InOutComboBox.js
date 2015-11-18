
Ext.define('Eway.view.field.card.InOutComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_InOutComboBox',
	
	name : 'inOut',
	fieldLabel : EwayLocale.commen.insideOutside,
	store : 'monitor.card.InoutComboBox',
	queryMode: 'local',
	displayField: 'display',
	valueField: 'value',
	emptyText: EwayLocale.combox.select
});