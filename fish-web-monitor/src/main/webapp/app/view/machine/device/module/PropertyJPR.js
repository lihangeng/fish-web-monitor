Ext.define("Eway.view.machine.device.module.PropertyJPR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyjpr',
	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				title : '日志打印机模块(JPR)属性信息',
				titleAlign:'center',
				layout : 'column',
				items : [ {
					columnWidth : .8,
					border : false,
					defaultType : 'textfield',
					defaults : {
						anchor : '100%',
						readOnly : true,
						labelWidth: 120,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '是否具有退纸能力',
						name : 'canEject',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否具有回收能力',
						name : 'canCapture'
					}, {
						fieldLabel : '是否具有暂存能力',
						name : 'canStack'
					} ]
				}]
			} ]
		});
		this.callParent(arguments);
	}
});