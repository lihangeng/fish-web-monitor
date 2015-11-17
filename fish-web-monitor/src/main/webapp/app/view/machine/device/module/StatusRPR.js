Ext.define("Eway.view.machine.device.module.StatusRPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusrpr',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '<center>'+EwayLocale.machine.device.comStatus+'</center>',
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
				title : '<center>'+EwayLocale.machine.device.RPRStatus+'</center>',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						height : 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.media,
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.bin,
						name : 'bin'
					}, {
						fieldLabel : EwayLocale.machine.device.supplyLevel,
						name : 'supplyLevel'
					} ]
				}, {
					columnWidth : .5,
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel :  EwayLocale.machine.device.ink,
						name : 'ink',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.toner,
						name : 'toner'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});