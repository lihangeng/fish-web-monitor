Ext.define("Eway.view.machine.device.module.StatusIDC", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusidc',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '<center>'+ EwayLocale.machine.device.comStatus+'</center>',
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
				title : EwayLocale.machine.device.IDCStatus,
				titleAlign:'center',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						height : 40
					},
					items : [ {
						fieldLabel : EwayLocale.machine.device.media,
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.retainBin,
						name : 'retainBin'
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
						fieldLabel : EwayLocale.machine.device.cards,
						name : 'cards',
						style : 'margin-top:2px'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});