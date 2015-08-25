
Ext.define('Eway.view.field.RoleName', {
	extend: 'Ext.form.field.Text',
	alias: 'widget.field_roleName',

	name : 'name',
	fieldLabel : '<font color="red">*</font> 角色名称',
	allowBlank : false,
	regex : /^[a-zA-Z0-9\u4E00-\u9FA5]{0,40}$/,
	regexText : '由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位',
	xtype : 'field_roleName',
	width : 600,
	msgTarget : 'side'

});