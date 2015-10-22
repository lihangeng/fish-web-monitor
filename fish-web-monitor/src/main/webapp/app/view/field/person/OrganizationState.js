Ext.define('Eway.view.field.person.OrganizationState', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field.organizationState',

	fieldLabel : Eway.locale.commen.state,
	name : 'organizationState',
	hiddenName : 'organizationState',
	msgTarget : 'side',
	store : 'person.organization.OrganizationStateDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText : Eway.locale.combox.select
});