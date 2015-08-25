Ext.define('Eway.controller.agent.remote.RemoteBrowseScreenCustom',{

	extend : 'Ext.app.Controller',

	views : [
		'agent.remote.RemoteBrowseScreenCustomView'
	],
	stores : ['agent.RemoteBrowseScreenCustom'],
	models : ['agent.RemoteBrowseScreenCustom'],
	refs : [ {
		ref : 'grid',
		selector : 'remote_remoteBrowseScreenCustomGrid'
	} ],

	init : function(){
		this.control({
			'agent_remote_remoteBrowseScreenCustomView remote_remoteBrowseScreenCustomGrid button[action="custom"]' : {
				click : this.onCustom,
				scope : this
			},
			'agent_remote_remoteBrowseScreenCustomView remote_remoteBrowseScreenCustomGrid button[action="admin"]' : {
				click : this.onAdmin,
				scope : this
			},
			'agent_remote_remoteBrowseScreenCustomView remote_remoteBrowseScreenCustomGrid button[action="advertise"]' : {
				click : this.onAdvertise,
				scope : this
			}
		});
	},

	display : function(ip,code){
		var win = Ext.create('Eway.view.agent.remote.RemoteBrowseScreenCustomView');
		win.down('remote_remoteBrowseScreenCustomGrid').getStore().removeAll();//窗口打开时，清掉grid中的数据
		win.show();
		var grid = this.getGrid();
		var winEl = grid.getEl();
		winEl.mask('正在处理......');
		var store = grid.getStore();
		store.load({
			params : {ip : ip, code : code},
			callback: function(records, operation, success){
				winEl.unmask();
				if(success == false && operation.request.scope.reader.jsonData.hasOwnProperty('errors')== true){
					Eway.alert(operation.request.scope.reader.jsonData.errors);
					operation.request.scope.reader.jsonData="";
				}else if(operation.request.scope.reader.jsonData.hasOwnProperty('errors')== false && success == false){
					Eway.alert("与服务器断开连接.");
				}
			}
		});
	},

	onCustom : function(button){
		var win = button.up('agent_remote_remoteBrowseScreenCustomView');
		var winEl = win.getEl();
		winEl.mask('正在处理......');
		var grid = button.up('remote_remoteBrowseScreenCustomGrid');
		var store =  grid.getStore();
		var ip = this.win.down('textfield[name="ip"]').getValue();
		var code = this.win.down('textfield[name="code"]').getValue();
		store.load({
//			url : 'api/agent/screenshot/custom',
			params : {monitorType : 'CUSTOM', ip : ip,code : code},
			callback: function(records, operation, success){
				winEl.unmask();
				if(success == false){
					Eway.alert(operation.request.scope.reader.jsonData.errors);
				}
			}
		});
	},
	onAdmin : function(button){
		var grid = button.up('remote_remoteBrowseScreenCustomGrid');
		var store =  grid.getStore();
		var win = button.up('agent_remote_remoteBrowseScreenCustomView');
		var winEl = win.getEl();
		winEl.mask('正在处理......');
		var ip = this.win.down('textfield[name="ip"]').getValue();
		var code = this.win.down('textfield[name="code"]').getValue();
		store.load({
			scpoe: this,
//			url : 'api/agent/screenshot/custom',
			params : {monitorType : 'ADMIN', ip : ip, code : code},
			callback: function(records, operation, success){
				winEl.unmask();
				if(success == false){
					Eway.alert(operation.request.scope.reader.jsonData.errors);
				}
			}
		});
	},
	onAdvertise : function(button){
		var grid = button.up('remote_remoteBrowseScreenCustomGrid');
		var store =  grid.getStore();
		var win = button.up('agent_remote_remoteBrowseScreenCustomView');
		var winEl = win.getEl();
		winEl.mask('正在处理......');
		var ip = this.win.down('textfield[name="ip"]').getValue();
		var code = this.win.down('textfield[name="code"]').getValue();
		store.load({
//			url : 'api/agent/screenshot/custom',
			params : {monitorType : 'ADVERTISE', ip : ip, code : code},
			callback: function(records, operation, success){
				winEl.unmask();
				if(success == false){
					Eway.alert(operation.request.scope.reader.jsonData.errors);
				}
			}
		});
	}/*,
	onCellClick : function(grid, record, item, index, e, options){
		if(e && e.getTarget().innerHTML === '下载'){
//			var value = record.data.allPath;
//			var index = value.lastIndexOf('resources');
//			var newValue = value.substring(index);
//			var string = 'http://localhost:8085/fish-web/';
//			window.location.href = string + newValue;
			var path = record.allPath;
			var itemEl = grid.getEl();
			var iframe = itemEl.prev();
			if(iframe){
				Ext.core.Element.get(iframe).destroy();
			}
			iframe = Ext.core.DomHelper.createDom({
				tag : 'iframe',
				src : 'api/agent/screenshot/downloadScreen?path=' + path,
				style : "display:none"
			});
			itemEl.insertSibling(iframe);
		}
	}*/
});