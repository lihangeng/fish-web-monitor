Ext.define('Eway.view.version.download.TaskGrid', {
	alias : 'widget.version_download_taskGrid',
	extend : 'Ext.grid.Panel',

	requires : [ 'Eway.lib.Util'],

	store : 'version.Task',
	border : false,
	viewConfig : {
		forceFit : true,
		stripeRows : true,
		loadMask : false
	},
	firstIn : true,
	initComponent : function() {
		Ext.apply(this, {
			initRegion : true,
			tbar: ['->',{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'-',{
				text: '查询',
				glyph : 0xf002,
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
			},{
				text:'开启自动刷新',
				action:'autoRefresh',
				tooltip:'刷新周期60秒',
				started:false
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
	            locked: true,
				hidden: true
			},{
				header: '作业批次名称',
	            locked: true,
				dataIndex:'jobName'
			},{
				header: '设备编号',
	            locked: true,
				dataIndex:'terminalId'
			},{
				header:'分发版本',
	            locked: true,
				dataIndex:'version'
			},{
				header :'任务状态',
	            locked: true,
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
				header: '设备IP',
				dataIndex:'deviceIp',
				sortable: true
			},{
				header: Eway.locale.commen.orgNameBelongs,
				dataIndex:'orgName',
				sortable: true
			},{
				header:'分发前的版本',
				dataIndex:'versionBeforeUpdate'
			},{
				header:'预期版本',
				dataIndex:'exceptVersion'
			},{
				header :'执行时间',
				dataIndex:'excuteTime',
				width: 140
			},{
				header :'下载源',
				dataIndex:'downSource',
			},{
				header :'计划时间',
				dataIndex:'planTime',
			},{
				header :'执行服务器',
				dataIndex:'excuteMachine',
			},{
				header: Eway.locale.commen.remark,
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
											Eway.alert("已发送重启命令！");
										}else{
											Eway.alert(text.errors);
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
						if(taskStatus == Eway.locale.commen.stateDict.newCreate){
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
									Eway.alert("取消下发 成功！");
								}else{
									if(text.errors !== ""){
										Eway.alert(text.errors);
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
			})
		});

		this.callParent(arguments);
	},

	refresh: function(jobId){
		var store = this.getStore();
		store.setBaseParam("jobId",jobId);
		store.loadPage(1);
	}
});