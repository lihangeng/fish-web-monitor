Ext.define('Eway.view.base.Panel',{
	extend : 'Ext.panel.Panel',

	closable : true,
	closeAction : 'hide',

	initComponent : function(){
		Ext.apply(this,{});
		this.callParent(arguments);
	}

});