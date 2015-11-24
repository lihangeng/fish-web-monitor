Ext.define('Eway.view.advert.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_filterForm',

	requires : [ 'Ext.ux.form.DateTimeField' ],
	height : 35,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			layout : 'column',
			items : [ {
				columnWidth : .3,
				items : [ {
					xtype : 'combobox',
					fieldLabel : EwayLocale.advert.type,
					displayField : 'display',
					store : Ext.StoreMgr.lookup("advert.AdvertType"),
					queryMode : 'local',
					valueField : 'value',
					name : 'advertType',
					labelWidth:80,
					editable : false,
					width : 320
				} ]
			}, {
				columnWidth : .33,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStart,
					labelWidth:150,
					xtype : 'datetimefield',
					name : 'createdTimeStart',
					editable : false,
					format : 'Y-m-d H:i:s',
					width : 335
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStop,
					xtype : 'datetimefield',
					name : 'createdTimeEnd',
					labelWidth:130,
					editable : false,
					format : 'Y-m-d H:i:s',
					width : 310
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});