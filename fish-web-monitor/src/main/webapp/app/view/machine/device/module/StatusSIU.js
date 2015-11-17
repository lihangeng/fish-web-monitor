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
				title : EwayLocale.machine.device.SIUStatus,
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
						fieldLabel : EwayLocale.machine.device.vandalShield,
						name : 'vandalShield',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.operatorSwitch,
						name : 'operatorSwitch'
					}, {
						fieldLabel : EwayLocale.machine.device.ambientLight,
						name : 'ambientLight'
					}, {
						fieldLabel : EwayLocale.machine.device.cabinet,
						name : 'cabinet'
					}, {
						fieldLabel : EwayLocale.machine.device.safe,
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
						fieldLabel : EwayLocale.machine.device.idcGuidelight,
						name : 'idcGuidelight',
						style : 'margin-top:2px'
					}, {
						fieldLabel :EwayLocale.machine.device.cdmGuidelight,
						name : 'cdmGuidelight'
					}, {
						fieldLabel : EwayLocale.machine.device.receiptGuidelight,
						name : 'receiptGuidelight'
					}, {
						fieldLabel : EwayLocale.machine.device.cimGuidelight,
						name : 'cimGuidelight'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});