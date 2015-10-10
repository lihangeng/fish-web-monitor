Ext.define('Eway.view.case.caseFault.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.caseFault_filterForm',

	requires : [ 'Eway.view.field.case.FaultClassify',
	             'Eway.view.field.case.DevMod',
	             'Eway.view.field.case.FaultStatus',
	             'Eway.view.field.case.FaultClassify',
	             'Eway.lib.Util'
	             ],

	layout : 'column',

	initComponent : function(){
		Ext.apply(this,{
			items : [{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					labelWidth : 60,
					xtype : 'textfield',
					fieldLabel : Eway.locale.commen.terminalId,
					maxLength:20,
					regexText:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
					name : 'terminalId',
					msgTarget:'side',
					vtype : 'terminalId'
				},{
					labelWidth : 60,
					xtype : 'field_faultStatus',
					fieldLabel : '故障状态',
					name : 'faultStatus'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					labelWidth : 60,
					xtype : 'field_devMod',
					fieldLabel : '故障模块',
					name : 'devMod'
				},{
					labelWidth : 60,
					xtype : 'field_case_faultClassify',
					fieldLabel : '故障分类',
					name : 'faultClassify'
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype:'datefield',
					fieldLabel : '故障开始时间',
					name : 'faultTime',
					value : new Date(),
					editable : false,
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
					endDateField : 'closedTime',
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
					fieldLabel : '故障关闭时间',
					name : 'closedTime',
					editable : false,
					format : 'Y-m-d',
					vtype : 'daterange',
					startDateField : 'faultTime',
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
//			},{
//				columnWidth : .2,
//				xtype : 'form',
//				items : [{
//					xtype:'numberfield',
//					fieldLabel : '故障持续时长',
//					hideTrigger: true,
//					name : 'duration',
//			        minValue: 0
//				},{
//					xtype : 'numberfield',
//					fieldLabel : '升级次数',
//					hideTrigger: true,
//					name : 'upgrade',
//					minValue: 0
//
//				}]
			}]
		});
		this.callParent(arguments);
	}

});