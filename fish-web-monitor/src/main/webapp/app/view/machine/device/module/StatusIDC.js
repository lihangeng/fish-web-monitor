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
				title : '<center>'+ Eway.locale.machine.device.comStatus+'</center>',
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
				title : Eway.locale.machine.device.IDCStatus,
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
						fieldLabel : Eway.locale.machine.device.media,
						name : 'media',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.retainBin,
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
						fieldLabel : Eway.locale.machine.device.cards,
						name : 'cards',
						style : 'margin-top:2px'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});