Ext.define('Eway.view.bsAdvert.field.BsAdvertStatusComBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_bsadvert_advertStatus',
	fieldLabel : '广告状态',
	name : 'actived',
	hiddenName : 'actived',
	store : 'bsAdvert.BsAdvertStatus',
	valueField : 'value',
	displayField : 'display',
	queryMode: 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});