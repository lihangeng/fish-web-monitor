
Ext.define('Eway.view.field.RoleDescription', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field_roleDescription',

	name : 'description',
	fieldLabel: '<font color="red">*</font> '+Eway.locale.permission.role.description,
	xtype:'field_roleDescription',
	regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,99}$/,
	regexText: Eway.locale.tip.roleDescription,
	width:600,
	height:60,
	allowBlank:false,
	msgTarget : 'side',
	grow:true,
	anchor    : '100%'

});