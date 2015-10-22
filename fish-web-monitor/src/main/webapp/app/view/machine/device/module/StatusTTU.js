Ext.define("Eway.view.machine.device.module.StatusTTU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statusttu',
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
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.hwCode,
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>'+Eway.locale.machine.device.TTUStatus+'</center>',
				layout : 'column',
				border : 'false',
				items : [ {
					columnWidth : .5,
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right'
					}
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});