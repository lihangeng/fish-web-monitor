
Ext.define('Eway.view.field.announcement.PublishDate', {
	extend: 'Ext.form.field.Date',
	alias: 'widget.field_publishDate',
	
	fieldLabel : Eway.locale.commen.publishDate,
	name : 'publishDate',
	format : 'Y-m-d'
	
});