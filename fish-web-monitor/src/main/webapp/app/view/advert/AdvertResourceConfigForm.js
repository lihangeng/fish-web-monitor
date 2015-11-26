Ext.define('Eway.view.advert.AdvertResourceConfigForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_resourceConfigForm',

	defaults : {
		layout : {
			type : 'hbox'
		}
	},
	width :470,
	height:'100%',
	title: EwayLocale.advert.configTitle,

	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			items : [{
					xtype:'displayfield',
					name:'fileName',
					fieldLabel:EwayLocale.advert.fileName,
					labelWidth: 60,
//					hidden: true,
					value:''
				},{
					xtype:'displayfield',
					name:'originalFileName',
					fieldLabel:EwayLocale.advert.resourceName,
			    	labelAlign :'right',
					labelWidth: 100
		        },{
			    	xtype: 'fieldcontainer',
			        fieldLabel: EwayLocale.advert.playTime,
			        labelWidth: 100,
			    	labelAlign :'right',
			        combineErrors: true,
			        msgTarget: 'under',
			        defaults: {
			            hideLabel: true
			        },
			        items:[{
			        	xtype:'numberfield',
			        	fieldLabel: EwayLocale.advert.times,
			        	hideTrigger:true,
			        	value: 10,
			            minValue: 1,
			            maxValue: 60,
			            name:'playTime',
			            width:50
			        },{
			        	xtype: 'displayfield',
			        	value :EwayLocale.advert.secondeDisplay
			        }]
		    	}, {
		    	xtype:'datefield',
		    	fieldLabel: EwayLocale.advert.beginDate,
		        labelWidth: 100,
		    	labelAlign :'right',
		    	name:'beginDate',
		    	disabled: false,
		    	editable: false,
		        format: 'Y-m-d',
		        width: 240,
		        hidden:false,
		        value:new Date(),
		        minValue : new Date()
		    },{
		    	xtype:'datefield',
		    	fieldLabel: EwayLocale.advert.endDate,
		    	labelAlign :'right',
		    	name:'endDate',
		    	disabled: false,
		    	editable: false,
		        format: 'Y-m-d',
		        hidden: false,
		        labelWidth: 100,
		        width: 240,
		        bodyStyle:'margin-right:-20px',
		        minValue : new Date()
		     },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.advert.beginTime,
		        combineErrors: true,
		        labelWidth: 100,
		    	labelAlign :'right',
		        defaults: {
		            hideLabel: true
		        },
		        items: [{
		            	xtype: 'combobox',
		            	fieldLabel: 'hour',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Hour"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name: 'beginHour',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.hourDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginMinute',
		                width:75
		            }, {xtype: 'displayfield', value: EwayLocale.advert.minuteDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginSecond',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.secondeDisplay}

		        ]
		    },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.advert.endTime,
		        combineErrors: true,
		        labelWidth: 100,
		    	labelAlign :'right',
		        defaults: {
		            hideLabel: true
		        },
		        items: [
		            /*{xtype: 'displayfield', value: '结束时间：'
		            },*/{
		            	xtype: 'combobox',
		            	fieldLabel: 'hour',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Hour"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'23',
		                name:'endHour',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.hourDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endMinute',
		                width:75
		            }, {xtype: 'displayfield', value: EwayLocale.advert.minuteDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endSecond',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.advert.secondeDisplay}
		           ]
		       }]
		});
		this.callParent(arguments);
	}

});