
Ext.define('Eway.view.agent.remote.RemoteBrowseFileSystemGrid', {
	alias: 'widget.remote_remoteBrowseFileSystemGrid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util'],

	store: 'agent.RemoteBrowseFileSystem',
	border : false,
	autoFit:true,
	mergeDownLoadStore:null,
	mergeDownFileSize:0,
	initComponent: function() {
		var me = this;		   
		me.mergeDownLoadStore = Ext.create('Eway.store.agent.MergeDownLoadFileList',{});
		Ext.apply(this, {
			initRegion : true,
			viewConfig : {
				loadMask   : {
       			 	msg : EwayLocale.agent.remote.loadData
        		}
			},
			tbar: [{
				text:EwayLocale.agent.remote.back,
				iconCls:'up-btn-custom',
				action:'returnMenu',
				xtype : 'button'
			},{
				text: EwayLocale.agent.remote.refresh,
				iconCls:'refresh-btn-custom',
				action:'reflesh',
				xtype : 'button'
			},{
				text: EwayLocale.agent.remote.upload,
				iconCls:'upfile-btn-custom',
				action:'upFile',
				xtype : 'button',
				code : 'remoteUpFile',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.agent.remote.mergeload,
				action:'mergeLoad',
				glyph : 0xf002,
				xtype : 'button',
				code : 'remoteMergeDownFile',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.agent.remote.Mkdir,
				iconCls:'remoteMkdirBtn',
				action:'remoteMkDir',
				xtype : 'button',
				code : 'remoteMkDir',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.agent.remote.MkFile,
				iconCls:'remoteFileBtn',
				action:'remoteMkFile',
				xtype : 'button',
				code : 'remoteMkFile',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.agent.remote.remove,
				iconCls:'remoteDeleteBtn',
				action:'remoteDel',
				xtype : 'button',
				code : 'remoteDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},{
				text: EwayLocale.agent.remote.execute,
				iconCls:'remoteExecBtn',
				action:'remoteExec',
				xtype : 'button',
				code : 'remoteExec',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			},
			{
				xtype : 'textfield',
				name : 'ip',
				hidden: true
			},{
				xtype : 'textfield',
				name : 'path',
				hidden: true
			},{
				fieldLabel : EwayLocale.agent.remote.path,
				labelWidth :50,
				width: 248,
				labelAlign: 'left',
				xtype : 'textfield',
				align:'left',
				enableKeyEvents:true,
				name : 'queryPath'
			}, {
				text: EwayLocale.agent.remote.search,
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : EwayLocale.agent.remote.name,
				sortable : true,
				renderer: this.iconBackground,
				width : 260,
				dataIndex : 'name',
				renderer: function (value, meta, record) {
					meta.tdAttr = 'data-qtip="'+ value+ '"';
					
					if(record.data.type == "DIR") {
						return '<span class="floder-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;</span>'+" "+value;
					} else {
						return '<span class="file-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;</span>'+" "+value;
					}
					
//					return value;
	            }

			}, {
				header : EwayLocale.commen.type,
				dataIndex : 'type',
				hidden: true,
				sortable : true
			}, {
				header : EwayLocale.agent.remote.size,
				dataIndex : 'size',
				renderer: function(value,metadata,record){
					if(record.data.type=="DIR"){
	                 	   return "";;
	                   }else{
	                   	metadata.tdAttr ='data-qtip="'+EwayLocale.agent.remote.fileSize + value+" B"+'"';
	                   	if(value>1024*1024*1024){
	                   		return (value/(1024*1024*1024)).toFixed(2)+" GB";
	                   	}else if(value>1024*1024){
	                   		return (value/(1024*1024)).toFixed(1)+" MB";
	                   	}else if(value>1024){
	                   		return Math.ceil(value/1024)+" KB";
	                   	}
	                	    return value+" B";
	                   }
				},
				sortable : true
			},{
				header : EwayLocale.agent.remote.path,
				dataIndex : 'path',
				width : 150,
				hidden: true,
				sortable : true
			},{
				header : EwayLocale.agent.remote.lastTime,
				dataIndex : 'lastTime',
				width : 160,
				sortable : true
			},{
				header : EwayLocale.agent.remote.screen.loading,
				flex: 1,

				xtype:'actioncolumn',
				flex:1,
				items:[{
                    icon:"././././resources/images/downfile.png",
                    iconCls:'<span class="floder-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>',
                    tooltip: EwayLocale.agent.remote.clickLoadFile,
					getClass:function(v,metadata,r,rowIndex,colIndex,store){
						var rec=store.getAt(rowIndex);
						if(rec.data.type=="DIR"){
							return "actioncolumn-hidden";
	                       }
					},
					scope : me,
                    handler : function(grid, rowIndex, colIndex) {
						var me = this;
						var rec = grid.getStore().getAt(rowIndex);
						var requestName = rec.get('name');
						var requestPath = rec.get('path');
						var ip = grid.up('remote_remoteBrowseView').down('textfield[name="ip"]').getValue();
						if (rec.get('size') > 209715200) {
							Eway.alert(EwayLocale.agent.remote.loadFileSize);
						} else {
							var win = grid.up('window');
							var gridEl = grid.getEl();
							var mask = new Ext.LoadMask(grid, {msg : EwayLocale.agent.remote.nowLoadFile});
							Ext.MessageBox.show({
								title : EwayLocale.tip.remind,
								msg : EwayLocale.agent.remote.judgeLoad,
								modal : true,
								fn : function(id) {
									mask.show();
									if (id == 'yes') {
										var flag = true;
										me.download(flag, ip,requestName,requestPath,mask, gridEl)
									} else if (id == 'no') {
										var flag = false;
										me.download(flag, ip,requestName,requestPath,mask, gridEl)
									} else {
										mask.hide();
									}
								},
								buttons : Ext.Msg.YESNOCANCEL
							}).setWidth(260);
						}
					}
                  },{
                	  //增加中间的空格
                	  xtype:'panel',
                	  width:100
                  },{
                	  name:'downFile',
                      icon:"././././resources/images/addFile.png",
                      iconCls:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="floder-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>',
                      tooltip: EwayLocale.agent.remote.clickAddLoadFile,
                      getClass:function(v,metadata,r,rowIndex,colIndex,store){
  						var rec=store.getAt(rowIndex);
  						if(rec.data.type=="DIR"){
  							return "actioncolumn-hidden";
  	                       }
  					},
  					  scope : me,
                      handler : function(grid, rowIndex, colIndex) {
  						var me = this;
  						var rec = grid.getStore().getAt(rowIndex); 						
  						var data = new Object();
  						data.name = rec.get('name');
  						data.path = rec.get('path');
  						data.lastTime = rec.get('lastTime');
  						data.size = rec.get('size'); 
  						var record = Ext.create('Eway.model.agent.MergeDownLoadFileList',data);  
  						if(me.mergeDownLoadStore.findRecord('path',data.path,0,false,true,true))
  						{
  							Eway.alert(EwayLocale.agent.remote.exitDownLoadFile);  
  							return;
  						}
  						me.mergeDownFileSize = me.mergeDownFileSize + data.size;
  						if(me.mergeDownFileSize > 209715200)
  						{
  							Eway.alert(EwayLocale.agent.remote.maxDownLoadFileSize);    							
  							return;
  						}  						
  						me.mergeDownLoadStore.add(record);
  				//		this.down("actioncolumn").items[2].icon = '././././resources/images/delete.gif';
  						Eway.alert(EwayLocale.agent.remote.addFileSuccess);
  					}
                    
                  }],
                  align:'center',
                  code : 'remoteDownFile',
  				  listeners:{
  					'afterrender': Eway.lib.ButtonUtils.onButtonBeforeRender
  				  }
			}]
		});

		this.callParent(arguments);
	},

	iconBackground: function(value,metadata,record){
		if(record.data.type=="DIR"){
			return '<span class="floder-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;</span>'+" "+value;
		}else{
			return '<span class="file-name-blackground">&nbsp;&nbsp;&nbsp;&nbsp;</span>'+" "+value;
		}
	},

	onReload: function() {
		this.getStore().load();
	},

	download : function(flag, ip, requestName, requestPath, mask, gridEl) {
		Ext.Ajax.request({
			method : 'POST',
			url : 'api/agent/remoteBrowse/download?date=' + new Date(),
			timeout : 120000,
			params : {
				flag : flag,
				ip : ip,
				requestName : requestName,
				requestPath : requestPath
			},
			success : function(response) {
				var object = Ext.decode(response.responseText);
				if (object.success == true) {
					mask.hide();
					var iframe = gridEl.prev();
					var fileName = object.fileName.replace("&", "%26");// 将文件名含有&符号的用URL编码“%26”替换
					if (iframe) {
						// 2015-11-19,该js方法在5.1版本不存在
//						Ext.core.Element.get(iframe).destroy();
						iframe.destroy();
					}
					iframe = Ext.core.DomHelper.createDom({
								tag : 'iframe',
								src : 'api/agent/remoteBrowse/downloadFile?path='
										+ object.path + '&fileName=' + fileName,
								style : "display:none"
							});
					gridEl.insertSibling(iframe);
				} else {
					mask.hide();
					Eway.alert(object.errors);
				}
			},
			failure : function() {
				mask.hide();
				Eway.alert(EwayLocale.agent.remote.loadFailure);
			}
		});
	}

});
