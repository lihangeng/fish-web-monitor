Ext.define('Eway.view.machine.device.module.StatusCAM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscam',
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
				title : EwayLocale.machine.device.CAMStatusInfo,
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
						fieldLabel : EwayLocale.machine.device.CAMRoomStatus,//'房内'
						name : 'roomStatus',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.CAMPersonStatus,//'客户'
						name : 'personStatus'
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
						fieldLabel : EwayLocale.machine.device.CAMExitSlotStatus,//'初潮'
						name : 'exitSlotStatus',
						style : 'margin-top:2px'
					} ]
				} ]
			}
			]
		});

		this.callParent(arguments);
	}
});