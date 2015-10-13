
Ext.define('Eway.view.version.field.Desc', {
	extend: 'Ext.form.field.TextArea',
	alias: 'widget.field.desc',
	
	fieldLabel : Eway.locale.version.View.remark,
	name : 'desc',
	maxLength : 40,
	autoScroll :true
	
});