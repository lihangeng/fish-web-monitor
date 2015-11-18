
Ext.define('Eway.view.field.person.OrganizationType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_organizationType',

	fieldLabel : EwayLocale.person.bankOrg.orgType,
	name : 'organizationType',
	hiddenName : 'organizationType',
	msgTarget : 'side',
	store: 'person.organization.OrganizationTypeDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: EwayLocale.combox.select
});