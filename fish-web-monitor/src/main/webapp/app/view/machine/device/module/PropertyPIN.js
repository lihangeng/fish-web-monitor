Ext.define("Eway.view.machine.device.module.PropertyPIN", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertypin',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '密码键盘(PIN)属性信息',
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
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '能否EBC',
						name : 'canEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '能否CBC',
						name : 'canCBC'
					}, {
						fieldLabel : '能否MAC',
						name : 'canMAC'
					}, {
						fieldLabel : '能否RSA',
						name : 'canRSA'
					}, {
						fieldLabel : '能否验证VISA',
						name : 'canVerifyVISA'
					}, {
						fieldLabel : '能否验证DES',
						name : 'canVerifyDES'
					}, {
						fieldLabel : '功能键支持',
						xtype : 'textareafield',
						name : 'functionKeys'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textfield',
						labelWidth: 150,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '是否支持多重EBC',
						name : 'canTripleEBC',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否支持多重CBC',
						name : 'canTripleCBC'
					}, {
						fieldLabel : '是否支持多重MAC',
						name : 'canTripleMAC'
					}, {
						fieldLabel : '是否支持多重CFB',
						name : 'canTripleCFB'
					}, {
						fieldLabel : '能否验证ECB',
						name : 'canVerifyECB'
					}, {
						fieldLabel : '能否DeS偏移',
						name : 'canDESOffset'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});