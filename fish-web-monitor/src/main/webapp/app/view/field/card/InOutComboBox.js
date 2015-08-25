
Ext.define('Eway.view.field.card.InOutComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_InOutComboBox',
	
	name : 'inOut',
	fieldLabel : '在行/离行',
	store : 'monitor.card.InoutComboBox',
	queryMode: 'local',
	displayField: 'display',
	valueField: 'value',
	emptyText: '--请选择--'
});