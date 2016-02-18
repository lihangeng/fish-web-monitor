
Ext.define('Eway.view.bsAdvert.field.BsGroupTypeComBox', {
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.field_bsadvert_groupType',
	//'广告组类型',
	fieldLabel : EwayLocale.bsAdvert.advertGroupType,
	name : 'actived',
	hiddenName : 'actived',
	store : 'bsAdvert.BsAdvertGroupType',
	valueField : 'value',
	displayField : 'display',
	queryMode: 'local',
	editable : false,
	emptyText : EwayLocale.combox.select

});

