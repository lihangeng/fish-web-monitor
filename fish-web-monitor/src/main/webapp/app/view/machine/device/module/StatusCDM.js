Ext.define('Eway.view.machine.device.module.StatusCDM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscdm',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : Eway.locale.machine.device.comStatus,
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
						fieldLabel: Eway.locale.machine.device.hwCode,
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title :Eway.locale.machine.device.CDMStatus,
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
						fieldLabel : Eway.locale.machine.device.cashUnits,
						name : 'cashUnits',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.safeDoor,
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
						fieldLabel : Eway.locale.machine.device.intermediateStacker,
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>'+ Eway.locale.machine.device.outBox+'</center>',
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
						fieldLabel : Eway.locale.machine.device.pcuId,
						name : 'pcuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.cuId,
						name : 'cuId'
					}, {
						fieldLabel : Eway.locale.machine.device.cuCurrency',
						name : 'cuCurrency'
					}, {
						fieldLabel : Eway.locale.machine.device.cuCurrentCount,
						name : 'cuCurrentCount'
					}, {
						fieldLabel : Eway.locale.machine.device.cuInitialCount,
						name : 'cuInitialCount'
					}, {
						fieldLabel : Eway.locale.machine.device.cuRejectCount,
						name : 'cuRejectCount'
					}, {
						fieldLabel : Eway.locale.machine.device.cuNoteValue,
						name : 'cuNoteValue'
					}, {
						fieldLabel : Eway.locale.machine.device.cuBinStatus,
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
						fieldLabel : Eway.locale.machine.device.puId,
						name : 'puId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.puPosName,
						name : 'puPosName'
					}, {
						fieldLabel : Eway.locale.machine.device.puBinStatus,
						name : 'puBinStatus'
					}, {
						fieldLabel : Eway.locale.machine.device.puCurrentCount,
						name : 'puCurrentCount'
					}, {
						fieldLabel : Eway.locale.machine.device.puInitialCount,
						name : 'puInitialCount'
					}, {
						fieldLabel : Eway.locale.machine.device.puRejectCount,
						name : 'puRejectCount'
					}, {
						fieldLabel : Eway.locale.machine.device.cuBinType,
						name : 'cuBinType'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});