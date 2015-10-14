Ext.define('Eway.controller.agent.remote.RemoteLookVesion',{

	extend : 'Ext.app.Controller',
	
	views : [],
	stores : [],
	models : [],
	
	init : function(){
		this.control({
		});
	},
	
	display : function(code, ip){
		var win = Ext.create('Ext.window.Window',{
			width : 400,
			height : 200,
			title : Eway.locale.agent.remote.checkVersionInfo,
			modal : true,
		    layout: 'border',
		    items: [{
			    	region:'north',
			    	height: 50,
			    	border:false,
			    	xtype:'panel',
			    	html:'<br><font size="2" face="Times"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+Eway.locale.agent.remote.versionInfo+'</b></font>'
				},{
			    	xtype : 'form',
			    	border : 0 ,
			    	region:'center',
			    	items : [{
					    xtype : 'displayfield',
					    fieldLabel: Eway.locale.agent.remote.ATMCVersion,
					    name : 'atmcVersion'
					},{
						xtype : 'displayfield',
						fieldLabel: Eway.locale.agent.remote.monitorVersion,
						name : 'agentVersion'
					}]
				}]
		});
		
		this.win = win;
		
//		Ext.Ajax.request({
//   			method : 'POST',
//   			url : 'api/agent/atmVersion/versioninfo',
//   			params :{
//   				terminalId: code,
//   				ip: ip
//   			},
//   			success : function(response){
//   				var object = Ext.decode(response.responseText);
//   				if(object.success == true){
//   					if(object.data != null){
//   						win.down('form').down('displayfield[name="atmcVersion"]').setValue(object.data.atmcVersion);
//   	   					win.down('form').down('displayfield[name="agentVersion"]').setValue(object.data.agentVersion);
//   	   					win.show();
//   					}else{
//   						win.close();
//   						Ext.Msg.alert("提示", "查看失败，请重新操作！");
//   					}
//   				}else{
//   					win.close();
//   					Ext.Msg.alert("提示", "查看失败，请重新操作！");
//   				}
//   			},
//   			failure : function(){
//   				win.close();
//   				Ext.Msg.alert("提示", "查看失败！");
//   			}
//		});
		
	}
	
});