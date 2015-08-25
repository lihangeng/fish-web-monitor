Ext.define('Eway.view.machine.device.module.PropertyIDC', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyidc',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : '读卡器模块(IDC)属性信息',
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
						labelWidth: 180,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '读卡器类型',
						name : 'variant',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否具有退卡能力',
						name : 'canEject'
					}, {
						fieldLabel : '是否具有TrackJisii读能力',
						name : 'trackJisiiRead'
					}, {
						fieldLabel : '是否具有读一磁道数据能力',
						name : 'track1Read'
					}, {
						fieldLabel : '是否具有读二磁道数据能力',
						name : 'track2Read'
					}, {
						fieldLabel : '是否具有读三磁道数据能力',
						name : 'track3Read'
					}, {
						fieldLabel : '是否具有吞卡能力',
						name : 'canCapture'
					} ]
				}, {
					columnWidth : .5,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '90%',
						readOnly : true,
						xtype : 'textfield',
						labelWidth: 180,
						labelAlign : 'right'
					},
					items : [ {
						fieldLabel : '最大吞卡张数',
						name : 'binCapacity',
						style : 'margin-top:2px'
					}, {
						fieldLabel : '是否具有安全支持',
						name : 'security'
					}, {
						fieldLabel : '是否具有TrackJisii写能力',
						name : 'trackJisiiWrite'
					}, {
						fieldLabel : '是否具有写一磁道数据能力',
						name : 'track1Write'
					}, {
						fieldLabel : '是否具有写二磁道数据能力',
						name : 'track2Write'
					}, {
						fieldLabel : '是否具有写三磁道数据能力',
						name : 'track3Write'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});