Ext.define('Eway.controller.agent.remote.RemoteBrowse',{

	extend : 'Ext.app.Controller',

	views : [
		'agent.remote.RemoteBrowseView',
		'agent.remote.RemoteBrowseDiskGrid',
		'agent.remote.RemoteBrowseFileSystemGrid'
	],
	stores : [
		'agent.RemoteBrowseDisk',
		'agent.RemoteBrowseFileSystem'
	],
	models : [
		'agent.RemoteBrowseDisk',
		'agent.RemoteBrowseFileSystem'
	],
	init : function(){
	},

	display : function(ip){
		var win = Ext.widget('remote_remoteBrowseView',{
			width : 700,
			height : 450
		});
		win.show();
		var grid = win.down('remote_remoteBrowseDiskGrid');
		grid.getStore().load({
			params : {
				ip : ip
			},
			scope   : this,
		    callback: function(records, operation, success) {
		        if(success==true){
		        	var me = this;
					this.win = win;
					grid.on('itemdblclick',me.onRemoteBrowseFileSystem,this);
					var path = win.down('textfield[name="path"]').getValue();
					win.down('button[action="upFile"]').on('click',me.onUpFile,this);;
					win.down('button[action="remoteMkDir"]').on('click',Ext.bind(me.onRemoteMkDir,this,[true]),this);;
					win.down('button[action="remoteMkFile"]').on('click',Ext.bind(me.onRemoteMkDir,this,[false]),this);;
					win.down('button[action="remoteDel"]').on('click',me.onRemoteDel,this);;
					win.down('button[action="remoteExec"]').on('click',me.onRemoteExec,this);
				}else{
					Eway.alert(Eway.locale.vtype.remoteFailure);
				}
			}
		});
		win.down('textfield[name="ip"]').setValue(ip);
	},
	/**
	 * 远程新建
	 * */
	onRemoteMkDir:function(isDirectory){
		var me = this;
		var mkFileWin = Ext.create('Eway.view.agent.remote.RemoteMkFileSys');
		var path = me.win.down('textfield[name="path"]').getValue();

		if(isDirectory){
			mkFileWin.setTitle(Eway.locale.agent.remote.MKcatalog);
			mkFileWin.down('textfield[name="file"]').setFieldLabel(Eway.locale.agent.remote.catalogName);
		}
		else{
			mkFileWin.setTitle(Eway.locale.agent.remote.MkFile);
			mkFileWin.down('textfield[name="file"]').setFieldLabel(Eway.locale.agent.remote.screen.fileNameClient);
		}
		mkFileWin.down('[name="nowPath"]').setValue(path);
		mkFileWin.down('button[action="confirm"]').on('click',Ext.bind(this.onMkDirConfirm,this,[path,mkFileWin,isDirectory]),this);
		mkFileWin.show();
	},
	//TODO
	/**
	 *path 原始路径（暂时无用）
	 *mkFileWin(新建文件系统窗口)
	 *isDirectory 是否新建目录，不是目录则是文件
	 *isForce (是否强制新建;true存在则覆盖)
	 * */
	onMkDirConfirm:function(path,mkFileWin,isDirectory){
		var me = this;
		var newFile = mkFileWin.down('textfield[name="file"]').getValue();
		var ip = this.win.down('textfield[name="ip"]').getValue();
		Ext.Ajax.request({
		    url: 'api/agent/fileSysOperator/mkFileSys',
		    method:'POST',
		    params: {
		        ip:ip,
		        path:path,
		        file:newFile,
		        isDirectory:isDirectory
		    },
		    success: function(response){
		        var obj = Ext.decode(response.responseText);
		        if(obj.success==true){
					Eway.alert(Eway.locale.agent.remote.distanceSuccess);
		        }
		        else{
					Eway.alert(Eway.locale.agent.remote.distanceFailure);
		        }
		        me.onQuery();
		        mkFileWin.close();
		    },
		    failure:function(){

				Eway.alert(Eway.locale.agent.remote.distanceFailure);
		    }
		});
	},

	/**
	 * 远程删除,没有选中文件或者文件夹不做处理
	 * */
	onRemoteDel:function(){
		var me = this;
		var ip = me.win.down('textfield[name="ip"]').getValue();
		var grid = me.win.down('remote_remoteBrowseFileSystemGrid');
		var path = me.win.down('textfield[name="path"]').getValue();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var newFile = record.get("name");
			Ext.Msg.confirm({
			    title: Eway.locale.tip.remind,
			    msg: Eway.locale.agent.remote.confirmDelete+'['+newFile+']?',
			    width: 300,
	            modal:true,
	            closable:true,
			    buttons: Ext.Msg.OKCANCEL,
			    fn: function(but,text){
					if(but=='ok'){
						Ext.Ajax.request({
						    url: 'api/agent/fileSysOperator/deleteFile',
						    method:'POST',
						    params: {
						        ip:ip,
						        path:path,
						        file:newFile
						    },
						    success: function(response){
						        var obj = Ext.decode(response.responseText);
						        if(obj.success==true){
									Eway.alert(Eway.locale.agent.remote.distanceSuccess);
						        }
						        else{
									Eway.alert(Eway.locale.agent.remote.distanceFailure);
						        }
						        me.onQuery();
						    },
						    failure:function(){

								Eway.alert(Eway.locale.agent.remote.distanceFailure);
						    }
						});
						sm.deselect(record);
					}
				},
			    icon: Ext.window.MessageBox.INFO
			});

		}
		else{
			Eway.alert(Eway.locale.agent.remote.choseDeleteFile);
		}
	},



	/**
	 * 远程执行,没有选中文件不做处理
	 * */
	onRemoteExec:function(){
		var me = this;
		var ip = me.win.down('textfield[name="ip"]').getValue();
		var grid = me.win.down('remote_remoteBrowseFileSystemGrid');
		var path = me.win.down('textfield[name="path"]').getValue();
		var sm = grid.getSelectionModel();
		if (sm.getCount() == 1) {
			var record = sm.getLastSelected();
			var newFile = record.get("name");
			Ext.Msg.confirm({
			    title: Eway.locale.tip.remind,
			    msg: Eway.locale.agent.remote.confirmExecute+'['+newFile+']?',
			    width: 300,
	            modal:true,
	            closable:true,
			    buttons: Ext.Msg.OKCANCEL,
			    fn: function(but,text){
					if(but=='ok'){
						Ext.Ajax.request({
						    url: 'api/agent/fileSysOperator/execFile',
						    method:'POST',
						    params: {
						        ip:ip,
						        path:path,
						        file:newFile
						    },
						    success: function(response){
						        var obj = Ext.decode(response.responseText);
						        if(obj.success==true){
									Eway.alert(Eway.locale.agent.remote.distanceExecuteSuccess);
						        }
						        else{
									Eway.alert(Eway.locale.agent.remote.distanceExecuteFailure);
						        }
						        me.onQuery();
						    },
						    failure:function(){

								Eway.alert(Eway.locale.agent.remote.distanceExecuteFailure);
						    }
						});
						sm.deselect(record);
					}
				},
			    icon: Ext.window.MessageBox.INFO
			});
		}
		else{
			Eway.alert(Eway.locale.agent.remote.choseExecuteFile);
		}
	},

	onRemoteBrowseFileSystem : function(view, record, item, index){
		var me = this;
		var diskgrid = view.up('remote_remoteBrowseDiskGrid');
		var diskstore=diskgrid.getStore();
		var layout = diskgrid.up('window').getLayout();

	    var diskRecord=diskstore.getAt(index);
	    var path=diskRecord.get('path');
	    var ip = this.win.down('textfield[name="ip"]').getValue();
		var panel = layout.next();
		var store =  panel.getStore();
		store.load({
			params : {path : path ,ip : ip},
			scope   : this,
		    callback: function(records, operation, success) {
				this.store = store;
		        if(success==true){
		        	this.win.setTitle(Eway.locale.agent.remote.distanceExplorer+path);
					this.win.down('textfield[name="path"]').setValue(path);
					this.win.down('textfield[name="queryPath"]').setValue(path);
					panel.on('itemdblclick',me.onRemoteBrowseFileSystem2,this);
				}else{
					Eway.alert(Eway.locale.agent.remote.distanceExplorerFailure);
				}
			}
		});

		//返回上层目录：
		var returnbutton = panel.down('button[action="returnMenu"]');
		returnbutton.on('click',me.returnMenu,this);

		//刷新按钮刷新：
		var refleshbutton = panel.down('button[action="reflesh"]');
		refleshbutton.on('click',me.reflesh,this);

		//查询按钮：
		var querybutton = panel.down('button[action="query"]');
		querybutton.on('click',me.onQuery,this);
		this.win.down('textfield[name="queryPath"]').on('keypress',function(field,e){
                if (e.getKey() == e.ENTER && field.getValue().length > 0) {
                   me.onQuery();
                }});
	},

	onRemoteBrowseFileSystem2 : function(view, record, item, index){
		var me = this;
		var grid = view.up('remote_remoteBrowseFileSystemGrid');
		var store = grid.getStore();
	    var record = store.getAt(index);
	    var path = record.get('path');
	    var ip = this.win.down('textfield[name="ip"]').getValue();
	    var type = record.get('type');
	    if(type=="DIR"){
		    store.load({
		    	params : {path :path ,ip : ip},
		    	scope   : this,
			    callback: function(records, operation, success) {
			        if(success==true){
			        	this.win.setTitle(Eway.locale.agent.remote.distanceExplorer+path);
					    this.win.down('textfield[name="path"]').setValue(path);
					    this.win.down('textfield[name="queryPath"]').setValue(path);
						grid.on('itemdblclick',me.onRemoteBrowseFileSystem2,this);
					}else{
						Eway.alert(Eway.locale.agent.remote.distanceExplorerFailure);
					}
				}
		    });
	    }

	    //返回上层目录：
		var returnbutton = grid.down('button[action="returnMenu"]');
		returnbutton.on('click',me.returnMenu,this);

		//刷新按钮刷新：
		var refleshbutton = grid.down('button[action="reflesh"]');
		refleshbutton.on('click',me.reflesh,this);

		//查询按钮：
		var querybutton = grid.down('button[action="query"]');
		querybutton.on('click',me.onQuery,this);
		this.win.down('textfield[name="queryPath"]').on('keypress',function(field,e){
                if (e.getKey() == e.ENTER && field.getValue().length > 0) {
                   me.onQuery();
                }});
	},


	onUpFile: function(){
		var me = this;
		var upFileWin = Ext.create('Eway.view.agent.remote.UpFile');
		this.upFileWin = upFileWin;
		var path = me.win.down('textfield[name="path"]').getValue();
		upFileWin.down('button[action="confirm"]').on('click',Ext.bind(this.onConfirm,this,[path,upFileWin]),this);
		upFileWin.show()
	},

	onConfirm: function(path,upFileWin){
		  var form = this.upFileWin.down('form').getForm();
		  var me = this;
		  var ip = this.win.down('textfield[name="ip"]').getValue();
		  var filepath = this.upFileWin.down('filefield').lastValue;
		  if(Ext.isDefined(filepath)){
		  	  var index = filepath.lastIndexOf("\\");
			  var fileName = filepath.substring(index+1);
			  var store = this.win.down('remote_remoteBrowseFileSystemGrid').getStore();
			  var i = 0;
			  store.each(function fn(record){
			  		if(record.data.name==fileName){
			  			return false
			  		}
			  		i++;
			  	}
			  );

			  if(form.isValid()){
		   		 if(i<store.getCount()){
			  		 Ext.MessageBox.show({
						title : Eway.locale.tip.remind,
						msg : Eway.locale.agent.remote.fileExist,
						modal : true,
						fn : function callBack(id){
							var winEl = upFileWin.getEl();
							winEl.mask(Eway.locale.agent.remote.nowUploadFile);
					     	if(id=='yes'){
							     form.submit({
					                  url: 'api/agent/remoteBrowse/upload',
					                  params: {
					                  	  flag :'true',
					                	  desPath: path,
					                	  ip : ip
					                  },
					                  success: function(form, action) {
					                  	  winEl.unmask();
					                	  Eway.alert(Eway.locale.agent.remote.uploadSuccess);
					                	  upFileWin.close();
					                	  me.reflesh();
					                  },
					                  failure: function(form, action) {
					                  	  winEl.unmask();
					                      Eway.alert(action.result.errors);
					                  }
					              });
						   }else if(id=='no'){
						   		form.submit({
					                  url: 'api/agent/remoteBrowse/upload',
					                  params: {
					                  	  flag :'flase',
					                	  desPath: path,
					                	  ip : ip
					                  },
					                  success: function(form, action) {
					                  	  winEl.unmask();
					                  	  Eway.alert(Eway.locale.agent.remote.uploadSuccess);
					                	  upFileWin.close();
					                	  me.reflesh();
					                  },
					                  failure: function(form, action) {
					                  	  winEl.unmask();
					                      Eway.alert(action.result.errors);
					                  }
					              });
						   }else{
						   		winEl.unmask();
						   }
				  	 	},
						buttons : Ext.Msg.YESNOCANCEL,
						buttonText: {yes: Eway.locale.agent.remote.yes,no: Eway.locale.agent.remote.cancel,cancel: Eway.locale.agent.remote.cancel}
				     });
				  }else{
				  	var winEl = upFileWin.getEl();
					winEl.mask(Eway.locale.agent.remote.nowUploadFile);
				  	form.submit({
	                  url: 'api/agent/remoteBrowse/upload',
	                  params: {
	                  	  flag :'flase',
	                	  desPath: path,
	                	  ip : ip
	                  },
	                  success: function(form, action) {
	                  	  winEl.unmask();
	                	  Eway.alert(Eway.locale.agent.remote.uploadSuccess);
	                	  upFileWin.close();
	                	  me.reflesh();
	                  },
	                  failure: function(form, action) {
	                  	  winEl.unmask();
	                      Eway.alert(action.result.errors);
	                  }
	              	});
				  }
			   }
		}else{
			Eway.alert(Eway.locale.agent.remote.choseFile);
		}

	},

	returnMenu : function() {
		var store = this.store;

		var title = this.win.title;
		var currentPath = title.substring(title.indexOf(":")+1);

		var index = currentPath.lastIndexOf("\\");
		var parentPath = currentPath.substring(0,index);

		var win = this.win;
		var ip = win.down('textfield[name="ip"]').getValue();
		if(parentPath==""){
			var layout = win.getLayout();
			layout.setActiveItem(0);
			this.win.setTitle(Eway.locale.agent.remote.distanceExplorer);
			store.removeAll();
		}else{
			this.store.load({
				params : {path :parentPath ,ip : ip},
				scope   : this,
			    callback: function(records, operation, success) {
			        if(success==true){
			        	this.win.setTitle(Eway.locale.agent.remote.distanceExplorer+parentPath);
						this.win.down('textfield[name="path"]').setValue(parentPath);
						this.win.down('textfield[name="queryPath"]').setValue(parentPath);
					}else{
						Eway.alert(Eway.locale.agent.remote.returnFailure);
					}
				}
			});
		}
	},

	reflesh : function(){
		var store = this.store;
		var title = this.win.title;
		var currentPath = title.substring(title.indexOf(":")+1);
		var win = this.win;
		var ip = win.down('textfield[name="ip"]').getValue();
		this.store.load({
			params : {path :currentPath , ip : ip },
			scope   : this,
		    callback: function(records, operation, success) {
		        if(success==true){
		        	this.win.setTitle(Eway.locale.agent.remote.distanceExplore+currentPath);
				}else{
					Eway.alert(Eway.locale.agent.remote.refreshFailure);
				}
			}
		});
	},

	onQuery : function(){
		var me = this;
		var grid = this.win.down('remote_remoteBrowseFileSystemGrid');
		var store = grid.getStore();
	    var path = this.win.down('textfield[name="queryPath"]').getValue();
	    var ip = this.win.down('textfield[name="ip"]').getValue();
	    store.load({
	    	params : {path :path ,ip : ip},
	    	scope   : this,
		    callback: function(records, operation, success) {
		        if(success==true){
		        	this.win.setTitle(Eway.locale.agent.remote.distanceExplore+path);
				    this.win.down('textfield[name="path"]').setValue(path);
				    this.win.down('textfield[name="queryPath"]').setValue(path);
					grid.on('itemdblclick',me.onRemoteBrowseFileSystem2,this);
					grid.on()
				}else{
					Eway.alert(Eway.locale.agent.remote.catalogExist);
				}
			}
	    });
	    //返回上层目录：
		var returnbutton = grid.down('button[action="returnMenu"]');
		returnbutton.on('click',me.returnMenu,this);

		//刷新按钮刷新：
		var refleshbutton = grid.down('button[action="reflesh"]');
		refleshbutton.on('click',me.reflesh,this);
	}

});