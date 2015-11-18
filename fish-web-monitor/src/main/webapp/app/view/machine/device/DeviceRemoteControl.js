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
	title : EwayLocale.machine.device.remoteControl,

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
						 title:EwayLocale.machine.device.collectJPR,
						 id:'log'
					 },{
						 title:EwayLocale.machine.device.remoteScreen,
						 id:'screen',
						 xtype : 'remote_remoteBrowseScreenCustomGrid'
					 },{
						 title:EwayLocale.machine.device.processCheck,
						 id:'process',
						 xtype : 'remote_remoteBrowseProcessGrid'
					 },{
						 title:EwayLocale.machine.device.remoteExplorer,
						 id:'deviceRemoteControlPanelId',
						 xtype : 'device.deviceRemoteControlPanel'
					 },{
						 title:EwayLocale.machine.device.netWorkLink,
						 id:'netWorkId',
						 xtype : 'remote.NetWork'
					 },{
						 title:EwayLocale.machine.device.remoteRestart,
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : EwayLocale.machine.device.restartApply,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmRestartApply,callback);
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
												                title:EwayLocale.machine.device.progressTip,
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
												        		   processText =EwayLocale.machine.device.nowRestartApply;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(EwayLocale.machine.device.restartApplySuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert(EwayLocale.machine.device.restartApplyFail);
													}
												}
											});
									 }
								 }
								}
						 },{
							 xtype : 'button',
							 text : EwayLocale.machine.device.restartDrive,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmRestartDrive,callback);
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
												                title:EwayLocale.machine.device.progressTip,
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
												        		   processText =EwayLocale.machine.device.nowRestartDrive;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(EwayLocale.machine.device.restartDriveSuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(EwayLocale.machine.device.restartDriveFail);
													}
												}
											});
									 }
								 }

								}
						 },{
							 xtype : 'button',
							 text : EwayLocale.machine.device.restartOS,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmRestartOS,callback);
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
												                title:EwayLocale.machine.device.progressTip,
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
												        		   processText =EwayLocale.machine.device.nowRestartOS;
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert(EwayLocale.machine.device.restartOSSuc);
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(EwayLocale.machine.device.restartOSFail);
													}
												}
											});
									 }
								 }

								}
						 }]
					 },{
						 title:EwayLocale.machine.device.remoteShutdown,
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : EwayLocale.machine.device.shutdownApply,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmShutdownApply,callback);
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
											                title:EwayLocale.machine.device.progressTip,
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
											        		   processText =EwayLocale.machine.device.nowShutdownApply;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(EwayLocale.machine.device.shutdownApplySuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert(EwayLocale.machine.device.shutdownApplyFail);
													}
												}
											});
									 }
								 }

							 }
						 },{
							 xtype : 'button',
							 text : EwayLocale.machine.device.shutdownDrive,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmShutdownDrive,callback);
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
											                title:EwayLocale.machine.device.progressTip,
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
											        		   processText =EwayLocale.machine.device.nowShutdownDrive;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(EwayLocale.machine.device.shutdownDriveSuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert(EwayLocale.machine.device.shutdownDriveFail);
													}
												}
											});
									 }
								 }

							 }


						 },{
							 xtype : 'button',
							 text : EwayLocale.machine.device.shutdownOS,
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm(EwayLocale.tip.remind,EwayLocale.machine.device.confirmShutdownOS,callback);
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
											                title:EwayLocale.machine.device.progressTip,
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
											        		   processText =EwayLocale.machine.device.nowShutdownOS;
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">'+EwayLocale.machine.device.updateProBar+'</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert(EwayLocale.machine.device.shutdownOSSuc);
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet == '01'){
														Eway.alert(EwayLocale.machine.device.shutdownOSFail);
													}
												}
											});
									 }

								 }

							 }
						 }]
					 },{
						 title:EwayLocale.machine.device.getSoftwareList,
						 id:'software',
						 xtype : 'remote_remoteBrowseInstationGrid'
					 },{
						 title:EwayLocale.machine.device.forceReset,
						 id:'reset'
					 },{
						 title:EwayLocale.machine.device.openService,
						 id:'openservice'
					 },{
						 title:EwayLocale.machine.device.pauseService,
						 id:'pause'
					 },{
						 title:EwayLocale.machine.device.checkStatus,
						 id:'check'
					 }]
			}
		});

		this.callParent(arguments);
	}

});