Ext.define('Eway.view.machine.device.module.StatusCIM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscim',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '厂商状态信息',
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
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : '厂商故障码',
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '存款模块(CIM)状态信息',
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
						labelAlign : 'right',
						height: 40
					},
					items : [ {
						fieldLabel : '挡板状态',
						name : 'safeDoor',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '传输状态',
						name : 'inOutPositionStatus'
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
						height: 40
					},
					items : [ {
						fieldLabel : '暂存器状态',
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '存款钞箱',
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
						labelAlign : 'right',
						labelWidth: 160,
						height: 40
					},
					items : [ {
						fieldLabel : '物理钞箱ID',
						name : 'puId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '物理钞箱位置名称',
						name : 'puPosName'
					}, {
						fieldLabel : '物理钞箱状态',
						name : 'puBinStatus'
					}, {
						fieldLabel : '物理钞箱当前张数',
						name : 'puCurrentCount'
					}, {
						fieldLabel : '物理钞箱入钞张数',
						name : 'puCashInCount'
					}, {
						fieldLabel : '物理钞箱与逻辑钞箱对应关系',
						name : 'pcuId'
					} ]
				}, {
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
						fieldLabel : '逻辑钞箱ID',
						name : 'cuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '逻辑钞箱类型',
						name : 'cuType'
					}, {
						fieldLabel : '逻辑钞箱状态',
						name : 'cuBinStatus'
					}, {
						fieldLabel : '逻辑钞箱当前张数',
						name : 'cuCurrentCount'
					}, {
						fieldLabel : '逻辑钞箱币种',
						name : 'cuCurrency'
					}, {
						fieldLabel : '逻辑钞箱面值',
						name : 'cuNoteValue'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});