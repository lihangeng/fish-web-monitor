Ext.define("Eway.view.machine.device.module.PropertyCIM", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertycim',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '存款模块 （CIM）属性信息',
				titleAlign: 'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelWidth: 150,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '是否具有暂存器',
						name : 'canEscrow',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否支持控制shutter门',
						name : 'shutterControlSupported'
					}, {
						fieldLabel : '单笔最大验钞张数',
						name : 'maxAcceptItems'
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
						fieldLabel : '是否能探测钞票放入',
						name : 'canDetectCashInserted',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否能探测钞票被取走',
						name : 'canDetectCashTaken'
					}, {
						fieldLabel : '回收位置',
						name : 'retractAreas'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});