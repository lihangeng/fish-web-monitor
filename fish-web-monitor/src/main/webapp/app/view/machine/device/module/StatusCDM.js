Ext.define('Eway.view.machine.device.module.StatusCDM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscdm',
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
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel: EwayLocale.machine.device.hwCode,
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title :EwayLocale.machine.device.CDMStatus,
				titleAlign: 'center',
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
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.cashUnits,
						name : 'cashUnits',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.safeDoor,
						name : 'safeDoor'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.intermediateStacker,
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>'+ EwayLocale.machine.device.outBox+'</center>',
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
						height: 40,
						labelAlign : 'right',
						labelWidth: 120
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.pcuId,
						name : 'pcuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.cuId,
						name : 'cuId'
					}, {
						fieldLabel : EwayLocale.machine.device.cuCurrency,
						name : 'cuCurrency'
					}, {
						fieldLabel : EwayLocale.machine.device.cuCurrentCount,
						name : 'cuCurrentCount'
					}, {
						fieldLabel : EwayLocale.machine.device.cuInitialCount,
						name : 'cuInitialCount'
					}, {
						fieldLabel : EwayLocale.machine.device.cuRejectCount,
						name : 'cuRejectCount'
					}, {
						fieldLabel : EwayLocale.machine.device.cuNoteValue,
						name : 'cuNoteValue'
					}, {
						fieldLabel : EwayLocale.machine.device.cuBinStatus,
						name : 'cuBinStatus'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						height: 40,
						labelAlign : 'right'
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
						fieldLabel : EwayLocale.machine.device.puInitialCount,
						name : 'puInitialCount'
					}, {
						fieldLabel : EwayLocale.machine.device.puRejectCount,
						name : 'puRejectCount'
					}, {
						fieldLabel : EwayLocale.machine.device.cuBinType,
						name : 'cuBinType'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});