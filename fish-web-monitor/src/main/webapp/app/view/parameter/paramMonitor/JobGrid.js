Ext.define('Eway.view.parameter.paramMonitor.JobGrid', {
	alias : 'widget.parameter_paramMonitor_JobGrid',
	extend : 'Eway.view.base.Grid',

	
	initComponent : function() {
		var store = Ext.create('Eway.store.parameter.paramMonitor.JobMonitor');
		store.loadPage(1);
		Ext.apply(this, {
			initRegion : true,
			store : store,
			tbar : [ '->', {
				text : EwayLocale.button.search,
				glyph : 0xf002,
				action : 'query',
				code : 'paramMonitorQuery',
				listeners:{
					'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
				}
			 },{
				 text:EwayLocale.param.paramDownloadMonitor.jobDetail,
				 glyph : 0xf129,
				 action:'detail',
				 code : 'paramMonitorDetail',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
			 }],
			columns : [ {
				header : EwayLocale.param.paramDownloadMonitor.jobId,
				dataIndex : 'id',
				width:150,
				sortable:true
			},{
				header : EwayLocale.param.paramDownloadMonitor.date,
				dataIndex : 'date',
				width:150,
				sortable:true
			},{
				header : EwayLocale.param.template.templateName,
				dataIndex : 'templateName',
				width:150,
				sortable:true
			},{
				header : EwayLocale.param.paramDownloadMonitor.publisherName,
				dataIndex : 'publisherName',
				sortable:true,
				flex:1
			}/*,{
				header : EwayLocale.commen.devTypeName,
				dataIndex : 'devTypeName',
				width:150
			},{
				header : EwayLocale.commen.devCatalogName,
				dataIndex : 'devCatalogName',
				flex : 1
			}*/],
			bbar : Ext.create('Ext.PagingToolbar', {
				store : store,
				displayInfo : true
			})
		});

		this.callParent(arguments);
	},
	onReload : function() {
		this.getStore().load();
	}
});