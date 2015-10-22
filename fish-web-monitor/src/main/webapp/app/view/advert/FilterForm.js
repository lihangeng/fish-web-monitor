Ext.define('Eway.view.advert.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_filterForm',

	requires : ['Ext.ux.form.DateTimeField'],
	height : 105,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .8,
				items : [{
			                xtype: 'combobox',
			                fieldLabel: Eway.locale.advert.type,
			                displayField: 'display',
			                store: Ext.StoreMgr.lookup("advert.AdvertType"),
			                queryMode: 'local',
			                valueField : 'value',
			                name:'advertType',
			                editable : false,
				            width: 280
			            },{
			                fieldLabel: Eway.locale.advert.createdTimeStart,
			                xtype:'datetimefield',
				        	name:'createdTimeStart',
				        	editable: false,
				            format: 'Y-m-d H:i:s',
				            width: 280
			            },{
			                fieldLabel: Eway.locale.advert.createdTimeStop,
			                xtype:'datetimefield',
				        	name:'createdTimeEnd',
				        	editable: false,
				            format: 'Y-m-d H:i:s',
				            width: 280
			            }]
			    }/*,
			    {
				columnWidth : .4,
				items : [{
			                xtype: 'combobox',
			                fieldLabel: '下发方式',
			                displayField: 'display',
			                store: Ext.StoreMgr.lookup("advert.AdvertDownMethodSearch"),
			                queryMode: 'local',
			                valueField : 'value',
			                name:'advertDownMethod',
			                editable : false,
			                value :'',
				            width: 280
			            },{
			                fieldLabel: '制作时间结束于',
			                xtype:'datetimefield',
				        	name:'createdTimeEnd',
				        	editable: true,
				            format: 'Y-m-d H:i:s',
				            width: 280
			            }]
			    }*/]
		});
		this.callParent(arguments);
	}

});