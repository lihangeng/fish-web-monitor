Ext.define('Eway.view.base.Panel',{
	extend : 'Ext.panel.Panel',

	closable : true,
	closeAction : 'hide',

	margin:'10 10 0 10',
	border:true,
	initComponent : function(){
		Ext.apply(this,{});
		this.callParent(arguments);
	}

});