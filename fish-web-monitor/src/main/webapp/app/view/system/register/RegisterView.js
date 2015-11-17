Ext.define('Eway.view.system.register.RegisterView',{
	alias : 'widget.system_RegisterView',
	extend : 'Eway.view.base.Panel',
	requires: ['Eway.view.system.register.RegisterForm'],
	layout: 'border',
	title :EwayLocale.system.sysRegist,
	layout: {
        type: 'vbox',
        align:'center'
	},
	header:false,
	initComponent: function() {
		Ext.apply(this, {
			items:[
				{
				  region : 'center',
				  xtype : 'register_form'
			    }
			]
		});
		this.callParent(arguments);
	}

})

