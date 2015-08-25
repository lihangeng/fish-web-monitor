Ext.define("Eway.view.machine.device.module.PropertyCDM", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertycdm',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '取款模块（CDM）属性信息',
				titleAlign: 'center',
				layout : 'column',
				items : [ {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textfield',
					defaults : {
						anchor : '90%',
						labelWidth: 130,
						readOnly : true,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '是否具有暂存器',
						name : 'hasStack',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否具有shutter门',
						name : 'hasShutter'
					}, {
						fieldLabel : '是否具有回收能力',
						name : 'canRetract'
					}, {
						fieldLabel : '是否探测钞币被拿走',
						name : 'canDetectCashTaken'
					}, {
						fieldLabel : '是否能测试物理单元',
						name : 'canTestPhysicalUnits'
					}, {
						fieldLabel : '获取单笔最大挖钞张数',
						name : 'maxDispensBills'
					} ]
				}, {
					columnWidth : .5,
					layout : 'anchor',
					border : false,
					defaults : {
						anchor : '90%',
						readOnly : true,
						labelWidth: 130,
						xtype : 'textfield',
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '逻辑钞箱个数',
						name : 'logicalUnits',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '物理钞箱个数',
						name : 'physicalUnits'
					}, {
						fieldLabel : '支持的币种类别总个数',
						name : 'currency'
					}, {
						fieldLabel : '支持的币种类别',
						name : 'currencies'
					}, {
						fieldLabel : '指数',
						name : 'exponents'
					} ]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});