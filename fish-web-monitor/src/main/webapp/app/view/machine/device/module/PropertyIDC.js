Ext.define('Eway.view.machine.device.module.PropertyIDC', {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertyidc',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : Eway.locale.machine.device.IDCInfo,
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
						fieldLabel : Eway.locale.machine.device.variant,
						name : 'variant',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.canEjectCard,
						name : 'canEject'
					}, {
						fieldLabel : Eway.locale.machine.device.trackJisiiRead,
						name : 'trackJisiiRead'
					}, {
						fieldLabel : Eway.locale.machine.device.track1Read,
						name : 'track1Read'
					}, {
						fieldLabel : Eway.locale.machine.device.track2Read,
						name : 'track2Read'
					}, {
						fieldLabel : Eway.locale.machine.device.track3Read,
						name : 'track3Read'
					}, {
						fieldLabel : Eway.locale.machine.device.canCapture,
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
						fieldLabel :  Eway.locale.machine.device.binCapacity,
						name : 'binCapacity',
						style : 'margin-top:2px'
					}, {
						fieldLabel : Eway.locale.machine.device.security,
						name : 'security'
					}, {
						fieldLabel : Eway.locale.machine.device.trackJisiiWrite,
						name : 'trackJisiiWrite'
					}, {
						fieldLabel :  Eway.locale.machine.device.track1Write,
						name : 'track1Write'
					}, {
						fieldLabel :  Eway.locale.machine.device.track2Write,
						name : 'track2Write'
					}, {
						fieldLabel : Eway.locale.machine.device.track3Write,
						name : 'track3Write'
					} ]
				} ]
			} ]
		});

		this.callParent(arguments);
	}
});