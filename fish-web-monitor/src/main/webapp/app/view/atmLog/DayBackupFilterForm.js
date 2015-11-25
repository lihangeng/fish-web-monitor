Ext.define('Eway.view.atmLog.DayBackupFilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.atmLog_DayBackupFilterForm',
	
	layout : 'column',
	
	initComponent : function(){
		Ext.apply(this,{			
			items : [{		
				columnWidth : .50,
				items : [{
					xtype : 'fieldcontainer',
					columnWidth : .4,
					layout : 'hbox',
					fieldLabel : EwayLocale.atmLog.backupDate,
					labelWidth : 70,
					defaults :{
						hideLabel:true
					},
				items :[{
					    fieldLabel : 'startDateTime',
					    displayField :'dispalay',
					    valueField :'value',
					    xtype : 'datefield',
					    format : 'Y-m-d',
					    name : 'startDate',
						editable : false,
						vtype : 'daterange',
						value : Ext.Date.format(Ext.Date.add(new Date(), Ext.Date.DAY, -7),'Y-m-d'),
						width : 120,
						editable:false,
						endDateField : 'endDate',
						listeners : {
							blur : {
					            fn: function(This, options){
					            	var value = this.getValue();
					            	if (!value) {
					            		var endField = this.up('form').getForm().findField(this.endDateField);
					            		endField.setMinValue(null);
					            	}
					            }
							}
						}
					
				       },{
				    	   xtype : 'displayfield',
						   value : EwayLocale.machine.quittingNotice.to
				       },{
				    	   fieldLabel :'endDateTime',
				    	   displayField : 'display',
						   valueField : 'value',
						   width : 120,
						   xtype : 'datefield',
						   editable : false,
						   format : 'Y-m-d',
						   name : 'endDate',
						   value : new Date(),
						   vtype : 'daterange',
						   editable:false,
						   startDateField : 'startDate',
							listeners : {
								blur : {
						            fn: function(This, options){
						            	var value = this.getValue();
						            	if (!value) {
						            		var startField = this.up('form').getForm().findField(this.startDateField);
						            		startField.setMaxValue(null);
						            	}
						            }
								}
							}
				       }]
				}]
			},{
				columnWidth : .28,
				xtype : 'combo',
				fieldLabel : EwayLocale.atmLog.dayBackupResult,
				editable : false,
				name : 'dayBackupResult',
				store : Ext.create('Ext.data.Store',{
					fields : ['dayBackupResult','displayField'],
					data : [
						{'dayBackupResult':'','displayField':EwayLocale.atmLog.whole},
						{'dayBackupResult':'SUCCESS','displayField':EwayLocale.atmLog.backupSuccess},
						{'dayBackupResult':'DOING','displayField':EwayLocale.atmLog.backupProcess},
						{'dayBackupResult':'ERROR','displayField':EwayLocale.atmLog.unKnownFail}
					]
				}),
				queryMode : 'local',
				valueField : 'dayBackupResult',
				displayField : 'displayField',
				hiddenName : 'dayBackupResult',
				emptyText: EwayLocale.combox.select
			}]
		});
		this.callParent(arguments);	
	}
	
});