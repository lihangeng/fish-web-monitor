
Ext.define('Eway.view.field.RoleName', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field_roleName',

	name : 'name',
	fieldLabel : '<font color="red">*</font> '+EwayLocale.permission.role.name,
	allowBlank : false,
	regex : /^[a-zA-Z0-9\u4E00-\u9FA5]{0,40}$/,
	regexText : EwayLocale.machine.device.roleName,
	xtype : 'field_roleName',
	width : 600,
	msgTarget : 'side'

});