Ext.define('Eway.view.bsAdvert.BsAdvertResourceConfigForm', {
	extend : 'Eway.view.base.FilterForm',
	alias : 'widget.advert_bs_resourceConfigForm',

	defaults : {
		layout : {
			type : 'hbox'
		}
	},
	width :470,
	height:'100%',
	title: EwayLocale.bsAdvert.configTitle,

	initComponent : function() {
		var me = this;
		Ext.apply(this, {
			items : [{
					xtype:'displayfield',
					name:'fileName',
					fieldLabel:EwayLocale.bsAdvert.fileName,
					labelWidth: 60,
					hidden: true,
					value:''
				},{
					xtype:'displayfield',
					name:'originalFileName',
					fieldLabel:EwayLocale.bsAdvert.resourceName,
			    	labelAlign :'right',
					labelWidth: 100
		        },{
			    	xtype: 'fieldcontainer',
			        fieldLabel: EwayLocale.bsAdvert.playTime,
			        labelWidth: 100,
			    	labelAlign :'right',
			        combineErrors: true,
			        msgTarget: 'under',
			        defaults: {
			            hideLabel: true
			        },
			        items:[{
			        	xtype:'numberfield',
			        	fieldLabel: EwayLocale.bsAdvert.times,
			        	hideTrigger:true,
			        	value: 10,
			            minValue: 1,
			            maxValue: 60,
			            name:'playTime',
			            width:50
			        },{
			        	xtype: 'displayfield',
			        	value :EwayLocale.bsAdvert.secondeDisplay
			        }]
		    	}, {
		    	xtype:'datefield',
		    	fieldLabel: EwayLocale.bsAdvert.beginDate,
		        labelWidth: 100,
		    	labelAlign :'right',
		    	name:'beginDate',
		    	disabled: false,
		    	editable: false,
		        format: 'Y-m-d',
		        width: 240,
		        vtype : 'daterange',
		        value:new Date(),
		        minValue : new Date,
		    	endDateField : 'endDate',
				listeners : {
					blur : {
						fn : function(This,options) {
							var value = this.getValue();
							if (!value) {
								var endField = this.up('form').getForm().findField(this.endDateField);
								endField.setMinValue(null);
							}
						}
					}
				}

		    },{
		    	xtype:'datefield',
		    	fieldLabel: EwayLocale.bsAdvert.endDate,
		    	labelAlign :'right',
		    	name:'endDate',
		    	disabled: false,
			    editable: false,
			    format: 'Y-m-d',
			    vtype : 'daterange',
			    labelWidth: 100,
			    width: 240,
			    bodyStyle:'margin-right:-20px',
		    	vtype : 'daterange',
				editable : false,
				minValue : new Date(),
				startDateField : 'beginDate',
				listeners : {
					blur : {
						fn : function(This,options) {
							var value = this.getValue();
							if (!value) {
								var startField = this.up('form').getForm().findField(this.startDateField);
								startField.setMaxValue(null);
							}
						}
					}
				}
		    	
		     },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.bsAdvert.beginTime,
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
				    	editable: false,
		                value:'00',
		                name: 'beginHour',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.bsAdvert.hourDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'minute',
				    	editable: false,
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginMinute',
		                width:75
		            }, {xtype: 'displayfield', value: EwayLocale.bsAdvert.minuteDisplay},
		            {
		            	xtype: 'combobox',
		            	fieldLabel: 'second',
				    	editable: false,
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'00',
		                name:'beginSecond',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.bsAdvert.secondeDisplay}

		        ]
		    },{
		        xtype: 'fieldcontainer',
		        fieldLabel: EwayLocale.bsAdvert.endTime,
		        combineErrors: true,
		        labelWidth: 100,
		    	labelAlign :'right',
		        defaults: {
		            hideLabel: true
		        },
		        items: [
		            /*
					 * {xtype: 'displayfield', value: '结束时间：' },
					 */{
		            	xtype: 'combobox',
		            	fieldLabel: 'hour',
				    	editable: false,
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Hour"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'23',
		                name:'endHour',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.bsAdvert.hourDisplay},
		            {
		            	xtype: 'combobox',
				    	editable: false,
		            	fieldLabel: 'minute',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endMinute',
		                width:75
		            }, {xtype: 'displayfield', value: EwayLocale.bsAdvert.minuteDisplay},
		            {
		            	xtype: 'combobox',
				    	editable: false,
		            	fieldLabel: 'second',
		                displayField: 'display',
		                store: Ext.StoreMgr.lookup("Minute"),
		                queryMode: 'local',
		                valueField : 'value',
		                value:'59',
		                name:'endSecond',
		                width:75
		            },
		            {xtype: 'displayfield', value: EwayLocale.bsAdvert.secondeDisplay}
		           ]
		       }]
		});
		this.callParent(arguments);
	}

});