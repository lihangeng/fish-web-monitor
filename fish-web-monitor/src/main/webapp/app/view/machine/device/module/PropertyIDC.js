Ext.define('Eway.view.machine.device.module.PropertyIDC', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyidc',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.IDCInfo,
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
						fieldLabel : EwayLocale.machine.device.variant,
						name : 'variant',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.canEjectCard,
						name : 'canEject'
					}, {
						fieldLabel : EwayLocale.machine.device.trackJisiiRead,
						name : 'trackJisiiRead'
					}, {
						fieldLabel : EwayLocale.machine.device.track1Read,
						name : 'track1Read'
					}, {
						fieldLabel : EwayLocale.machine.device.track2Read,
						name : 'track2Read'
					}, {
						fieldLabel : EwayLocale.machine.device.track3Read,
						name : 'track3Read'
					}, {
						fieldLabel : EwayLocale.machine.device.canCapture,
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
						fieldLabel :  EwayLocale.machine.device.binCapacity,
						name : 'binCapacity',
						style : 'margin-top:2px'
					}, {
						fieldLabel : EwayLocale.machine.device.security,
						name : 'security'
					}, {
						fieldLabel : EwayLocale.machine.device.trackJisiiWrite,
						name : 'trackJisiiWrite'
					}, {
						fieldLabel :  EwayLocale.machine.device.track1Write,
						name : 'track1Write'
					}, {
						fieldLabel :  EwayLocale.machine.device.track2Write,
						name : 'track2Write'
					}, {
						fieldLabel : EwayLocale.machine.device.track3Write,
						name : 'track3Write'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});