Ext.define('Eway.controller.agent.remote.RemoteBrowseNetWork', {
	extend : 'Ext.app.Controller',
	views : [ 'Eway.view.agent.remote.RemoteBrowseNetWorkView' ],
	refs : [ {
		ref : 'ewayView',
		selector : '#agent_remote_remoteBrowseNetWorkView',
		autoCreate : true,
		xtype : 'agent_remote_remoteBrowseNetWorkView',
		id : 'agent_remote_remoteBrowseNetWorkView'
	} ],

	init : function() {
		this.control({
			'agent_remote_remoteBrowseNetWorkView #bandWidth' : {
				render : this.bandWidth,
				single : true,
				scope : this
			},
			'agent_remote_remoteBrowseNetWorkView button[action="againTest"]' : {
				click : this.againTestClick,
				single : true,
				scope : this
			}
		});
	},
	display : function(ip, sendByte, receivedByte, conenctRate) {
		var win = this.getEwayView();

		if (ip) {
			this.ip = ip;
			var surface = win.query('draw')[0].getSurface();

			surface.add({
				type : 'text',
				stroke : 'black',
				text : conenctRate,
				x : 90,
				y : 120,
				group : 'top-left-line'
			});

			surface.add({
				type : 'text',
				stroke : 'black',
				text : receivedByte,
				x : 260,
				y : 78,
				group : 'top-left-line'
			});

			surface.add({
				type : 'text',
				stroke : 'black',
				text : sendByte,
				x : 90,
				y : 78,
				group : 'top-left-line'
			});
		} else {
			win.query('tabpanel #bandWidth')[0].setDisabled(true);
		}

		win.show();
	},

	bandWidth : function(tab) {
		var ip = this.ip;
		if (!ip) {
			return;
		}
		var store = tab.query('chart')[0].getStore();

		Ext.Ajax.request({
			method : 'POST',
			url : 'api/agent/netWork/bandwidth',
			params : {
				ip : this.ip
			},
			success : function(response) {

				var object = Ext.decode(response.responseText);

				tab.query('button')[0].setDisabled(false);
				if (!object.success || 'false' == object.success) {
					Eway.alert('测试宽带异常.');
					tab.query('displayfield')[0].setValue('<font color="red">测试宽带异常.</font>');
					return;
				}

				var downLoadTime = object.data.downLoadTime;
				var filesize = object.data.filesize;
				var value = (1000 / downLoadTime * filesize / 1024).toFixed(2);

				var data = [];
				data.push({
					ip : ip,
					bandWidth : value
				});
				store.loadData(data);

				var text = '网络最大接入速度为<font color="red">' + value
						+ 'MB</font>/秒';

				if (value >= 2) {
					text += '，相当于 <font color="red">专线</font>  宽带'
				}
				tab.query('displayfield')[0].setValue(text);
			}
		});
	},

	againTestClick : function(btn) {
		btn.setDisabled(true);
		var panel = btn.up('panel');
		this.bandWidth(panel);
	}
});