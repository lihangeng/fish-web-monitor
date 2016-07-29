Ext.define("Eway.view.machine.device.module.PropertyUKR", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyukr',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.UKRInfo,
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
						fieldLabel : EwayLocale.machine.device.variantUKR,//'获取读卡器类型',
						name : 'variant',
						style : 'margin-top:2px'
					} ,{
						fieldLabel : EwayLocale.machine.device.canEjectUKR,//'是否具有退卡能力',
						name : 'canEject',
						style : 'margin-top:2px'
					} ,{
						fieldLabel : EwayLocale.machine.device.canCaptureUKR,//'是否具有吞卡能力',
						name : 'canCapture',
						style : 'margin-top:2px'
					} ,{
						fieldLabel : EwayLocale.machine.device.binCapacityUKR,//'获取最大吞卡张数',
						name : 'binCapacity',
						style : 'margin-top:2px'
					} ]
				},{
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
						fieldLabel : EwayLocale.machine.device.security,//'是否具有安全支持',
						name : 'security',
						style : 'margin-top:2px'
					},{
						fieldLabel : EwayLocale.machine.device.trackJisiiRead,//'是否具有TrackJisii读能力',
						name : 'trackJisiiRead',
						style : 'margin-top:2px'
					},{
						fieldLabel : EwayLocale.machine.device.trackJisiiWrite,//'是否具有TrackJisii写能力',
						name : 'trackJisiiWrite',
						style : 'margin-top:2px'
					}]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});