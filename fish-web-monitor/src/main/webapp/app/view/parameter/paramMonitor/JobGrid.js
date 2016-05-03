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
				 text:'详情',
				 action:'detail',
				 code : 'paramMonitorDetail',
					listeners:{
						'beforerender': Eway.lib.ButtonUtils.onButtonBeforeRender
					}
			 }],
			columns : [ {
				header : '作业ID',
				dataIndex : 'id',
				width:150,
				sortable:true
			},{
				header : '日期',
				dataIndex : 'date',
				width:150,
				sortable:true
			},{
				header : '发布者',
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