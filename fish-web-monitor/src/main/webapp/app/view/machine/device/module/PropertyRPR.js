/**
 * 凭条打印机 视图页面
 */
Ext.define("Eway.view.machine.device.module.PropertyRPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyrpr',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			border : false,
			items : [ {
				title : Eway.locale.machine.device.RPRInfo,
				titleAlign:'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						labelWidth : 150
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.canEject,
						name : 'canEject',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.canCapture,
						name : 'canCapture'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelAlign : 'right',
						labelWidth : 150
					},
					items : [ {
						fieldLabel : Eway.locale.machine.device.canStack,
						name : 'canStack',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.maxRetract,
						name : 'maxRetract'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}

});
