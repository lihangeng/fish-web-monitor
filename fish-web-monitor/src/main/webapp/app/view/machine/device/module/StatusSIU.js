Ext.define("Eway.view.machine.device.module.StatusSIU", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statussiu',
	defaults : {
		border : false
	},
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : Eway.locale.machine.device.SIUStatus,
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
						height : 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.vandalShield,
						name : 'vandalShield',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.operatorSwitch,
						name : 'operatorSwitch'
					}, {
						fieldLabel : Eway.locale.machine.device.ambientLight,
						name : 'ambientLight'
					}, {
						fieldLabel : Eway.locale.machine.device.cabinet,
						name : 'cabinet'
					}, {
						fieldLabel : Eway.locale.machine.device.safe,
						name : 'safe'
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
						labelWidth: 140,
						height : 40
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.idcGuidelight,
						name : 'idcGuidelight',
						style : 'margin-top:2px'
					}, {
						fieldLabel :Eway.locale.machine.device.cdmGuidelight,
						name : 'cdmGuidelight'
					}, {
						fieldLabel : Eway.locale.machine.device.receiptGuidelight,
						name : 'receiptGuidelight'
					}, {
						fieldLabel : Eway.locale.machine.device.cimGuidelight,
						name : 'cimGuidelight'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});