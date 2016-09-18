/**
 * 机构信息列表Grid：
 */
Ext.define('Eway.view.report.run.Grid', {
	alias: 'widget.report_run_grid',
	extend: 'Eway.view.base.Grid',

	border : false,

	initComponent: function() {
		var store = Ext.create('Eway.store.report.run.AnalysisReport');
		Ext.apply(this, {
			store : store,
			tbar: [{
				text:'',
				action:'tip',
				xtype:'tbtext'
			},'->',{
				text: EwayLocale.button.search,
				glyph : 0xf002,
				action: 'query'
			}],
			columns : [{
				header : EwayLocale.report.run.createDate,
				dataIndex : 'createDate',
				sortable : true
			},{
				header : EwayLocale.report.run.fileName,
				dataIndex : 'fileName',
				width:350,
				sortable : true
			},{
				header : EwayLocale.report.run.fileSize,
				dataIndex : 'fileSize',
				renderer: function(value,metadata,record){
                   	if(value>1024*1024*1024){
                   		return (value/(1024*1024*1024)).toFixed(2)+" GB";
                   	}else if(value>1024*1024){
                   		return (value/(1024*1024)).toFixed(1)+" MB";
                   	}else if(value>1024){
                   		return Math.ceil(value/1024)+" KB";
                   	}else{
                   		return value+" B";
                   	}
				},
			},{
				xtype:'actioncolumn',
				flex : .5,
				dataIndex : 'reportType',
				menuText: EwayLocale.agent.remote.screen.loading,
				align:'center',
				header: EwayLocale.agent.remote.screen.loading,
				items : [{
					icon : 'resources/images/down.gif',
					tooltip: EwayLocale.agent.remote.screen.loading,
					handler : function(grid,rowIndex,colIndex){
						var record = grid.getStore().getAt(rowIndex);
						var url = encodeURI('api/report/runAnalysisReport/downloadFile?fileName='+record.get('fileName')+'&reportType='+record.get('reportType')); 
						debugger;
						var iframe = document.getElementById('downloadFileFromWeb'); 
						iframe.src = url;
					},
					scope : this
				}],
			}],
			bbar : Ext.create('Ext.PagingToolbar',{
				store : store,
				displayInfo : true
			})
		});
		this.callParent(arguments);
	}
});