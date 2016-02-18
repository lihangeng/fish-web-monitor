Ext.define('Eway.view.bsAdvert.field.AdvertGroupComBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_advert_advertGroup',
	//广告组
	fieldLabel : EwayLocale.bsAdvert.advertGroup,
	name : 'advertGroupId',
	hiddenName : 'advertGroupId',
	store : 'bsAdvert.BsAdvertGroupList',
	valueField : 'id',
	displayField : 'groupName',
	queryMode: 'local',
	editable : false,
	emptyText : EwayLocale.combox.select
});

