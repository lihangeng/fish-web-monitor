
Ext.define('Eway.view.field.PersonRemark', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field_personRemark',

	name : 'remark',
	fieldLabel: '<font color="red"></font> '+EwayLocale.commen.remark,
	xtype:'field_personRemark',
	regexText: EwayLocale.vtype.personRemark,
	height:60,
	msgTarget : 'side',
	grow:true,
	anchor : '100%'

});