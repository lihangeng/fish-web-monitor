Ext.define("Eway.view.machine.device.module.StatusJPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusjpr',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.comStatus,
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
				title : '<center>'+EwayLocale.machine.device.JRPStatus+'</center>',
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
						fieldLabel : EwayLocale.machine.device.supplyLevel,
						name : 'supplyLevel',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.ink,
						name : 'ink'
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
						fieldLabel : EwayLocale.machine.device.media,
						name : 'media',
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