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
				title : '凭条打印机(RPR)属性信息',
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
						fieldLabel : '是否具有退纸能力',
						name : 'canEject',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否具有回收能力',
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
						fieldLabel : '是否具有暂存能力',
						name : 'canStack',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '最大回收张数',
						name : 'maxRetract'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}

});
