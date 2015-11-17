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
					editable : false,
					width : 280
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStart,
					xtype : 'datetimefield',
					name : 'createdTimeStart',
					editable : false,
					format : 'Y-m-d H:i:s',
					width : 280
				} ]
			}, {
				columnWidth : .3,
				items : [ {
					fieldLabel : EwayLocale.advert.createdTimeStop,
					xtype : 'datetimefield',
					name : 'createdTimeEnd',
					editable : false,
					format : 'Y-m-d H:i:s',
					width : 280
				} ]
			} ]
		});
		this.callParent(arguments);
	}

});