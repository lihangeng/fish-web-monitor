Ext.define("Eway.view.machine.device.module.PropertyCAM", {
	extend : 'Ext.form.Panel',
	alias : 'widget.machine_device_module_propertycam',
	initComponent : function() {
		Ext.apply(this, {
			defaults : {
				anchor : '100%'
			},
			items : [ {
				title : EwayLocale.machine.device.CAMInfo,
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
						fieldLabel :EwayLocale.machine.device.CAMMaxPictures,//'最大拍照张数'
						name : 'maxPictures',
						style : 'margin-top:2px'
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
						fieldLabel : EwayLocale.machine.device.MaxDataLength,//''最大文字数''
						name : 'maxDataLength',
						style : 'margin-top:2px'
					}]
				} ]
			} ]
		});
		this.callParent(arguments);
	}
});