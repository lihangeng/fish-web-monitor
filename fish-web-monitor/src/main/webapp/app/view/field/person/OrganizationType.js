
Ext.define('Eway.view.field.person.OrganizationType', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_organizationType',

	fieldLabel : '机构类型',
	name : 'organizationType',
	hiddenName : 'organizationType',
	msgTarget : 'side',
	store: 'person.organization.OrganizationTypeDict',
	valueField : 'value',
	displayField : 'display',
	queryMode : 'local',
	emptyText: '--请选择--'
});