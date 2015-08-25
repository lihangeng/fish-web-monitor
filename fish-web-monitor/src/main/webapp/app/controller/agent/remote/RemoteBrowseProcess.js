Ext.define('Eway.controller.agent.remote.RemoteBrowseProcess',{

	extend : 'Ext.app.Controller',

	views : [
		'Eway.view.agent.remote.RemoteBrowseProcessView',
		'Eway.view.agent.remote.RemoteBrowseProcessGrid'
	],
	stores : ['agent.RemoteBrowseProcess'],
	models : ['agent.RemoteBrowseProcess'],

	init : function(){
		this.control({
			'remote_remoteBrowseProcessGrid' : {
				'render' : function(){
				}
			}
		});
	},

	display : function(ip){
		var win = Ext.widget('remote_remoteBrowseProcessView',{
			width : 800,
			height : 500
		});
		win.show();
		var grid = win.down('remote_remoteBrowseProcessGrid');
		var store = grid.getStore();
		store.load({
			scope: this,
			params : {
				ip : ip
			},
			callback: function(records, operation, success){
				if(success == false){
					Eway.alert(operation.getError());
				}
			}
		});
	},
	test : function(){

	}

});