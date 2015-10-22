Ext.define('Eway.view.machine.device.DeviceRemoteControl', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.device.deviceRemoteControl',

	requires : [
	            'Eway.view.agent.remote.RemoteBrowseProcessGrid',
	            'Eway.view.agent.remote.RemoteBrowseInstationGrid',
	            'Eway.view.machine.device.DeviceRemoteControlPanel',
	            'Eway.view.agent.remote.RemoteBrowseView',
	            'Eway.view.agent.remote.NetWork',
	            'Eway.view.agent.remote.RemoteBrowseScreenCustomGrid'
	            ],

	id : 'DeviceRemoteControlId',
	title : Eway.locale.machine.device.remoteControl,

	initComponent : function() {
		Ext.apply(this, {
			items :{
				xtype : 'tabpanel',
				width : 1000,
				height : 700,
				activeTab : 0,
				enableTabScroll : true,
				split : true,
				border : true,

				items:[{
						 title:Eway.locale.machine.device.collectJPR,
						 id:'log'
					 },{
						 title:Eway.locale.machine.device.remoteScreen,
						 id:'screen',
						 xtype : 'remote_remoteBrowseScreenCustomGrid'
					 },{
						 title:Eway.locale.machine.device.processCheck,
						 id:'process',
						 xtype : 'remote_remoteBrowseProcessGrid'
					 },{
						 title:Eway.locale.machine.device.remoteExplorer,
						 id:'deviceRemoteControlPanelId',
						 xtype : 'device.deviceRemoteControlPanel'
					 },{
						 title:Eway.locale.machine.device.netWorkLink,
						 id:'netWorkId',
						 xtype : 'remote.NetWork'
					 },{
						 title:Eway.locale.machine.device.remoteRestart,
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : Eway.locale.machine.device.restartApply,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmRestartApply,callback);
								 function callback(id){
									 if(id == 'yes'){

										 Ext.Ajax.request({
												scope: this,
												method : 'POST',
												url : 'api/agent/restart',
												params : {restartType:'APP'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet = '00'){
												           var msgBox = Ext.MessageBox.show({
												                title:Eway.locale.machine.device.progressTip,
												                modal:true,
												                msg : '',
												                progress:true,
												                width:400
												            });
												           var count = 0;
												           var percentage = 0;
												           var processText = '';
												           var task = {
												        	   run:function(){
												        		   count++;
												        		   percentage = count/10;
//												        		   processText = '当前进度：'+percentage*100+'%';
												        		   processText =Eway.locale.machine.device.nowRestartApply;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(Eway.locale.machine.device.restartApplySuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert(Eway.locale.machine.device.restartApplyFail);
													}
												}
											});
									 }
								 }
								}
						 },{
							 xtype : 'button',
							 text : Eway.locale.machine.device.restartDrive,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmRestartDrive,callback);
								 function callback(id){
									 if(id == 'yes'){
										 Ext.Ajax.request({
												scope: this,
												method : 'POST',
												url : 'api/agent/restart',
												params : {restartType:'SP'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet = '00'){
												           var msgBox = Ext.MessageBox.show({
												                title:Eway.locale.machine.device.progressTip,
												                modal:true,
												                msg : '',
												                progress:true,
												                width:400
												            });
												           var count = 0;
												           var percentage = 0;
												           var processText = '';
												           var task = {
												        	   run:function(){
												        		   count++;
												        		   percentage = count/10;
//												        		   processText = '当前进度：'+percentage*100+'%';
												        		   processText =Eway.locale.machine.device.nowRestartDrive;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(Eway.locale.machine.device.restartDriveSuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(Eway.locale.machine.device.restartDriveFail);
													}
												}
											});
									 }
								 }

								}
						 },{
							 xtype : 'button',
							 text : Eway.locale.machine.device.restartOS,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmRestartOS,callback);
								 function callback(id){
									 if(id == 'yes'){
										 Ext.Ajax.request({
												scope: this,
												method : 'POST',
												url : 'api/agent/restart',
												params : {restartType:'OS'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet = '00'){

												           var msgBox = Ext.MessageBox.show({
												                title:Eway.locale.machine.device.progressTip,
												                modal:true,
												                msg : '',
												                progress:true,
												                width:400
												            });
												           var count = 0;
												           var percentage = 0;
												           var processText = '';
												           var task = {
												        	   run:function(){
												        		   count++;
												        		   percentage = count/10;
//												        		   processText = '当前进度：'+percentage*100+'%';
												        		   processText =Eway.locale.machine.device.nowRestartOS;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(Eway.locale.machine.device.restartOSSuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(Eway.locale.machine.device.restartOSFail);
													}
												}
											});
									 }
								 }

								}
						 }]
					 },{
						 title:Eway.locale.machine.device.remoteShutdown,
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : Eway.locale.machine.device.shutdownApply,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmShutdownApply,callback);
								 function callback(id){
									 if(id == 'yes'){
										 Ext.Ajax.request({
												scope : this,
												method : 'POST',
												url : 'api/agent/shutdown',
												params : {shutdownType : 'APP'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet = '00'){

														var msgBox = Ext.MessageBox.show({
											                title:Eway.locale.machine.device.progressTip,
											                modal:true,
											                msg : '',
											                progress:true,
											                width:400
											            });
											           var count = 0;
											           var percentage = 0;
											           var processText = '';
											           var task = {
											        	   run:function(){
											        		   count++;
											        		   percentage = count/10;
//											        		   processText = '当前进度：'+percentage*100+'%';
											        		   processText =Eway.locale.machine.device.nowShutdownApply;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(Eway.locale.machine.device.shutdownApplySuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert(Eway.locale.machine.device.shutdownApplyFail);
													}
												}
											});
									 }
								 }

							 }
						 },{
							 xtype : 'button',
							 text : Eway.locale.machine.device.shutdownDrive,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmShutdownDrive,callback);
								 function callback(id){
									 if(id == 'yes'){
										 Ext.Ajax.request({
												scope : this,
												method : 'POST',
												url : 'api/agent/shutdown',
												params : {shutdownType : 'SP'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet = '00'){

														var msgBox = Ext.MessageBox.show({
											                title:Eway.locale.machine.device.progressTip,
											                modal:true,
											                msg : '',
											                progress:true,
											                width:400
											            });
											           var count = 0;
											           var percentage = 0;
											           var processText = '';
											           var task = {
											        	   run:function(){
											        		   count++;
											        		   percentage = count/10;
//											        		   processText = '当前进度：'+percentage*100+'%';
											        		   processText =Eway.locale.machine.device.nowShutdownDrive;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(Eway.locale.machine.device.shutdownDriveSuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(Eway.locale.machine.device.shutdownDriveFail);
													}
												}
											});
									 }
								 }

							 }


						 },{
							 xtype : 'button',
							 text : Eway.locale.machine.device.shutdownOS,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(Eway.locale.tip.remind,Eway.locale.machine.device.confirmShutdownOS,callback);
								 function callback(id){
									 if(id == 'yes'){
										 Ext.Ajax.request({
												scope : this,
												method : 'POST',
												url : 'api/agent/shutdown',
												params : {shutdownType : 'OS'},
												success : function(response){
													var value = response.responseText;
													var object =Ext.JSON.decode(value);
													var appRet = object.appRet;
													if(appRet == '00'){

														var msgBox = Ext.MessageBox.show({
											                title:Eway.locale.machine.device.progressTip,
											                modal:true,
											                msg : '',
											                progress:true,
											                width:400
											            });
											           var count = 0;
											           var percentage = 0;
											           var processText = '';
											           var task = {
											        	   run:function(){
											        		   count++;
											        		   percentage = count/10;
//											        		   processText = '当前进度：'+percentage*100+'%';
											        		   processText =Eway.locale.machine.device.nowShutdownOS;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+Eway.locale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(Eway.locale.machine.device.shutdownOSSuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet == '01'){
														Eway.alert(Eway.locale.machine.device.shutdownOSFail);
													}
												}
											});
									 }

								 }

							 }
						 }]
					 },{
						 title:Eway.locale.machine.device.getSoftwareList,
						 id:'software',
						 xtype : 'remote_remoteBrowseInstationGrid'
					 },{
						 title:Eway.locale.machine.device.forceReset,
						 id:'reset'
					 },{
						 title:Eway.locale.machine.device.openService,
						 id:'openservice'
					 },{
						 title:Eway.locale.machine.device.pauseService,
						 id:'pause'
					 },{
						 title:Eway.locale.machine.device.checkStatus,
						 id:'check'
					 }]
			}
		});

		this.callParent(arguments);
	}

});