Ext.define('Eway.view.machine.detail.basic.HiddenControllerInfo', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.detail_basic_hiddenControllerInfo',
	

	requires : [],
	layout: {
        type: 'column'
    },
    closable:false,
    //bodyPadding: 10,
    

	initComponent : function() {
		Ext.apply(this, {
			layout : 'column',
			defaults : {
				xtype : 'displayfield',
				labelWidth : 115,
				width : '25%'
			},
			items : []
		});
		
		this.callParent(arguments);
	}
});
