Ext.define('Eway.view.machine.device.module.StatusCDM', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_statuscdm',
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
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '厂商故障码',
						name : 'hwCode',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '取款模块(CDM)状态信息',
				titleAlign: 'center',
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
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '钞箱状态',
						name : 'cashUnits',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '安全门状态',
						name : 'safeDoor'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textareafield',
						height: 40,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '暂存器状态',
						name : 'intermediateStacker',
						style : 'margin-top:2px'
					} ]
				} ]
			}, {
				title : '<center>取款钞箱</center>',
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
						height: 40,
						labelAlign : 'right',
						labelWidth: 120
					},
					items : [ {
						fieldLabel : '物理逻辑钞箱对应关系',
						name : 'pcuId',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '逻辑钞箱ID',
						name : 'cuId'
					}, {
						fieldLabel : '逻辑钞箱币种',
						name : 'cuCurrency'
					}, {
						fieldLabel : '逻辑钞箱当前张数',
						name : 'cuCurrentCount'
					}, {
						fieldLabel : '逻辑钞箱初始张数',
						name : 'cuInitialCount'
					}, {
						fieldLabel : '逻辑钞箱reject张数',
						name : 'cuRejectCount'
					}, {
						fieldLabel : '逻辑钞箱面值',
						name : 'cuNoteValue'
					}, {
						fieldLabel : '逻辑钞箱状态',
						name : 'cuBinStatus'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaultType : 'textareafield',
					defaults : {
						anchor : '90%',
						readOnly : true,
						height: 40,
						labelAlign : 'right'
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
						fieldLabel : '物理钞箱初始张数',
						name : 'puInitialCount'
					}, {
						fieldLabel : '物理钞箱Reject张数',
						name : 'puRejectCount'
					}, {
						fieldLabel : '逻辑钞箱类型',
						name : 'cuBinType'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});