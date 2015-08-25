
Ext.define('Eway.view.field.card.OrganizationComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_OrganizationComboBox',
	
	name : 'orgId',
	hiddenName : 'orgId',
	fieldLabel : '所属机构',
	store : 'monitor.card.OrganizationComboBox',
	queryMode: 'local',
	displayField: 'name',
	valueField: 'id',
	emptyText: '--请选择--'
});