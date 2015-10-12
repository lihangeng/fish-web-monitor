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
				title : '<center>'+Eway.locale.machine.device.comStatus+'</center>',
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
				title : '<center>'+Eway.locale.machine.device.RPRStatus+'</center>',
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
						fieldLabel : Eway.locale.machine.device.media,
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.bin,
						name : 'bin'
					}, {
						fieldLabel : Eway.locale.machine.device.supplyLevel,
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
						fieldLabel :  Eway.locale.machine.device.ink,
						name : 'ink',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.toner,
						name : 'toner'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});