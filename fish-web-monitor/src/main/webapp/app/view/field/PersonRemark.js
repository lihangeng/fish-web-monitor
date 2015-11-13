
Ext.define('Eway.view.field.PersonRemark', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field_personRemark',

	name : 'remark',
	fieldLabel: '<font color="red"></font> '+Eway.locale.commen.remark,
	xtype:'field_personRemark',
	regexText: Eway.locale.vtype.personRemark,
	width:600,
	height:60,
	msgTarget : 'side',
	grow:true,
	anchor    : '100%'

});