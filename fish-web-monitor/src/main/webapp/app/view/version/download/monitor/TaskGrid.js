Ext.define('Eway.view.version.download.monitor.TaskGrid', {
	alias : 'widget.version_download_monitor_taskgrid',
	extend : 'Eway.view.base.Grid',

	requires : [ 'Eway.lib.Util'],

	store : 'version.monitor.Task',
	border : false,
	autoScroll : true,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		loadMask : true,
        enableTextSelection : true
	},
	config:{
		jobId:0
	},
	firstIn : true,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar: ['->',{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},{
				text:'开启自动刷新',
				action:'autoRefresh',
				tooltip:'刷新周期60秒',
				started:false
			},'-',{
				xtype : 'combo',
				fieldLabel : '升级结果',
				name : 'updateResult',
				store : Ext.create('Ext.data.Store',{
					fields : ['value','name'],
					data : [
						{'value':'','name':'全部'},
						{'value':'1','name':'成功'},
						{'value':'0','name':'失败'}
					]
				}),
				queryMode : 'local',
				valueField : 'value',
				displayField : 'name',
				value:'',
				width: 200
			},{
				xtype:'textfield',
				fieldLabel:'设备编号',
				name:'terminalId',
				width: 180
			},{
				text: '查询',
				iconCls : 'queryBtn',
				action: 'taskquery',
				tooltip:'根据条件查询选中作业下的详情信息'
			},{
				text: '导出',
				iconCls : 'exportToExcel',
				action: 'export',
				tooltip:'导出选中作业下的全部下发结果',
				code : 'exportJobToExcel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}/*,{
				text: '全部重启',
				iconCls : 'exportToExcel',
				action: 'rebootAll',
				tooltip:'重启该作业下的可以重启的全部设备',
				code : 'rebootAll',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}*/],
			columns : [
			{
				header : 'ID',
				dataIndex : 'id',
				hidden: true
			}/*,{
				xtype:'actioncolumn',
				width: 40,
				items : [{
					icon : 'resources/images/accept.png',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == '新建'){
							return '';
						}else {
							return 'hiddenComp';
						}
					}
				}]
			}*/,{
				header: '设备编号',
				dataIndex:'terminalId'
			},{
				header: '设备IP',
				dataIndex:'deviceIp',
				sortable: true
			},{
				header: '所属机构',
				dataIndex:'orgName',
				sortable: true
			},{
				header:'分发前的版本',
				dataIndex:'versionBeforeUpdate'
			},{
				header:'分发版本',
				dataIndex:'version'
			},{
				header:'预期版本',
				dataIndex:'exceptVersion'
			},{
				header :'任务状态',
				dataIndex:'taskStatus',
				renderer:function(value,meta,record){
					if(value == '部署已确认'){
						return value + "&nbsp;<img src='resources/images/accept.png'>";
					}else{
						return value;
					}
				},
				width: 150
			},{
				header :'执行时间',
				dataIndex:'excuteTime',
				width: 140
			},{
				header: '备注',
				dataIndex:'reason',
				flex:1
			},{
				xtype:'actioncolumn',
				width: 80,
				header: '重启ATM',
				items : [{
					icon : 'resources/images/version/reboot.png',
					tooltip: '重启ATM',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == '等待部署'){
							return '';
						}else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var taskId = record.get('id');
						Ext.MessageBox.confirm('提示','执行重启命令可能存在风险,确认重启?',callBack);
	            		function callBack(id){
	            			if(id == 'yes'){
								Ext.Ajax.request({
									method : 'POST',
									url : 'api/version/download/rebootOne' ,
									params : {
										'taskId' : taskId
									},
									success : function(response){
										var text = Ext.decode(response.responseText);
										if(text.success){
											Ext.Msg.alert("提示", "已发送重启命令！");
										}else{
											Ext.Msg.alert("提示", text.errors);
										}
										grid.getStore().load();
									}
								});
			            	}
	            		}
					},
					scope : this
				}]
			},{
				xtype:'actioncolumn',
				width: 80,
				header: '取消下发',
				items : [{
					icon : 'resources/images/remove.png',
					tooltip: '取消下发',
					getClass : function(value,metadata,record,ronwIndex,colindex,store){
						var taskStatus = record.get('taskStatus');
						if(taskStatus == '新建'){
							return '';
						}else {
							return 'hiddenComp';
						}
					},
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var taskId = record.get('id');
						var jobId = record.get('jobId');
						Ext.Ajax.request({
							method : 'POST',
							url : 'api/version/download/task/cancel' ,
							params : {
								'jobId' : jobId,
								'taskId' : taskId
							},
							success : function(response){
								var text = Ext.decode(response.responseText);
								if(text.success){
									Ext.Msg.alert("提示", "取消下发 成功！");
								}else{
									if(text.errors !== ""){
										Ext.Msg.alert("提示", text.errors);
									}
								}
								grid.getStore().load();
							}
						});
					},
					scope : this
				}]
			}
			],
			bbar : Ext.create('Ext.toolbar.Paging',{
				store : this.store,
				displayInfo : true
			}),
			listeners:{
				activate:function( _this, eOpts ){
					var jobId = _this.getConfig().jobId;
					_this.getStore().setBaseParam("jobId",jobId);
					_this.getStore().load();
				}
			}
		});

		this.callParent(arguments);
	},

	refresh: function(jobId){
		var store = this.getStore();
		store.setBaseParam("jobId",jobId);
		store.loadPage(1);
	}
});