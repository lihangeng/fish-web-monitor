Ext.define('Eway.controller.agent.remote.RemoteBrowseInstation',{

	extend : 'Ext.app.Controller',

	views : [
		'Eway.view.agent.remote.RemoteBrowseInstationView',
		'Eway.view.agent.remote.RemoteBrowseInstationGrid'
	],
	stores : ['agent.RemoteBrowseInstation'],
	models : ['agent.RemoteBrowseInstation'],

	init : function(){
		this.control({
			'remote_remoteBrowseInstationGrid' : {
				'render' : function(){
				}
			}
		});
	},

	display : function(ip){
		var win = Ext.widget('remote_remoteBrowseInstationView',{
			width : 800,
			height : 500
		});
		win.show();
		var grid = win.down('remote_remoteBrowseInstationGrid');
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
	}

});