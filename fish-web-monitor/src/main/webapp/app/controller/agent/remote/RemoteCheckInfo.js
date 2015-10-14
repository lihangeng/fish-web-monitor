Ext.define('Eway.controller.agent.remote.RemoteCheckInfo',{

	extend : 'Ext.app.Controller',

	views : [
		'Eway.view.agent.remote.RemoteCheckInfoWin'
	],
	stores : ['machine.atmHardSoft.AtmHardSoft','machine.atmHardSoft.Disk'],
	models : ['machine.atmHardSoft.AtmHardSoft','machine.atmHardSoft.Disk'],

	init : function(){
		this.control({
		});
	},

	display : function(ip,terminalId){
		var me = this;
		var win = Ext.create('Eway.view.agent.remote.RemoteCheckInfoWin');
		win.show();
		this.checkATM(win,ip,terminalId);
		var checkButton = win.down('button[action="checkATM"]');
		checkButton.on('click',Ext.bind(this.checkATM,this,[win,ip,terminalId]),this);
	},

	checkATM : function(win,ip,terminalId){
		if(!win.down('progressbar')){
			var p = Ext.create('Ext.ProgressBar', {});
		}
		else {
			var p=win.down('progressbar');
		}
		var me = this;
		var htmlPanel = Ext.getCmp("checkATMPointhtml");
		htmlPanel.body.update('');
		htmlPanel.add(p);
		win.show();
		p.wait({
			interval: 500,
		    duration: 50000,
		    increment: 2,
		    text: '<font color="#FFB6C1" size = "3">'+Eway.locale.agent.remote.ATMCheck+'</font>',
		    scope: this
        });
		Ext.Ajax.request({
   			method : 'GET',
   			url : 'api/agent/check',
   			params :{
   				ip : ip
   			},
   			success : function(response){
   				var object = Ext.decode(response.responseText);
   				if(object.success == true){
   					if(object.data != null){
   						htmlPanel.remove(p);
   						win.hide();
   						win.down('form').down('displayfield[name="cpuIdle"]').setValue(object.data.cpuIdle);
   	   					win.down('form').down('displayfield[name="memoryIdle"]').setValue(object.data.memoryIdle);
   	   					win.down('form').down('displayfield[name="hardDiskIdle"]').setValue('<pre class="link">'+object.data.hardDiskIdle+'</pre>');
   	   					if(object.data.checkPoint>=85){
   	   						htmlPanel.body.update('<p><font color="#45B97C" size = "3">'+Eway.locale.agent.remote.ATMExamination+':&nbsp;&nbsp;&nbsp<font size="4">'+Eway.locale.agent.remote.excellent+'（'+object.data.checkPoint+Eway.locale.agent.remote.point'）</p>'+'<br>'); 
   	   					}else if(object.data.checkPoint>=70){
   	   						htmlPanel.body.update('<p><font color="#698B22" size = "3">'+Eway.locale.agent.remote.ATMExamination+':&nbsp;&nbsp;&nbsp<font size="4">'+Eway.locale.agent.remote.fine+'（'+object.data.checkPoint+Eway.locale.agent.remote.point'）</p>'+'<br>'); 
   	   					}else if(object.data.checkPoint>=50){
   	   						htmlPanel.body.update('<p><font color="#FF8C00" size = "3">'+Eway.locale.agent.remote.ATMExamination+':&nbsp;&nbsp;&nbsp<font size="4">'+Eway.locale.agent.remote.middle+'（'+object.data.checkPoint+Eway.locale.agent.remote.point'）</p>'+'<br>'); 
   	   					}else{
   	   						htmlPanel.body.update('<p><font color="#FF0000" size = "3">'+Eway.locale.agent.remote.ATMExamination+':&nbsp;&nbsp;&nbsp<font size="4">'+Eway.locale.agent.remote.bad+'（'+object.data.checkPoint+Eway.locale.agent.remote.point'）</p>'+'<br>'); 
   	   					}
   	   					var hardDiskIdle = win.down('displayfield[name="hardDiskIdle"]');
						var text = hardDiskIdle.getEl().down('pre.link');
						if (text) {
							text.on('click', function(e, htmlEl) {
								var form = hardDiskIdle.up('form');
								var hardDiskWin = Ext.create('Eway.view.agent.remote.DiskDetail');
								var store = me.getMachineAtmHardSoftAtmHardSoftStore();
								store.load({
									params : {
										terminalId : terminalId
									},
									callback : function(records, operation, success) {// 回调函数
										if (!success) {
											Eway.alert(Eway.locale.vtype.devLinkNormal);
											return;
										}
										if (records && records.length > 0) { // 判断是否有数据
											// 加载disk的信息
											hardDiskWin.down('grid').getStore().loadData(records[0].get('hardDisk'));
										}
										hardDiskWin.show();
									}
								});
							}, this);
							win.show();
						}
   					}else{
   						Eway.alert(Eway.locale.agent.remote.checkFailure);
   						win.close();
   					}
   				}else{
   					Eway.alert(Eway.locale.agent.remote.checkFailure);
   					win.close();
   				}
   			},
   			failure : function(){
   				Eway.alert(Eway.locale.agent.remote.checkFailure);
   				win.close();
   			}
		});
	}


});