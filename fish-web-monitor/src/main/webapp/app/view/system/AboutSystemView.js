Ext.define('Eway.view.system.AboutSystemView',{
	alias : 'widget.system_aboutSystemView',
	extend : 'Eway.view.base.Panel',
	requires: ['Eway.view.system.AboutSystemForm'
	          ],
	layout: 'border',
	title :Eway.locale.system.aboutSystem,
	header:false,
	border:false,
	layout: {
        type: 'vbox',
        align:'center'
	},
	initComponent: function() {
		Ext.apply(this, {
			items:[
				{
				  region : 'center',
				  xtype : 'system_form'
			    }
			]
		});
		this.callParent(arguments);
	}
})
