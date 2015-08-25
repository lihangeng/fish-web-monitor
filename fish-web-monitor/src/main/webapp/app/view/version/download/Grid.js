
Ext.define('Eway.view.version.download.Grid', {
	alias: 'widget.version_download_grid',
	extend: 'Eway.view.base.Grid',

	requires: ['Eway.lib.Util','Ext.ux.PreviewPlugin'],

	border : false,
	autoScroll : true,

	 viewConfig: {
            /*id: 'gv',
            trackOver: false,
            stripeRows: false,*/
            plugins: [{
                ptype: 'preview',
                bodyField: 'extraBody',
                expanded: true,
                pluginId: 'preview'
            }]
        },

	initComponent: function() {
		var gridStore = Ext.create('Eway.store.version.VersionDownload',{
			listeners: {
				load : function(store,records){
							if(records.length > 0){
								var sm = this.getSelectionModel();
								sm.select(records[0]);
								sm.lastSelected = records[0];
							}
					   },
				scope : this
			}
		});
		Ext.apply(this, {
			initRegion : true,
			store: gridStore,
			tbar: ['->',{
				text: '查询',
				glyph : 0xf002,
				action: 'query'
			}/*, {
				text: '创建新的作业',
				action: 'add'
			}, {
				text:'开始',
				action: 'start'
			},{
				text: '暂停',
				action: 'pause'
			}*/, {
				text: '撤销作业',
				iconCls : 'removeBtn',
				action: 'remove',
				code : 'versionMonitorDel',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			}],
			columns : [
			{
				header: '作业ID',
				dataIndex: 'id',
				width: 50
			},{
				header: '下发的版本',
				dataIndex: 'versionName',
				sortable: true,
				width: 180
			}/*,{
				header : '作业名称',
				dataIndex : 'jobName',
				sortable : true
			},{
				header: '版本文件',
				dataIndex: 'versionFile',
				renderer:function(value,meta,record){
					if(value != null){
						return "<a class='link' href='#'>"+ value + "</a>";;
					}else{
						return value;
					}
				},
				width: 200
			}*//*,{
				header: '作业类型',
				dataIndex: 'jobType',
				renderer: Eway.lib.Util.dictRenderer('version.JobType'),
				sortable: true,
				width: 120
			},{
				header: '作业优先级',
				dataIndex: 'jobPriority',
				renderer: Eway.lib.Util.dictRenderer('version.JobPriority'),
				sortable: true
			}*/,{
				header : '计划执行时间',
				dataIndex : 'planTime',
			/*	xtype:'datecolumn',
				format:'Y-m-d H:i:s',*/
				sortable : true,
				width: 140
			},{
				header: '作业状态',
				dataIndex: 'jobStatus',
				renderer: Eway.lib.Util.dictRenderer('version.JobStatus'),
				sortable: true,
				width: 60
			},{
				header : '备注',
				dataIndex : 'desc',
				flex : 1,
				sortable : true
			}],
			bbar : Ext.create('Ext.toolbar.Paging',{
				store : gridStore,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	}
});