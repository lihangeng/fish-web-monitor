Ext.define('Eway.view.machine.device.module.StatusCIM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscim',
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
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.hwCode,
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : Eway.locale.machine.device.CIMStatus,
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
						fieldLabel : Eway.locale.machine.device.baffle,
						name : 'safeDoor',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.inOutPositionStatus,
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
						fieldLabel : Eway.locale.machine.device.intermediateStacker,
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title :  Eway.locale.machine.device.inBox,
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
						fieldLabel : Eway.locale.machine.device.puCashInCount,
						name : 'puCashInCount'
					}, {
						fieldLabel :Eway.locale.machine.device.pcuId,
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
						fieldLabel : Eway.locale.machine.device.cuId,
						name : 'cuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.cuType,
						name : 'cuType'
					}, {
						fieldLabel : Eway.locale.machine.device.cuBinStatus,
						name : 'cuBinStatus'
					}, {
						fieldLabel :Eway.locale.machine.device.cuCurrentCount,
						name : 'cuCurrentCount'
					}, {
						fieldLabel : Eway.locale.machine.device.cuCurrency,
						name : 'cuCurrency'
					}, {
						fieldLabel : Eway.locale.machine.device.cuNoteValue,
						name : 'cuNoteValue'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});