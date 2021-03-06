Ext.define('Eway.view.case.caseFault.FilterForm',{

	extend : 'Eway.view.base.FilterForm',
	alias  : 'widget.caseFault_filterForm',

	requires : [ 'Eway.view.field.case.FaultClassify',
	             'Eway.view.field.case.DevMod',
	             'Eway.view.field.case.FaultStatus',
	             'Eway.lib.Util'
	             ],

	layout : 'column',
	height : 80,
	initComponent : function(){
		Ext.apply(this,{
			items : [{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					labelWidth : 90,
					xtype : 'textfield',
					fieldLabel : EwayLocale.commen.terminalId,
					name : 'terminalId',
					msgTarget:'side'
				},{
					labelWidth : 90,
					xtype : 'field_faultStatus',
					fieldLabel :EwayLocale.cases.caseFault.faultState,
					name : 'faultStatus'
				}]
			},{
				columnWidth : .3,
				xtype : 'form',
				items : [{
					labelWidth : 90,
					xtype : 'field_devMod',
					fieldLabel : EwayLocale.cases.caseFault.faultModule,
					name : 'devMod'
				},{
					labelWidth : 90,
					xtype : 'field_case_faultClassify',
					fieldLabel : EwayLocale.cases.caseFault.faultClassify,
					name : 'faultClassify'
				}]
			},{
				columnWidth : .4,
				xtype : 'form',
				items : [{
					xtype:'datefield',
					fieldLabel : EwayLocale.cases.caseFault.faultStartTime,
					name : 'faultTime',
					value : new Date(),
					editable : false,
		            format: 'Y-m-d',
		            vtype : 'daterange',
					endDateField : 'closedTime'
				},{
					xtype : 'datefield',
					fieldLabel : EwayLocale.cases.caseFault.faultCloseTime,
					name : 'closedTime',
					editable : false,
					format : 'Y-m-d',
					vtype : 'daterange',
					startDateField : 'faultTime'
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
