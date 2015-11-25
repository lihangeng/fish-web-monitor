Ext.define('Eway.view.case.caseNotify.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.caseNotify_filterForm',

	requires : [ 'Eway.view.field.case.NotifyWay',
	             'Ext.ux.form.DateTimeField',
	             'Eway.lib.Util'],

	layout : 'column',

	initComponent : function(){
		Ext.apply(this,{
			items : [{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.commen.terminalId,
					msgTarget:'side',
					name : 'terminalId'
				},{
					xtype : 'field_notifyWay',
					fieldLabel : EwayLocale.cases.caseFault.informWay,
					name : 'notifyWay'
				}]
			},{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					fieldLabel : EwayLocale.cases.caseFault.informMobile,
					msgTarget : 'side',
					name : 'mobile'
				},{
					xtype : 'textfield',
					fieldLabel : 'E-Mail',
					vtype : 'email',
					regexText : EwayLocale.vtype.emailRules,
					msgTarget : 'side',
					maxLength:50,
					name : 'mail'
				}]
			},{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : EwayLocale.cases.caseFault.createTime,
					name : 'createTime',
					value : new Date(),
		        	editable: false,
		            format: 'Y-m-d',
		            vtype : 'daterange',
		            onClearClick : function() {
						this.setValue("");
						var value = this.getValue();
		            	if (!value) {
		            		var endField = this.up('form').getForm().findField(this.endDateField);
		            		endField.setMinValue(null);
		            	}
					},
					endDateField : 'sendTime',
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
					xtype : 'datefield',
					fieldLabel : EwayLocale.cases.caseFault.sendTime,
					name : 'sendTime',
					editable: false,
					format : 'Y-m-d',
					vtype : 'daterange',
					startDateField : 'createTime',
					onClearClick : function() {
						this.setValue("");
						var value = this.getValue();
		            	if (!value) {
		            		var startField = this.up('form').getForm().findField(this.startDateField);
		            		startField.setMaxValue(null);
		            	}
					},
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
			},{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype:'numberfield',
					fieldLabel : EwayLocale.cases.caseFault.notifyTimes,
					hideTrigger: true,
					name : 'notifyTimes',
					regexText : EwayLocale.vtype.notifyTimesRules,
					msgTarget : 'side',
			        minValue: 0,
			        maxValue: 100
				},{
					xtype : 'numberfield',
					fieldLabel : EwayLocale.cases.caseFault.sendTimes,
					hideTrigger: true,
					name : 'sendTimes',
					regexText : EwayLocale.vtype.sendTimesRules,
					msgTarget : 'side',
			        minValue: 0,
			        maxValue: 100

				}]
			}]
		});
		this.callParent(arguments);
	}

});