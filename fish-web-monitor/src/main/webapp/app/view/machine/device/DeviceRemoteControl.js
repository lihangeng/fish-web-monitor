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
	title : '远程控制',

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
						 title:'提取日志',
						 id:'log'
					 },{
						 title:'远程抓屏',
						 id:'screen',
						 xtype : 'remote_remoteBrowseScreenCustomGrid'
					 },{
						 title:'进程查看',
						 id:'process',
						 xtype : 'remote_remoteBrowseProcessGrid'
					 },{
						 title:'远程浏览',
						 id:'deviceRemoteControlPanelId',
						 xtype : 'device.deviceRemoteControlPanel'
					 },{
						 title:'网络连接',
						 id:'netWorkId',
						 xtype : 'remote.NetWork'
					 },{
						 title:'远程重启',
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : ' 重启应用',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要重启应用？',callback);
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
												                title:'进度提示',
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
												        		   processText ='正在重启应用';
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert('成功重启该设备应用');
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert('重启应用失败！');
													}
												}
											});
									 }
								 }
								}
						 },{
							 xtype : 'button',
							 text : '重启硬件驱动',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要重启硬件驱动？',callback);
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
												                title:'进度提示',
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
												        		   processText ='正在重启硬件驱动';
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert('成功重启该设备硬件驱动');
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert('重启硬件驱动失败！');
													}
												}
											});
									 }
								 }

								}
						 },{
							 xtype : 'button',
							 text : '重启操作系统',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要重启操作系统？',callback);
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
												                title:'进度提示',
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
												        		   processText ='正在重启操作系统';
												        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
												        		   if(count>10){
												        			   Ext.TaskManager.stop(task);
												                        msgBox.hide();
												                        Eway.alert('成功重启该设备操作系统');
												                    }
												        	   } ,
												        	   interval:1000
												           }
												           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert('重启操作系统失败！');
													}
												}
											});
									 }
								 }

								}
						 }]
					 },{
						 title:'远程关机',
						 layout: {
                             type: 'hbox',
                             padding:'5',
                             align:'top'
                         },
						 defaults : {margins:'0 5 0 0'},
						 items : [{
							 xtype : 'button',
							 text : ' 关闭应用',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要关闭应用？',callback);
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
											                title:'进度提示',
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
											        		   processText ='正在关闭应用';
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert('成功关闭该设备应用');
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet = '01'){
														Eway.alert('关闭应用失败！');
													}
												}
											});
									 }
								 }

							 }
						 },{
							 xtype : 'button',
							 text : '关闭硬件驱动',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要关闭硬件驱动？',callback);
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
											                title:'进度提示',
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
											        		   processText ='正在关闭硬件驱动';
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert('成功关闭该设备硬件驱动');
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);
													}else if(appRet = '01'){
														Eway.alert('关闭硬件驱动失败！');
													}
												}
											});
									 }
								 }

							 }


						 },{
							 xtype : 'button',
							 text : '关闭操作系统',
							 scale : 'large',
							 handler : function(){
								 Ext.MessageBox.confirm('提示','确定要关闭操作系统？',callback);
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
											                title:'进度提示',
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
											        		   processText ='正在关闭操作系统';
											        		   msgBox.updateProgress(percentage,processText,'<font color="red">这是通过动态更新内容形成的进度条</font>');
											        		   if(count>10){
											        			   Ext.TaskManager.stop(task);
											                        msgBox.hide();
											                        Eway.alert('成功关闭该设备操作系统');
											                    }
											        	   } ,
											        	   interval:1000
											           }
											           Ext.TaskManager.start(task);

													}else if(appRet == '01'){
														Eway.alert('关闭操作系统失败！');
													}
												}
											});
									 }

								 }

							 }
						 }]
					 },{
						 title:'获取软件安装列表',
						 id:'software',
						 xtype : 'remote_remoteBrowseInstationGrid'
					 },{
						 title:'强制复位',
						 id:'reset'
					 },{
						 title:'开启服务',
						 id:'openservice'
					 },{
						 title:'暂停服务',
						 id:'pause'
					 },{
						 title:'状态检测',
						 id:'check'
					 }]
			}
		});

		this.callParent(arguments);
	}

});