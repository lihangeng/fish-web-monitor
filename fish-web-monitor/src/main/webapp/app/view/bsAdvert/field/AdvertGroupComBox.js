Ext.define('Eway.view.bsAdvert.field.AdvertGroupComBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.field_advert_advertGroup',
	fieldLabel : '广告组',
	name : 'advertGroupId',
	hiddenName : 'advertGroupId',
	store : 'bsAdvert.BsAdvertGroupList',
	valueField : 'id',
	displayField : 'groupName',
	queryMode: 'local',
	editable : false,
	emptyText : EwayLocale.combox.select,
	listeners:{
		focus:function(_this, event, eOpts ){
			_this.getStore().load();
		}
	}
});