
Ext.define('Eway.view.field.RoleDescription', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field_roleDescription',

	name : 'description',
	fieldLabel: EwayLocale.permission.role.description,
	width:600,
	height:60,
	allowBlank:false,
	msgTarget : 'side',
	maxLength:100,
	grow:true,
	anchor    : '100%'
});