Ext.define('Eway.view.advert.FilterForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_filterForm',

	requires : ['Ext.ux.form.DateTimeField'],
	height : 70,
	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			layout : 'column',
			items : [{
				columnWidth : .4,
				items : [{
			                xtype: 'combobox',
			                fieldLabel: Eway.locale.advert.type,//'广告类型',
			                displayField: 'display',
			                store: "advert.AdvertType",
			                queryMode: 'local',
			                valueField : 'value',
			            	hiddenName : 'advertType',
			                name:'advertType',
			                editable : false,
				            width: 280
			            },{
			                fieldLabel: Eway.locale.advert.createdTimeStart,//'制作时间开始于',
			                xtype:'datetimefield',
				        	name:'createdTimeStart',
				        	editable: true,
				            format: 'Y-m-d H:i:s',
				            width: 280
			            }]
			    },
			    {
				columnWidth : .4,
				items : [{
			                xtype: 'combobox',
			                fieldLabel: Eway.locale.advert.downType,//'下发方式',
			                displayField: 'display',
			                store: "advert.AdvertDownMethodSearch",
			                queryMode: 'local',
			                valueField : 'value',
			            	hiddenName : 'advertDownMethod',
			                name:'advertDownMethod',
			                editable : false,
				            width: 280
			            },{
			                fieldLabel: Eway.locale.advert.createdTimeStop,//'制作时间结束于',
			                xtype:'datetimefield',
				        	name:'createdTimeEnd',
				        	editable: true,
				            format: 'Y-m-d H:i:s',
				            width: 280
			            }]
			    }]
		});
		this.callParent(arguments);
	}

});