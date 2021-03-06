
Ext.define('Eway.view.field.card.OrganizationComboBox',{
	extend:'Ext.form.field.ComboBox',
	
	alias:'widget.card_OrganizationComboBox',
	
	name : 'orgId',
	hiddenName : 'orgId',
	fieldLabel : EwayLocale.commen.orgNameBelongs,
	store : 'monitor.card.OrganizationComboBox',
	queryMode: 'local',
	displayField: 'name',
	valueField: 'id',
	emptyText: EwayLocale.combox.select
});