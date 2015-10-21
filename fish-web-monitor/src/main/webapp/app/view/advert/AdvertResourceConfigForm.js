Ext.define('Eway.view.advert.AdvertResourceConfigForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_resourceConfigForm',

	defaults : {
		layout : {
			type : 'hbox'
		}
	},
	width :280,
	height:'100%',
	title: Eway.locale.advert.configTitle,

	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			items : [{
					xtype:'displayfield',
					name:'fileName',
					fieldLabel:Eway.locale.advert.fileName,
					labelWidth: 60,
					hidden: true,
					value:''
				},{
					xtype:'displayfield',
					name:'originalFileName',
					fieldLabel:Eway.locale.advert.resourceName,
					labelWidth: 60
		        },{
			    	xtype: 'fieldcontainer',
			        fieldLabel: Eway.locale.advert.playTime,
			        labelWidth: 60,
			        combineErrors: true,
			        msgTarget: 'under',
			        defaults: {
			            hideLabel: true
			        },
			        items:[{
			        	xtype:'numberfield',
			        	fieldLabel: Eway.locale.advert.times,
			        	hideTrigger:true,
			        	value: 10,
			            minValue: 1,
			            maxValue: 60,
			            name:'playTime',
			            width:50
			        },{
			        	xtype: 'displayfield',
			        	value :Eway.locale.advert.secondeDisplay
			        }]
		    	}, {
		    	xtype:'datefield',
		    	fieldLabel: Eway.locale.advert.beginDate,
		    	name:'beginDate',
		    	disabled: false,
		    	editable: false,
		        format: 'Y-m-d',
		        width: 180,
		        hidden:false,
		        labelWidth: 60,
		        value:new Date(),
		        minValue : new Date()
		    },{
		    	xtype:'datefield',
		    	fieldLabel: Eway.locale.advert.endDate,
		    	labelAlign :'right',
		    	name:'endDate',
		    	disabled: false,
		    	editable: false,
		        format: 'Y-m-d',
		        hidden: false,
		        labelWidth: 60,
		        width: 180,
		        bodyStyle:'margin-right:-20px',
		        minValue : new Date()
		     },{
		        xtype: 'fieldcontainer',
		        fieldLabel: Eway.locale.advert.beginTime,
		        combineErrors: true,
		        labelWidth: 60,
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
		                width:50
		            },
		            {xtype: 'displayfield', value: Eway.locale.advert.hourDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginMinute',
		                width:50
		            }, {xtype: 'displayfield', value: Eway.locale.advert.minuteDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginSecond',
		                width:50
		            },
		            {xtype: 'displayfield', value: Eway.locale.advert.secondeDisplay}

		        ]
		    },{
		        xtype: 'fieldcontainer',
		        fieldLabel: Eway.locale.advert.endTime,
		        combineErrors: true,
		        labelWidth: 60,
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
		                width:50
		            },
		            {xtype: 'displayfield', value: Eway.locale.advert.hourDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endMinute',
		                width:50
		            }, {xtype: 'displayfield', value: Eway.locale.advert.minuteDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endSecond',
		                width:50
		            },
		            {xtype: 'displayfield', value: Eway.locale.advert.secondeDisplay}
		           ]
		       }]
		});
		this.callParent(arguments);
	}

});