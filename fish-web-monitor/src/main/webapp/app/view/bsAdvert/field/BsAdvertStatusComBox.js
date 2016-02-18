Ext.define('Eway.view.bsAdvert.field.BsAdvertStatusComBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_bsadvert_advertStatus',
	//'广告状态'
	fieldLabel : EwayLocale.bsAdvert.advertStatus,
	name : 'actived',
	hiddenName : 'actived',
	store : 'bsAdvert.BsAdvertStatus',
	valueField : 'value',
	displayField : 'display',
	queryMode: 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});