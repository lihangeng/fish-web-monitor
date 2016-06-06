Ext.define('Eway.view.version.download.monitor.TaskPanel', {
	alias : 'widget.version_download_monitor_taskpanel',
	extend : 'Eway.view.base.Panel',

	requires : [ 'Eway.lib.Util','Eway.view.version.download.monitor.TaskGrid'],
	border : false,
	autoScroll : true,
	closable : false,
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
		var me = this;
		Ext.apply(this, {
			layout:'border',
			initRegion : true,
			border:true,
			weight : 15,
			tbar: [{
				text:  EwayLocale.version.download.callBack,//'查询',
				glyph : 0xf122,
				action: 'toJob',
				tooltip:EwayLocale.version.download.callBackJob,//'根据条件查询选中作业下的详情信息'
				code:'toJob'
			},"->",{
				//text:  EwayLocale.version.download.beforeJob,//'查询',
				glyph : 0xf060,
				action: 'pref',
				tooltip:EwayLocale.version.download.beforeJob,//'根据条件查询选中作业下的详情信息'
				code:'pref'
			},{
				//text:  EwayLocale.version.download.afterJob,//'查询',
				glyph : 0xf061,
				action: 'next',
				tooltip:EwayLocale.version.download.afterJob,//'根据条件查询选中作业下的详情信息'
				code:'next'
			},
		],
			items:[{
//				xtype:'panel',
				name:'jobDetailInfo',
				title:EwayLocale.version.download.JobDetails,
				region: 'north',
				xtype:'fieldset',
		        collapsible: true,
		        defaultType: 'displayfield',
				layout : 'column'
			},{
				region: 'center',
				xtype:'version_download_monitor_taskgrid',
				jobId:this.getJobId()
			}],
			listeners:{
				activate:function( _this, eOpts ){
					var jobId = _this.getConfig().jobId;
					_this.down('version_download_monitor_taskgrid').getStore().setBaseParam("jobId",jobId);
					_this.down('version_download_monitor_taskgrid').getStore().load();
				}
			}
		});

		this.callParent(arguments);
	},

	refresh: function(jobId){
		var store = this.down('version_download_monitor_taskgrid').getStore();
		store.setBaseParam("jobId",jobId);
		store.loadPage(1);
	}
});