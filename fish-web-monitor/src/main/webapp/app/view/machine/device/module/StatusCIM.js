Ext.define('Eway.view.machine.device.module.StatusCIM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscim',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.comStatus,
				titleAlign:'center',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.hwCode,
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : EwayLocale.machine.device.CIMStatus,
				titleAlign:'center',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.baffle,
						name : 'safeDoor',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.inOutPositionStatus,
						name : 'inOutPositionStatus'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.intermediateStacker,
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title :  EwayLocale.machine.device.inBox,
				titleAlign:'center',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						labelWidth: 160,
						height: 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.puId,
						name : 'puId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.puPosName,
						name : 'puPosName'
					}, {
						fieldLabel : EwayLocale.machine.device.puBinStatus,
						name : 'puBinStatus'
					}, {
						fieldLabel : EwayLocale.machine.device.puCurrentCount,
						name : 'puCurrentCount'
					}, {
						fieldLabel : EwayLocale.machine.device.puCashInCount,
						name : 'puCashInCount'
					}, {
						fieldLabel :EwayLocale.machine.device.pcuId,
						name : 'pcuId'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.cuId,
						name : 'cuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.cuType,
						name : 'cuType'
					}, {
						fieldLabel : EwayLocale.machine.device.cuBinStatus,
						name : 'cuBinStatus'
					}, {
						fieldLabel :EwayLocale.machine.device.cuCurrentCount,
						name : 'cuCurrentCount'
					}, {
						fieldLabel : EwayLocale.machine.device.cuCurrency,
						name : 'cuCurrency'
					}, {
						fieldLabel : EwayLocale.machine.device.cuNoteValue,
						name : 'cuNoteValue'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});