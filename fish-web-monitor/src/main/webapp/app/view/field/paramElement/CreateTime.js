
Ext.define('Eway.view.field.paramElement.CreateTime', {
	extend: 'Ext.ux.form.DateTimeField',
	alias: 'widget.field_paramElement_CreateTime',

	fieldLabel : '创建时间',
	name : 'createTime',
	format : 'Y-m-d H:i:s',
	editable : false,
	maxValue : Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d H:i:s'),"Y-m-d H:i:s"),
//	value : Ext.Date.add(new Date(), Ext.Date.DAY, 0)

});