
Ext.define('Eway.view.field.PersonRemark', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field_personRemark',

	name : 'remark',
	fieldLabel: '<font color="red"></font> 备注',
	xtype:'field_personRemark',
	regex: /^[a-zA-Z0-9\u4E00-\u9FA5][a-zA-Z0-9-_\.\u4E00-\u9FA5]{0,199}$/,
	regexText: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位',
	width:600,
	height:60,
	msgTarget : 'side',
	grow:true,
	anchor    : '100%'

});