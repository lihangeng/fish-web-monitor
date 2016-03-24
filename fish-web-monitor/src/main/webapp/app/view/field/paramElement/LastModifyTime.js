
Ext.define('Eway.view.field.paramElement.LastModifyTime', {
	extend: 'Ext.ux.form.DateTimeField',
	alias: 'widget.field_paramElement_LastModifyTime',

	fieldLabel : '最后修改时间',
	name : 'lastModifyTime',
	format : 'Y-m-d H:i:s',
	editable : false,
	maxValue : Ext.Date.parse(Ext.Date.format(new Date(), 'Y-m-d H:i:s'),"Y-m-d H:i:s")

});