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
					vtype : 'terminalId',
					maxLength:20,
					regexText:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
					fieldLabel : Eway.locale.commen.terminalId,
					msgTarget:'side',
					name : 'terminalId'
				},{
					xtype : 'field_notifyWay',
					fieldLabel : '通知方式',
					name : 'notifyWay'
				}]
			},{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype : 'textfield',
					vtype : 'mobile',
					fieldLabel : '通知手机号',
					regexText : '手机电话号码只能输入8到11位数字‘0-9’！',
					msgTarget : 'side',
					name : 'mobile'
				},{
					xtype : 'textfield',
					fieldLabel : 'E-Mail',
					vtype : 'email',
					regexText : 'email必须符合*@*.*标准。',
					msgTarget : 'side',
					maxLength:50,
					name : 'mail'
				}]
			},{
				columnWidth : .25,
				xtype : 'form',
				items : [{
					xtype : 'datefield',
					fieldLabel : '创建时间',
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
					fieldLabel : '发送时间',
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
					fieldLabel : '通知次数',
					hideTrigger: true,
					name : 'notifyTimes',
					regexText : '通知次数必须为数字，最小值为0,最大值为100。',
					msgTarget : 'side',
			        minValue: 0,
			        maxValue: 100
				},{
					xtype : 'numberfield',
					fieldLabel : '发送次数',
					hideTrigger: true,
					name : 'sendTimes',
					regexText : '发送次数必须为数字，最小值为0,最大值为100。',
					msgTarget : 'side',
			        minValue: 0,
			        maxValue: 100

				}]
			}]
		});
		this.callParent(arguments);
	}

});